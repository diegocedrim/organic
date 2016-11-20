package br.pucrio.opus.smells.resources;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.eclipse.jdt.core.dom.ITypeBinding;

public class ParenthoodRegistry {
	
	private static ParenthoodRegistry singleton;
	
	/**
	 * A set of children that a given type (identified by its FQN) has
	 */
	private Map<String, Set<String>> parenthoodMap;
	
	static {
		singleton = new ParenthoodRegistry();
	}
	
	private ParenthoodRegistry() {
		reset();
	}
	
	public static ParenthoodRegistry getInstance() {
		return singleton;
	}
	
	private String getQualifiedName(ITypeBinding typeBinding) {
		String fqn = typeBinding.getQualifiedName();
		if (fqn.contains("<")) {
			fqn = fqn.substring(0, fqn.indexOf("<"));
		}
		return fqn;
	}
	
	public boolean isChild(Type child, Type parent) {
		String childFqn = this.getQualifiedName(child.getBinding());
		return this.getChildren(parent).contains(childFqn);
	} 
	
	public boolean isChild(ITypeBinding child, ITypeBinding parent) {
		String childFqn = this.getQualifiedName(child);
		return this.getChildren(parent).contains(childFqn);
	} 
	
	private boolean isInterfaceDescendant(ITypeBinding type, ITypeBinding ancestor) {
		Queue<ITypeBinding> queue = new LinkedList<>(Arrays.asList(type));
		Set<ITypeBinding> visited = new HashSet<>();
		String ancestorFqn = this.getQualifiedName(ancestor);
		while (!queue.isEmpty()) {
			ITypeBinding current = queue.poll();
			visited.add(current);
			ITypeBinding[] superInterfaces = current.getInterfaces();
			for (ITypeBinding iInterface : superInterfaces) {
				if (!visited.contains(iInterface)) {
					String interfaceFqn = this.getQualifiedName(iInterface);
					if (interfaceFqn.equals(ancestorFqn)) {
						return true;
					}
					queue.add(iInterface);
				}
			}
		}
		return false;
	}
	
	private boolean isClassDescendant(ITypeBinding type, ITypeBinding ancestor) {
		String ancestorFqn = this.getQualifiedName(ancestor);
		ITypeBinding current = type;
		while (current != null) {
			String currentFqn = this.getQualifiedName(current);
			if (currentFqn.equals(ancestorFqn)){
				return true;
			}
			current = current.getSuperclass();
		}
		return false;
	}
	
	public boolean isDescendant(Type type, Type ancestor) {
		ITypeBinding ancestorBinding = ancestor.getBinding();
		ITypeBinding typeBinding = type.getBinding();
		if (ancestorBinding == null || typeBinding == null || typeBinding == ancestorBinding) {
			return false;
		}
		return this.isClassDescendant(typeBinding, ancestorBinding) 
				|| this.isInterfaceDescendant(typeBinding, ancestorBinding);
	}
	
	private void addChild(ITypeBinding parent, ITypeBinding child) {
		String parentFqn = this.getQualifiedName(parent);
		Set<String> children = this.parenthoodMap.get(parentFqn);
		if (children == null) {
			children = new HashSet<>();
			this.parenthoodMap.put(parentFqn, children);
		}

		String childFqn = this.getQualifiedName(child);
		children.add(childFqn);
	}
	
	/**
	 * Get the type parent and register its new child
	 * @param child the child
	 */
	public void registerChild(Type child) {
		ITypeBinding childBinding = child.getBinding();
		if (childBinding == null) {
			return;
		}
		
		//adding child of the direct superclass
		ITypeBinding parent = child.getSuperclassBinding();
		if (parent !=  null) {
			this.addChild(parent, childBinding);
		}
		
		//add child of all implementing interfaces
		ITypeBinding[] interfaces = childBinding.getInterfaces();
		for (ITypeBinding implementing : interfaces) {
			this.addChild(implementing, childBinding);
		}
	}
	
	public void reset() {
		this.parenthoodMap = new HashMap<>();
	}
	
	public Integer getChildrenCount(Type type) {
		ITypeBinding binding = type.getBinding();
		if (binding == null) {
			return 0;
		}
		String fqn = this.getQualifiedName(binding);
		Set<String> children = this.parenthoodMap.get(fqn);
		if (children != null) {
			return children.size();
		}
		return 0;
	}
	
	private Set<String> getChildren(Type type) {
		ITypeBinding binding = type.getBinding();
		return this.getChildren(binding);
	}
	
	private Set<String> getChildren(ITypeBinding binding) {
		if (binding == null) {
			return new HashSet<>();
		}
		String fqn = this.getQualifiedName(binding);
		Set<String> children = this.parenthoodMap.get(fqn);
		if (children != null) {
			return children;
		}
		return new HashSet<>();
	}

}
