package br.pucrio.opus.smells.ast.visitors;

import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * Searches for  methods in one TypeDeclaration. All type declarations
 * made inside this given type will be ignored. It means the if a class X has
 * a private class declared inside it,  only  methods of X would be
 * returned.
 * 
 * @author Diego Cedrim
 *
 */
public class PublicMethodCollector extends CollectorVisitor<MethodDeclaration> {
	
	private Boolean typeDeclarationFound;
	
	public PublicMethodCollector() {
		this.typeDeclarationFound = false;
	}

	/**
	 * Ensures that only the first type declaration will 
	 * be visited. In this way, public methods of inner classes
	 * will be ignored
	 */
	@Override
	public boolean visit(TypeDeclaration node) {
		if (typeDeclarationFound) {
			return false;
		}
		typeDeclarationFound = true;
		return true;
	}
	
	public boolean visit(AnonymousClassDeclaration node) {
		return false;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		int modifiers = node.getModifiers();
		if (Modifier.isPublic(modifiers)) {
			super.addCollectedNode(node);
		}
		return true;
	}
}
