package br.pucrio.opus.smells.ast.visitors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;

/**
 * Assumes that the root node is a method declaration. This visitor
 * explores all method calls and determines the classes more targeted by this method. 
 * As output, provides a map containing the distinct method calls for each different class.
 * This vistor discards methods from outside of the system and methods from the java core library. It
 * implies that this visitor only counts method calls made to classes in the same system (including the declaring one)
 * @author Diego Cedrim
 */
public class DistinctMethodInvocationVisitor extends ASTVisitor {

	private Map<ITypeBinding, Set<IMethodBinding>> methodsCalls;

	/**
	 * Type that declares the method being visited
	 */
	private ITypeBinding declaringClass;

	public DistinctMethodInvocationVisitor(ITypeBinding declaringClass) {
		this.methodsCalls = new HashMap<>();
		this.declaringClass = declaringClass;
	}

	private void registerCall(ITypeBinding type, IMethodBinding method) {
		//increments the number of calls to the binded class
		Set<IMethodBinding> calls = methodsCalls.get(type);
		if (calls == null) {
			calls = new HashSet<>();
			this.methodsCalls.put(type, calls);
		}
		calls.add(method);
	}

	@Override
	public boolean visit(MethodInvocation node) {
		if (this.declaringClass == null) {
			return true;
		}
		IMethodBinding methodBinding = node.resolveMethodBinding();
		if (methodBinding == null) {
			return true;
		}

		ITypeBinding typeBinding = methodBinding.getDeclaringClass();
		if (typeBinding == null) { // if we were not able to bind it, just discard.
			return true;
		}

		this.registerCall(typeBinding, methodBinding);
		return true;
	}

	public ITypeBinding getDeclaringClass() {
		return declaringClass;
	}

	public Map<ITypeBinding, Set<IMethodBinding>> getMethodsCalls() {
		return methodsCalls;
	}
}

























