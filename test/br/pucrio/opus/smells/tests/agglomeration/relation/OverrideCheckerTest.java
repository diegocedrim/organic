package br.pucrio.opus.smells.tests.agglomeration.relation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.agglomeration.SmellyNode;
import br.pucrio.opus.smells.agglomeration.relation.OverrideChecker;
import br.pucrio.opus.smells.agglomeration.relation.RelationChecker;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class OverrideCheckerTest {
	
	private Type intChild;
	
	private Type floatChild;
	
	private Type parent;
	
	private RelationChecker checker;
	
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
		List<Type> types = TypeLoader.loadAllFromDir(new File("test/br/pucrio/opus/smells/tests/dummy/relation/extension"));
		this.intChild = findByName(types, "IntChild.java");
		this.floatChild = findByName(types, "FloatChild.java");
		this.parent = findByName(types, "GenericParent.java");
		this.checker = new OverrideChecker();
	}
	
	@Test
	public void intAGenericATest() {
		SmellyNode a = this.getSmellyNode(intChild, "a");
		SmellyNode b = this.getSmellyNode(parent, "a");
		boolean isRelated = checker.isRelated(a, b);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void intBGenericBTest() {
		SmellyNode a = this.getSmellyNode(intChild, "a");
		SmellyNode b = this.getSmellyNode(parent, "b");
		boolean isRelated = checker.isRelated(a, b);
		Assert.isTrue(!isRelated);
	}
	
	@Test
	public void intGenericGenericGenericTest() {
		SmellyNode a = this.getSmellyNode(intChild, "generic");
		SmellyNode b = this.getSmellyNode(parent, "generic");
		boolean isRelated = checker.isRelated(a, b);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void floatAGenericATest() {
		SmellyNode a = this.getSmellyNode(floatChild, "a");
		SmellyNode b = this.getSmellyNode(parent, "a");
		boolean isRelated = checker.isRelated(a, b);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void floatGenericGenericGenericTest() {
		SmellyNode a = this.getSmellyNode(floatChild, "generic");
		SmellyNode b = this.getSmellyNode(parent, "generic");
		boolean isRelated = checker.isRelated(a, b);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void floatCGenericATest() {
		SmellyNode a = this.getSmellyNode(floatChild, "c");
		SmellyNode b = this.getSmellyNode(parent, "a");
		boolean isRelated = checker.isRelated(a, b);
		Assert.isTrue(!isRelated);
	}
}
