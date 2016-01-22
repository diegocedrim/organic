package br.pucrio.opus.smells.metrics;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.pucrio.opus.smells.metrics.calculators.ClassLOCCalculator;
import br.pucrio.opus.smells.metrics.calculators.IsClassAbstract;
import br.pucrio.opus.smells.metrics.calculators.OverrideRatioCalculator;
import br.pucrio.opus.smells.metrics.calculators.PublicFieldCalculator;
import br.pucrio.opus.smells.metrics.calculators.TCCMetricValueCalculator;

public class TypeMetricValueCollector extends MetricValueCollector<TypeDeclaration> {

	public TypeMetricValueCollector() {
		addCalculator(new TCCMetricValueCalculator());
		addCalculator(new PublicFieldCalculator());
		addCalculator(new ClassLOCCalculator());
		addCalculator(new OverrideRatioCalculator());
		addCalculator(new IsClassAbstract());
	}
	
	
}
