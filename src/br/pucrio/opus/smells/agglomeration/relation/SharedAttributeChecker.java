package br.pucrio.opus.smells.agglomeration.relation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IBinding;

import br.pucrio.opus.smells.agglomeration.SmellyNode;
import br.pucrio.opus.smells.ast.visitors.FieldAccessCollector;

/**
 * Verifies if both resources are methods and if both share at least one attribute.
 * @author Diego Cedrim
 */
public class SharedAttributeChecker extends RelationChecker {
	
	private Map<SmellyNode, List<IBinding>> accessedCache;
	
	public SharedAttributeChecker() {
		this.accessedCache = new HashMap<>();
	}
	
	private List<IBinding> getAccessedVariables(SmellyNode node) {
		if (accessedCache.containsKey(node)) {
			return accessedCache.get(node);
		}
		FieldAccessCollector collector = new FieldAccessCollector();
		ASTNode methodDeclaration = node.getResource().getNode();
		methodDeclaration.accept(collector);
		List<IBinding> accessed = collector.getNodesCollected();
		this.accessedCache.put(node, accessed);
		return accessed;
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
