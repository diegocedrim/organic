package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.pucrio.opus.smells.ast.visitors.PublicFieldCount;

public class PublicFieldCalculator implements MetricValueCalculator<TypeDeclaration> {
	
	public static final String NAME = "PublicFieldCount";

	@Override
	public Double getValue(TypeDeclaration target) {
		PublicFieldCount visitor = new PublicFieldCount();
		target.accept(visitor);
		return visitor.getPublicFieldsCount().doubleValue();
	}

	@Override
	public String getMetricName() {
		return NAME;
	}

}
