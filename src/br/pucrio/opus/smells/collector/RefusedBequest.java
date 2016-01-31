package br.pucrio.opus.smells.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.pucrio.opus.smells.metrics.MetricName;
import br.pucrio.opus.smells.resources.Resource;

/**
 * All classes overriding more than half of the methods inherited by a superclass
 * @author Diego Cedrim
 */
public class RefusedBequest extends SmellDetector {
	
	@Override
	public List<Smell> detect(Resource resource) {
		Double overrideRatio = resource.getMetricValue(MetricName.OverrideRatio);
		if (overrideRatio != null && overrideRatio > 0.5) {
			Smell smell = super.createSmell(resource);
			return Arrays.asList(smell);
		}
		return new ArrayList<>();
	}
	
	@Override
	protected SmellName getSmellName() {
		return SmellName.RefusedBequest;
	}

}
