package br.pucrio.opus.smells.tests.util;

import br.pucrio.opus.smells.metrics.MethodMetricValueCollector;
import br.pucrio.opus.smells.metrics.TypeMetricValueCollector;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.Type;

public class GenericCollector {

	public static void collectTypeMetricValues(Type type) {
		TypeMetricValueCollector collector = new TypeMetricValueCollector();
		collector.collect(type);
	}
	
	public static void collectTypeAndMethodsMetricValues(Type type) {
		TypeMetricValueCollector collector = new TypeMetricValueCollector();
		collector.collect(type);
		for (Method method : type.getMethods()) {
			MethodMetricValueCollector mColl = new MethodMetricValueCollector(type.getNodeAsTypeDeclaration());
			mColl.collect(method);
		}
	}
}
