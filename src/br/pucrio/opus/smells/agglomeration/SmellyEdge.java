package br.pucrio.opus.smells.agglomeration;

import java.util.List;

public class SmellyEdge {

	private transient SmellyNode origin;
	
	private transient SmellyNode destination;
	
	private List<String> relations;
	
	private String destinationFQN;

	public SmellyEdge(SmellyNode u, SmellyNode v, List<String> relations) {
		this.origin = u;
		this.destination = v;
		this.relations = relations;
		this.destinationFQN = v.getResource().getFullyQualifiedName();
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

	public String getDestinationFQN() {
		return destinationFQN;
	}
}
