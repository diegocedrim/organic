package br.pucrio.opus.smells.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

import br.pucrio.opus.smells.metrics.ClassMetrics;

public class PublicFieldCount extends ASTVisitor {
	
	private Integer publicFieldsCount;
	
	private ClassMetrics classMetrics;
	
	public PublicFieldCount(ClassMetrics classMetrics) {
		this.publicFieldsCount = 0;
		this.classMetrics = classMetrics;
	}

	public void endVisit(CompilationUnit node) {
		this.classMetrics.setPublicFieldsCount(this.publicFieldsCount);
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
