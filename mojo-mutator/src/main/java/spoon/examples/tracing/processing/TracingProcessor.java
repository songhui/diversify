package spoon.examples.tracing.processing;

import spoon.examples.tracing.annotation.Log;
import spoon.examples.tracing.template.TracingTemplate_generic;
import spoon.processing.AbstractAnnotationProcessor;
import spoon.reflect.declaration.CtExecutable;
import spoon.template.Substitution;

/**
 * This example processor inserts a log at the begining of an executable's body
 * (a method or a constructor). The Log is defined by the template
 * {@link TracingTemplate_generic}, with delegates to an introduced
 * {@link TracingTemplate_generic#trace(String, Object[])} method.
 */
public class TracingProcessor<T> extends
		AbstractAnnotationProcessor<Log, CtExecutable<T>> {
	public void process(Log l, CtExecutable<T> e) {
		// create the template (a statement list template)
		TracingTemplate_generic template = new TracingTemplate_generic(e);
		// insert the result of the substitution
		e.getBody().insertBegin(template.getSubstitution(e.getDeclaringType()));
		// insert {@link TracingTemplate_generic#trace(String, Object[])}
		System.err.println("process");
		Substitution.insertAll(e.getDeclaringType(), template);
	}
}
