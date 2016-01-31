package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.pucrio.opus.smells.metrics.MetricName;

public class MethodParameterCountCalculator extends MetricValueCalculator {
	
	@Override
	protected Double computeValue(ASTNode target) {
		MethodDeclaration declaration = (MethodDeclaration)target;
		return new Double(declaration.parameters().size());
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.ParameterCount;
	}
	
	@Override
	public boolean shouldComputeAggregate() {
		return true;
	}

}
