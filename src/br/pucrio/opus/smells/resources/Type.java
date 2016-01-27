package br.pucrio.opus.smells.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.pucrio.opus.smells.ast.visitors.MethodCollector;

public class Type extends Resource<TypeDeclaration> {

	private List<Method> methods;
	
	private transient Set<Type> children;
	
	public String getSuperclassFQN() {
		ITypeBinding binding = this.getNode().resolveBinding();
		if (binding != null) {
			ITypeBinding superclass = binding.getSuperclass();
			if (superclass != null) {
				return superclass.getQualifiedName();
			}
		}
		return null;
	}
	
	public Type(SourceFile sourceFile, TypeDeclaration typeDeclaration) {
		super(sourceFile, typeDeclaration);
		
		this.children = new HashSet<>();
		
		IBinding binding = typeDeclaration.resolveBinding();
		if (binding != null) {
			String fqn = typeDeclaration.resolveBinding().getQualifiedName();
			setFullyQualifiedName(fqn);
		}
		this.searchForMethods();
	}
	
	private void searchForMethods() {
		this.methods = new ArrayList<>();
		MethodCollector visitor = new MethodCollector();
		this.getNode().accept(visitor);
		List<MethodDeclaration> methodsDeclarations = visitor.getNodesCollected();
		for (MethodDeclaration methodDeclaration : methodsDeclarations) {
			Method method = new Method(getSourceFile(), methodDeclaration);
			this.methods.add(method);
		}
	}
	
	public List<Method> getMethods() {
		return methods;
	}
	
	public Set<Type> getChildren() {
		return children;
	}
	
}
