package br.pucrio.opus.smells.metrics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class ClassMetrics {

	private String fqn;
	
	private CompilationUnit compilationUnit;
	
	/**
	 * Lines of code
	 */
	private Integer loc;
	
	private Double cohesion;
	
	/**
	 * Count of public fields (static fields does not count)
	 */
	private Integer publicFieldsCount;
	
	/**
	 * How many methods the superclass has
	 */
	private Integer superclassMethodsCount;
	
	/**
	 * How many methods are overriding superclass' methods
	 */
	private Integer overridingMethodsCount;
	
	private Boolean isAbstract;
	
	/**
	 * How many classes extends this one
	 */
	private Integer childrenCount;
	
	private List<MethodMetrics> methodMetrics;
	
	public ClassMetrics() {
		this.methodMetrics = new ArrayList<>();
	}

	public String getFqn() {
		return fqn;
	}

	public void setFqn(String fqn) {
		this.fqn = fqn;
	}

	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

	public void setCompilationUnit(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	public Integer getLoc() {
		return loc;
	}

	public void setLoc(Integer loc) {
		this.loc = loc;
	}

	public Double getCohesion() {
		return cohesion;
	}

	public void setCohesion(Double cohesion) {
		this.cohesion = cohesion;
	}

	public Integer getPublicFieldsCount() {
		return publicFieldsCount;
	}

	public void setPublicFieldsCount(Integer publicFieldsCount) {
		this.publicFieldsCount = publicFieldsCount;
	}

	public Integer getSuperclassMethodsCount() {
		return superclassMethodsCount;
	}

	public void setSuperclassMethodsCount(Integer superclassMethodsCount) {
		this.superclassMethodsCount = superclassMethodsCount;
	}

	public Integer getOverridingMethodsCount() {
		return overridingMethodsCount;
	}

	public void setOverridingMethodsCount(Integer overridingMethodsCount) {
		this.overridingMethodsCount = overridingMethodsCount;
	}

	public Boolean getIsAbstract() {
		return isAbstract;
	}

	public void setIsAbstract(Boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public Integer getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}

	public List<MethodMetrics> getMethodMetrics() {
		return methodMetrics;
	}

}
