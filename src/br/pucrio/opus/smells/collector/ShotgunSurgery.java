package br.pucrio.opus.smells.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.pucrio.opus.smells.metrics.MetricName;
import br.pucrio.opus.smells.metrics.Thresholds;
import br.pucrio.opus.smells.resources.Resource;

/**
 * Shotgun Surgery resembles Divergent Change but is actually the opposite smell. 
 * Divergent Change is when many changes are made to a single class. Shotgun Surgery 
 * refers to when a single change is made to multiple classes simultaneously.
 * 
 * @author Diego Cedrim
 */
public class ShotgunSurgery extends SmellDetector {
	
	@Override
	public List<Smell> detect(Resource resource) {
		Double cc = resource.getMetricValue(MetricName.CC);
		Double cm = resource.getMetricValue(MetricName.ChangingMethods);
		if (cc == null || cm == null) {
			return new ArrayList<>();
		}
		
		if (cm > Thresholds.SHORT_MEMORY_CAP && cc > Thresholds.MANY) {
			StringBuilder builder = new StringBuilder();
			builder.append("CM > " + Thresholds.SHORT_MEMORY_CAP);
			builder.append(", CC > " + Thresholds.MANY);
			
			Smell smell = super.createSmell(resource);
			smell.setReason(builder.toString());
			return Arrays.asList(smell);
		}
		return new ArrayList<>();
	}
	
	@Override
	protected SmellName getSmellName() {
		return SmellName.ShotgunSurgery;
	}

}
