package br.pucrio.opus.tests.visitor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.ASTBuilder;
import br.pucrio.opus.smells.visitors.PublicMethodCollector;
import br.pucrio.opus.smells.visitors.TypeDeclarationCollector;

public class PublicMethodCollectorTest {
	
	private CompilationUnit compilationUnit;

	@Before
	public void setUp() throws IOException{
		File file = new File("test");
		String[] srcPaths = new String[]{file.getAbsolutePath()};
		ASTBuilder builder = new ASTBuilder(srcPaths);
		ASTParser parser = builder.create();
		
		String source = FileUtils.readFileToString(new File("test/br/pucrio/opus/tests/dummy/AnonymousClass.java"));
		parser.setSource(source.toCharArray());
		this.compilationUnit = (CompilationUnit)parser.createAST(null);
	}
	
	@Test
	public void publicMethodsCountTest() {
		TypeDeclarationCollector typeCollector = new TypeDeclarationCollector();
		this.compilationUnit.accept(typeCollector);
		List<ASTNode> types = typeCollector.getNodesCollected();
		
		PublicMethodCollector visitor = new PublicMethodCollector();
		types.get(0).accept(visitor);
		List<MethodDeclaration> collectedFields = visitor.getNodesCollected();
		Assert.assertEquals(3, collectedFields.size());
		
		visitor = new PublicMethodCollector();
		types.get(1).accept(visitor);
		collectedFields = visitor.getNodesCollected();
		Assert.assertEquals(1, collectedFields.size());
		
		visitor = new PublicMethodCollector();
		types.get(2).accept(visitor);
		collectedFields = visitor.getNodesCollected();
		Assert.assertEquals(13, collectedFields.size());
	}
}
