package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.ast.visitors.PublicMethodCollector;
import br.pucrio.opus.smells.metrics.MetricName;

/**
 * The number of non-assessors public methods divided by the total number of public methods.
 * 
 * @author Diego Cedrim
 */
public class WOCCalculator extends MetricValueCalculator {
	
	@Override
	protected Double computeValue(ASTNode target) {
		NOAMCalculator noamCalculator = new NOAMCalculator();
		Double noam = noamCalculator.computeValue(target);
		Double publicMethods = getPublicMethodsCount(target).doubleValue();
		if (publicMethods == 0) {
			return 0d;
		}
		Double woc = (publicMethods - noam)/publicMethods;
		return woc;
	}
	
	private Integer getPublicMethodsCount(ASTNode target) {
		PublicMethodCollector methocCollector = new PublicMethodCollector();
		target.accept(methocCollector);
		return methocCollector.getNodesCollected().size();
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.WOC;
	}

}
