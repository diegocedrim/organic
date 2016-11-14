package br.pucrio.opus.smells.metrics.calculators;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.pucrio.opus.smells.ast.visitors.CyclomaticComplexityVisitor;
import br.pucrio.opus.smells.ast.visitors.MethodCollector;
import br.pucrio.opus.smells.metrics.MetricName;

public class MaxNestingCalculator extends MetricValueCalculator {

	@Override
	protected Double computeValue(ASTNode target) {
		List<MethodDeclaration> methods = getMethods(target);
		if (methods.isEmpty()) {
			return 0d;
		}
		
		Double maxNesting = Double.MIN_VALUE;
		for (MethodDeclaration methodDeclaration : methods) {
			CyclomaticComplexityVisitor ccVisitor = new CyclomaticComplexityVisitor();
			methodDeclaration.accept(ccVisitor);
			Double cc = ccVisitor.getCyclomaticComplexity().doubleValue();
			if (cc > maxNesting) {
				maxNesting = cc;
			}
		}
		return maxNesting;
	}

	private List<MethodDeclaration> getMethods(ASTNode target) {
		MethodCollector methocCollector = new MethodCollector();
		target.accept(methocCollector);
		return methocCollector.getNodesCollected();
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.MaxNesting;
	}

}
