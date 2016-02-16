package br.pucrio.opus.smells.collector;

public class ClassLevelSmellDetector extends CompositeSmellDetector {
	
	public ClassLevelSmellDetector() {
		super.addDetector(new BlobClass());
		super.addDetector(new ClassDataShouldBePrivate());
		super.addDetector(new ComplexClass());
		super.addDetector(new LazyClass());
		super.addDetector(new RefusedBequest());
		super.addDetector(new SpaghettiCode());
		super.addDetector(new SpeculativeGenerality());
	}

	@Override
	protected SmellName getSmellName() {
		return null;
	}

}
