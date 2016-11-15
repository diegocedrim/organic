package br.pucrio.opus.smells.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.pucrio.opus.smells.metrics.MetricName;
import br.pucrio.opus.smells.metrics.Thresholds;
import br.pucrio.opus.smells.resources.Resource;

/**
 * A data class refers to a class that contains only fields and crude methods 
 * for accessing them (getters and setters). These are simply containers for data 
 * used by other classes. These classes do not contain any additional functionality 
 * and cannot independently operate on the data that they own.
 * @author Diego Cedrim
 */
public class DataClass extends SmellDetector {
	
	@Override
	public List<Smell> detect(Resource resource) {
		Double highWmc = Thresholds.getHighThreshold(MetricName.WMC);
		Double veryHighWmc = Thresholds.getVeryHighThreshold(MetricName.WMC);
		
		Double woc = resource.getMetricValue(MetricName.WOC);
		Double nopa = resource.getMetricValue(MetricName.PublicFieldCount);
		Double noam = resource.getMetricValue(MetricName.NOAM);
		Double wmc = resource.getMetricValue(MetricName.WMC);
		
		boolean moreFewPublicComplexityNotHigh = (nopa + noam) > Thresholds.FEW && wmc < highWmc;
		boolean manyPublicComplexityNotVeryHigh =  (nopa + noam) > Thresholds.MANY && wmc < veryHighWmc;
		boolean dataRatherThanServices = woc < Thresholds.ONE_THIRD;

		
		if ((moreFewPublicComplexityNotHigh || manyPublicComplexityNotVeryHigh) && dataRatherThanServices) {
			StringBuilder builder = new StringBuilder();
			builder.append("WOC = " + woc);
			builder.append(", NOPA = " + nopa);
			builder.append(", NOAM = " + noam);
			builder.append(", WMC = " + wmc);
			
			Smell smell = super.createSmell(resource);
			smell.setReason(builder.toString());
			return Arrays.asList(smell);
		}
		return new ArrayList<>();
	}
	
	@Override
	protected SmellName getSmellName() {
		return SmellName.DataClass;
	}

}
