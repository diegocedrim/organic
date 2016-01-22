package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodParameterCountCalculator  implements MetricValueCalculator<MethodDeclaration> {
	
	public static final String NAME = "ParameterCount";

	@Override
	public Double getValue(MethodDeclaration target) {
		return new Double(target.parameters().size());
	}

	@Override
	public String getMetricName() {
		return NAME;
	}

}
