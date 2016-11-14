package br.pucrio.opus.smells.tests.dummy.graph;

public class B {

	public void ba() {
		new String().toCharArray();
		new A().aa();
	}
	
	public void bb() {
		new A().ad();
		ba();
	}
	
	public void bc() {
		
	}
}
