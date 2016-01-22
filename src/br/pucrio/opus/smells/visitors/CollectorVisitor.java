package br.pucrio.opus.smells.visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;

public abstract class CollectorVisitor<T> extends ASTVisitor {
	
	private List<T> nodesCollected;
	
	public CollectorVisitor() {
		nodesCollected = new ArrayList<>();
	}
	
	protected void addCollectedNode(T node) {
		this.nodesCollected.add(node);
	}
	
	public List<T> getNodesCollected() {
		return nodesCollected;
	}
}
