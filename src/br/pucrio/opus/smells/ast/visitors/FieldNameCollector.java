package br.pucrio.opus.smells.ast.visitors;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class FieldNameCollector extends CollectorVisitor<String> {

	@Override
	public boolean visit(FieldDeclaration node) {
		Object obj = node.fragments().get(0);
		if(obj instanceof VariableDeclarationFragment){
			String fieldName = ((VariableDeclarationFragment) obj).getName().toString();
			super.addCollectedNode(fieldName);
		}
		return false;
	}
}
