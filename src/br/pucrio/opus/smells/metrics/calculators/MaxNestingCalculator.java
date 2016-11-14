package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.ast.visitors.MaximumNestingLevelVisitor;
import br.pucrio.opus.smells.metrics.MetricName;

/**
 * Computes the max nesting level of a method
 * @author Diego Cedrim
 */
public class MaxNestingCalculator extends MetricValueCalculator {

	@Override
	protected Double computeValue(ASTNode target) {
		MaximumNestingLevelVisitor visitor = new MaximumNestingLevelVisitor();
		target.accept(visitor);
		return visitor.getMNL();
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.MaxNesting;
	}

}
