package br.pucrio.opus.smells.ast.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.AssertStatement;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.LambdaExpression;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

public class LinesOfCodeVisitor extends ASTVisitor {

	private Integer loc;
	
	public Integer getLoc() {
		return loc;
	}
	
	public LinesOfCodeVisitor() {
		this.loc = 0;
	}
	
	@Override
	public boolean visit(AnnotationTypeDeclaration node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(AnnotationTypeMemberDeclaration node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(AssertStatement node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(BreakStatement node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(CatchClause node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(ContinueStatement node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(DoStatement node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(EnhancedForStatement node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(EnumConstantDeclaration node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(EnumDeclaration node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(ExpressionStatement node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(ForStatement node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(IfStatement node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(ImportDeclaration node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(Initializer node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(LabeledStatement node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(LambdaExpression node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(MarkerAnnotation node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(MethodDeclaration node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(NormalAnnotation node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(PackageDeclaration node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(ReturnStatement node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(SingleMemberAnnotation node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(SuperConstructorInvocation node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(SwitchCase node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(SwitchStatement node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(ThrowStatement node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(TryStatement node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(TypeDeclarationStatement node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(TypeLiteral node) {
		loc++;
		return true;
	}


	@Override
	public boolean visit(VariableDeclarationStatement node) {
		loc++;
		return true;
	}

	@Override
	public boolean visit(WhileStatement node) {
		loc++;
		return true;
	}
	
}
