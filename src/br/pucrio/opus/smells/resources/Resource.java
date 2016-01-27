package br.pucrio.opus.smells.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.collector.Smell;

public abstract class Resource<T extends ASTNode> {
	
	private transient SourceFile sourceFile;
	
	private Map<String, Double> metricsValues;
	
	private String fullyQualifiedName;
	
	private List<Smell> smells;
	
	private transient T node;
	
	public T getNode() {
		return node;
	}

	public Resource(SourceFile sourceFile, T node) {
		this.metricsValues = new HashMap<>();
		this.sourceFile = sourceFile;
		this.node = node;
		this.smells = new ArrayList<>();
	}
	
	public void addSmell(Smell smell) {
		this.smells.add(smell);
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
	
	public List<Smell> getSmells() {
		return smells;
	}
	
}
