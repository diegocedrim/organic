package br.pucrio.opus.smells.tests.smells;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.collector.FeatureEnvy;
import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.collector.SmellName;
import br.pucrio.opus.smells.metrics.AggregateMetricValues;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.GenericCollector;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class FeatureEnvyTest {
	
	@Before
	public void setup() {
		AggregateMetricValues aggr = AggregateMetricValues.getInstance();
		aggr.reset();
	}

	@Test
	public void ccTest() throws Exception {
		Type type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/CC.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
		
		FeatureEnvy smellDetector = new FeatureEnvy();
		List<Smell> smells = smellDetector.detect(type.findMethodByName("cc11"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("cc1"));
		Assert.assertEquals(0, smells.size());
	}
	
	@Test
	public void fieldDeclarationTest() throws Exception {
		Type type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/FieldDeclaration.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
		
		FeatureEnvy smellDetector = new FeatureEnvy();
		List<Smell> smells = smellDetector.detect(type.findMethodByName("getTest1"));
		Assert.assertEquals(0, smells.size());
	}
	
	@Test
	public void methodLocalityTest() throws Exception {
		Type type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/MethodLocality.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
		
		FeatureEnvy smellDetector = new FeatureEnvy();
		List<Smell> smells = smellDetector.detect(type.findMethodByName("moreForeign"));
		Assert.assertEquals(1, smells.size());
		Assert.assertEquals(SmellName.FeatureEnvy, smells.get(0).getName());
		
		smells = smellDetector.detect(type.findMethodByName("localA"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("localB"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("localC"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("localD"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("superForeign"));
		Assert.assertEquals(0, smells.size());
	}
}
