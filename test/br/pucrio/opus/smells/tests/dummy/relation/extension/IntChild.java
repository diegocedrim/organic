package br.pucrio.opus.smells.tests.dummy.relation.extension;

public class IntChild extends GenericParent<Integer> {

	public void a() {
		super.a();
	}
	
	public void b() {
		
	}
	
	@Override
	public <L> void generic(L a) {
		super.generic(a);
	}
}
