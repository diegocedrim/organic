package br.pucrio.opus.smells.tests.smells;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.collector.DataClass;
import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.collector.SmellName;
import br.pucrio.opus.smells.metrics.AggregateMetricValues;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.GenericCollector;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class DataClassTest {
	
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
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/NotDataClass.java");
		
		Type type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/DataClass1.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
		DataClass smellDetector = new DataClass();
		List<Smell> smells = smellDetector.detect(type);
		Smell smell = smells.get(0);
		Assert.assertEquals(1, smells.size());
		Assert.assertEquals(SmellName.DataClass, smell.getName());
	}
	
}
