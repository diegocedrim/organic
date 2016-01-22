package br.pucrio.opus.smells.tests.visitor;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.FieldNameCollector;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class FieldNameCollectorTest {
	
	private CompilationUnit compilationUnit;

	@Before
	public void setUp() throws IOException{
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/FieldDeclaration.java");
		this.compilationUnit = CompilationUnitLoader.getCompilationUnit(file);
	}
	
	@Test
	public void allFieldsCollectionCountTest() {
		FieldNameCollector visitor = new FieldNameCollector();
		this.compilationUnit.accept(visitor);
		List<String> collectedFields = visitor.getNodesCollected();
		Assert.assertEquals(6, collectedFields.size());
	}
	
	@Test
	public void allFieldsCollectionNamesTest() {
		FieldNameCollector visitor = new FieldNameCollector();
		this.compilationUnit.accept(visitor);
		List<String> collectedFields = visitor.getNodesCollected();
		
		Collections.sort(collectedFields);
		String[] collectedNames = new String[collectedFields.size()];
		collectedFields.toArray(collectedNames);
		
		String[] expectedFields = new String[]{"list1", "test1", "test2", "test3", "test4", "test5"};
		Assert.assertArrayEquals(expectedFields, collectedNames); 
	}
}
