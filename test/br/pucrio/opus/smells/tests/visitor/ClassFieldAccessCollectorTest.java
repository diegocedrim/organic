package br.pucrio.opus.smells.tests.visitor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.ClassFieldAccessCollector;
import br.pucrio.opus.smells.ast.visitors.MethodCollector;
import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;


public class ClassFieldAccessCollectorTest {
	
	private CompilationUnit compilationUnit;
	private List<MethodDeclaration> allMethods;
	private TypeDeclaration typeDeclaration;
	

	private MethodDeclaration findMethodByName(String name) {
		for (MethodDeclaration decls : allMethods) {
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
		
		
		MethodCollector visitor = new MethodCollector();
		compilationUnit.accept(visitor);
		this.allMethods = visitor.getNodesCollected();
		
		TypeDeclarationCollector typeVisitor = new TypeDeclarationCollector();
		this.compilationUnit.accept(typeVisitor);
		typeDeclaration = typeVisitor.getNodesCollected().get(0);
	}
	
	@Test
	public void methodKFieldAccessesTest() {
				
		ClassFieldAccessCollector visitor = new ClassFieldAccessCollector(typeDeclaration);
		
		MethodDeclaration declaration = findMethodByName("k");
		
		declaration.accept(visitor);
				
		Assert.assertEquals(0, visitor.getNodesCollected().size());
	}
	
	@Test
	public void methodMFieldAccessesTest() {
				
		ClassFieldAccessCollector visitor = new ClassFieldAccessCollector(typeDeclaration);
		
		MethodDeclaration declaration = findMethodByName("m");
		
		declaration.accept(visitor);
				
		Assert.assertEquals(0, visitor.getNodesCollected().size());
	}
	
	
	@Test
	public void methodXFieldAccessesTest() {
				
		ClassFieldAccessCollector visitor = new ClassFieldAccessCollector(typeDeclaration);
		
		MethodDeclaration declaration = findMethodByName("x");
		
		declaration.accept(visitor);
				
		Assert.assertEquals(4, visitor.getNodesCollected().size());
	}
	
	
	@Test
	public void methodYFieldAccessesTest() {
				
		ClassFieldAccessCollector visitor = new ClassFieldAccessCollector(typeDeclaration);
		
		MethodDeclaration declaration = findMethodByName("y");
		
		declaration.accept(visitor);
				
		Assert.assertEquals(1, visitor.getNodesCollected().size());
	}
	
	@Test
	public void methodZFieldAccessesTest() {
				
		ClassFieldAccessCollector visitor = new ClassFieldAccessCollector(typeDeclaration);
		
		MethodDeclaration declaration = findMethodByName("z");
		
		declaration.accept(visitor);
				
		Assert.assertEquals(2, visitor.getNodesCollected().size());
	}
}
