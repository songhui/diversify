package spoon.examples.tracing.template;

import java.util.List;

import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtParameter;
import spoon.template.Local;
import spoon.template.Parameter;
import spoon.template.StatementListTemplateParameter;
import spoon.template.Template;

public class TestTemplat extends StatementListTemplateParameter
		implements Template {
	/**
	 * This template parameter contains the signature of the traced method.
	 */
	@Parameter
	String _methodname_;
	
	@Parameter
	String _annotation_parameter_;

	@Parameter
	String _annotationname_;
	
	

	@Local
	public TestTemplat(String _annotation_parameter_, String _annotationname_,
			String _methodname_) {
		super();
		this._annotation_parameter_ = _annotation_parameter_;
		this._annotationname_ = _annotationname_;
		this._methodname_ = _methodname_;
	}

	@Local
	public void set_annotation_parameter_(String _annotation_parameter_) {
		this._annotation_parameter_ = _annotation_parameter_;
	}

	@Local
	public void set_methodname_(String _name_) {
		this._methodname_ = _name_;
	}

	/**
	 * Creates this template for a given executable.
	 */
	@Local
	public TestTemplat() {
		this._methodname_ = "toto";
	}

	/**
	 * This is the code to be inserted at the begining of the logged method.
	 */
	@Local
	public void statements() {
		System.out.println("l'annotation " +_annotationname_ + " avec le parametre " + _annotation_parameter_ + " a ete placé sur la méthode "+ _methodname_);;
	}

	

}
