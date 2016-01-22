package br.pucrio.opus.smells.visitors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class TypeDeclarationCollector extends CollectorVisitor<ASTNode> {
	
	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		super.addCollectedNode(node);
		return true;
	}
	
	@Override
	public boolean visit(TypeDeclaration node) {
		super.addCollectedNode(node);
		return true;
	}
}
