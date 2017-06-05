package br.pucrio.opus.smells.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.pucrio.opus.smells.ast.visitors.MethodCollector;

public class Type extends Resource {

	private List<Method> methods;
	
	private transient Set<Type> children;
	
	public TypeDeclaration getNodeAsTypeDeclaration() {
		return (TypeDeclaration)getNode();
	}
	
	public ITypeBinding getBinding() {
		ITypeBinding binding = this.getNodeAsTypeDeclaration().resolveBinding();
		return binding;
	}
	
	public ITypeBinding getSuperclassBinding() {
		ITypeBinding binding = this.getNodeAsTypeDeclaration().resolveBinding();
		if (binding != null) {
			ITypeBinding superclass = binding.getSuperclass();
			return superclass;
		}
		return null;
	}
	
	@Override
	public boolean isSmelly() {
		if (super.isSmelly()) {
			return true;
		}
		
		for (Method method : this.methods) {
			if (method.isSmelly()) {
				return true;
			}
		}
		
		return false;
	}
	
	public void removeAllNonSmellyMethods() {
		List<Method> toRemove = new ArrayList<>();
		for (Method method : this.methods) {
			if (!method.isSmelly()) {
				toRemove.add(method);
			}
		}
		this.methods.removeAll(toRemove);
	}
	
	protected void identifyKind() {
		TypeDeclaration typeDeclaration = (TypeDeclaration)getNode();
		StringBuffer buffer = new StringBuffer();
		int modifiers = typeDeclaration.getModifiers(); 
		
		if (Modifier.isPublic(modifiers)) {
			buffer.append("public ");
		}
		
		if (Modifier.isPrivate(modifiers)) {
			buffer.append("private ");
		}
		
		if (Modifier.isProtected(modifiers)) {
			buffer.append("protected ");
		}
		
		if (Modifier.isAbstract(modifiers)) {
			buffer.append("abstract ");
		}
		
		if (Modifier.isFinal(modifiers)) {
			buffer.append("final ");
		}
		
		if (typeDeclaration.isInterface()) {
			buffer.append("interface");
		} else {
			buffer.append("class");
		}
		
		this.setKind(buffer.toString());
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
		
		//register itself in the ParenthoodRegistry 
		ParenthoodRegistry.getInstance().registerChild(this);
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
	
	public Method findMethodByName(String name) {
		for (Method method : this.methods) {
			String toBeFound = this.getFullyQualifiedName() + "." + name;
			if (method.getFullyQualifiedName().equals(toBeFound)) {
				return method;
			}
		}
		return null;
	}
	
	public List<Method> getMethods() {
		return methods;
	}
	
	public Set<Type> getChildren() {
		return children;
	}
	
	@Override
	public String toString() {
		return "Type [fqn=" + getFullyQualifiedName() + "]";
	}
	
}
