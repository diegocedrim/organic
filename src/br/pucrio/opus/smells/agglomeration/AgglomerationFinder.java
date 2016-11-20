package br.pucrio.opus.smells.agglomeration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class AgglomerationFinder {

	private SmellyGraph graph;
	
	private Set<SmellyNode> visited;

	public AgglomerationFinder(SmellyGraph graph) {
		this.graph = graph;
		this.visited = new HashSet<>();
	}
	
	private Agglomeration findOneStartingAt(SmellyNode node) {
		Agglomeration agglomeration = new Agglomeration();
		Queue<SmellyNode> queue = new LinkedList<>(Arrays.asList(node));
		while (!queue.isEmpty()) {
			SmellyNode current = queue.poll();
			this.visited.add(current);
			agglomeration.addNode(current);
			for (SmellyNode neighbor : current.getNeighbors()) {
				if (!this.visited.contains(neighbor)) {
					queue.add(neighbor);
				}
			}
		}
		return agglomeration;
	}
	
	public List<Agglomeration> findAll() {
		List<Agglomeration> agglomerations = new ArrayList<>();
		List<SmellyNode> nodes = this.graph.getNodes();
		for (SmellyNode node : nodes) {
			if (!this.visited.contains(node)) {
				Agglomeration agglomeration = this.findOneStartingAt(node);
				if (agglomeration.size() > 1) {
					agglomerations.add(agglomeration);
				}
			}
		}
		return agglomerations;
	}
}
