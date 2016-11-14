package br.pucrio.opus.smells.ast.visitors;

import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * Searches for methods in one TypeDeclaration. All type declarations
 * made inside this given type will be ignored. It means the if a class X has
 * a private class declared inside it,  only public methods of X would be
 * returned.
 * 
 * @author Diego Cedrim
 *
 */
public class MethodCollector extends CollectorVisitor<MethodDeclaration> {
	
	private Boolean typeDeclarationFound;
	
	public MethodCollector() {
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
	
	@Override
	public boolean visit(AnonymousClassDeclaration node) {
		return false;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		super.addCollectedNode(node);
		return true;
	}
}
