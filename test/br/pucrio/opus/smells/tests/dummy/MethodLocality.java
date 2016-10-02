package br.pucrio.opus.smells.tests.dummy;

public class MethodLocality {
	
	private String privado;
	
	private FieldAccessedByMethod foreign1;
	
	private RefusedBedquestSample foreign2;
	
	public MethodLocality() {
		foreign1 = new FieldAccessedByMethod();
		foreign2 = new RefusedBedquestSample();
	}
	
	protected String getPrivado() {
		return privado;
	}

	public void localA() {
		this.privado = "";
	}

	public void localB() {
		System.out.println("");
		this.privado = "";
	}

	public void localC() {
		new String().length();
		this.privado = "";
	}
	
	public void localD() {
		Object.class.getName().toLowerCase().toUpperCase().toLowerCase().trim().length();
	}

	public void superLocal() {
		this.localA();
		this.localB();
		this.localC();
		this.localD();
		new String().toCharArray();
		new String().toLowerCase();
		Integer.bitCount(12);
		new String().toCharArray();
		new String().toLowerCase();
		Integer.bitCount(12);
		new String().toCharArray();
		new String().toLowerCase();
		Integer.bitCount(12);
	}
	
	public void superForeign(int a) {
		foreign1.a();
		foreign1.b();
		foreign1.c();
		new String().toCharArray();
		new String().toLowerCase();
		Integer.bitCount(12);
		new String().toCharArray();
		new String().toLowerCase();
		Integer.bitCount(12);
	}
	
	public void moreLocal(int a, int b) {
		this.localA();
		this.localB();
		this.localC();
		this.localD();
		foreign1.a();
		foreign1.b();
		foreign2.b();
		new String().toCharArray();
		new String().toLowerCase();
		Integer.bitCount(12);
	}
	
	public void moreForeign(int a, int b, int c) {
		this.moreLocal(a, b);
		this.localD();
		new String().toCharArray();
		new String().toLowerCase();
		Integer.bitCount(12);
		foreign1.a();
		foreign1.b();
		foreign1.b();
		foreign1.b();
		foreign1.b();
		foreign2.b();
	}
}
