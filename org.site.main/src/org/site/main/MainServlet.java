package org.site.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.osgi.framework.Bundle;

public class MainServlet extends HttpServlet {

	private List<String> jsFiles = new ArrayList<>();
	private List<String> jsLibFiles = new ArrayList<>();
	
	private List<String> cssFiles = new ArrayList<>();
	private List<String> cssLibFiles = new ArrayList<>();
	private String mainContent;
	
	public void start() {
		
	}

	public void jsBundleAdded(Bundle bundle) {
		addFiles(jsFiles, bundle, String.valueOf(bundle.getHeaders().get("X-SPA-JS")));
	}
	
	public void jsBundleRemoved(Bundle bundle) {
		removeFiles(jsFiles, bundle, String.valueOf(bundle.getHeaders().get("X-SPA-JS")));
	}
	
	public void jsLibBundleAdded(Bundle bundle) {
		addFiles(jsLibFiles, bundle, String.valueOf(bundle.getHeaders().get("X-SPA-JSLIB")));
	}
	
	public void jsLibBundleRemoved(Bundle bundle) {
		removeFiles(jsLibFiles, bundle, String.valueOf(bundle.getHeaders().get("X-SPA-JSLIB")));
	}
	
	
	public void cssBundleAdded(Bundle bundle) {
		addFiles(cssFiles, bundle, String.valueOf(bundle.getHeaders().get("X-SPA-CSS")));
	}
	
	public void cssBundleRemoved(Bundle bundle) {
		removeFiles(cssFiles, bundle, String.valueOf(bundle.getHeaders().get("X-SPA-CSS")));
	}
	
	public void cssLibBundleAdded(Bundle bundle) {
		addFiles(cssLibFiles, bundle, String.valueOf(bundle.getHeaders().get("X-SPA-CSSLIB")));
	}
	
	public void cssLibBundleRemoved(Bundle bundle) {
		removeFiles(cssLibFiles, bundle, String.valueOf(bundle.getHeaders().get("X-SPA-CSSLIB")));
	}
	public void mainAdded(Bundle bundle) {
		System.out.println("Main added");
		String mainContentPath = String.valueOf(bundle.getHeaders().get("X-SPA-Main"));
		
		try {
			this.mainContent = IOUtils.toString(bundle.getResource(mainContentPath).openStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mainRemoved(Bundle bundle) {
		System.out.println("Main removed");
	}
	private void addFiles(List<String> list, Bundle bundle, String pathToAdd) {
		if (pathToAdd.contains(",")) {
			for (String path : pathToAdd.split(",")) {
				addFiles(list, bundle, path);
			}
			return;
		}
		if (pathToAdd.endsWith(".js") || pathToAdd.endsWith(".css")) {
			list.add(pathToAdd);
			return;
		}
		Enumeration paths = bundle.getEntryPaths(pathToAdd);
		while (paths.hasMoreElements()) {
			String path = (String) paths.nextElement();
			addFiles(list, bundle, path);
		}
	}
	
	private void removeFiles(List<String> list, Bundle bundle, String pathToRemove) {
		if (pathToRemove.contains(",")) {
			for (String path : pathToRemove.split(",")) {
				removeFiles(list, bundle, path);
			}
			return;
		}
		if (pathToRemove.endsWith(".js") || pathToRemove.endsWith(".css")) {
			list.remove(pathToRemove);
			return;
		}
		Enumeration paths = bundle.getEntryPaths(pathToRemove);
		while (paths.hasMoreElements()) {
			String path = (String) paths.nextElement();
			removeFiles(list, bundle, path);
		}
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		
		out.println("<head>");
		for (String cssFileName : cssLibFiles) {
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + cssFileName + "\">");
		}
		for (String cssFileName : cssFiles) {
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + cssFileName + "\">");
		}
		
		out.println("<body>");
		if (mainContent != null) {
			out.println(mainContent);
		
		}
		// add scripts
		for (String scriptName : jsLibFiles) {
			out.println("<script src=\"" + scriptName + "\"></script>");
		}
		for (String scriptName : jsFiles) {
			out.println("<script src=\"" + scriptName + "\"></script>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}

}
