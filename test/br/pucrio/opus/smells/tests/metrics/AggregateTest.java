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
		
		Type type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/CC.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
		
		type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/MethodLocality.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
		
		Assert.assertEquals(new Double(457.0), aggr.getAverageValue(MetricName.CLOC));
		Assert.assertEquals(39, aggr.getAverageValue(MetricName.MLOC).intValue());
		Assert.assertEquals(new Double(0.4347826086956521), aggr.getAverageValue(MetricName.ParameterCount));
		Assert.assertEquals(new Double(0.14423076923076922), aggr.getAverageValue(MetricName.TCC));
	}
}
