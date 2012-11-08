package spoon.examples.tracing.processing;

import spoon.examples.tracing.annotation.Log;
import spoon.examples.tracing.template.TestTemplat;
import spoon.processing.AbstractAnnotationProcessor;
import spoon.reflect.declaration.CtExecutable;
import spoon.template.Substitution;

public class MyTestProcessor <T> extends
		AbstractAnnotationProcessor<Log, CtExecutable<T>> {
	public void process(Log l, CtExecutable<T> e) {
		TestTemplat template = new TestTemplat(l.p(), l.annotationType().getName(),e.getSignature());
		
		e.getBody().insertBegin(template.getSubstitution(e.getDeclaringType()));
		Substitution.insertAll(e.getDeclaringType(), template);
	}
}
