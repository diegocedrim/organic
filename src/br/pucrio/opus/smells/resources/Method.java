package br.pucrio.opus.smells.resources;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

public class Method extends Resource {
	
	private List<String> parametersTypes;

	public Method(SourceFile sourceFile, MethodDeclaration node) {
		super(sourceFile, node);
		
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
	
}
