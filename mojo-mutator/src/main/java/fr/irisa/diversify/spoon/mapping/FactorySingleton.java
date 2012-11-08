package fr.irisa.diversify.spoon.mapping;

import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtPackage;

public class FactorySingleton {

	private static FactorySingleton instance = new FactorySingleton();

	
	private CtPackage pack;
	public CtPackage getPack() {
		return pack;
	}

	public void setPack(CtPackage pack) {
		this.pack = pack;
	}

	private CtClass factoryclass;	

	public CtClass getFactoryclass() {
		return factoryclass;
	}

	public void setFactoryclass(CtClass factoryclass) {
		this.factoryclass = factoryclass;
	}

	public static FactorySingleton getInstance() {
		return instance;
	}
}
