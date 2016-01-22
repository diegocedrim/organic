package br.pucrio.opus.smells.metrics.calculators;

public interface MetricValueCalculator<T> {
	Double getValue(T target);
}
