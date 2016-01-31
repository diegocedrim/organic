package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.ast.visitors.CyclomaticComplexityVisitor;
import br.pucrio.opus.smells.metrics.MetricName;

public class CyclomaticComplexityCalculator implements MetricValueCalculator {
	
	@Override
	public Double getValue(ASTNode target) {
		CyclomaticComplexityVisitor visitor = new CyclomaticComplexityVisitor();
		target.accept(visitor);
		return visitor.getCyclomaticComplexity().doubleValue();
	}

	@Override
	public String getMetricName() {
		return MetricName.CC.toString();
	}

}
