package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.pucrio.opus.smells.ast.visitors.LinesOfCodeVisitor;

public class ClassLOCCalculator  implements MetricValueCalculator<TypeDeclaration> {
	
	public static final String NAME = "LOC";

	@Override
	public Double getValue(TypeDeclaration target) {
		LinesOfCodeVisitor visitor = new LinesOfCodeVisitor();
		target.accept(visitor);
		return visitor.getLoc().doubleValue();
	}

	@Override
	public String getMetricName() {
		return NAME;
	}

}
