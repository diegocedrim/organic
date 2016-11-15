package br.pucrio.opus.smells.ast.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.LambdaExpression;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.WhileStatement;

public class CyclomaticComplexityVisitor extends ASTVisitor {
	
	private Integer cyclomaticComplexity;
	
	public CyclomaticComplexityVisitor() {
		this.cyclomaticComplexity = 1;
	}
	
	public Integer getCyclomaticComplexity() {
		return cyclomaticComplexity;
	}
	
	@Override
	public boolean visit(CatchClause node) {
		this.cyclomaticComplexity++;
		return true;
	}

	@Override
	public boolean visit(DoStatement node) {
		this.cyclomaticComplexity++;
		return true;
	}

	@Override
	public boolean visit(EnhancedForStatement node) {
		this.cyclomaticComplexity++;
		return true;
	}

	@Override
	public boolean visit(ForStatement node) {
		this.cyclomaticComplexity++;
		return true;
	}

	@Override
	public boolean visit(IfStatement node) {
		this.cyclomaticComplexity++;
		if (node.getElseStatement() != null) {
			this.cyclomaticComplexity++;
		}
		return true;
	}
	
	@Override
	public boolean visit(LambdaExpression node) {
		this.cyclomaticComplexity++;
		return true;
	}

	@Override
	public boolean visit(SwitchCase node) {
		this.cyclomaticComplexity++;
		return true;
	}
	
	@Override
	public boolean visit(WhileStatement node) {
		this.cyclomaticComplexity++;
		return true;
	}
	
	
}
