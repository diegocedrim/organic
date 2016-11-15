package br.pucrio.opus.smells.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.pucrio.opus.smells.metrics.MetricName;
import br.pucrio.opus.smells.metrics.Thresholds;
import br.pucrio.opus.smells.resources.Resource;

/**
 * Dispersed Coupling is the case of an operation which is excessively 
 * tied to many other operations in the system.
 * 
 * @author Diego Cedrim
 */
public class DispersedCoupling extends SmellDetector {
	
	@Override
	public List<Smell> detect(Resource resource) {
		Double cint = resource.getMetricValue(MetricName.CINT);
		Double cdisp = resource.getMetricValue(MetricName.CDISP);
		Double maxNesting = resource.getMetricValue(MetricName.MaxNesting);
		if (cint > Thresholds.SHORT_MEMORY_CAP && cdisp >= Thresholds.HALF && maxNesting > Thresholds.SHALLOW) {
			StringBuilder builder = new StringBuilder();
			builder.append("CINT > " + Thresholds.SHORT_MEMORY_CAP);
			builder.append(", CDISP > " + Thresholds.HALF);
			builder.append(", CC > " + Thresholds.SHALLOW);
			
			Smell smell = super.createSmell(resource);
			smell.setReason(builder.toString());
			return Arrays.asList(smell);
		}
		return new ArrayList<>();
	}
	
	@Override
	protected SmellName getSmellName() {
		return SmellName.DispersedCoupling;
	}

}
