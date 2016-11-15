package br.pucrio.opus.smells.tests.smells;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.collector.BrainClass;
import br.pucrio.opus.smells.collector.BrainMethod;
import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.collector.SmellName;
import br.pucrio.opus.smells.metrics.AggregateMetricValues;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.GenericCollector;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class BrainClassTest {
	
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
	public void oneBrainMethodTest() throws Exception {
		Type blobType = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/BrainClassWithOneBrainMethod.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(blobType);
		
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/graph/A.java");
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/graph/B.java");
		for (int i = 0; i < 100; i++) {
			loadMetrics("test/br/pucrio/opus/smells/tests/dummy/EmptyClass.java");
		}
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/Noav.java");
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/Noav.java");
		
		for (Method method: blobType.getMethods()) {
			List<Smell> smells = new BrainMethod().detect(method);
			method.addAllSmells(smells);
		}
		
		BrainClass smellDetector = new BrainClass();
		List<Smell> smells = smellDetector.detect(blobType);
		Smell smell = smells.get(0);
		Assert.assertEquals(1, smells.size());
		Assert.assertEquals(SmellName.BrainClass, smell.getName());
	}
	
	@Test
	public void twoBrainMethodsTest() throws Exception {
		Type blobType = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/BrainClassWithTwoBrainMethods.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(blobType);
		
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/graph/A.java");
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/graph/B.java");
		for (int i = 0; i < 100; i++) {
			loadMetrics("test/br/pucrio/opus/smells/tests/dummy/EmptyClass.java");
		}
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/Noav.java");
		loadMetrics("test/br/pucrio/opus/smells/tests/dummy/Noav.java");
		
		for (Method method: blobType.getMethods()) {
			List<Smell> smells = new BrainMethod().detect(method);
			method.addAllSmells(smells);
		}
		
		BrainClass smellDetector = new BrainClass();
		List<Smell> smells = smellDetector.detect(blobType);
		Smell smell = smells.get(0);
		Assert.assertEquals(1, smells.size());
		Assert.assertEquals(SmellName.BrainClass, smell.getName());
	}
	
	@Test
	public void noBrainClassTest() throws Exception {
		Type blobType = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/BrainClassWithTwoBrainMethods.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(blobType);
		
		for (Method method: blobType.getMethods()) {
			List<Smell> smells = new BrainMethod().detect(method);
			method.addAllSmells(smells);
		}
		
		BrainClass smellDetector = new BrainClass();
		List<Smell> smells = smellDetector.detect(blobType);
		Assert.assertEquals(0, smells.size());
	}
}
