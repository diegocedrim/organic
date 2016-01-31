package br.pucrio.opus.smells.resources;

import java.util.HashMap;
import java.util.Map;

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
	
	private void incrementChildCount(String fqn) {
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
		incrementChildCount(child.getSuperclassFQN());
	}
	
	public void reset() {
		this.parenthoodMap = new HashMap<>();
	}
	
	public Integer getChildrenCount(Type type) {
		String superclassFqn = type.getFullyQualifiedName();
		Integer count = this.parenthoodMap.get(superclassFqn);
		if (count != null) {
			return count;
		}
		return 0;
	}

}
