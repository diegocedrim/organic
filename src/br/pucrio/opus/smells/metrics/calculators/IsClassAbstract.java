package br.pucrio.opus.smells.metrics.calculators;

import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.dom.TypeDeclaration;

public class IsClassAbstract implements MetricValueCalculator<TypeDeclaration> {
	
	public static final String NAME = "IsAbstract";
	

	@Override
	public Double getValue(TypeDeclaration target) {
		if (Modifier.isAbstract(target.getModifiers())){
			return 1d;
		} else {
			return 0d;
		}
	}

	@Override
	public String getMetricName() {
		return NAME;
	}

}
