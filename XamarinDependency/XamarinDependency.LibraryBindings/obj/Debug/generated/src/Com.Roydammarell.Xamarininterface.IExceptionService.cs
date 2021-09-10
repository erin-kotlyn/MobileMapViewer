using System;
using System.Collections.Generic;
using Android.Runtime;
using Java.Interop;

namespace Com.Roydammarell.Xamarininterface {

	// Metadata.xml XPath interface reference: path="/api/package[@name='com.roydammarell.xamarininterface']/interface[@name='IExceptionService']"
	[Register ("com/roydammarell/xamarininterface/IExceptionService", "", "Com.Roydammarell.Xamarininterface.IExceptionServiceInvoker")]
	public partial interface IExceptionService : IJavaObject, IJavaPeerable {
		// Metadata.xml XPath method reference: path="/api/package[@name='com.roydammarell.xamarininterface']/interface[@name='IExceptionService']/method[@name='throwNullReferenceException' and count(parameter)=0]"
		[Register ("throwNullReferenceException", "()V", "GetThrowNullReferenceExceptionHandler:Com.Roydammarell.Xamarininterface.IExceptionServiceInvoker, XamarinDependency.LibraryBindings")]
		void ThrowNullReferenceException ();

	}

	[global::Android.Runtime.Register ("com/roydammarell/xamarininterface/IExceptionService", DoNotGenerateAcw=true)]
	internal partial class IExceptionServiceInvoker : global::Java.Lang.Object, IExceptionService {
		static readonly JniPeerMembers _members = new XAPeerMembers ("com/roydammarell/xamarininterface/IExceptionService", typeof (IExceptionServiceInvoker));

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

		public static IExceptionService GetObject (IntPtr handle, JniHandleOwnership transfer)
		{
			return global::Java.Lang.Object.GetObject<IExceptionService> (handle, transfer);
		}

		static IntPtr Validate (IntPtr handle)
		{
			if (!JNIEnv.IsInstanceOf (handle, java_class_ref))
				throw new InvalidCastException ($"Unable to convert instance of type '{JNIEnv.GetClassNameFromInstance (handle)}' to type 'com.roydammarell.xamarininterface.IExceptionService'.");
			return handle;
		}

		protected override void Dispose (bool disposing)
		{
			if (this.class_ref != IntPtr.Zero)
				JNIEnv.DeleteGlobalRef (this.class_ref);
			this.class_ref = IntPtr.Zero;
			base.Dispose (disposing);
		}

		public IExceptionServiceInvoker (IntPtr handle, JniHandleOwnership transfer) : base (Validate (handle), transfer)
		{
			IntPtr local_ref = JNIEnv.GetObjectClass (((global::Java.Lang.Object) this).Handle);
			this.class_ref = JNIEnv.NewGlobalRef (local_ref);
			JNIEnv.DeleteLocalRef (local_ref);
		}

		static Delegate cb_throwNullReferenceException;
#pragma warning disable 0169
		static Delegate GetThrowNullReferenceExceptionHandler ()
		{
			if (cb_throwNullReferenceException == null)
				cb_throwNullReferenceException = JNINativeWrapper.CreateDelegate ((_JniMarshal_PP_V) n_ThrowNullReferenceException);
			return cb_throwNullReferenceException;
		}

		static void n_ThrowNullReferenceException (IntPtr jnienv, IntPtr native__this)
		{
			var __this = global::Java.Lang.Object.GetObject<global::Com.Roydammarell.Xamarininterface.IExceptionService> (jnienv, native__this, JniHandleOwnership.DoNotTransfer);
			__this.ThrowNullReferenceException ();
		}
#pragma warning restore 0169

		IntPtr id_throwNullReferenceException;
		public unsafe void ThrowNullReferenceException ()
		{
			if (id_throwNullReferenceException == IntPtr.Zero)
				id_throwNullReferenceException = JNIEnv.GetMethodID (class_ref, "throwNullReferenceException", "()V");
			JNIEnv.CallVoidMethod (((global::Java.Lang.Object) this).Handle, id_throwNullReferenceException);
		}

	}
}
