package com.roydammarell.kotlinappwithxamarindependency

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.roydammarell.xamarin.ServiceFactory
import com.roydammarell.xamarininterface.IExceptionService
import com.roydammarell.xamarininterface.IHelloService
import mil.nga.geopackage.GeoPackage
import mil.nga.geopackage.GeoPackageFactory
import mil.nga.geopackage.GeoPackageManager
import mil.nga.geopackage.features.user.FeatureCursor
import mil.nga.geopackage.features.user.FeatureDao
import mil.nga.geopackage.map.geom.GoogleMapShape
import mil.nga.geopackage.map.geom.GoogleMapShapeConverter
import mil.nga.geopackage.map.geom.GoogleMapShapeType
import mil.nga.geopackage.map.tiles.overlay.GeoPackageOverlayFactory
import mil.nga.geopackage.tiles.user.TileDao
import mil.nga.sf.proj.ProjectionConstants
import mil.nga.sf.proj.ProjectionFactory
import android.view.View

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_persistent_bottom_sheet.*

//TODO: https://github.com/mobiraft/BottomSheetExample/blob/master/app/src/main/java/com/mobiraft/bottomsheetexample/MainActivity.kt
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private val helloService: IHelloService = ServiceFactory.createHelloService()
    private val exceptionService: IExceptionService = ServiceFactory.createExceptionService()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

//        val helloView = findViewById<TextView>(R.id.map)
//        helloView.text = helloService.createHello()


//        throwButton.setOnClickListener {
//            exceptionService.throwNullReferenceException()s
//        }

        bottomSheetBehavior = BottomSheetBehavior.from<LinearLayout>(persistent_bottom_sheet)

        bottomSheetBehavior.setBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, state: Int) {
                print(state)
                when (state) {

                    BottomSheetBehavior.STATE_HIDDEN -> {
                        persistentBtn.text = "Show Bottom Sheet"
                    }
                    BottomSheetBehavior.STATE_EXPANDED ->
                        persistentBtn.text = "Close Bottom Sheet"
                    BottomSheetBehavior.STATE_COLLAPSED ->
                        persistentBtn.text = "Show Bottom Sheet"
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {

                    }
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        persistentBtn.setOnClickListener(View.OnClickListener {
            expandCollapseSheet()
        })

        bottomSheetDialogBtn.setOnClickListener{
            val modalSheetView = layoutInflater.inflate(R.layout.fragment_modal_bottom_sheet,null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(modalSheetView)
            dialog.show()

        }

        bottomSheetDialogFragmentBtn.setOnClickListener {
            val modalbottomSheetFragment = ModalBottomSheet()
            modalbottomSheetFragment.show(supportFragmentManager,modalbottomSheetFragment.tag)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        val manager:GeoPackageManager = GeoPackageFactory.getManager(applicationContext)
        manager.importGeoPackage("StLouis", applicationContext.assets.open("StLouis.gpkg"), true)
        val geoPackage:GeoPackage = manager.open("StLouis")
        val tileTable: String = geoPackage.tileTables[0]
        val tileDao: TileDao = geoPackage.getTileDao(tileTable)
        val overlay:TileProvider = GeoPackageOverlayFactory.getTileProvider(tileDao)
        val overlayOptions = TileOverlayOptions()
        overlayOptions.tileProvider(overlay)
        overlayOptions.zIndex(-2.0f)
        mMap.addTileOverlay(overlayOptions)
        val featureTables= geoPackage.featureTables
        featureTables.forEach {
            val featureDao: FeatureDao = geoPackage.getFeatureDao(it)
            val converter = GoogleMapShapeConverter(featureDao.projection)
            val featureCursor: FeatureCursor = featureDao.queryForAll()
            var icon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(applicationContext.resources, R.drawable.poi))
            if (it == "Pizza") { // In Kotlin, 'it' is out iterator
                icon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(applicationContext.resources, R.drawable.pizza))
            }
            try {
                while (featureCursor.moveToNext()) {
                    val featureRow = featureCursor.row
                    val geometryData = featureRow.geometry
                    val geometry = geometryData.geometry
                    val googleMapShape: GoogleMapShape = converter.toShape(geometry)
                    if (googleMapShape.shapeType == GoogleMapShapeType.LAT_LNG) {
                        val markerOptions: MarkerOptions = MarkerOptions()
                            .title(featureRow.getValue("name") as String)
                            .position(googleMapShape.shape as LatLng)
                            .icon(icon)
                        val newShape = GoogleMapShape(geometry.geometryType, GoogleMapShapeType.MARKER_OPTIONS, markerOptions)
                        GoogleMapShapeConverter.addShapeToMap(mMap, newShape)
                    } else {
                        GoogleMapShapeConverter.addShapeToMap(mMap, googleMapShape)
                    }
                }
            } finally {
                featureCursor.close()
            }
        }
        // Find the data and set the bounds
        val boundingBox = tileDao.getBoundingBox(14)
        val transform = ProjectionFactory
            .getProjection(ProjectionConstants.EPSG_WEB_MERCATOR.toLong())
            .getTransformation(ProjectionConstants.EPSG_WORLD_GEODETIC_SYSTEM.toLong())
        val projectedBoundingBox = boundingBox.transform(transform)
        val boundsBuilder = LatLngBounds.builder()
        boundsBuilder.include(LatLng(projectedBoundingBox.minLatitude, projectedBoundingBox.minLongitude))
        boundsBuilder.include(LatLng(projectedBoundingBox.minLatitude, projectedBoundingBox.maxLongitude))
        boundsBuilder.include(LatLng(projectedBoundingBox.maxLatitude, projectedBoundingBox.minLongitude))
        boundsBuilder.include(LatLng(projectedBoundingBox.maxLatitude, projectedBoundingBox.maxLongitude))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(boundsBuilder.build().center, 12.0f))
    }

    private fun expandCollapseSheet() {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            persistentBtn.text = "Close Bottom Sheet"
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            persistentBtn.text = "Show Bottom Sheet"
        }
    }
}
