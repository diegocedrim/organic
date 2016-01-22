package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.pucrio.opus.smells.ast.visitors.CyclomaticComplexityVisitor;

public class CyclomaticComplexityCalculator implements MetricValueCalculator<MethodDeclaration> {
	
	public static final String NAME = "PublicFieldCount";

	@Override
	public Double getValue(MethodDeclaration target) {
		CyclomaticComplexityVisitor visitor = new CyclomaticComplexityVisitor();
		target.accept(visitor);
		return visitor.getCyclomaticComplexity().doubleValue();
	}

	@Override
	public String getMetricName() {
		return NAME;
	}

}
