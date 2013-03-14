package fr.irisa.diversify.spoon.utils;

import java.util.List;

import spoon.reflect.Factory;
import spoon.support.builder.CtFile;
import spoon.support.builder.JDTCompiler;
import spoon.support.builder.SpoonBuildingManager;

public class MyBuilder extends SpoonBuildingManager {

	private MyJDTBuilder compiler;


	public MyBuilder(Factory factory) {

		super(factory);
	}

	@Override
	public boolean build() throws Exception {
		// TODO Auto-generated method stub
		// this.getFactory().
		JDTCompiler.JAVA_COMPLIANCE = this.getFactory().getEnvironment()
				.getComplianceLevel();
		boolean srcSuccess, templateSuccess;
		long t = System.currentTimeMillis();
		
		compiler = new MyJDTBuilder();
		
		
		 
		srcSuccess = compiler.compileSrc(this.getFactory(), this
				.getSource().getAllJavaFiles());
		this.getFactory().getEnvironment().debugMessage(
				"compiled in " + (System.currentTimeMillis() - t) + " ms");
		// this.getFactory().getEnvironment().debugMessage("compiling templates: "+this.getTemplates().getAllJavaFiles());
		t = System.currentTimeMillis();
		templateSuccess = new JDTCompiler().compileTemplate(this.getFactory(),
				this.getTemplates().getAllJavaFiles());
		this.getFactory().Template().parseTypes();
		this.getFactory().getEnvironment().debugMessage(
				"compiled in " + (System.currentTimeMillis() - t) + " ms");
		return srcSuccess && templateSuccess;
	}

	
	public boolean addAndCompileSource(List<CtFile> allJavafiles){
		compiler.recompileSrc(allJavafiles,this.getFactory());
		return true;
	}
}
