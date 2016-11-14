package br.pucrio.opus.smells.metrics.calculators;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

import br.pucrio.opus.smells.ast.visitors.MethodCollector;
import br.pucrio.opus.smells.metrics.MetricName;

/**
 * Computes how many assessors methods (getters and setters) exist in a given class
 * @author Diego Cedrim
 */
public class NOAMCalculator extends MetricValueCalculator {
	
	private static final String GET = "get";
	
	private static final String SET = "set"; 

	@Override
	protected Double computeValue(ASTNode target) {
		List<MethodDeclaration> methods = getMethods(target);
		
		Double noam = 0d;
		for (MethodDeclaration methodDeclaration : methods) {
			String name = methodDeclaration.getName().toString();
			if ( (name.startsWith(GET) || name.startsWith(SET)) 
					&& this.isPublicAndNonStatic(methodDeclaration)) {
				noam++;
			}
		}
		return noam;
	}
	
	private boolean isPublicAndNonStatic(MethodDeclaration method) {
		int modifiers = method.getModifiers();
		return Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers);
	}
	
	private List<MethodDeclaration> getMethods(ASTNode target) {
		MethodCollector methocCollector = new MethodCollector();
		target.accept(methocCollector);
		return methocCollector.getNodesCollected();
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.NOAM;
	}

}
