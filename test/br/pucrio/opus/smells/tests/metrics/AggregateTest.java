package br.pucrio.opus.smells.tests.metrics;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.metrics.AggregateMetricValues;
import br.pucrio.opus.smells.metrics.MetricName;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.GenericCollector;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class AggregateTest {

	@Test
	public void ccTest() throws Exception {
		AggregateMetricValues aggr = AggregateMetricValues.getInstance();
		aggr.reset();
		
		Type type = TypeLoader.loadOneWithMetrics(new File("test/br/pucrio/opus/smells/tests/dummy/CC.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
		
		type = TypeLoader.loadOneWithMetrics(new File("test/br/pucrio/opus/smells/tests/dummy/MethodLocality.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
		
		Assert.assertEquals(new Double(66.0), aggr.getAverageValue(MetricName.CLOC));
		Assert.assertEquals(new Double(66.0), aggr.getAverageValue(MetricName.CLOC));
		Assert.assertEquals(new Double(6.4), aggr.getAverageValue(MetricName.MLOC));
		Assert.assertEquals(new Double(0.3), aggr.getAverageValue(MetricName.ParameterCount));
		Assert.assertEquals(new Double(0.05357142857142857), aggr.getAverageValue(MetricName.TCC));
	}
}
