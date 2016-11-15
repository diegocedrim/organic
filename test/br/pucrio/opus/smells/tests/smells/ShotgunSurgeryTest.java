package br.pucrio.opus.smells.tests.smells;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.collector.ShotgunSurgery;
import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.collector.SmellName;
import br.pucrio.opus.smells.metrics.AggregateMetricValues;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.GenericCollector;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class ShotgunSurgeryTest {
	@Before
	public void setup() {
		AggregateMetricValues aggr = AggregateMetricValues.getInstance();
		aggr.reset();
	}

	@Test
	public void shotgunSurgeryExampleTest() throws Exception {
		Type type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/ShotgunSurgeryExample.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
		
		ShotgunSurgery smellDetector = new ShotgunSurgery();
		List<Smell> smells = smellDetector.detect(type.findMethodByName("ss"));
		Assert.assertEquals(1, smells.size());
		Assert.assertEquals(SmellName.ShotgunSurgery, smells.get(0).getName());
		
		smells = smellDetector.detect(type.findMethodByName("a"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("b"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("c"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("d"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("e"));
		Assert.assertEquals(0, smells.size());
	}
	
}
