package br.pucrio.opus.smells.tests.agglomeration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.agglomeration.Agglomeration;
import br.pucrio.opus.smells.agglomeration.AgglomerationFinder;
import br.pucrio.opus.smells.agglomeration.SmellyGraph;
import br.pucrio.opus.smells.agglomeration.SmellyGraphBuilder;
import br.pucrio.opus.smells.agglomeration.SmellyNode;
import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.collector.SmellName;
import br.pucrio.opus.smells.graph.CallGraph;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.ParenthoodRegistry;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.tests.util.TypeLoader;

public class AgglomerationFinderTest {

	@Before
	public void setup() throws IOException {
		CallGraph.getInstance().reset();
		ParenthoodRegistry.getInstance().reset();
	}

	private void makeSmelly(Type type) {
		type.addSmell(new Smell(SmellName.FeatureEnvy));
		for (Method m : type.getMethods()) {
			m.addSmell(new Smell(SmellName.FeatureEnvy));
		}
	}

	private SmellyGraph buildGraph(List<Type> types) {
		SmellyGraphBuilder builder = new SmellyGraphBuilder();
		for (Type type : types) {
			makeSmelly(type);
			builder.addTypeAndItsMethods(type);
		}
		return builder.build();
	}

	private boolean contains(Agglomeration agglomeration, String partialName) {
		for (SmellyNode node : agglomeration.getNodes()) {
			if (node.getResource().getFullyQualifiedName().contains(partialName)) {
				return true;
			}
		}
		return false;
	}

	private boolean isTheSame(Agglomeration agglomeration, List<String> nodePartialNames) {
		for (String partialName : nodePartialNames) {
			if (!contains(agglomeration, partialName)) {
				return false;
			}
		}
		return true;
	}

	private void testIfexists(List<Agglomeration> agglomerations, List<String> nodePartialNames) {
		for (Agglomeration agg : agglomerations) {
			if (isTheSame(agg, nodePartialNames)) {
				Assert.assertTrue(true);
				return;
			}
		}
		Assert.assertTrue(nodePartialNames.toString() + " does not exist", false);
	}

	protected void printAgglomerations(List<Agglomeration> agglomerations) {
		for (Agglomeration agglomeration : agglomerations) {
			System.out.println("Agglomeration: ");
			for (SmellyNode node : agglomeration.getNodes()) {
				System.out.println(node);
			}
			System.out.println("");
		}
	}

	@Test
	public void methodCallsTest() throws IOException {
		List<Type> types = TypeLoader.loadAllFromDir(new File("test/br/pucrio/opus/smells/tests/dummy/relation/methodcalls"));
		SmellyGraph graph = buildGraph(types);
		AgglomerationFinder finder = new AgglomerationFinder(graph);
		List<Agglomeration> agglomerations = finder.findAll();
		Assert.assertEquals(2, agglomerations.size());
		testIfexists(agglomerations, Arrays.asList("MethodCalls.a", "MethodCalls.c", "MethodCallsExternal.a", "MethodCalls.b"));
		testIfexists(agglomerations, Arrays.asList("MethodCalls.secondCallGroup1", "MethodCalls.secondCallGroup2"));
	}
	
	@Test
	public void classExtensionTest() throws IOException {
		List<Type> types = TypeLoader.loadAllFromDir(new File("test/br/pucrio/opus/smells/tests/dummy/relation/extension"));
		ParenthoodRegistry.getInstance().getAncestors(types.get(0));
		SmellyGraph graph = buildGraph(types);
		AgglomerationFinder finder = new AgglomerationFinder(graph);
		List<Agglomeration> agglomerations = finder.findAll();
		printAgglomerations(agglomerations);
		Assert.assertEquals(4, agglomerations.size());
		testIfexists(agglomerations, Arrays.asList("GenericInterface1", "GenericInterface2", "Parent", "Interface", "TheMostGenericInterface", "Child"));
		testIfexists(agglomerations, Arrays.asList("MoreGeneric", "GenericParent", "SuperGeneric", "FloatChild", "IntChild"));
		testIfexists(agglomerations, Arrays.asList("FloatChild.a", "IntChild.a", "GenericParent.a"));
		testIfexists(agglomerations, Arrays.asList("FloatChild.generic", "IntChild.generic", "GenericParent.generic"));
	}
	
	@Test
	public void sharedAttributeTest() throws IOException {
		List<Type> types = TypeLoader.loadAllFromDir(new File("test/br/pucrio/opus/smells/tests/dummy/relation/shared"));
		SmellyGraph graph = buildGraph(types);
		AgglomerationFinder finder = new AgglomerationFinder(graph);
		List<Agglomeration> agglomerations = finder.findAll();
		printAgglomerations(agglomerations);
		Assert.assertEquals(2, agglomerations.size());
		testIfexists(agglomerations, Arrays.asList("SharedAttributeExternal.a", "SharedAttribute.d", "SharedAttribute.a"));
		testIfexists(agglomerations, Arrays.asList("SharedAttribute.c", "SharedAttribute.b"));
	}

}
