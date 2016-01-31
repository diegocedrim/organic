package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.ast.visitors.PublicFieldCount;
import br.pucrio.opus.smells.metrics.MetricName;

public class PublicFieldCalculator implements MetricValueCalculator {
	
	@Override
	public Double getValue(ASTNode target) {
		PublicFieldCount visitor = new PublicFieldCount();
		target.accept(visitor);
		return visitor.getPublicFieldsCount().doubleValue();
	}

	@Override
	public String getMetricName() {
		return MetricName.PublicFieldCount.toString();
	}

}
