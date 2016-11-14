package br.pucrio.opus.smells.tests.visitor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.LocalFieldAccessCollector;
import br.pucrio.opus.smells.ast.visitors.FieldDeclarationCollector;
import br.pucrio.opus.smells.ast.visitors.PublicMethodCollector;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class FieldAccessCollectorTest {
	
	private CompilationUnit compilationUnit;
	
	private List<MethodDeclaration> publicMethods;
	
	private List<FieldDeclaration> fields;
	
	private MethodDeclaration findMethodByName(String name) {
		for (MethodDeclaration decls : publicMethods) {
			if (decls.getName().toString().equals(name)) {
				return decls;
			}
		}
		return null;
	}

	@Before
	public void setUp() throws IOException{
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/FieldAccessedByMethod.java");
		this.compilationUnit = CompilationUnitLoader.getCompilationUnit(file);
		
		FieldDeclarationCollector fieldCollector = new FieldDeclarationCollector();
		compilationUnit.accept(fieldCollector);
		this.fields = fieldCollector.getNodesCollected();
		
		PublicMethodCollector visitor = new PublicMethodCollector();
		compilationUnit.accept(visitor);
		this.publicMethods = visitor.getNodesCollected();
	}
	
	@Test
	public void methodXFieldAccessesTest() {
		MethodDeclaration declaration = findMethodByName("x");
		LocalFieldAccessCollector visitor = new LocalFieldAccessCollector(this.fields);
		declaration.accept(visitor);
		List<FieldDeclaration> accesses = visitor.getNodesCollected();
		Assert.assertEquals(3, accesses.size());
	}
	
	@Test
	public void methodYFieldAccessesTest() {
		MethodDeclaration declaration = findMethodByName("y");
		LocalFieldAccessCollector visitor = new LocalFieldAccessCollector(this.fields);
		declaration.accept(visitor);
		List<FieldDeclaration> accesses = visitor.getNodesCollected();
		Assert.assertEquals(1, accesses.size());
	}
	
	@Test
	public void methodZFieldAccessesTest() {
		MethodDeclaration declaration = findMethodByName("z");
		LocalFieldAccessCollector visitor = new LocalFieldAccessCollector(this.fields);
		declaration.accept(visitor);
		List<FieldDeclaration> accesses = visitor.getNodesCollected();
		Assert.assertEquals(2, accesses.size());
	}
	
}
