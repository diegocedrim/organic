package br.pucrio.opus.smells.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.pucrio.opus.smells.metrics.MetricName;
import br.pucrio.opus.smells.resources.ParenthoodRegistry;
import br.pucrio.opus.smells.resources.Resource;
import br.pucrio.opus.smells.resources.Type;

/**
 * A class declared as abstract having less than three children classes using its methods.
 * @author Diego Cedrim
 */
public class SpeculativeGenerality extends SmellDetector {
	
	@Override
	public List<Smell> detect(Resource resource) {
		ParenthoodRegistry registry = ParenthoodRegistry.getInstance();
		Integer childrenCount = registry.getChildrenCount((Type)resource);
		Integer isAbstract = resource.getMetricValue(MetricName.IsAbstract).intValue();
		if (isAbstract == 1 && childrenCount < 3) {
			Smell smell = super.createSmell(resource);
			smell.setReason("IS_ABSTRACT = 1, CHILDREN_COUNT = " + childrenCount);
			return Arrays.asList(smell);
		}
		return new ArrayList<>();
	}
	
	@Override
	protected SmellName getSmellName() {
		return SmellName.SpeculativeGenerality;
	}

}
