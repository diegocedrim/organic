package br.pucrio.opus.smells.metrics;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.pucrio.opus.smells.metrics.calculators.CyclomaticComplexityCalculator;
import br.pucrio.opus.smells.metrics.calculators.MethodLOCCalculator;

public class MethodMetricValueCollector extends MetricValueCollector<MethodDeclaration> {

	public MethodMetricValueCollector() {
		addCalculator(new MethodLOCCalculator());
		addCalculator(new CyclomaticComplexityCalculator());
		//TODO All methods having more calls with another class than the one they are implemented.
		//TODO All methods having a number of parameters higher than the average of the system.
		//TODO All chains of methods’ calls longer than three.
//		addCalculator(new ClassLOCCalculator());
//		addCalculator(new OverrideRatioCalculator());
//		addCalculator(new IsClassAbstract());
	}
	
	
}
