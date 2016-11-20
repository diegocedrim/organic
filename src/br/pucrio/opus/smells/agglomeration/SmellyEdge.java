package br.pucrio.opus.smells.agglomeration;

import java.util.List;

public class SmellyEdge {

	private SmellyNode origin;
	
	private SmellyNode destination;
	
	private List<String> relations;

	public SmellyEdge(SmellyNode u, SmellyNode v, List<String> relations) {
		this.origin = u;
		this.destination = v;
		this.relations = relations;
	}

	public SmellyNode getOrigin() {
		return origin;
	}

	public SmellyNode getDestination() {
		return destination;
	}

	public List<String> getRelations() {
		return relations;
	}
}
