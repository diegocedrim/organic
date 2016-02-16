package br.pucrio.opus.smells.collector;

public class MethodLevelSmellDetector extends CompositeSmellDetector {
	
	public MethodLevelSmellDetector() {
		super.addDetector(new FeatureEnvy());
		super.addDetector(new LongMethod());
		super.addDetector(new LongParameterList());
		super.addDetector(new MessageChain());
	}

	@Override
	protected SmellName getSmellName() {
		return null;
	}

}
