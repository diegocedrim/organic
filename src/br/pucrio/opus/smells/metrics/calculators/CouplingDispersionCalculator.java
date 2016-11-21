package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.metrics.MetricName;

public class CouplingDispersionCalculator extends CouplingIntensityCalculator {

	@Override
	protected Double computeValue(ASTNode target) {
		Double cint = super.computeValue(target);
		if (cint == null || cint == 0) {
			return 0d;
		}
		Double differentClassses = new Double(super.methodCalls.keySet().size());
		return differentClassses/cint;
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.CDISP;
	}

}
