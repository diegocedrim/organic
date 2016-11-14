package br.pucrio.opus.smells.metrics.calculators;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.pucrio.opus.smells.ast.visitors.CyclomaticComplexityVisitor;
import br.pucrio.opus.smells.ast.visitors.MethodCollector;
import br.pucrio.opus.smells.metrics.MetricName;

/**
 * Computes the Weighted Method Count of a given class. This metric is defined
 * as the sum of Cyclomatic Complexity of all methods declared in the given class
 * @author Diego Cedrim
 */
public class WMCCalculator extends MetricValueCalculator {

	@Override
	protected Double computeValue(ASTNode target) {
		List<MethodDeclaration> methods = getMethods(target);
		
		Double wmc = 0d;
		for (MethodDeclaration methodDeclaration : methods) {
			CyclomaticComplexityVisitor ccVisitor = new CyclomaticComplexityVisitor();
			methodDeclaration.accept(ccVisitor);
			Double cc = ccVisitor.getCyclomaticComplexity().doubleValue();
			wmc += cc;
		}
		return wmc;
	}

	private List<MethodDeclaration> getMethods(ASTNode target) {
		MethodCollector methocCollector = new MethodCollector();
		target.accept(methocCollector);
		return methocCollector.getNodesCollected();
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.WMC;
	}
	
	@Override
	public boolean shouldComputeAggregate() {
		return true;
	}

}
