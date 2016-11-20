package br.pucrio.opus.smells.agglomeration.relation;

import java.util.ArrayList;
import java.util.List;

import br.pucrio.opus.smells.agglomeration.SmellyNode;

public abstract class CompositeRelationChecker extends RelationChecker {
	
	private List<RelationChecker> checkers;
	
	public CompositeRelationChecker() {
		this.checkers = new ArrayList<>();
	}
	
	public void addChecker(RelationChecker checker) {
		this.checkers.add(checker);
	}
	
	@Override
	public boolean isRelated(SmellyNode u, SmellyNode v) {
		for (RelationChecker checker : checkers) {
			if (checker.isRelated(u, v)) {
				return true;
			}
		}
		return false;
	}
}
