package br.pucrio.opus.smells.metrics;

public class Thresholds {
	
	public static final Double ONE_QUARTER = 0.25;
	
	public static final Double ONE_THIRD = 0.33;
	
	public static final Double HALF = 0.5;
	
	public static final Double TWO_THIRDS = 0.66;
	
	public static final Double THREE_QUARTERS = 0.75;
	
	public static final Double NONE = 0.0;
	
	public static final Double SHALLOW = 1.0;
	
	public static final Double TWO = 2.0;
	
	public static final Double THREE = 3.0;
	
	public static final Double FEW = 4.0;
	
	public static final Double SEVERAL = 5.0;
	
	public static final Double MANY = 6.0;
	
	public static final Double SHORT_MEMORY_CAP = 8.0;

	public static Double getLowThreshold(MetricName metric) {
		AggregateMetricValues aggregate = AggregateMetricValues.getInstance();
		return aggregate.getAverageValue(metric) - aggregate.getStandardDeviation(metric);
	}
	
	public static Double getHighThreshold(MetricName metric) {
		AggregateMetricValues aggregate = AggregateMetricValues.getInstance();
		return aggregate.getAverageValue(metric) + aggregate.getStandardDeviation(metric);
	}

	public static Double getVeryHighThreshold(MetricName metric) {
		return getHighThreshold(metric) * 1.5;
	}
	
}
