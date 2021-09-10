package com.roydammarell.xamarindependency;


public class ExceptionXamarinService
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.roydammarell.xamarininterface.IExceptionService
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_throwNullReferenceException:()V:GetThrowNullReferenceExceptionHandler:Com.Roydammarell.Xamarininterface.IExceptionServiceInvoker, XamarinDependency.LibraryBindings\n" +
			"";
		mono.android.Runtime.register ("XamarinDependency.Library.ExceptionXamarinService, XamarinDependency.Library", ExceptionXamarinService.class, __md_methods);
	}


	public ExceptionXamarinService ()
	{
		super ();
		if (getClass () == ExceptionXamarinService.class)
			mono.android.TypeManager.Activate ("XamarinDependency.Library.ExceptionXamarinService, XamarinDependency.Library", "", this, new java.lang.Object[] {  });
	}


	public void throwNullReferenceException ()
	{
		n_throwNullReferenceException ();
	}

	private native void n_throwNullReferenceException ();

	private java.util.ArrayList refList;
	public void monodroidAddReference (java.lang.Object obj)
	{
		if (refList == null)
			refList = new java.util.ArrayList ();
		refList.add (obj);
	}

	public void monodroidClearReferences ()
	{
		if (refList != null)
			refList.clear ();
	}
}
