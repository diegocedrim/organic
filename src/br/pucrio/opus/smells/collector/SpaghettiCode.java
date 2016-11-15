package br.pucrio.opus.smells.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.pucrio.opus.smells.ast.visitors.LocalFieldAccessCollector;
import br.pucrio.opus.smells.ast.visitors.FieldDeclarationCollector;
import br.pucrio.opus.smells.ast.visitors.LocalMethodCallVisitor;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.Resource;
import br.pucrio.opus.smells.resources.Type;

/**
 * For performance reasons, the class assumes that all methods' smells were collected before. This is
 * used in order to get all long methods. The definition of spaghetti code used here is:
 * 
 * - A class implementing at least two long methods interacting between them through method calls or shared fields.
 * @author Diego Cedrim
 */
public class SpaghettiCode extends SmellDetector {
	
	private List<Method> getLongMethods(Type type) {
		List<Method> longMethods = new ArrayList<>();
		for (Method method : type.getMethods()) {
			if (method.hasSmell(SmellName.LongMethod)) {
				longMethods.add(method);
			}
		}
		return longMethods;
	}
	
	private boolean atLeastOneIn(Set<Object> local, Set<Object> global) {
		for (Object obj : local) {
			if (global.contains(obj)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean callsOtherLongMethod(List<IMethodBinding> localMethodsCalls, List<Method> longMethods, Method origin) {
		IMethodBinding originBinding = origin.getBinding();
		for (IMethodBinding mBinding : localMethodsCalls) {
			for (Method longMethod : longMethods) {
				MethodDeclaration declaration = (MethodDeclaration)longMethod.getNode(); 
				IMethodBinding binding = declaration.resolveBinding();
				if (binding != null && binding.equals(mBinding) && !binding.equals(originBinding)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if at least two methods are interacting between them through method calls or shared fields. 
	 * @param methods list of methods
	 * @return true if at least two methods are connected or false, otherwise
	 */
	private boolean areConnected(Type type, List<Method> methods) {
		Set<Object> elements = new HashSet<>();
		TypeDeclaration typeDeclaration = type.getNodeAsTypeDeclaration();
		
		FieldDeclarationCollector fieldCollector = new FieldDeclarationCollector();
		type.getNode().accept(fieldCollector);
		List<FieldDeclaration> fields = fieldCollector.getNodesCollected();
		
		for (int i = 0; i < methods.size(); i++) {
			Set<Object> localElements = new HashSet<>();
			Method method = methods.get(i);
			
			//collects all calls to local methods
			LocalMethodCallVisitor localMethodCallsVisitor = new LocalMethodCallVisitor(typeDeclaration);
			method.getNode().accept(localMethodCallsVisitor);
			List<IMethodBinding> localMethodCalls = localMethodCallsVisitor.getNodesCollected();
			localElements.addAll(localMethodCalls);
			
			//collects all accesses to local fields
			LocalFieldAccessCollector fieldAccessCollector = new LocalFieldAccessCollector(fields);
			method.getNode().accept(fieldAccessCollector);
			localElements.addAll(fieldAccessCollector.getNodesCollected());
			
			/*
			 * if the current method accesses at least one method or field accessed by
			 * previous methods, then there is a connection between (at least) two 
			 */
			if (this.atLeastOneIn(localElements, elements)) {
				return true;
			} else if (this.callsOtherLongMethod(localMethodCalls, methods, method)) {
				return true;
			}
			elements.addAll(localElements);
		}
		
		
		return false;
	}

	@Override
	public List<Smell> detect(Resource resource) {
		Type type = (Type)resource;
		
		List<Method> longMethods = this.getLongMethods(type);
		if (longMethods.size() < 2) {
			return new ArrayList<>();
		}
		
		if (this.areConnected(type, longMethods)) {
			StringBuilder builder = new StringBuilder();
			builder.append("LONG_METHODS_CONNECTED = " + longMethods.size());
			
			Smell smell = super.createSmell(resource);
			smell.setReason(builder.toString());
			return Arrays.asList(smell);
		}
		
		return new ArrayList<>();
	}

	@Override
	protected SmellName getSmellName() {
		return SmellName.SpaghettiCode;
	}

}
