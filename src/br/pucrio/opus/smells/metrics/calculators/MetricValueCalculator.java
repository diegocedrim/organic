package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;

public interface MetricValueCalculator {
	
	Double getValue(ASTNode target);
	
	/**
	 * Metric's name. It MUST be unique for each subclass
	 * @return metric's name 
	 */
	String getMetricName();
}
