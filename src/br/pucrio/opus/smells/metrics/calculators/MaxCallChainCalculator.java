package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.ast.visitors.MaxCallChainVisitor;
import br.pucrio.opus.smells.metrics.MetricName;

public class MaxCallChainCalculator extends MetricValueCalculator {
	
	@Override
	protected Double computeValue(ASTNode target) {
		MaxCallChainVisitor visitor = new MaxCallChainVisitor();
		target.accept(visitor);
		return visitor.getMaxCallChain().doubleValue();
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.MaxCallChain;
	}

}
