package br.pucrio.opus.smells.metrics;

import br.pucrio.opus.smells.metrics.calculators.ClassLOCCalculator;
import br.pucrio.opus.smells.metrics.calculators.IsClassAbstract;
import br.pucrio.opus.smells.metrics.calculators.MaxNestingCalculator;
import br.pucrio.opus.smells.metrics.calculators.NOAMCalculator;
import br.pucrio.opus.smells.metrics.calculators.OverrideRatioCalculator;
import br.pucrio.opus.smells.metrics.calculators.PublicFieldCountCalculator;
import br.pucrio.opus.smells.metrics.calculators.TCCMetricValueCalculator;
import br.pucrio.opus.smells.metrics.calculators.WMCCalculator;

public class TypeMetricValueCollector extends MetricValueCollector {

	public TypeMetricValueCollector() {
		addCalculator(new TCCMetricValueCalculator());
		addCalculator(new PublicFieldCountCalculator());
		addCalculator(new ClassLOCCalculator());
		addCalculator(new OverrideRatioCalculator());
		addCalculator(new IsClassAbstract());
		addCalculator(new MaxNestingCalculator());
		addCalculator(new NOAMCalculator());
		addCalculator(new WMCCalculator());
	}
	
	
}
