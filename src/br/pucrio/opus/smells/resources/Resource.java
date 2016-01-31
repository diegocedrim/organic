package br.pucrio.opus.smells.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.metrics.MetricName;

public abstract class Resource {
	
	private transient SourceFile sourceFile;
	
	private Map<String, Double> metricsValues;
	
	private String fullyQualifiedName;
	
	private List<Smell> smells;
	
	private transient ASTNode node;
	
	public ASTNode getNode() {
		return node;
	}
	
	/**
	 * Line in the source file where node starts
	 * @return line where node starts
	 */
	public int getStartLineNumber() {
		CompilationUnit compUnit = sourceFile.getCompilationUnit();
		return compUnit.getLineNumber(node.getStartPosition());
	}
	
	public int getEndLineNumber() {
		CompilationUnit compUnit = sourceFile.getCompilationUnit();
		return compUnit.getLineNumber(node.getStartPosition() + node.getLength());
	}

	public Resource(SourceFile sourceFile, ASTNode node) {
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

	public Double getMetricValue(MetricName metricName) {
		return this.metricsValues.get(metricName.getLabel());
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
