package org.tsts.main;

import java.util.Properties;

//import javax.servlet.Servlet;


import javax.servlet.Servlet;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.http.HttpService;

public class Activator extends DependencyActivatorBase {

	@Override
	public void destroy(BundleContext arg0, DependencyManager arg1)
			throws Exception {
		
		
	}

	@Override
	public void init(BundleContext arg0, DependencyManager manager)
			throws Exception {
		
		manager.add(createComponent().setImplementation(Main.class)
				.add(createServiceDependency().setService(HttpService.class)));
////		
		Properties props = new Properties();
		props.put("alias", "/");
		manager.add(createComponent()
				.setInterface(Servlet.class.getName(), props)
				.setImplementation(MainServlet.class));
		
		System.out.println("Initialized");
	}

}
