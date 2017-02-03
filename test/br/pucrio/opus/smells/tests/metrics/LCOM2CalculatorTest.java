package br.pucrio.opus.smells.tests.metrics;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.metrics.calculators.LCOM2Calculator;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class LCOM2CalculatorTest {
	private final double DELTA = 0.000001d;
	private List<Type> types;
	
	
	@Before
	public void setUp() throws IOException {
		File dir = new File("test/br/pucrio/opus/smells/tests/dummy/lcom");
		this.types = TypeLoader.loadAllFromDir(dir);
		
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
	
	private Double getLCOM2(String dummyClassName) throws IOException {
		Type typeClass = findTypeByName(dummyClassName);
		
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		typeClass.getNode().accept(visitor);
		
		TypeDeclaration type = visitor.getNodesCollected().get(0);
		LCOM2Calculator calculator = new LCOM2Calculator(); 
		
		return calculator.getValue(type);
	}

	/*
	 * The lcom.DummyLCOM class has LCOM2 = 0.714285
	 */
	@Test
	public void testLCOM2NotEmptyClass()  throws IOException{
		Double obtained = getLCOM2("DummyLCOM");
		
		Assert.assertEquals(new Double(0.714285),obtained, DELTA);
		
	}
	
	/*
	 * Test Empty class, the LCOM2 must be 0
	 */
	@Test
	public void testLCOM2tEmptyClass()  throws IOException{
		Double obtained = getLCOM2("EmptyClass");
		
		Assert.assertEquals(new Double(0),obtained, DELTA);
		
	}
	
	/*
	 * Test class with LCOM2 must be 0.5
	 */
	@Test
	public void testLCOM2DummyDad()  throws IOException{
		Double obtained = getLCOM2("DummyDad");
		
		Assert.assertEquals(new Double(0.5),obtained, DELTA);
		
	}
	
	/*
	 * The LCOM2 must be 0.5
	 */
	@Test
	public void testLCOM2DummySon()  throws IOException{
		Double obtained = getLCOM2("DummySon");
		
		Assert.assertEquals(new Double(0.5),obtained, DELTA);
		
	}
	
	/*
	 * The LCOM2 must be 0
	 */
	@Test
	public void testLCOM2DummyGrandSon()  throws IOException{
		Double obtained = getLCOM2("DummyGrandSon");
		
		Assert.assertEquals(new Double(0.0),obtained, DELTA);
		
	}

}
