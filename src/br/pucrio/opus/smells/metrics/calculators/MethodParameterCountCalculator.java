package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.pucrio.opus.smells.metrics.MetricName;

public class MethodParameterCountCalculator  implements MetricValueCalculator {
	
	@Override
	public Double getValue(ASTNode target) {
		MethodDeclaration declaration = (MethodDeclaration)target;
		return new Double(declaration.parameters().size());
	}

	@Override
	public String getMetricName() {
		return MetricName.ParameterCount.toString();
	}

}
