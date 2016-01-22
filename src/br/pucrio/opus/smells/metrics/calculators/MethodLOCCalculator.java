package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.pucrio.opus.smells.ast.visitors.LinesOfCodeVisitor;

public class MethodLOCCalculator  implements MetricValueCalculator<MethodDeclaration> {
	
	public static final String NAME = "LOC";

	@Override
	public Double getValue(MethodDeclaration target) {
		LinesOfCodeVisitor visitor = new LinesOfCodeVisitor();
		target.accept(visitor);
		return visitor.getLoc().doubleValue();
	}

	@Override
	public String getMetricName() {
		return NAME;
	}

}
