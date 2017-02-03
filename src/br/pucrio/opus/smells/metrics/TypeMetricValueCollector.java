package br.pucrio.opus.smells.metrics;

import br.pucrio.opus.smells.metrics.calculators.ClassLOCCalculator;
import br.pucrio.opus.smells.metrics.calculators.IsClassAbstract;
import br.pucrio.opus.smells.metrics.calculators.LCOM2Calculator;
import br.pucrio.opus.smells.metrics.calculators.LCOM3Calculator;
import br.pucrio.opus.smells.metrics.calculators.NOAMCalculator;
import br.pucrio.opus.smells.metrics.calculators.OverrideRatioCalculator;
import br.pucrio.opus.smells.metrics.calculators.PublicFieldCountCalculator;
import br.pucrio.opus.smells.metrics.calculators.TCCMetricValueCalculator;
import br.pucrio.opus.smells.metrics.calculators.WMCCalculator;
import br.pucrio.opus.smells.metrics.calculators.WOCCalculator;

public class TypeMetricValueCollector extends MetricValueCollector {

	public TypeMetricValueCollector() {
		addCalculator(new TCCMetricValueCalculator());
		addCalculator(new PublicFieldCountCalculator());
		addCalculator(new ClassLOCCalculator());
		addCalculator(new OverrideRatioCalculator());
		addCalculator(new IsClassAbstract());
		addCalculator(new NOAMCalculator());
		addCalculator(new WMCCalculator());
		addCalculator(new WOCCalculator());
		addCalculator(new LCOM2Calculator());
		addCalculator(new LCOM3Calculator());
	}
	
	
}
