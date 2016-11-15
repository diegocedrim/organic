package br.pucrio.opus.smells.collector;

public class ClassLevelSmellDetector extends CompositeSmellDetector {
	
	public ClassLevelSmellDetector() {
		super.addDetector(new GodClass());
		super.addDetector(new ClassDataShouldBePrivate());
		super.addDetector(new ComplexClass());
		super.addDetector(new LazyClass());
		super.addDetector(new RefusedBequest());
		super.addDetector(new SpaghettiCode());
		super.addDetector(new SpeculativeGenerality());
		super.addDetector(new DataClass());
		super.addDetector(new BrainClass());
	}

	@Override
	protected SmellName getSmellName() {
		return null;
	}

}
