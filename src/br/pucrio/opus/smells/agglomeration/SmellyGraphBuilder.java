package br.pucrio.opus.smells.agglomeration;

import java.util.ArrayList;
import java.util.List;

import br.pucrio.opus.smells.resources.Resource;

public class SmellyGraphBuilder {

	private SmellyGraph graph;

	private List<Resource> resources;

	public SmellyGraphBuilder() {
		this.resources = new ArrayList<>();
		this.graph = new SmellyGraph();
	}

	public void addResource(Resource resource) {
		this.resources.add(resource);
	}

	private void buildNodes() {
		for (Resource resource : this.resources) {
			if (resource.isSmelly()) {
				graph.addNode(new SmellyNode(resource));
			}
		}
	}
	
	private void buildEdges() {
		List<SmellyNode> nodes = new ArrayList<>(this.graph.getNodes());
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = i; j < nodes.size(); j++) {

			}
		}
	}

	public SmellyGraph build() {
		this.buildNodes();
		this.buildEdges();
		return graph;
	}
}
