package br.pucrio.opus.smells.tests.smells;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.collector.SmellName;
import br.pucrio.opus.smells.collector.SpeculativeGenerality;
import br.pucrio.opus.smells.resources.JavaFilesFinder;
import br.pucrio.opus.smells.resources.ParenthoodRegistry;
import br.pucrio.opus.smells.resources.SourceFilesLoader;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.GenericCollector;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class SpeculativeGeneralityTest {
	
	@Before
	public void setup() throws IOException {
		ParenthoodRegistry.getInstance().reset();
		JavaFilesFinder sourceLoader = new JavaFilesFinder("test/br/pucrio/opus/smells/tests/dummy");
		new SourceFilesLoader(sourceLoader).getLoadedSourceFiles();
	}
	
	@Test
	public void ccTest() throws Exception {
		Type type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/CC.java"));
		GenericCollector.collectTypeMetricValues(type);
		SpeculativeGenerality smellDetector = new SpeculativeGenerality();
		List<Smell> smells = smellDetector.detect(type);
		Assert.assertEquals(0, smells.size());
	}
	
	@Test
	public void superDummyTest() throws Exception {
		Type type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/SuperDummy.java"));
		GenericCollector.collectTypeMetricValues(type);
		SpeculativeGenerality smellDetector = new SpeculativeGenerality();
		List<Smell> smells = smellDetector.detect(type);
		Assert.assertEquals(0, smells.size());
	}
	
	@Test
	public void speculativeGeneralitySampleTest() throws Exception {
		Type type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/SpeculativeGeneralitySample.java"));
		GenericCollector.collectTypeMetricValues(type);
		SpeculativeGenerality smellDetector = new SpeculativeGenerality();
		List<Smell> smells = smellDetector.detect(type);
		Smell smell = smells.get(0);
		Assert.assertEquals(1, smells.size());
		Assert.assertEquals(SmellName.SpeculativeGenerality, smell.getName());
	}
}
