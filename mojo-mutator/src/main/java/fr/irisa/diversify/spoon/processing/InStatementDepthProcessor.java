package fr.irisa.diversify.spoon.processing;

import java.lang.annotation.Annotation;

import spoon.reflect.Factory;
import spoon.reflect.code.CtArrayAccess;
import spoon.reflect.code.CtAssert;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtBreak;
import spoon.reflect.code.CtCase;
import spoon.reflect.code.CtCatch;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtConditional;
import spoon.reflect.code.CtContinue;
import spoon.reflect.code.CtDo;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtForEach;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtNewArray;
import spoon.reflect.code.CtNewClass;
import spoon.reflect.code.CtOperatorAssignment;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtStatementList;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.code.CtSynchronized;
import spoon.reflect.code.CtThrow;
import spoon.reflect.code.CtTry;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.CtVariableAccess;
import spoon.reflect.code.CtWhile;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtAnnotationType;
import spoon.reflect.declaration.CtAnonymousExecutable;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtEnum;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtTypeParameter;
import spoon.reflect.reference.CtArrayTypeReference;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.reference.CtLocalVariableReference;
import spoon.reflect.reference.CtPackageReference;
import spoon.reflect.reference.CtParameterReference;
import spoon.reflect.reference.CtTypeParameterReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.CtVisitor;

public abstract class  InStatementDepthProcessor implements CtVisitor{

	
	

	public void visitCtWhile(CtWhile arg0) {
		arg0.getBody().accept(this);
	}


	public <T> void visitCtVariableAccess(CtVariableAccess<T> arg0) {
	}


	public <T> void visitCtUnaryOperator(CtUnaryOperator<T> arg0) {
		arg0.getOperand().accept(this);

	}


	public <T> void visitCtTypeReference(CtTypeReference<T> arg0) {

	}


	public void visitCtTypeParameterReference(
			CtTypeParameterReference arg0) {
	}


	public void visitCtTypeParameter(CtTypeParameter arg0) {

	}


	public void visitCtTry(CtTry arg0) {
		arg0.getBody().accept(this);

	}


	public void visitCtThrow(CtThrow arg0) {
		arg0.getThrownExpression().accept(this);
	}


	public void visitCtSynchronized(CtSynchronized arg0) {
		arg0.getBlock().accept(this);
	}


	public <S> void visitCtSwitch(CtSwitch<S> arg0) {
	}


	public <R> void visitCtStatementList(CtStatementList<R> arg0) {
		for (CtStatement s : arg0.getStatements()) {
			s.accept(this);
		}
	}


	public <R> void visitCtReturn(CtReturn<R> arg0) {
		arg0.getReturnedExpression().accept(this);
	}


	public <T> void visitCtParameterReference(
			CtParameterReference<T> arg0) {

	}


	public <T> void visitCtParameter(CtParameter<T> arg0) {

	}


	public void visitCtPackageReference(CtPackageReference arg0) {
	}


	public void visitCtPackage(CtPackage arg0) {
		

	}


	public <T, A extends T> void visitCtOperatorAssignement(
			CtOperatorAssignment<T, A> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtNewClass(CtNewClass<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtNewArray(CtNewArray<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtMethod(CtMethod<T> arg0) {
		arg0.getBody().accept(this);

	}


	public <T> void visitCtLocalVariableReference(
			CtLocalVariableReference<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtLocalVariable(CtLocalVariable<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtLiteral(CtLiteral<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtInvocation(CtInvocation<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtInterface(CtInterface<T> arg0) {
		// TODO Auto-generated method stub

	}


	public void visitCtIf(CtIf arg0) {
		arg0.getThenStatement().accept(this);

	}


	public void visitCtForEach(CtForEach arg0) {
		arg0.getBody().accept(this);

	}


	public void visitCtFor(CtFor e) {
		e.getBody().accept(this);

	}


	public <T> void visitCtFieldReference(CtFieldReference<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtFieldAccess(CtFieldAccess<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtField(CtField<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtExecutableReference(
			CtExecutableReference<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T extends Enum<?>> void visitCtEnum(CtEnum<T> arg0) {
		// TODO Auto-generated method stub

	}


	public void visitCtDo(CtDo arg0) {
		arg0.getBody().accept(this);

	}


	public void visitCtContinue(CtContinue arg0) {

	}


	public <T> void visitCtConstructor(CtConstructor<T> arg0) {
		arg0.getBody().accept(this);

	}


	public <T> void visitCtConditional(CtConditional<T> arg0) {
		arg0.getThenExpression().accept(this);
		arg0.getElseExpression().accept(this);
	}


	public void visitCtCodeSnippetStatement(CtCodeSnippetStatement arg0) {

	}


	public <T> void visitCtCodeSnippetExpression(
			CtCodeSnippetExpression<T> arg0) {

	}


	public <T> void visitCtClass(CtClass<T> arg0) {
		// TODO Auto-generated method stub

	}


	public void visitCtCatch(CtCatch arg0) {
		arg0.getBody().accept(this);
	}


	public <S> void visitCtCase(CtCase<S> arg0) {
		// TODO

	}


	public void visitCtBreak(CtBreak arg0) {

	}


	public <R> void visitCtBlock(CtBlock<R> arg0) {

		for (CtStatement s : arg0.getStatements()) {
			s.accept(this);
		}
	}


	public <T> void visitCtBinaryOperator(CtBinaryOperator<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T, A extends T> void visitCtAssignment(
			CtAssignment<T, A> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtAssert(CtAssert<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T> void visitCtArrayTypeReference(
			CtArrayTypeReference<T> arg0) {
		// TODO Auto-generated method stub

	}


	public <T, E extends CtExpression<?>> void visitCtArrayAccess(
			CtArrayAccess<T, E> arg0) {
		// TODO Auto-generated method stub

	}


	public void visitCtAnonymousExecutable(CtAnonymousExecutable arg0) {
		// TODO Auto-generated method stub

	}


	public <A extends Annotation> void visitCtAnnotationType(
			CtAnnotationType<A> arg0) {
		// TODO Auto-generated method stub

	}


	public <A extends Annotation> void visitCtAnnotation(
			CtAnnotation<A> arg0) {
		// TODO Auto-generated method stub

	}
}
