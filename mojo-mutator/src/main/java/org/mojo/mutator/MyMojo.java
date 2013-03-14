package org.mojo.mutator;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import main.Test;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * Goal which touches a timestamp file.
 * 
 * @goal generate-sources
 * @phase process-sources
 * @requiresDependencyResolution compile
 * @requiresDependencyCollection compile
 */
public class MyMojo extends AbstractMojo {
	/**
	 * The directory root under which processor-generated source files will be
	 * placed; files are placed in subdirectories based on package namespace.
	 * This is equivalent to the <code>-s</code> argument for apt.
	 * 
	 * @parameter 
	 *            default-value="${project.build.directory}/generated-sources/kevoree"
	 */
	private File sourceOutputDirectory;

	/**
	 * The maven project.
	 * 
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 * @requiresDependencyResolution compile
	 * @requiresDependencyCollection compile
	 */
	private MavenProject project;

	public void execute() throws MojoExecutionException {

		List<File> files = new ArrayList<File>();
		for (String s : project.getCompileSourceRoots()) {
			files.add(new File(s));
		}
		ClassLoader old = Thread.currentThread().getContextClassLoader();
		Collection<URL> urls = new ArrayList<URL>();
		String old_classpath = System.getProperty("java.class.path");
		StringBuffer newclasspath = new StringBuffer();
		int i = 0;		
		
			try {
				for (String artifact : project.getCompileClasspathElements()){
					
					try {
						urls.add(new File(artifact).toURI().toURL());
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					newclasspath.append(artifact);
					i++;
					if (i<project.getDependencyArtifacts().size())
						newclasspath.append(File.pathSeparatorChar);
					}
			} catch (DependencyResolutionRequiredException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		try {
			urls.add(new File(project.getBuild().getOutputDirectory()).toURI()
					.toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
			
		System.setProperty("java.class.path",newclasspath.toString());
		Thread.currentThread()
				.setContextClassLoader(
						new URLClassLoader( urls.toArray(new java.net.URL[urls.size()]),
								old));
		new Test(files, sourceOutputDirectory);
		System.setProperty("java.class.path",old_classpath);
		Thread.currentThread().setContextClassLoader(old);
		project.addCompileSourceRoot(sourceOutputDirectory.getAbsolutePath());
	}
}
