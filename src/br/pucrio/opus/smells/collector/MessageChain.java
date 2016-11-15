package br.pucrio.opus.smells.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.pucrio.opus.smells.metrics.MetricName;
import br.pucrio.opus.smells.resources.Resource;

/**
 * All chains of methods’ calls longer than three.
 * @author Diego Cedrim
 */
public class MessageChain extends SmellDetector {
	
	@Override
	public List<Smell> detect(Resource resource) {
		Double maxCallChain = resource.getMetricValue(MetricName.MaxCallChain);
		if (maxCallChain > 3) {
			StringBuilder builder = new StringBuilder();
			builder.append("MAX_CALL_CHAIN = " + maxCallChain);
			
			Smell smell = super.createSmell(resource);
			smell.setReason(builder.toString());
			return Arrays.asList(smell);
		}
		return new ArrayList<>();
	}
	
	@Override
	protected SmellName getSmellName() {
		return SmellName.MessageChain;
	}

}
