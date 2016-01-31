package br.pucrio.opus.smells.metrics;

public enum MetricName {
	LOC("LinesOfCode"),
	CC("CyclomaticComplexity"),
	IsAbstract,
	MaxCallChain,
	LocalityRatio,
	ParameterCount,
	OverrideRatio,
	PublicFieldCount,
	TCC("TightClassCohesion");
	
	private String label;
	
	private MetricName() {
		this.label = name();
	}
	
	private MetricName(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	@Override
	public String toString() {
		return label;
	}
	
}
