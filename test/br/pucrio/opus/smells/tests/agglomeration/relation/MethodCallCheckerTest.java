package br.pucrio.opus.smells.tests.agglomeration.relation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.agglomeration.SmellyNode;
import br.pucrio.opus.smells.agglomeration.relation.MethodCallChecker;
import br.pucrio.opus.smells.graph.CallGraph;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class MethodCallCheckerTest {
	
	private Type methodCalls;
	
	private Type methodCallsExternal;
	
	private MethodCallChecker checker;
	
	private SmellyNode getSmellyNode(Type type, String methodName) {
		Method method = type.findMethodByName(methodName);
		return new SmellyNode(method);
	}
	
	private Type findByName(List<Type> types, String name) {
		for (Type type : types) {
			File file = type.getSourceFile().getFile();
			if (file.getName().equals(name)) {
				return type;
			}
		}
		return null;
	}
	
	@Before
	public void setup() throws IOException {
		CallGraph.getInstance().reset();
		List<Type> types = TypeLoader.loadAllFromDir(new File("test/br/pucrio/opus/smells/tests/dummy/relation/methodcalls"));
		this.methodCalls = findByName(types, "MethodCalls.java");
		this.methodCallsExternal = findByName(types, "MethodCallsExternal.java");
		this.checker = new MethodCallChecker();
	}
	
	@Test
	public void abTest() {
		SmellyNode a = this.getSmellyNode(methodCalls, "a");
		SmellyNode b = this.getSmellyNode(methodCalls, "b");
		boolean isRelated = checker.isRelated(a, b);
		Assert.isTrue(!isRelated);
	}
	
	@Test
	public void aaTest() {
		SmellyNode a = this.getSmellyNode(methodCalls, "a");
		SmellyNode b = this.getSmellyNode(methodCalls, "a");
		boolean isRelated = checker.isRelated(a, b);
		Assert.isTrue(!isRelated);
	}
	
	@Test
	public void acTest() {
		SmellyNode a = this.getSmellyNode(methodCalls, "a");
		SmellyNode c = this.getSmellyNode(methodCalls, "c");
		boolean isRelated = checker.isRelated(a, c);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void bcTest() {
		SmellyNode a = this.getSmellyNode(methodCalls, "b");
		SmellyNode d = this.getSmellyNode(methodCalls, "c");
		boolean isRelated = checker.isRelated(a, d);
		Assert.isTrue(isRelated);
	}
	
	
	@Test
	public void aExternalATest() {
		SmellyNode a = this.getSmellyNode(methodCalls, "a");
		SmellyNode d = this.getSmellyNode(methodCallsExternal, "a");
		boolean isRelated = checker.isRelated(a, d);
		Assert.isTrue(isRelated);
	}
}
