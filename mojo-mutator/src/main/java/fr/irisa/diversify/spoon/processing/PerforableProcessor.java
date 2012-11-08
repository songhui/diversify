package fr.irisa.diversify.spoon.processing;

import spoon.processing.AbstractAnnotationProcessor;
import spoon.reflect.Factory;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.declaration.CtExecutable;
import fr.irisa.diversify.annotations.Perforable;

public class PerforableProcessor<T> extends
		AbstractAnnotationProcessor<Perforable, CtExecutable<T>> {

	public PerforableProcessor() {
		
	}

	public void process(final Perforable l, CtExecutable<T> e) {

		e.getBody().accept(new InStatementDepthProcessor() {
			
			@Override
			public void visitCtFor(CtFor e) {
				super.visitCtFor(e);
				String varname = ((CtVariableAccess<T>) ((CtUnaryOperator<T>) e
						.getForUpdate().get(0)).getOperand()).getVariable()
						.getSimpleName();

				CtCodeSnippetStatement snip = Factory.getLauchingFactory()
						.Core().createCodeSnippetStatement();
				snip.setValue("{int " + varname + "=0; " + varname + " ="
						+ varname + "+" + l.step() + ";}");
				CtStatement statement = snip.compile();
				e.getForUpdate().clear();
				e.getForUpdate().add(
						(CtStatement) ((CtBlock) statement).getStatements()
								.get(1));

			}
		});

	}
}
