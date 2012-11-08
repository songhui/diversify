package spoon.examples.tracing.template;

import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtExecutable;
import spoon.template.Parameter;
import spoon.template.StatementListTemplateParameter;
import spoon.template.Template;

/**
 * This template defines an optimized statement list for tracing.
 */
public class TracingTemplate_static_0_5 extends StatementListTemplateParameter
		implements Template {

	/**
	 * This template parameter contains the signature of the traced method.
	 */
	@Parameter
	String _signature_;

	/**
	 * This template parameter contains the parameters of the traced method.
	 * When substituted in a method invocation, these are replaced by accesses
	 * to the current method's arguments.
	 */
	@Parameter
	CtExpression<?>[] _args_;

	/**
	 * Creates this template for a given executable.
	 */
	public TracingTemplate_static_0_5(CtExecutable<?> e) {
		this._signature_ = e.getSignature();
		this._args_ = e.getFactory().Code().createVariableAccesses(
				e.getParameters()).toArray(new CtExpression<?>[0]);
	}

	/**
	 * This is the code to be inserted at the begining of the logged method. It
	 * works only for executable having less than 5 parameters. Beyond this,
	 * another more genric template shall be used ({@link TracingTemplate_generic}
	 * or {@link TracingTemplate_static_generic}). It is recommended to use the
	 * partial evaluator on the result of the substitution in order to simplify
	 * useles ifs.
	 */
	public void statements() {
		if (_args_.length == 0) {
			System.out.println("TRACING: " + _signature_);
		}
		if (_args_.length == 1) {
			System.out.println("TRACING: " + _signature_ + " " + _args_[0].S());
		}
		if (_args_.length == 2) {
			System.out.println("TRACING: " + _signature_ + " " + _args_[0].S()
					+ " " + _args_[1].S());
		}
		if (_args_.length == 3) {
			System.out.println("TRACING: " + _signature_ + " " + _args_[0].S()
					+ " " + _args_[1].S() + " " + _args_[2].S());
		}
		if (_args_.length == 4) {
			System.out.println("TRACING: " + _signature_ + " " + _args_[0].S()
					+ " " + _args_[1].S() + " " + _args_[2].S() + " "
					+ _args_[3].S());
		}
		if (_args_.length == 5) {
			System.out.println("TRACING: " + _signature_ + " " + _args_[0].S()
					+ " " + _args_[1].S() + " " + _args_[2].S() + " "
					+ _args_[3].S() + " " + _args_[5].S());
		}
	}
}
