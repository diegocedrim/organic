package br.pucrio.opus.smells.agglomeration;

import br.pucrio.opus.smells.resources.Resource;

public class SmellyNode {

	private Resource resource;

	public SmellyNode(Resource resource) {
		this.resource = resource;
	}

	public Resource getResource() {
		return resource;
	}
	
}
