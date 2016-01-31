package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.ast.visitors.MaxCallChainVisitor;
import br.pucrio.opus.smells.metrics.MetricName;

public class MaxCallChainCalculator implements MetricValueCalculator {
	
	@Override
	public Double getValue(ASTNode target) {
		MaxCallChainVisitor visitor = new MaxCallChainVisitor();
		target.accept(visitor);
		return visitor.getMaxCallChain().doubleValue();
	}

	@Override
	public String getMetricName() {
		return MetricName.MaxCallChain.toString();
	}

}
