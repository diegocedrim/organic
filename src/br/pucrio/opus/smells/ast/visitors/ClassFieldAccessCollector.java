package br.pucrio.opus.smells.ast.visitors;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * Visits a method body in order to find all accesses class fields. During
 * SimpleName visits, this visitor uses binding to determine if the simple name refers
 * to a field or not. If the simple name refers to a field, the visitor checks if the field
 * belongs to the class
 * 
 * @author Diego Cedrim
 */
public class ClassFieldAccessCollector extends CollectorVisitor<IBinding> {
	
	/**
	 * Type that declares the method being visited
	 */
	private ITypeBinding declaringTypeBinding;
	
	public ClassFieldAccessCollector(TypeDeclaration declaringType) {
		this.declaringTypeBinding = declaringType.resolveBinding();
	}

	public boolean visit(SimpleName node) {
		if (this.declaringTypeBinding == null) {
			return false;
		}
		
		IBinding binding = node.resolveBinding();
		if (binding == null) {
			return false;
		}
		
		IVariableBinding variableBinding = (IVariableBinding)binding;
		ITypeBinding typeBinding = variableBinding.getDeclaringClass();
		if (typeBinding == null) {
			return false;
		}
		/*
		 * Checks if the binding refers to a variable access. If yes,
		 * checks if the variable is a field.
		 */
		if (binding.getKind() == IBinding.VARIABLE && typeBinding.isEqualTo(this.declaringTypeBinding)) {
			if (!wasAlreadyCollected(binding)) {
				this.addCollectedNode(binding);
			}
		}
		return true;
	};
}
