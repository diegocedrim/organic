package br.pucrio.opus.smells.ast.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * Assumes that the root node is a method declaration. This visitor
 * explores all methods' call and determines if the target method is 
 * from the local class or not. As output, provides two values:
 * - localMethodsCallCount: the number of times that local methods are being called
 * - foreignMethodsCallCount: the number of times that foreign methods are being called
 * @author Diego Cedrim
 */
public class MethodCallLocalityVisitor extends ASTVisitor {
	
	private Integer localMethodsCallCount;
	
	private Integer foreignMethodsCallCount;
	
	
	/**
	 * Type that declares the method being visited
	 */
	private ITypeBinding declaringTypeBinding;
	
	public MethodCallLocalityVisitor(TypeDeclaration declaringType) {
		this.localMethodsCallCount = 0;
		this.foreignMethodsCallCount = 0;
		this.declaringTypeBinding = declaringType.resolveBinding();
	}
	
	@Override
	public boolean visit(MethodInvocation node) {
		if (this.declaringTypeBinding == null) {
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
		if (typeBinding.isEqualTo(this.declaringTypeBinding)) {
			this.localMethodsCallCount++;
		} else {
			this.foreignMethodsCallCount++;
		}
		return true;
	}
	
	public Integer getForeignMethodsCallCount() {
		return foreignMethodsCallCount;
	}
	
	public Integer getLocalMethodsCallCount() {
		return localMethodsCallCount;
	}
}
