package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.pucrio.opus.smells.ast.visitors.MethodCallLocalityVisitor;

/**
 * Computes the LocalityRatio of the method. This metric computes how local the method is, i.e.,
 * if it calls more local than foreign methods. If the value of this metric is 0, then
 * the method only calls foreign methods. If the value is 1, than the method only calls
 * local methods. We can use this value in order to discover Feature Envy methods. 
 * 
 * @see MethodCallLocalityVisitor
 * @author Diego Cedrim
 */
public class MethodLocalityRatioCalculator implements MetricValueCalculator<MethodDeclaration> {
	
	public static final String NAME = "LocalityRatio";
	
	private TypeDeclaration declaringClass;
	
	
	public MethodLocalityRatioCalculator(TypeDeclaration declaringClass) {
		this.declaringClass = declaringClass;
	}

	@Override
	public Double getValue(MethodDeclaration target) {
		MethodCallLocalityVisitor visitor = new MethodCallLocalityVisitor(declaringClass);
		target.accept(visitor);
		Double localCalls = visitor.getLocalMethodsCallCount().doubleValue();
		Double foreignCalls = visitor.getForeignMethodsCallCount().doubleValue();
		return localCalls / (localCalls + foreignCalls);
	}

	@Override
	public String getMetricName() {
		return null;
	}

}
