package br.pucrio.opus.smells.metrics.calculators;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.pucrio.opus.smells.ast.visitors.ClassFieldAccessCollector;
import br.pucrio.opus.smells.ast.visitors.MethodCollector;
import br.pucrio.opus.smells.metrics.MetricName;

/**
 * Abstract class to support the calculation of the LCOM ‘lack of cohesion of
 * methods’ metrics.
 * 
 * This class contains the fields required to calculate the metric and its
 * variations: nMethods = number of procedures (methods) in class nAttributes=
 * number of variables (attributes) in class timesAccessedAttributes = sum of
 * the number of times that each attribute is accessed by all methods
 * 
 * The metric definition as well as its implementation are available at:
 * http://www.aivosto.com/project/help/pm-oo-cohesion.html
 * 
 * @author leonardo
 */

public abstract class BaseLCOM extends MetricValueCalculator {
	private int nMethods;
	private int nAttributes;
	private int timesAccessedAttributes;

	public BaseLCOM() {
		setNumberOfMethods(0);
		setNumberOfAttributes(0);
		setTimesAccessedAttributes(0);
	}

	/**
	 * This method initializes the attributes required to calculate the LCOM The
	 * method does not compute the value of the metric, instead it initializes
	 * the variables required to calculate the LCOM
	 * 
	 * If the return is equal to 0.0, then the class doesn't have methods or
	 * attribute. Thus, the LCOM is 0. Otherwise, if the return is -1.0, then is
	 * possible to calculate the LCOM
	 */
	@Override
	protected Double computeValue(ASTNode target) {
		TypeDeclaration type = (TypeDeclaration) target;
		ITypeBinding binding = type.resolveBinding();
		
		if (binding == null) {
			return null;
		}
		List<MethodDeclaration> methods = getMethods(target);
		
		this.nAttributes = this.getVariablesInHierarchy(binding).size();
		
		
		// get the number of methods within a class
		setNumberOfMethods(methods.size());

		// Sum the number of times that each attribute is accessed by all methods
		for (MethodDeclaration md : methods) {
			ClassFieldAccessCollector faVisitor = new ClassFieldAccessCollector(type);
			md.accept(faVisitor);
			timesAccessedAttributes += faVisitor.getNodesCollected().size();
		}

		if (nMethods == 0 || nAttributes == 0) {
			return 0.0; // it is impossible to calculate lcom
		}

		return -1.0;
	}
	
	private Set<IVariableBinding> getVariablesInHierarchy(ITypeBinding type) {
		Set<IVariableBinding> variables = new HashSet<>();
		
		IVariableBinding[] localVariables = type.getDeclaredFields();
		variables.addAll(Arrays.asList(localVariables));
		type = type.getSuperclass();
		
		
		//begin to go in the superclasses
		while (type != null) {
			localVariables = type.getDeclaredFields();
			for(IVariableBinding variable: localVariables){
								
				if(variable.getModifiers() != Modifier.PRIVATE){
					variables.add(variable);
				}
			}
			
			type = type.getSuperclass();
		}
		return variables;
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

	public int getNumberOfMethods() {
		return nMethods;
	}

	public void setNumberOfMethods(int nMethods) {
		this.nMethods = nMethods;
	}

	public int getNumberOfAttributes() {
		return nAttributes;
	}

	public void setNumberOfAttributes(int nAttributes) {
		this.nAttributes = nAttributes;
	}

	public int getTimesAccessedAttributes() {
		return timesAccessedAttributes;
	}

	public void setTimesAccessedAttributes(int timeAccessedAttributes) {
		this.timesAccessedAttributes = timeAccessedAttributes;
	}

}
