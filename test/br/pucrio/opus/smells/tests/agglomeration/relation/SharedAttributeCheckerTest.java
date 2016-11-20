package br.pucrio.opus.smells.tests.agglomeration.relation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.agglomeration.SmellyNode;
import br.pucrio.opus.smells.agglomeration.relation.SharedAttributeChecker;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class SharedAttributeCheckerTest {
	
	private Type sharedAttribute;
	
	private Type sharedAttributeExternal;
	
	private SharedAttributeChecker checker;
	
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
		List<Type> types = TypeLoader.loadAllFromDir(new File("test/br/pucrio/opus/smells/tests/dummy/relation/shared"));
		this.sharedAttribute = findByName(types, "SharedAttribute.java");
		this.sharedAttributeExternal = findByName(types, "SharedAttributeExternal.java");
		this.checker = new SharedAttributeChecker();
	}
	
	@Test
	public void abTest() {
		SmellyNode a = this.getSmellyNode(sharedAttribute, "a");
		SmellyNode b = this.getSmellyNode(sharedAttribute, "b");
		boolean isRelated = checker.isRelated(a, b);
		Assert.isTrue(!isRelated);
	}
	
	@Test
	public void acTest() {
		SmellyNode a = this.getSmellyNode(sharedAttribute, "a");
		SmellyNode c = this.getSmellyNode(sharedAttribute, "c");
		boolean isRelated = checker.isRelated(a, c);
		Assert.isTrue(!isRelated);
	}
	
	@Test
	public void adTest() {
		SmellyNode a = this.getSmellyNode(sharedAttribute, "a");
		SmellyNode d = this.getSmellyNode(sharedAttribute, "d");
		boolean isRelated = checker.isRelated(a, d);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void bcTest() {
		SmellyNode a = this.getSmellyNode(sharedAttribute, "b");
		SmellyNode d = this.getSmellyNode(sharedAttribute, "c");
		boolean isRelated = checker.isRelated(a, d);
		Assert.isTrue(isRelated);
	}
	
	@Test
	public void bdTest() {
		SmellyNode a = this.getSmellyNode(sharedAttribute, "b");
		SmellyNode d = this.getSmellyNode(sharedAttribute, "d");
		boolean isRelated = checker.isRelated(a, d);
		Assert.isTrue(!isRelated);
	}
	
	@Test
	public void aExternalDTest() {
		SmellyNode a = this.getSmellyNode(sharedAttribute, "d");
		SmellyNode d = this.getSmellyNode(sharedAttributeExternal, "a");
		boolean isRelated = checker.isRelated(a, d);
		Assert.isTrue(isRelated);
	}
}
