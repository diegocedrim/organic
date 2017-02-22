package br.pucrio.opus.smells.metrics.calculators;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.metrics.MetricName;

/**
 * Class to calculate the LCOM2 ‘lack of cohesion of methods’ metrics.
 * 
 * The equation to calculate the LCOM2 is:
 * 		m = #declaredMethods(C)
 * 		a = #declaredAttributes(C)
 * 		m(A) = # of methods in C that reference attribute A
 * 		s = sum(m(A)) for A in declaredAttributes(C)
 * 		LCOM2(C) = 1 - s/(m * a)
 * 
 * Observation = timesAccessedAttributes comprises the s
 * 
 * The metric definition as well as its implementation are available at:
 * http://www.aivosto.com/project/help/pm-oo-cohesion.html and
 * http://www.cs.sjsu.edu/~pearce/cs251b/DesignMetrics.htm
 * 
 * @author leonardo
 */
public class LCOM2Calculator extends BaseLCOM {
	
	public LCOM2Calculator(){
		super();
	}

	@Override
	protected Double computeValue(ASTNode target) {
		Double lcom = super.computeValue(target);	//call the super first in order to initialize the attributes
		if (lcom == null) {
			return null;
		}
		
		if (lcom.equals(0.0)){
			return lcom;
		}
		
		Double a = (double) getNumberOfAttributes();
		Double m = (double) getNumberOfMethods();
		Double sum = (double) getTimesAccessedAttributes();
		
		lcom = 1 - (sum) / (m * a);
		
		return lcom;
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.LCOM2;
	}	

}
