package org.site.login.mongo;

import org.amdatu.mongo.MongoDBService;
import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.site.login.api.UserService;

public class Activator extends DependencyActivatorBase {

	@Override
	public void destroy(BundleContext arg0, DependencyManager manager)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(BundleContext arg0, DependencyManager manager)
			throws Exception {
		manager.add(createComponent()
				.setInterface(UserService.class.getName(), null)
				.setImplementation(MongoUserService.class)
				.add(createServiceDependency().setService(MongoDBService.class).setRequired(true)));
		
	}

}
