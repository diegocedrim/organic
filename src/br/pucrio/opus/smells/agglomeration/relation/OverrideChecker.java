package br.pucrio.opus.smells.agglomeration.relation;

import org.eclipse.jdt.core.dom.IMethodBinding;

import br.pucrio.opus.smells.agglomeration.SmellyNode;
import br.pucrio.opus.smells.resources.Method;

/**
 * Verifies if both resources are methods and if one overrides the other
 * @author Diego Cedrim
 */
public class OverrideChecker extends RelationChecker {
	
	private IMethodBinding getBinding(SmellyNode node) {
		Method method = (Method)node.getResource();
		return method.getBinding();
	}

	@Override
	public boolean isRelated(SmellyNode u, SmellyNode v) {
		if (!super.areBothMethods(u, v)) {
			return false;
		}
		
		IMethodBinding uBinding = getBinding(u);
		IMethodBinding vBinding = getBinding(v);
		
		if (uBinding == null || vBinding == null || uBinding == vBinding) {
			//TODO LOG!
			return false;
		}

		return uBinding.overrides(vBinding) || vBinding.overrides(uBinding);
	}

}
