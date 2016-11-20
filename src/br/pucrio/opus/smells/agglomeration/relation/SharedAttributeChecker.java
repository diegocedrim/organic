package br.pucrio.opus.smells.agglomeration.relation;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IBinding;

import br.pucrio.opus.smells.agglomeration.SmellyNode;
import br.pucrio.opus.smells.ast.visitors.FieldAccessCollector;

/**
 * Verifies if both resources are methods and if both share at least one attribute.
 * @author Diego Cedrim
 */
public class SharedAttributeChecker extends RelationChecker {
	
	private List<IBinding> getAccessedVariables(SmellyNode node) {
		FieldAccessCollector collector = new FieldAccessCollector();
		ASTNode methodDeclaration = node.getResource().getNode();
		methodDeclaration.accept(collector);
		return collector.getNodesCollected();
	}

	@Override
	public boolean isRelated(SmellyNode u, SmellyNode v) {
		if (!super.areBothMethods(u, v)) {
			return false;
		}
		List<IBinding> uAccesses = this.getAccessedVariables(u);
		List<IBinding> vAccesses = this.getAccessedVariables(v);
		return super.intersects(uAccesses, vAccesses);
	}

}
