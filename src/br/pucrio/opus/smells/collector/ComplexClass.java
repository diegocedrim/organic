package br.pucrio.opus.smells.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.pucrio.opus.smells.metrics.MetricName;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.Resource;
import br.pucrio.opus.smells.resources.Type;

/**
 * Complex class: A class having at least one method for which McCabe cyclomatic complexity is higher than 10.
 * @author Diego Cedrim
 */
public class ComplexClass  extends SmellDetector {
	
	@Override
	public List<Smell> detect(Resource resource) {
		Type type = (Type)resource;
		for (Method method : type.getMethods()) {
			Double cc = method.getMetricValue(MetricName.CC);
			if (cc != null && cc > 10) {
				StringBuilder builder = new StringBuilder();
				builder.append("CC = " + cc);
				
				Smell smell = super.createSmell(resource);
				smell.setReason(builder.toString());
				return Arrays.asList(smell);
			}
		}
		
		return new ArrayList<>();
	}
	
	@Override
	protected SmellName getSmellName() {
		return SmellName.ComplexClass;
	}

}