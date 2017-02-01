package br.pucrio.opus.smells.metrics.calculators;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import br.pucrio.opus.smells.ast.visitors.FieldAccessCollector;
import br.pucrio.opus.smells.ast.visitors.MethodCollector;
import br.pucrio.opus.smells.metrics.MetricName;

/**
 * Class to calculate the LCOM ‘lack of cohesion of methods’ metrics.
 * 
 * This class contains the fields required to calculate the metric and its variations:
 * nMethods = number of procedures (methods) in class
 * anAttributes= number of variables (attributes) in class
 * nMethodsAccAttributes = number of methods that access a variable (attribute)
 * 
 * The metric definition as well as its implementation are available at:
 * http://www.aivosto.com/project/help/pm-oo-cohesion.html
 * 
 * @author leonardo
 */

public class LCOMCalculator extends MetricValueCalculator{
	private int nMethods;
	private int nAttributes;
	private int nMethodsAccAttributes;
	
	
	public LCOMCalculator() {
		nMethods = 0;
		nAttributes = 0;
		nMethodsAccAttributes = 0;
	}
	
	/**
	 * This method initializes the attributes required to calculate the LCOM
	 * The method does not compute the value of the metric, instead it initializes
	 * the variables required to calculate the LCOM
	 */
	@Override
	protected Double computeValue(ASTNode target) {
		List<MethodDeclaration> methods = getMethods(target);
		
		//get the number of methods within a class
		nMethods = methods.size();
		
		//get the number of methods accessing an attribute
		for(MethodDeclaration md: methods){
			FieldAccessCollector faVisitor = new FieldAccessCollector();
			md.accept(faVisitor);
			faVisitor.getNodesCollected().isEmpty();
			
		}
		
		
		return -1.0;
	}

	@Override
	public MetricName getMetricName() {
		return MetricName.LCOM;
	}
	
	/*
	 * Get the list with all the methods implemented inside of the class
	 */
	private List<MethodDeclaration> getMethods(ASTNode target) {
		MethodCollector methocCollector = new MethodCollector();
		target.accept(methocCollector);
		
		return methocCollector.getNodesCollected();
	}

}
