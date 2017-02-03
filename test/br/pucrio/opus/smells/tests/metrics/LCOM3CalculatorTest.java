package br.pucrio.opus.smells.tests.metrics;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.metrics.calculators.LCOM3Calculator;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class LCOM3CalculatorTest {
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
	
	private Double getLCOM3(String dummyClassName) throws IOException {
		Type typeClass = findTypeByName(dummyClassName);
		
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		typeClass.getNode().accept(visitor);
		
		TypeDeclaration type = visitor.getNodesCollected().get(0);
		LCOM3Calculator calculator = new LCOM3Calculator(); 
		
		return calculator.getValue(type);
	}

	/*
	 * The lcom.DummyLCOM class has LCOM3 = 1.071428571
	 * 
	 */
	@Test
	public void testLCOM3NotEmptyClass()  throws IOException{
		Double obtained = getLCOM3("DummyLCOM");
		
		Assert.assertEquals(new Double(1.071428571),obtained, DELTA);
		
	}
	
	/*
	 * Test Empty class, the LCOM3 must be 0
	 */
	@Test
	public void testLCOM3tEmptyClass()  throws IOException{
		Double obtained = getLCOM3("EmptyClass");
		
		Assert.assertEquals(new Double(0),obtained, DELTA);
		
	}
	
	/*
	 * TheLCOM3 must be 0
	 */
	@Test
	public void testLCOM3DummyDad()  throws IOException{
		Double obtained = getLCOM3("DummyDad");
		
		Assert.assertEquals(new Double(0.0),obtained, DELTA);
		
	}
	
	/*
	 * Test class with 1 attribute (from DummyDad) and 2 method, the LCOM3 must be 1
	 */
	@Test
	public void testLCOM2DummySon()  throws IOException{
		Double obtained = getLCOM3("DummySon");
		
		Assert.assertEquals(new Double(1.0),obtained, DELTA);
		
	}
	
	/*
	 * Test class with 1 attribute  (from DummyDad)  and 1 method, the LCOM3 must be 3
	 */
	@Test
	public void testLCOM3DummyGrandSon()  throws IOException{
		Double obtained = getLCOM3("DummyGrandSon");
		
		Assert.assertEquals(new Double(0.0),obtained, DELTA);
		
	}

}
