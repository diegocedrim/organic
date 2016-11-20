package br.pucrio.opus.smells.tests.agglomeration.relation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.agglomeration.SmellyNode;
import br.pucrio.opus.smells.agglomeration.relation.ClassExtensionChecker;
import br.pucrio.opus.smells.agglomeration.relation.RelationChecker;
import br.pucrio.opus.smells.resources.ParenthoodRegistry;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class ExtensionCheckerTest {
	
	private RelationChecker checker;
	
	private List<Type> types;
	
	private SmellyNode getSmellyNode(String name) {
		return new SmellyNode(findByName(name));
	}
	
	private Type findByName(String name) {
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
		ParenthoodRegistry.getInstance().reset();
		this.types = TypeLoader.loadAllFromDir(new File("test/br/pucrio/opus/smells/tests/dummy/relation/extension"));
		this.checker = new ClassExtensionChecker();
	}
	
	@Test
	public void childInterfaceTest() {
		SmellyNode child = this.getSmellyNode("Child.java");
		SmellyNode parent = this.getSmellyNode("Interface.java");
		boolean isRelated = checker.isRelated(child, parent);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void childTheMostGenericInterfaceTest() {
		SmellyNode child = this.getSmellyNode("Child.java");
		SmellyNode parent = this.getSmellyNode("TheMostGenericInterface.java");
		boolean isRelated = checker.isRelated(child, parent);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void childGenericInterface1Test() {
		SmellyNode child = this.getSmellyNode("Child.java");
		SmellyNode parent = this.getSmellyNode("GenericInterface1.java");
		boolean isRelated = checker.isRelated(child, parent);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void childGenericInterface2Test() {
		SmellyNode child = this.getSmellyNode("Child.java");
		SmellyNode parent = this.getSmellyNode("GenericInterface2.java");
		boolean isRelated = checker.isRelated(child, parent);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void childParentTest() {
		SmellyNode child = this.getSmellyNode("Child.java");
		SmellyNode parent = this.getSmellyNode("Parent.java");
		boolean isRelated = checker.isRelated(child, parent);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void intChildGenericParentTest() {
		SmellyNode child = this.getSmellyNode("IntChild.java");
		SmellyNode parent = this.getSmellyNode("GenericParent.java");
		boolean isRelated = checker.isRelated(child, parent);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void intChildInterfaceParentTest() {
		SmellyNode child = this.getSmellyNode("IntChild.java");
		SmellyNode parent = this.getSmellyNode("Interface.java");
		boolean isRelated = checker.isRelated(child, parent);
		Assert.isTrue(!isRelated);
	}
	
	@Test
	public void intChildTheMostGenericInterfaceParentTest() {
		SmellyNode child = this.getSmellyNode("IntChild.java");
		SmellyNode parent = this.getSmellyNode("TheMostGenericInterface.java");
		boolean isRelated = checker.isRelated(child, parent);
		Assert.isTrue(!isRelated);
	}
	
	@Test
	public void genericChildIntParentTest() {
		SmellyNode child = this.getSmellyNode("GenericParent.java");
		SmellyNode parent = this.getSmellyNode("IntChild.java");
		boolean isRelated = checker.isRelated(child, parent);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void floatChildGenericParentTest() {
		SmellyNode child = this.getSmellyNode("FloatChild.java");
		SmellyNode parent = this.getSmellyNode("GenericParent.java");
		boolean isRelated = checker.isRelated(child, parent);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void floatChildSuoerGenericParentTest() {
		SmellyNode child = this.getSmellyNode("FloatChild.java");
		SmellyNode parent = this.getSmellyNode("SuperGeneric.java");
		boolean isRelated = checker.isRelated(child, parent);
		Assert.isTrue(isRelated);
	}
}
