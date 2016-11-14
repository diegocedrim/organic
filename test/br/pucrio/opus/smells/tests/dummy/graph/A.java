package br.pucrio.opus.smells.tests.dummy.graph;

public class A {

	public void aa() {
		this.ab();
		this.ac();
	}
	
	private void ab() {
		this.ab();
	}
	
	private void ac() {
		this.ac();
	}
	
	public void ad() {
		
	}
}
