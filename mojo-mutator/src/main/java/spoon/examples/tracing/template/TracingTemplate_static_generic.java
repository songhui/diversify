package spoon.examples.tracing.template;

import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtExecutable;
import spoon.template.Parameter;
import spoon.template.StatementListTemplateParameter;
import spoon.template.Template;

public class TracingTemplate_static_generic extends
		StatementListTemplateParameter implements Template {

	/**
	 * This template parameter contains the signature of the traced method.
	 */
	@Parameter
	String _name_;

	/**
	 * This template parameter contains the variable accesses to the parameters
	 * of the traced method.
	 */
	@Parameter
	CtExpression<?>[] _args_;

	/**
	 * Creates this template for a given executable.
	 */
	public TracingTemplate_static_generic(CtExecutable<?> e) {
		this._name_ = e.getSignature();
		this._args_ = e.getFactory().Code().createVariableAccesses(
				e.getParameters()).toArray(new CtExpression<?>[0]);
	}

	/**
	 * This is the code to be inserted at the begining of the logged method.
	 */
	public void statements() {
		String trace = "TRACING: " + _name_;
		// a foreach on an array template parameter is expanded during the
		// substitution with the actual values in the array
		for (CtExpression<?> e : _args_)
			trace += " " + e;
		System.out.println(trace);
	}
}
