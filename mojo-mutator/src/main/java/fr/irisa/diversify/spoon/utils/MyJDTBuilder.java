package fr.irisa.diversify.spoon.utils;

/* 
 * Spoon - http://spoon.gforge.inria.fr/
 * Copyright (C) 2006 INRIA Futurs <renaud.pawlak@inria.fr>
 * 
 * This software is governed by the CeCILL-C License under French law and
 * abiding by the rules of distribution of free software. You can use, modify 
 * and/or redistribute the software under the terms of the CeCILL-C license as 
 * circulated by CEA, CNRS and INRIA at http://www.cecill.info. 
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the CeCILL-C License for more details.
 *  
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL-C license and that you accept its terms.
 */

import java.util.List;

import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;

import spoon.reflect.Factory;
import spoon.support.builder.CtFile;
import spoon.support.builder.JDTCompiler;
import spoon.support.builder.JDTTreeBuilder;

public class MyJDTBuilder extends JDTCompiler implements ICompilerRequestor {


	private boolean success;

	/*public boolean compileSrc(Factory f, List<CtFile> files) throws Exception {
		if (files.isEmpty())
			return true;
		// long t=System.currentTimeMillis();
		// Build input
		List<String> args = new ArrayList<String>();
		args.add("-1." + JAVA_COMPLIANCE);
		args.add("-preserveAllLocals");
		args.add("-enableJavadoc");
		args.add("-noExit");
		args.add("-nowarn");
		args.add("src");
		for (CtFile f1 : files) {
			if (f1.getPath().equals(null)) {
				args.add(f1.getPath());
				System.out.println("path " + f1.getPath());
			}
		}
		// JDTCompiler compiler = new JDTCompiler(new PrintWriter(System.out),
		// new PrintWriter(System.err));

		String[] essai = args.toArray(new String[0]);

		//		

		configure(essai);

		// f.getEnvironment().debugMessage("compiling src: "+files);
		CompilationUnitDeclaration[] units = getUnits(files,f);

		// f.getEnvironment().debugMessage("got units in "+(System.currentTimeMillis()-t)+" ms");

		builder = new JDTTreeBuilder(f);

		for (CompilationUnitDeclaration unit : units) {
			try {
				// t=System.currentTimeMillis();
				unit.traverse(builder, unit.scope);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return success;
	}

*/

	private JDTTreeBuilder builder;

	public MyJDTBuilder() {
		super();
	}


	public void recompileSrc(List<CtFile> allJavaFiles, Factory f) {
		try {
			
			CompilationUnitDeclaration[] units = getUnits(allJavaFiles,f);
			for (CompilationUnitDeclaration unit : units) {
				// t=System.currentTimeMillis();
				unit.traverse(builder, unit.scope);
				acceptResult(unit.compilationResult.tagAsAccepted());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public void acceptResult(CompilationResult result) {
		if (result.hasErrors()) {
			System.err.println(result);
			success=false;
		}
		
	}
}
