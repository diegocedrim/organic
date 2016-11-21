package br.pucrio.opus.smells.metrics;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import br.pucrio.opus.smells.metrics.calculators.MetricValueCalculator;

public class AggregateMetricValues implements Observer {

	private static AggregateMetricValues singleton;

	private Map<MetricName, DescriptiveStatistics> aggregateValues;

	private Map<MetricName, Double> avgCache;

	private Map<MetricName, Double> firstQuartileCache;
	
	private Map<MetricName, Double> stdDevCache;

	static {
		singleton = new AggregateMetricValues();
	}

	private AggregateMetricValues() {
		this.reset();
	}

	public void reset() {
		this.aggregateValues = new HashMap<>();
		this.avgCache = new HashMap<>();
		this.firstQuartileCache = new HashMap<>();
		this.stdDevCache = new HashMap<>();
	}

	private void register(MetricName metricName, Double value) {
		if (value == null) {
			return;
		}
		DescriptiveStatistics stats = this.aggregateValues.get(metricName);
		if (stats == null) {
			stats = new DescriptiveStatistics();
			this.aggregateValues.put(metricName, stats);
		}
		this.avgCache.remove(metricName);
		this.firstQuartileCache.remove(metricName);
		this.stdDevCache.remove(metricName);
		stats.addValue(value);
	}

	public Double getAverageValue(MetricName name) {
		//check if the value is in the cache
		if (avgCache.containsKey(name)) {
			return avgCache.get(name);
		}

		//if no value was computed, return null
		DescriptiveStatistics stats = this.aggregateValues.get(name);
		if (stats == null) {
			return null;
		}

		//stores in the cache and returns
		Double avg =  stats.getMean();
		this.avgCache.put(name, avg);
		return avg;
	}

	public Double getFirstQuartileValue(MetricName name) {
		//check if the value is in the cache
		if (this.firstQuartileCache.containsKey(name)) {
			return this.firstQuartileCache.get(name);
		}

		//if no value was computed, return null
		DescriptiveStatistics stats = this.aggregateValues.get(name);
		if (stats == null) {
			return null;
		}

		//stores in the cache and returns
		Double firstQuartile = stats.getPercentile(25);
		this.avgCache.put(name, firstQuartile);
		return firstQuartile;
	}
	
	public Double getStandardDeviation(MetricName name) {
		//check if the value is in the cache
		if (this.stdDevCache.containsKey(name)) {
			return this.stdDevCache.get(name);
		}

		//if no value was computed, return null
		DescriptiveStatistics stats = this.aggregateValues.get(name);
		if (stats == null) {
			return null;
		}

		//stores in the cache and returns
		Double standardDeviation = stats.getStandardDeviation();
		this.stdDevCache.put(name, standardDeviation);
		return standardDeviation;
	}

	public static AggregateMetricValues getInstance() {
		return singleton;
	}

	@Override
	public void update(Observable obervable, Object value) {
		MetricValueCalculator calculator = (MetricValueCalculator)obervable;
		if (calculator.shouldComputeAggregate()) {
			this.register(calculator.getMetricName(), (Double)value);
		}
	}
}
