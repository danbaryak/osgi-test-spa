package org.tsts.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

public class MainServlet extends HttpServlet {

	private List<String> jsFiles = new ArrayList<>();
	
	public void start() {
		System.out.println("Scanning for lacal JS files");
		// load all scripts
		Collection<File> files = FileUtils.listFiles(new File(
				"web-resources/js"), null, false);
		for (File f : files) {
			System.out.println("JS File name is: " + f.getName());
			jsFiles.add(f.getName());
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		for (String scriptName : jsFiles) {
			out.println("<script src=\"js/" + scriptName + "\"></script>");
		}
		out.println("<head>");

		out.println("<body>");
		out.println("Hello");
		out.println("</body>");
		out.println("</html>");
	}

}
