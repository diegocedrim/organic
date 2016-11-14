package br.pucrio.opus.smells.tests.metrics;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.graph.CallGraph;
import br.pucrio.opus.smells.metrics.calculators.ChangingClassesCalculator;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class ChangingClassTest {
	
	
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
	
	private void ccTest(String className, String methodName, Integer extectedCC) throws IOException {
		Type type = findTypeByName(className);
		Method method = type.findMethodByName(methodName);
		ChangingClassesCalculator calc = new ChangingClassesCalculator();
		Double actual = calc.getValue(method.getNode());
		Assert.assertEquals(new Double(extectedCC), actual);
	}
	
	@Test
	public void aaTest() throws IOException {
		this.ccTest("A", "aa", 1);
	}
	
	@Test
	public void abTest() throws IOException {
		this.ccTest("A", "ab", 1);
	}
	
	@Test
	public void acTest() throws IOException {
		this.ccTest("A", "ac", 1);
	}
	
	@Test
	public void adTest() throws IOException {
		this.ccTest("A", "ad", 1);
	}
	
	@Test
	public void baTest() throws IOException {
		this.ccTest("B", "ba", 1);
		this.ccTest("B", "bc", 0);
	}
}
