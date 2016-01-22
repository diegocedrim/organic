package br.pucrio.opus.smells.metrics.calculators;

public interface MetricValueCalculator<T> {
	
	Double getValue(T target);
	
	/**
	 * Metric's name. It MUST be unique for each subclass
	 * @return metric's name 
	 */
	String getMetricName();
}
