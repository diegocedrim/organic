package br.pucrio.opus.smells.agglomeration;

import java.util.ArrayList;
import java.util.List;

public class SmellyGraph {
	
	private List<SmellyNode> nodes;

	
	public SmellyGraph() {
		this.nodes = new ArrayList<>();
	}
	
	public void addNode(SmellyNode node) {
		this.nodes.add(node);
	}
	
	public List<SmellyNode> getNodes() {
		return nodes;
	}
	
	public void addEdge(SmellyNode u, SmellyNode v, List<String> relations) {
		SmellyEdge uToV = new SmellyEdge(u, v, relations);
		SmellyEdge vToU = new SmellyEdge(v, u, relations);
		u.addOutgoingEdge(uToV);
		u.addIncomingEdge(vToU);
		v.addOutgoingEdge(vToU);
		v.addIncomingEdge(uToV);
	}
	
}
