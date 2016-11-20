package br.pucrio.opus.smells.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.IMethodBinding;

public class CallGraph {
	
	/**
	 * For each IMethodBinding, we find the set of methods
	 * called by it
	 */
	private Map<IMethodBinding, Set<IMethodBinding>> calling;
	
	/**
	 * For each IMethodBinding, we found the list of 
	 * methods which call it
	 */
	private Map<IMethodBinding, Set<IMethodBinding>> calledBy;

	private static CallGraph singleton;
	
	static {
		singleton = new CallGraph();
	}
	
	private void addCaller(IMethodBinding caller, IMethodBinding callee) {
		Set<IMethodBinding> calls = this.calling.get(caller);
		if (calls == null) {
			calls = new HashSet<>();
			this.calling.put(caller, calls);
		}
		calls.add(callee);
	}
	
	private void addCalee(IMethodBinding caller, IMethodBinding callee) {
		Set<IMethodBinding> callers = this.calledBy.get(callee);
		if (callers == null) {
			callers = new HashSet<>();
			this.calledBy.put(callee, callers);
		}
		callers.add(caller);
	}
	
	public void addMethodCall(IMethodBinding caller, IMethodBinding callee) {
		this.addCaller(caller, callee);
		this.addCalee(caller, callee);
	}
	
	/**
	 * The methods that call a specific method
	 */
	public Set<IMethodBinding> getCallers(IMethodBinding method) {
		Set<IMethodBinding> calls = this.calledBy.get(method);
		if (calls == null) {
			return new HashSet<>();
		}
		return calls;
	}
	
	/**
	 * Return true if caller calls called in its body
	 */
	public boolean calls(IMethodBinding caller, IMethodBinding called) {
		Set<IMethodBinding> calls = this.getCalls(caller);
		return calls.contains(called);
	}
	
	/**
	 * The methods that a specific method calls
	 */
	public Set<IMethodBinding> getCalls(IMethodBinding method) {
		Set<IMethodBinding> callers = this.calling.get(method);
		if (callers == null) {
			return new HashSet<>();
		}
		return callers;
	}
	
	public void reset() {
		this.calledBy.clear();
		this.calling.clear();
	}
	
	public CallGraph() {
		this.calling = new HashMap<>();
		this.calledBy = new HashMap<>();
	}
	
	public static CallGraph getInstance() {
		return singleton;
	}
}
