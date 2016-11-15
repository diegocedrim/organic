package br.pucrio.opus.smells.metrics;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.pucrio.opus.smells.metrics.calculators.ChangingClassesCalculator;
import br.pucrio.opus.smells.metrics.calculators.ChangingMethodsCalculator;
import br.pucrio.opus.smells.metrics.calculators.CouplingDispersionCalculator;
import br.pucrio.opus.smells.metrics.calculators.CouplingIntensityCalculator;
import br.pucrio.opus.smells.metrics.calculators.CyclomaticComplexityCalculator;
import br.pucrio.opus.smells.metrics.calculators.MaxCallChainCalculator;
import br.pucrio.opus.smells.metrics.calculators.MaxNestingCalculator;
import br.pucrio.opus.smells.metrics.calculators.MethodLOCCalculator;
import br.pucrio.opus.smells.metrics.calculators.MethodParameterCountCalculator;
import br.pucrio.opus.smells.metrics.calculators.NOAVCalculator;

public class MethodMetricValueCollector extends MetricValueCollector {

	public MethodMetricValueCollector(TypeDeclaration declaringClass) {
		addCalculator(new MethodLOCCalculator());
		addCalculator(new CyclomaticComplexityCalculator());
		addCalculator(new MethodParameterCountCalculator());
		addCalculator(new MaxCallChainCalculator());
		addCalculator(new CouplingDispersionCalculator());
		addCalculator(new CouplingIntensityCalculator());
		addCalculator(new NOAVCalculator());
		addCalculator(new ChangingClassesCalculator());
		addCalculator(new MaxNestingCalculator());
		addCalculator(new ChangingMethodsCalculator());
	}
	
	
}
