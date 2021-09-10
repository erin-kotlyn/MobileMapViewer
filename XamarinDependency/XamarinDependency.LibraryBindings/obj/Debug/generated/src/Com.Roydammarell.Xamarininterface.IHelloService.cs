using System;
using System.Collections.Generic;
using Android.Runtime;
using Java.Interop;

namespace Com.Roydammarell.Xamarininterface {

	// Metadata.xml XPath interface reference: path="/api/package[@name='com.roydammarell.xamarininterface']/interface[@name='IHelloService']"
	[Register ("com/roydammarell/xamarininterface/IHelloService", "", "Com.Roydammarell.Xamarininterface.IHelloServiceInvoker")]
	public partial interface IHelloService : IJavaObject, IJavaPeerable {
		// Metadata.xml XPath method reference: path="/api/package[@name='com.roydammarell.xamarininterface']/interface[@name='IHelloService']/method[@name='createHello' and count(parameter)=0]"
		[Register ("createHello", "()Ljava/lang/String;", "GetCreateHelloHandler:Com.Roydammarell.Xamarininterface.IHelloServiceInvoker, XamarinDependency.LibraryBindings")]
		string CreateHello ();

	}

	[global::Android.Runtime.Register ("com/roydammarell/xamarininterface/IHelloService", DoNotGenerateAcw=true)]
	internal partial class IHelloServiceInvoker : global::Java.Lang.Object, IHelloService {
		static readonly JniPeerMembers _members = new XAPeerMembers ("com/roydammarell/xamarininterface/IHelloService", typeof (IHelloServiceInvoker));

		static IntPtr java_class_ref {
			get { return _members.JniPeerType.PeerReference.Handle; }
		}

		[global::System.Diagnostics.DebuggerBrowsable (global::System.Diagnostics.DebuggerBrowsableState.Never)]
		[global::System.ComponentModel.EditorBrowsable (global::System.ComponentModel.EditorBrowsableState.Never)]
		public override global::Java.Interop.JniPeerMembers JniPeerMembers {
			get { return _members; }
		}

		[global::System.Diagnostics.DebuggerBrowsable (global::System.Diagnostics.DebuggerBrowsableState.Never)]
		[global::System.ComponentModel.EditorBrowsable (global::System.ComponentModel.EditorBrowsableState.Never)]
		protected override IntPtr ThresholdClass {
			get { return class_ref; }
		}

		[global::System.Diagnostics.DebuggerBrowsable (global::System.Diagnostics.DebuggerBrowsableState.Never)]
		[global::System.ComponentModel.EditorBrowsable (global::System.ComponentModel.EditorBrowsableState.Never)]
		protected override global::System.Type ThresholdType {
			get { return _members.ManagedPeerType; }
		}

		IntPtr class_ref;

		public static IHelloService GetObject (IntPtr handle, JniHandleOwnership transfer)
		{
			return global::Java.Lang.Object.GetObject<IHelloService> (handle, transfer);
		}

		static IntPtr Validate (IntPtr handle)
		{
			if (!JNIEnv.IsInstanceOf (handle, java_class_ref))
				throw new InvalidCastException ($"Unable to convert instance of type '{JNIEnv.GetClassNameFromInstance (handle)}' to type 'com.roydammarell.xamarininterface.IHelloService'.");
			return handle;
		}

		protected override void Dispose (bool disposing)
		{
			if (this.class_ref != IntPtr.Zero)
				JNIEnv.DeleteGlobalRef (this.class_ref);
			this.class_ref = IntPtr.Zero;
			base.Dispose (disposing);
		}

		public IHelloServiceInvoker (IntPtr handle, JniHandleOwnership transfer) : base (Validate (handle), transfer)
		{
			IntPtr local_ref = JNIEnv.GetObjectClass (((global::Java.Lang.Object) this).Handle);
			this.class_ref = JNIEnv.NewGlobalRef (local_ref);
			JNIEnv.DeleteLocalRef (local_ref);
		}

		static Delegate cb_createHello;
#pragma warning disable 0169
		static Delegate GetCreateHelloHandler ()
		{
			if (cb_createHello == null)
				cb_createHello = JNINativeWrapper.CreateDelegate ((_JniMarshal_PP_L) n_CreateHello);
			return cb_createHello;
		}

		static IntPtr n_CreateHello (IntPtr jnienv, IntPtr native__this)
		{
			var __this = global::Java.Lang.Object.GetObject<global::Com.Roydammarell.Xamarininterface.IHelloService> (jnienv, native__this, JniHandleOwnership.DoNotTransfer);
			return JNIEnv.NewString (__this.CreateHello ());
		}
#pragma warning restore 0169

		IntPtr id_createHello;
		public unsafe string CreateHello ()
		{
			if (id_createHello == IntPtr.Zero)
				id_createHello = JNIEnv.GetMethodID (class_ref, "createHello", "()Ljava/lang/String;");
			return JNIEnv.GetString (JNIEnv.CallObjectMethod (((global::Java.Lang.Object) this).Handle, id_createHello), JniHandleOwnership.TransferLocalRef);
		}

	}
}
