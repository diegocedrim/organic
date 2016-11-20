package br.pucrio.opus.smells.agglomeration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SmellyGraph {

	private Map<SmellyNode, Set<SmellyNode>> links;
	
	public SmellyGraph() {
		this.links = new HashMap<>();
	}
	
	public void addNode(SmellyNode node) {
		this.links.put(node, null);
	}
	
	public Set<SmellyNode> getNodes() {
		return links.keySet();
	}
	
	public void addNeighbor(SmellyNode node, SmellyNode neighbor) {
		Set<SmellyNode> neighbors = this.links.get(node);
		if (neighbors == null) {
			neighbors = new HashSet<>();
			this.links.put(node, neighbors);
		}
		neighbors.add(neighbor);
	}
	
	public void addEdge(SmellyNode u, SmellyNode v) {
		this.addNeighbor(u, v);
		this.addNeighbor(v, u);
	}
	
	public Set<SmellyNode> getNeighbors(SmellyNode node) {
		Set<SmellyNode> neighbors = this.links.get(node);
		if (neighbors == null) {
			return new HashSet<>();
		}
		return neighbors;
	}
}
