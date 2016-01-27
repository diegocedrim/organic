package br.pucrio.opus.smells.collector;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

import br.pucrio.opus.smells.resources.Resource;

/**
 * Defines a generic interface for detecting smells
 * @author Diego Cedrim
 */
public interface SmellDetector {

	/**
	 * Given a resource, return a list of existing smells 
	 * @param resource resource to be evaluated
	 * @return a list of existing smells
	 */
	List<Smell> detect(Resource<? extends ASTNode> resource);
	
}
