package br.pucrio.opus.smells.tests.dummy.relation.shared;

public class SharedAttribute {

	private int aa;
	
	private int ba;
	
	public int ca;
	
	public void a() {
		int va = 0;
		System.out.println(va);
		System.out.println(ba);
	}
	
	public void b() {
		int va = 0;
		System.out.println(va);
		aa++;
	}
	
	public void c() {
		System.out.println(aa);
	}
	
	public void d() {
		System.out.println(ba);
		ca++;
	}
}
