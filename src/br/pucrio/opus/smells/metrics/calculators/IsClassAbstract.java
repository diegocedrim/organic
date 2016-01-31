package br.pucrio.opus.smells.metrics.calculators;

import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.pucrio.opus.smells.metrics.MetricName;

public class IsClassAbstract implements MetricValueCalculator {
	
	@Override
	public Double getValue(ASTNode target) {
		TypeDeclaration type = (TypeDeclaration)target;
		if (Modifier.isAbstract(type.getModifiers())){
			return 1d;
		} else {
			return 0d;
		}
	}

	@Override
	public String getMetricName() {
		return MetricName.IsAbstract.toString();
	}

}
