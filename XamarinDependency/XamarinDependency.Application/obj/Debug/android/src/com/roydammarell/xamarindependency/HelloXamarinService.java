package com.roydammarell.xamarindependency;


public class HelloXamarinService
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.roydammarell.xamarininterface.IHelloService
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_createHello:()Ljava/lang/String;:GetCreateHelloHandler:Com.Roydammarell.Xamarininterface.IHelloServiceInvoker, XamarinDependency.LibraryBindings\n" +
			"";
		mono.android.Runtime.register ("XamarinDependency.Library.HelloXamarinService, XamarinDependency.Library", HelloXamarinService.class, __md_methods);
	}


	public HelloXamarinService ()
	{
		super ();
		if (getClass () == HelloXamarinService.class)
			mono.android.TypeManager.Activate ("XamarinDependency.Library.HelloXamarinService, XamarinDependency.Library", "", this, new java.lang.Object[] {  });
	}


	public java.lang.String createHello ()
	{
		return n_createHello ();
	}

	private native java.lang.String n_createHello ();

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
