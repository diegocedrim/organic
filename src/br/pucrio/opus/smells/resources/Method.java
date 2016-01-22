package br.pucrio.opus.smells.resources;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Method extends Resource<MethodDeclaration> {

	public Method(SourceFile sourceFile, MethodDeclaration node) {
		super(sourceFile, node);
	}
	
}
