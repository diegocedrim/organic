package br.pucrio.opus.smells.tests.smells;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.collector.IntensiveCoupling;
import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.GenericCollector;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class IntensiveCouplingTest {

	@Test
	public void couplingTest() throws Exception {
		Type type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/Coupling.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
		
		IntensiveCoupling smellDetector = new IntensiveCoupling();
		
		List<Smell> smells = smellDetector.detect(type.findMethodByName("dispersed"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("highCint"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("lowCint"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("cint1"));
		Assert.assertEquals(0, smells.size());
		
		Method m = type.findMethodByName("intensive1");
		smells = smellDetector.detect(m);
		Assert.assertEquals(1, smells.size());
		
		m = type.findMethodByName("intensive2");
		smells = smellDetector.detect(m);
		Assert.assertEquals(1, smells.size());
	}
	
}
