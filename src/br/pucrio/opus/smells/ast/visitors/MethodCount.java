package br.pucrio.opus.smells.ast.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class MethodCount extends ASTVisitor {

	public boolean visit(MethodDeclaration node) {
		//		System.out.println(node.getName().getFullyQualifiedName() + node.parameters());
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodInvocation node) {
		System.out.println("Name: " + node.getName());
		System.out.println(node.resolveMethodBinding());
        Expression expression = node.getExpression();
        if (expression != null) {
            System.out.println("Expr: " + expression.toString());
            ITypeBinding typeBinding = expression.resolveTypeBinding();
            if (typeBinding != null) {
                System.out.println("Type: " + typeBinding.getName());
            }
        }
        IMethodBinding binding = node.resolveMethodBinding();
        if (binding != null) {
            ITypeBinding type = binding.getDeclaringClass();
            if (type != null) {
                System.out.println("Decl: " + type.getName());
            }
        }
        System.out.println(" ");
        return true;
	}



	@Override
	public boolean visit(TypeDeclaration node) {
		//		System.out.println(node.getName().getFullyQualifiedName());
		return super.visit(node);
	};

}
