package br.pucrio.opus.smells.agglomeration;

import java.util.ArrayList;
import java.util.List;

import br.pucrio.opus.smells.resources.Resource;

public class SmellyNode {

	private Resource resource;
	
	private transient List<SmellyEdge> outgoingEdges;
	
	private transient List<SmellyEdge> incomingEdges;
	
	private String resourceFQN;

	public SmellyNode(Resource resource) {
		this.resource = resource;
		this.outgoingEdges = new ArrayList<>();
		this.incomingEdges = new ArrayList<>();
		this.resourceFQN = resource.getFullyQualifiedName();
	}
	
	public List<SmellyNode> getNeighbors() {
		List<SmellyNode> neighbors = new ArrayList<>();
		for (SmellyEdge edge : this.outgoingEdges) {
			neighbors.add(edge.getDestination());
		}
		return neighbors;
	}

	public Resource getResource() {
		return resource;
	}
	
	public void addIncomingEdge(SmellyEdge edge) {
		this.incomingEdges.add(edge);
	}
	
	public void addOutgoingEdge(SmellyEdge edge) {
		this.outgoingEdges.add(edge);
	}

	public List<SmellyEdge> getOutgoingEdges() {
		return outgoingEdges;
	}

	public List<SmellyEdge> getIncomingEdges() {
		return incomingEdges;
	}
	
	public String getResourceFQN() {
		return resourceFQN;
	}

	@Override
	public String toString() {
		return "SmellyNode [resource=" + resource + "]";
	}
}
