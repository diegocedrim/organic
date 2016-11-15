package br.pucrio.opus.smells.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.pucrio.opus.smells.metrics.AggregateMetricValues;
import br.pucrio.opus.smells.metrics.MetricName;
import br.pucrio.opus.smells.resources.Resource;

/**
 * All methods having LOCs higher than the average of the system.
 * @author Diego Cedrim
 */
public class LongMethod extends SmellDetector {
	
	@Override
	public List<Smell> detect(Resource resource) {
		AggregateMetricValues aggregate = AggregateMetricValues.getInstance();
		Double methodLoc = resource.getMetricValue(MetricName.MLOC);
		Double avgMLOC = aggregate.getAverageValue(MetricName.MLOC);
		if (methodLoc > avgMLOC && methodLoc > 30) {
			StringBuilder builder = new StringBuilder();
			builder.append("MLOC > " + avgMLOC);
			
			Smell smell = super.createSmell(resource);
			smell.setReason(builder.toString());
			return Arrays.asList(smell);
		}
		return new ArrayList<>();
	}
	
	@Override
	protected SmellName getSmellName() {
		return SmellName.LongMethod;
	}

}
