package br.pucrio.opus.smells.tests.dummy;

public class Coupling {

	public void highCint() {
		this.dummy1();
		this.dummy2();
		new String().length();
		CC c = new CC();
		c.cc10();
		c.cc10();
		c.cc10();
		c.cc11();
		c.cc12();
	}
	
	public void lowCint() {
		this.dummy1();
		CC c = new CC();
		c.cc10();
		Assessors a = new Assessors();
		a.getA();
	}
	
	public void cint1() {
		CC c = new CC();
		c.cc10();
		c.cc10();
		c.cc10();
		c.cc10();
		c.cc10();
		c.cc10();
		c.cc10();
		c.cc10();
	}
	
	public void dummy1() {
		
	}
	
	public void dummy2() {
		
	}
}
