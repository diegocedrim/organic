package br.pucrio.opus.smells.tests.graph;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.graph.CallGraph;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class CallGraphTest {
	
	private List<Type> types;
	
	@Before
	public void setUp() throws IOException {
		File dir = new File("test/br/pucrio/opus/smells/tests/dummy/graph");
		this.types = TypeLoader.loadAllFromDir(dir);
	}
	
	@After
	public void tearDown() {
		CallGraph.getInstance().reset();
	}
	
	private Type findTypeByName(String name) {
		for (Type type : types) {
			TypeDeclaration td = (TypeDeclaration)type.getNode();
			String typeName = td.getName().toString();
			if (typeName.equals(name)) {
				return type;
			}
		}
		return null;
	}
	
	private void callsTest(String className, String methodName, int calls, int callers) throws IOException {
		CallGraph cg = CallGraph.getInstance();
		Type type = findTypeByName(className);
		Method method = type.findMethodByName(methodName);
		IMethodBinding binding = method.getBinding();
		Assert.assertEquals(calls, cg.getCalls(binding).size());
		Assert.assertEquals(callers, cg.getCallers(binding).size());
	}
	
	@Test
	public void aaTest() throws IOException {
		this.callsTest("A", "aa", 2, 1);
	}
	
	@Test
	public void abTest() throws IOException {
		this.callsTest("A", "ab", 1, 2);
	}
	
	@Test
	public void acTest() throws IOException {
		this.callsTest("A", "ac", 1, 2);
	}
	
	@Test
	public void adTest() throws IOException {
		this.callsTest("A", "ad", 0, 1);
	}
	
	@Test
	public void baTest() throws IOException {
		this.callsTest("B", "ba", 1, 1);
		this.callsTest("A", "aa", 2, 1);
	}
}
