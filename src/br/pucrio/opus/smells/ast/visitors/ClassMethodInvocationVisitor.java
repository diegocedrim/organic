package br.pucrio.opus.smells.ast.visitors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;

import br.pucrio.opus.smells.collector.FeatureEnvy;

/**
 * Assumes that the root node is a method declaration. This visitor
 * explores all methods' call and determines the classes more targeted by this method. 
 * As output, provides a map containing the number of method calls for each different class.
 * This vistor discards methods from outside of the system and methods from the java core library. It
 * implies that this visitor only counts method calls made to another classes in the same system, providing
 * data for a possible scenario of {@link FeatureEnvy}
 * @author Diego Cedrim
 */
public class ClassMethodInvocationVisitor extends ASTVisitor {
	
	private Map<ITypeBinding, Integer> methodsCalls;
	
	/**
	 * Type that declares the method being visited
	 */
	private ITypeBinding declaringClass;
	
	public ClassMethodInvocationVisitor(ITypeBinding declaringClass) {
		this.methodsCalls = new HashMap<>();
		this.declaringClass = declaringClass;
	}
	
	@Override
	public boolean visit(MethodInvocation node) {
		if (this.declaringClass == null) {
			return false;
		}
		IMethodBinding methodBinding = node.resolveMethodBinding();
		if (methodBinding == null) {
			return false;
		}
		
		ITypeBinding typeBinding = methodBinding.getDeclaringClass();
		if (typeBinding == null) { // if we were not able to bind it, just discard.
			return true;
		}
		
		//increments the number of calls to the binded class
		Integer calls = methodsCalls.get(typeBinding);
		if (calls == null) {
			calls = 0;
		}
		methodsCalls.put(typeBinding, calls + 1);
		return true;
	}
	
	public ITypeBinding getDeclaringClass() {
		return declaringClass;
	}
	
	public Map<ITypeBinding, Integer> getMethodsCalls() {
		return methodsCalls;
	}
}

























