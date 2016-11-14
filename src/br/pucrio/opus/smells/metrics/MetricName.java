package br.pucrio.opus.smells.metrics;

public enum MetricName {
	CLOC("ClassLinesOfCode"),
	MLOC("MethodLinesOfCode"),
	CC("CyclomaticComplexity"),
	IsAbstract,
	MaxCallChain,
	ParameterCount,
	OverrideRatio,
	PublicFieldCount,
	TCC("TightClassCohesion"),
	MaxNesting;
	
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
