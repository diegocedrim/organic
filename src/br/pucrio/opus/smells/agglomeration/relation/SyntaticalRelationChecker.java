package br.pucrio.opus.smells.agglomeration.relation;

public class SyntaticalRelationChecker extends CompositeRelationChecker {

	public SyntaticalRelationChecker() {
		this.addChecker(new SharedAttributeChecker());
		this.addChecker(new MethodCallChecker());
		this.addChecker(new OverrideChecker());
		this.addChecker(new ClassExtensionChecker());
		this.addChecker(new OverrideChecker());
	}
}
