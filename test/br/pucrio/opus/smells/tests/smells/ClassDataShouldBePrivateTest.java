package br.pucrio.opus.smells.tests.smells;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.collector.ClassDataShouldBePrivate;
import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.collector.SmellName;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class ClassDataShouldBePrivateTest {

	@Test
	public void ccTest() throws Exception {
		Type type = TypeLoader.loadOneWithMetrics(new File("test/br/pucrio/opus/smells/tests/dummy/CC.java"));
		ClassDataShouldBePrivate smellDetector = new ClassDataShouldBePrivate();
		List<Smell> smells = smellDetector.detect(type);
		Smell smell = smells.get(0);
		Assert.assertEquals(1, smells.size());
		Assert.assertEquals(SmellName.ClassDataShouldBePrivate.toString(), smell.getName());
	}
	
	@Test
	public void fieldDeclarationTest() throws Exception {
		Type type = TypeLoader.loadOneWithMetrics(new File("test/br/pucrio/opus/smells/tests/dummy/FieldDeclaration.java"));
		ClassDataShouldBePrivate smellDetector = new ClassDataShouldBePrivate();
		List<Smell> smells = smellDetector.detect(type);
		Smell smell = smells.get(0);
		Assert.assertEquals(1, smells.size());
		Assert.assertEquals(SmellName.ClassDataShouldBePrivate.toString(), smell.getName());
	}
	
	@Test
	public void methodLocalityTest() throws Exception {
		Type type = TypeLoader.loadOneWithMetrics(new File("test/br/pucrio/opus/smells/tests/dummy/MethodLocality.java"));
		ClassDataShouldBePrivate smellDetector = new ClassDataShouldBePrivate();
		List<Smell> smells = smellDetector.detect(type);
		Assert.assertEquals(0, smells.size());
	}
}
