package br.pucrio.opus.smells.ast.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

public class PublicFieldCount extends ASTVisitor {
	
	private Integer publicFieldsCount;
	
	public PublicFieldCount() {
		this.publicFieldsCount = 0;
	}

	public Integer getPublicFieldsCount() {
		return publicFieldsCount;
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		int modifiers = node.getModifiers();
		if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
			this.publicFieldsCount++;
		}
		return false;
	}
}
