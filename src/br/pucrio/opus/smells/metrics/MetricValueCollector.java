package br.pucrio.opus.smells.metrics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.metrics.calculators.MetricValueCalculator;
import br.pucrio.opus.smells.resources.Resource;

public abstract class MetricValueCollector<T extends ASTNode> {
	
	private List<MetricValueCalculator<T>> calculators;
	
	public MetricValueCollector() {
		this.calculators = new ArrayList<>();
	}
	
	public void addCalculator(MetricValueCalculator<T> calculator) {
		this.calculators.add(calculator);
	}
	
	public void calculate(Resource<T> resource) {
		for (MetricValueCalculator<T> calculator : this.calculators) {
			T node = resource.getNode();
			Double value = calculator.getValue(node);
			resource.addMetricValue(calculator.getMetricName(), value);
		}
	}
}
