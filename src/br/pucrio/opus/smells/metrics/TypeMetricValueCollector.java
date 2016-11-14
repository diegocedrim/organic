package br.pucrio.opus.smells.metrics;

import br.pucrio.opus.smells.metrics.calculators.ClassLOCCalculator;
import br.pucrio.opus.smells.metrics.calculators.IsClassAbstract;
import br.pucrio.opus.smells.metrics.calculators.OverrideRatioCalculator;
import br.pucrio.opus.smells.metrics.calculators.PublicFieldCountCalculator;
import br.pucrio.opus.smells.metrics.calculators.TCCMetricValueCalculator;

public class TypeMetricValueCollector extends MetricValueCollector {

	public TypeMetricValueCollector() {
		addCalculator(new TCCMetricValueCalculator());
		addCalculator(new PublicFieldCountCalculator());
		addCalculator(new ClassLOCCalculator());
		addCalculator(new OverrideRatioCalculator());
		addCalculator(new IsClassAbstract());
	}
	
	
}
