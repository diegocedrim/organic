package br.pucrio.opus.smells.resources;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;

public abstract class Resource<T extends ASTNode> {
	
	private SourceFile sourceFile;
	
	private Map<String, Double> metricsValues;
	
	private String fullyQualifiedName;
	
	private T node;
	
	public T getNode() {
		return node;
	}

	public Resource(SourceFile sourceFile, T node) {
		this.metricsValues = new HashMap<>();
		this.sourceFile = sourceFile;
		this.node = node;
	}
	
	public void addMetricValue(String metricName, Double value) {
		this.metricsValues.put(metricName, value);
	}

	public SourceFile getSourceFile() {
		return sourceFile;
	}

	protected void setSourceFile(SourceFile sourceFile) {
		this.sourceFile = sourceFile;
	}

	public Map<String, Double> getMetricsValues() {
		return metricsValues;
	}

	protected void setMetricsValues(Map<String, Double> metricsValues) {
		this.metricsValues = metricsValues;
	}

	public String getFullyQualifiedName() {
		return fullyQualifiedName;
	}

	protected void setFullyQualifiedName(String fullyQualifiedName) {
		this.fullyQualifiedName = fullyQualifiedName;
	}
	
}
