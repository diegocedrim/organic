package br.pucrio.opus.smells.ast.visitors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class MaxCallChainVisitor extends ASTVisitor {
	
	private Integer maxCallChain;
	
	public MaxCallChainVisitor() {
		this.maxCallChain = 0;
	}

	@Override
	public boolean visit(MethodInvocation node) {
		Integer chainSize = 1;
		ASTNode parent = node.getParent();
		while (parent != null && parent instanceof MethodInvocation) {
			chainSize++;
			parent = parent.getParent();
		}
		this.maxCallChain = Math.max(this.maxCallChain, chainSize);
		return true;
	}
	
	public Integer getMaxCallChain() {
		return maxCallChain;
	}
}
