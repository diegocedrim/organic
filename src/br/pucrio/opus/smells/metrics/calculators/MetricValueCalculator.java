package br.pucrio.opus.smells.metrics.calculators;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.metrics.AggregateMetricValues;
import br.pucrio.opus.smells.metrics.MetricName;

public abstract class MetricValueCalculator extends Observable {
	
	public MetricValueCalculator() {
		Observer observer = AggregateMetricValues.getInstance();
		this.addObserver(observer);
	}
	
	public Double getValue(ASTNode target) {
		Double value = this.computeValue(target);
		setChanged();
		notifyObservers(value);
		return value;
	}
	
	protected abstract Double computeValue(ASTNode target);
	
	/**
	 * Metric's name. It MUST be unique for each subclass
	 * @return metric's name 
	 */
	public abstract MetricName getMetricName();
	
	/**
	 * Indicates if the observer AggregateMetricValues should compute
	 * aggregate values for this specific metric (avg, for instance)
	 * @return true if the observer should compute aggregate value or false (default), otherwise
	 */
	public boolean shouldComputeAggregate() {
		return false;
	}
}
