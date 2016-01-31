package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.ast.visitors.LinesOfCodeVisitor;
import br.pucrio.opus.smells.metrics.MetricName;

public class ClassLOCCalculator  implements MetricValueCalculator {
	
	@Override
	public Double getValue(ASTNode target) {
		LinesOfCodeVisitor visitor = new LinesOfCodeVisitor();
		target.accept(visitor);
		return visitor.getLoc().doubleValue();
	}

	@Override
	public String getMetricName() {
		return MetricName.LOC.toString();
	}

}
