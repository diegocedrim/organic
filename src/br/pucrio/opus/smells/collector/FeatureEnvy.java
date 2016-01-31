package br.pucrio.opus.smells.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.pucrio.opus.smells.metrics.MetricName;
import br.pucrio.opus.smells.resources.Resource;

/**
 * Feature envy: All methods having more calls with another class than the one they are implemented.
 * @author Diego Cedrim
 */
public class FeatureEnvy extends SmellDetector {
	
	@Override
	public List<Smell> detect(Resource resource) {
		Double localityRatio = resource.getMetricValue(MetricName.LocalityRatio);
		if (localityRatio != null && localityRatio < 0.5) {
			Smell smell = super.createSmell(resource);
			return Arrays.asList(smell);
		}
		return new ArrayList<>();
	}
	
	@Override
	protected SmellName getSmellName() {
		return SmellName.FeatureEnvy;
	}

}
