package br.pucrio.opus.smells.resources;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ITypeBinding;

public class ParenthoodRegistry {
	
	private static ParenthoodRegistry singleton;
	
	/**
	 * How many children a given type (identified by its FQN) has
	 */
	private Map<String, Integer> parenthoodMap;
	
	static {
		singleton = new ParenthoodRegistry();
	}
	
	private ParenthoodRegistry() {
		reset();
	}
	
	public static ParenthoodRegistry getInstance() {
		return singleton;
	}
	
	private String getQualifiedName(ITypeBinding superclass) {
		String fqn = superclass.getQualifiedName();
		if (fqn.contains("<")) {
			fqn = fqn.substring(0, fqn.indexOf("<"));
		}
		return fqn;
	}
	
	private void incrementChildCount(ITypeBinding superclass) {
		if (superclass == null) {
			return;
		}
		String fqn = this.getQualifiedName(superclass);
		Integer count = this.parenthoodMap.get(fqn);
		if (count == null) {
			count = 0;
		}
		this.parenthoodMap.put(fqn, ++count);
	}
	
	
	/**
	 * Get the type parent and register its new child
	 * @param child the child
	 */
	public void registerChild(Type child) {
		incrementChildCount(child.resolveSuperclassBinding());
	}
	
	public void reset() {
		this.parenthoodMap = new HashMap<>();
	}
	
	public Integer getChildrenCount(Type type) {
		ITypeBinding binding = type.getNodeAsTypeDeclaration().resolveBinding();
		if (binding == null) {
			return 0;
		}
		String fqn = this.getQualifiedName(binding);
		Integer count = this.parenthoodMap.get(fqn);
		if (count != null) {
			return count;
		}
		return 0;
	}

}
