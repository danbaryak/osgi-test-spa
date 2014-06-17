package org.tsts.main;

import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

public class Main {
	
	private volatile HttpService httpService;
	
	public void start() {
		System.out.println("Main module is starting");
		try {
			httpService.registerResources("/images", "/images", null);
			httpService.registerResources("/js", "/js", null); 
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
