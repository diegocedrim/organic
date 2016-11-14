package br.pucrio.opus.smells.tests.smells;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.collector.LazyClass;
import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.collector.SmellName;
import br.pucrio.opus.smells.metrics.AggregateMetricValues;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.GenericCollector;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class LazyClassTest {
	
	@Before
	public void setup() {
		AggregateMetricValues aggr = AggregateMetricValues.getInstance();
		aggr.reset();
	}
	
	private void loadMetrics(String clazz) throws Exception {
		Type type = TypeLoader.loadOne(new File(clazz));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
	}

	@Test
	public void ccTest() throws Exception {
		AggregateMetricValues aggr = AggregateMetricValues.getInstance();
		aggr.reset();
		
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/AnonymousClass.java");
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/CC.java");
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/MethodLocality.java");
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/MiscStructures.java");
		
		Type blobType = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/BlobClassSample.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(blobType);
		
		Type mlType = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/SuperDummy.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(mlType);
		
		LazyClass smellDetector = new LazyClass();
		List<Smell> smells = smellDetector.detect(blobType);
		Assert.assertEquals(0, smells.size());
		
		
		smells = smellDetector.detect(mlType);
		Smell smell = smells.get(0);
		Assert.assertEquals(1, smells.size());
		Assert.assertEquals(SmellName.LazyClass, smell.getName());
	}
}
