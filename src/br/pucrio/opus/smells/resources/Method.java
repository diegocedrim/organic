package br.pucrio.opus.smells.resources;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import br.pucrio.opus.smells.ast.visitors.MethodInvocationVisitor;
import br.pucrio.opus.smells.graph.CallGraph;

public class Method extends Resource {
	
	private List<String> parametersTypes;
	
	public IMethodBinding getBinding() {
		MethodDeclaration declaration = (MethodDeclaration)this.getNode(); 
		IMethodBinding binding = declaration.resolveBinding();
		return binding;
	}

	/**
	 * Every time a new method is declared, it must be
	 * registered in the call Graph
	 */
	private void registerOnCallGraph(MethodDeclaration node) {
		CallGraph graph = CallGraph.getInstance();
		IMethodBinding thisBinding = this.getBinding();
		if (thisBinding == null) {
			//TODO LOG!
			return;
		}
		
		/*
		 * Retrieves the list of method calls made by the new
		 * declared method
		 */
		MethodInvocationVisitor invocationVisitor = new MethodInvocationVisitor();
		node.accept(invocationVisitor);
		for (IMethodBinding methodBinding : invocationVisitor.getCalls()) {
			graph.addMethodCall(thisBinding, methodBinding);
		}
		
	}
	
	public Method(SourceFile sourceFile, MethodDeclaration node) {
		super(sourceFile, node);
		this.registerOnCallGraph(node);
		
		this.parametersTypes = new ArrayList<>();
		for(Object obj : node.parameters()) {
			SingleVariableDeclaration declaration = (SingleVariableDeclaration)obj;
			declaration.getName();
			parametersTypes.add(declaration.getType().toString());
		}
		
		IBinding binding = node.resolveBinding();
		if (binding != null) {
			IMethodBinding methodBinding = (IMethodBinding)binding;
			String classFqn = methodBinding.getDeclaringClass().getQualifiedName();
			setFullyQualifiedName(classFqn + "." + node.getName());
		}
	}
	
	public List<String> getParametersTypes() {
		return parametersTypes;
	}
	
	@Override
	public String toString() {
		return "Method [fqn=" + getFullyQualifiedName() + "]";
	}
	
}
