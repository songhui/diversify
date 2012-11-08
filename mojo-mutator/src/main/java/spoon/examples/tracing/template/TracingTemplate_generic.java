package spoon.examples.tracing.template;

import java.util.List;


import spoon.examples.tracing.processing.TracingProcessorOptimized;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtParameter;
import spoon.template.Local;
import spoon.template.Parameter;
import spoon.template.StatementListTemplateParameter;
import spoon.template.Template;

/**
 * This statement list template defines a simple tracing code. This tracing is
 * generic and dynamically achieved. To see more optimized solutions, refer to
 * {@link TracingProcessorOptimized}.
 */
public class TracingTemplate_generic extends StatementListTemplateParameter
		implements Template {
	/**
	 * This template parameter contains the signature of the traced method.
	 */
	@Parameter
	String _signature_;

	/**
	 * This template parameter contains the parameters of the traced method. When
	 * substituted in a method invocation, these are replaced by accesses to the
	 * current method's arguments.
	 */
	@Parameter
	List<CtParameter<?>> _args_;

	/**
	 * Creates this template for a given executable.
	 */
	@Local
	public TracingTemplate_generic(CtExecutable<?> e) {
		this._signature_ = e.getSignature();
		this._args_ = e.getParameters();
	}

	/**
	 * This method is introduced to actually implement the tracing in a generic
	 * maner (using varargs).
	 */
	public void trace(String signature, Object... args) {
		String trace = "TRACING: " + signature;
		for (Object arg : args) {
			trace += " " + arg;
		}
		System.out.println(trace);
	}

	/**
	 * This is the code to be inserted at the begining of the logged method.
	 */
	@Local
	public void statements() {
		trace(_signature_, _args_);
	}
}
