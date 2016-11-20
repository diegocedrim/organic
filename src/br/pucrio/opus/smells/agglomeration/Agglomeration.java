package br.pucrio.opus.smells.agglomeration;

import java.util.ArrayList;
import java.util.List;

public class Agglomeration {

	public List<SmellyNode> nodes;
	
	public Agglomeration() {
		this.nodes = new ArrayList<>();
	}
	
	public void addNode(SmellyNode node) {
		this.nodes.add(node);
	}

	public List<SmellyNode> getNodes() {
		return nodes;
	}
}
