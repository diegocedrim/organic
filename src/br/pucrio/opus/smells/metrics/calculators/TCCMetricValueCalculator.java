package br.pucrio.opus.smells.metrics.calculators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.pucrio.opus.smells.ast.visitors.FieldAccessCollector;
import br.pucrio.opus.smells.ast.visitors.FieldDeclarationCollector;
import br.pucrio.opus.smells.ast.visitors.PublicMethodCollector;

/**
 * Computes the Tight Class Cohesion value. 
 * @author Diego Cedrim
 */
public class TCCMetricValueCalculator implements MetricValueCalculator<TypeDeclaration> {
	
	public static final String NAME = "TightClassCohesion";
	
	private List<FieldDeclaration> classFields;
	
	private List<FieldDeclaration> getDeclaredFields(ASTNode target) {
		FieldDeclarationCollector fieldCollector = new FieldDeclarationCollector();
		target.accept(fieldCollector);
		return fieldCollector.getNodesCollected();
	}
	
	private List<MethodDeclaration> getPublicMethods(ASTNode target) {
		PublicMethodCollector collector = new PublicMethodCollector();
		target.accept(collector);
		return collector.getNodesCollected();
	}
	
	private Set<FieldDeclaration> getAccessedFields(MethodDeclaration method) {
		FieldAccessCollector collector = new FieldAccessCollector(classFields);
		method.accept(collector);
		Set<FieldDeclaration> fieldSet = new HashSet<>();
		fieldSet.addAll(collector.getNodesCollected());
		return fieldSet;
	}
	
	/**
	 * Creates a map that relates methods with its accessed fields. For each method
	 * declaration we would have a set of class member fields accessed by it
	 * @return a map that relates methods with its accessed fields
	 */
	private Map<MethodDeclaration, Set<FieldDeclaration>> buildAccessedFieldMap(List<MethodDeclaration> publicMethods) {
		Map<MethodDeclaration, Set<FieldDeclaration>> accessedFieldsMap = new HashMap<>();
		for (MethodDeclaration methodDeclaration : publicMethods) {
			Set<FieldDeclaration> fields = this.getAccessedFields(methodDeclaration);
			accessedFieldsMap.put(methodDeclaration, fields);
		}
		return accessedFieldsMap;
	}
	
	/**
	 * Number of visible directly connected methods. 
	 * Compute the number of method pairs that access at least one field in common.
	 * @return
	 */
	private Double computeNdc(List<MethodDeclaration> publicMethods) {
		Double connectedMethodsCount = 0d;
		Map<MethodDeclaration, Set<FieldDeclaration>> accessedFieldsMap = buildAccessedFieldMap(publicMethods);
		for (int i = 0; i < publicMethods.size() - 1; i++) {
			for (int j = i + 1; j < publicMethods.size(); j++) {
				Set<FieldDeclaration> fieldsByI = accessedFieldsMap.get(publicMethods.get(i));
				Set<FieldDeclaration> fieldsByJ = accessedFieldsMap.get(publicMethods.get(j));
				
				Set<FieldDeclaration> intersection = new HashSet<>(fieldsByI);
				intersection.retainAll(fieldsByJ);
				if (!intersection.isEmpty()) {
					connectedMethodsCount++;
				}
			}
		}
		return connectedMethodsCount;
	}
	
	
	@Override
	public Double getValue(TypeDeclaration target) {
		this.classFields = getDeclaredFields(target);
		List<MethodDeclaration> publicMethods = getPublicMethods(target);

		int n = publicMethods.size();
		if (n <= 1) {
			return 1d; //classes with only one method (or zero) have high cohesion
		}
		
		Double ndc = this.computeNdc(publicMethods);
		Double np = (double)(n * (n - 1) / 2);
		return ndc / np;
	}

	@Override
	public String getMetricName() {
		return NAME;
	}

}
