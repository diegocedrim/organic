package br.pucrio.opus.smells.tests.dummy;

public class Noav {
	private String a;
	private String b;
	private String c;
	
	public void none() {
		
	}
	
	public void noneWihLocal() {
		String a = null;
		System.out.println(a);
	}
	
	public void one() {
		System.out.println(a);
	}
	
	public void two() {
		System.out.println(a + b);
	}
	
	public void three() {
		System.out.println(a + b + c);
	}
	
	public void threePlusOneExternal() {
		System.out.println(a + b + c);
		String aLocal = new FieldDeclaration().test2;
		System.out.println(aLocal);
	}
}
