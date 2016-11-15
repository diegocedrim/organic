package br.pucrio.opus.smells.tests.smells;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.collector.GodClass;
import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.collector.SmellName;
import br.pucrio.opus.smells.metrics.AggregateMetricValues;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.GenericCollector;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class GodClassTest {
	
	@Before
	public void setup() {
		AggregateMetricValues aggr = AggregateMetricValues.getInstance();
		aggr.reset();
	}

	@Test
	public void ccTest() throws Exception {
		Type blobType = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/BlobClassSample.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(blobType);
		
		Type mlType = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/MethodLocality.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(mlType);
		
		GodClass smellDetector = new GodClass();
		List<Smell> smells = smellDetector.detect(blobType);
		Smell smell = smells.get(0);
		Assert.assertEquals(1, smells.size());
		Assert.assertEquals(SmellName.GodClass, smell.getName());
		
		smells = smellDetector.detect(mlType);
		Assert.assertEquals(0, smells.size());
	}
}
