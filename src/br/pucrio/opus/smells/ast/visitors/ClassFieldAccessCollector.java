package br.pucrio.opus.smells.ast.visitors;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
public class ClassFieldAccessCollector extends CollectorVisitor<IVariableBinding> {
	
	/**
	 * Type that declares the method being visited
	 */
	private ITypeBinding declaringTypeBinding;
	
	private Set<IVariableBinding> allVariables;
	
	public ClassFieldAccessCollector(TypeDeclaration declaringType) {
		this.declaringTypeBinding = declaringType.resolveBinding();
		this.allVariables = this.getVariablesInHierarchy();
	}

	public boolean visit(SimpleName node) {
		if (this.declaringTypeBinding == null) {
			return false;
		}
		
		IBinding binding = node.resolveBinding();
		if (binding == null) {
			return false;
		}
		
		/*
		 * Checks if the binding refers to a variable access. If yes,
		 * checks if the variable is a field.
		 */
		if (binding.getKind() == IBinding.VARIABLE) {
			IVariableBinding variableBinding = (IVariableBinding) binding;
			if (!wasAlreadyCollected(variableBinding) && this.allVariables.contains(variableBinding)) {
				this.addCollectedNode(variableBinding);
			}
		}
		return true;
	}
	
	private Set<IVariableBinding> getVariablesInHierarchy() {
		Set<IVariableBinding> variables = new HashSet<>();
		ITypeBinding type = this.declaringTypeBinding;
		while (type != null) {
			IVariableBinding[] localVariables = type.getDeclaredFields();
			variables.addAll(Arrays.asList(localVariables));
			type = type.getSuperclass();
		}
		return variables;
	}
	
}
