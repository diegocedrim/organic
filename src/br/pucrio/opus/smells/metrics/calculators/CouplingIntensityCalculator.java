package br.pucrio.opus.smells.metrics.calculators;

import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.pucrio.opus.smells.ast.visitors.DistinctMethodInvocationVisitor;
import br.pucrio.opus.smells.metrics.MetricName;

public class CouplingIntensityCalculator extends MetricValueCalculator {

	@Override
	protected Double computeValue(ASTNode target) {
		Map<ITypeBinding, Set<IMethodBinding>> calls = getMethodCalls(target);
		if (calls == null) {
			return null;
		}
		
		Double cint = 0d;
		for (Set<IMethodBinding> distinctCalls : calls.values() ) {
			cint += distinctCalls.size();
		}
		return cint;
	}

	private Map<ITypeBinding, Set<IMethodBinding>> getMethodCalls(ASTNode target) {
		MethodDeclaration method = (MethodDeclaration)target;
		IMethodBinding binding = method.resolveBinding();
		if (binding == null) {
			return null;
		}
		ITypeBinding clazz = binding.getDeclaringClass();
		DistinctMethodInvocationVisitor visitor = new DistinctMethodInvocationVisitor(clazz);
		target.accept(visitor);
		
		return visitor.getMethodsCalls();
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.CINT;
	}

}
