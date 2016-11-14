package br.pucrio.opus.smells.tests.smells;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.collector.DispersedCoupling;
import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.GenericCollector;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class DispersedCouplingTest {

	@Test
	public void couplingTest() throws Exception {
		Type type = TypeLoader.loadOne(new File("test/br/pucrio/opus/smells/tests/dummy/Coupling.java"));
		GenericCollector.collectTypeAndMethodsMetricValues(type);
		
		DispersedCoupling smellDetector = new DispersedCoupling();
		
		List<Smell> smells = smellDetector.detect(type.findMethodByName("dispersed"));
		Assert.assertEquals(1, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("highCint"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("lowCint"));
		Assert.assertEquals(0, smells.size());
		
		smells = smellDetector.detect(type.findMethodByName("cint1"));
		Assert.assertEquals(0, smells.size());
	}
	
}
