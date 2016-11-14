package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.ast.visitors.PublicFieldCount;
import br.pucrio.opus.smells.metrics.MetricName;

public class PublicFieldCountCalculator extends MetricValueCalculator {
	
	@Override
	protected Double computeValue(ASTNode target) {
		PublicFieldCount visitor = new PublicFieldCount();
		target.accept(visitor);
		return visitor.getPublicFieldsCount().doubleValue();
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.PublicFieldCount;
	}

}
