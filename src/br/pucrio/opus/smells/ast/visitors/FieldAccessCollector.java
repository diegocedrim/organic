package br.pucrio.opus.smells.ast.visitors;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.SimpleName;

/**
 * Visits a method body in order to find all accesses to any class fields. During
 * SimpleName visits this visitor uses binding to determine if the simple name refers
 * to a field or not  
 * 
 * @author Diego Cedrim
 */
public class FieldAccessCollector extends CollectorVisitor<IBinding> {

	public boolean visit(SimpleName node) {
		IBinding binding = node.resolveBinding();
		/*
		 * Checks if the binding refers to a variable access. If yes,
		 * checks if the variable is a field.
		 */
		if (binding != null && binding.getKind() == IBinding.VARIABLE) {
			if (!wasAlreadyCollected(binding)) {
				this.addCollectedNode(binding);
			}
		}
		return true;
	};
}
