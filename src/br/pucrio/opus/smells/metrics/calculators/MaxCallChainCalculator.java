package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.pucrio.opus.smells.ast.visitors.MaxCallChainVisitor;

public class MaxCallChainCalculator implements MetricValueCalculator<MethodDeclaration> {
	
	public static final String NAME = "MaxCallChain";

	@Override
	public Double getValue(MethodDeclaration target) {
		MaxCallChainVisitor visitor = new MaxCallChainVisitor();
		target.accept(visitor);
		return visitor.getMaxCallChain().doubleValue();
	}

	@Override
	public String getMetricName() {
		return NAME;
	}

}
