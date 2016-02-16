package br.pucrio.opus.smells.collector;

import java.util.List;

import br.pucrio.opus.smells.resources.Resource;

/**
 * Defines a generic interface for detecting smells
 * @author Diego Cedrim
 */
public abstract class SmellDetector {

	/**
	 * Given a resource, return a list of existing smells 
	 * @param resource resource to be evaluated
	 * @return a list of existing smells
	 */
	public abstract List<Smell> detect(Resource resource);
	
	protected Smell createSmell(Resource resource, String reason) {
		Smell smell = new Smell(getSmellName(), reason);
		smell.setStartingLine(resource.getStartLineNumber());
		smell.setEndingLine(resource.getEndLineNumber());
		return smell;
	}
	
	protected Smell createSmell(Resource resource) {
		Smell smell = new Smell(getSmellName());
		smell.setStartingLine(resource.getStartLineNumber());
		smell.setEndingLine(resource.getEndLineNumber());
		return smell;
	}
	
	protected abstract SmellName getSmellName();
}
