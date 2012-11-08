package spoon.examples.tracing.processing;

import spoon.examples.tracing.annotation.LogOptimized;
import spoon.examples.tracing.template.TracingTemplate_static_0_5;
import spoon.examples.tracing.template.TracingTemplate_static_generic;
import spoon.processing.AbstractAnnotationProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.declaration.CtExecutable;

/**
 * This example processor inserts a log at the begining of an executable's body
 * (a method or a constructor). The Log is statically calculated to be as
 * optimized as possible and is defined by the templates
 * {@link TracingTemplate_static_generic} and {@link TracingTemplate_static_0_5}.
 */
public class TracingProcessorOptimized<T> extends
		AbstractAnnotationProcessor<LogOptimized, CtExecutable<T>> {
	public void process(LogOptimized l, CtExecutable<T> e) {
		if (e.getParameters().size() <= 5) {
			TracingTemplate_static_0_5 template = new TracingTemplate_static_0_5(e);
			e.getBody().insertBegin(
					template.getSubstitution(e.getDeclaringType()));
			CtBlock<T> res = getFactory().Eval().createPartialEvaluator()
					.evaluate(e, e.getBody());
			e.setBody(res);
		} else {
			TracingTemplate_static_generic template = new TracingTemplate_static_generic(
					e);
			e.getBody().insertBegin(
					template.getSubstitution(e.getDeclaringType()));
		}
	}
}
