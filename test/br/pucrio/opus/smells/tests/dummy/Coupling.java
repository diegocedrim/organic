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

	public void dispersed() {
		CC c = new CC();
		c.cc10();
		c.cc11();
		c.cc12();
		dummy1();
		dummy2();
		new FieldDeclaration().getTest1();
		new FieldDeclaration().getTest2();
		new MiscStructures().b();
		for (int i = 0; i < 2; i++) {
			new Noav().threePlusOneExternal();
			if (true) {

			}
		}
	}

	public void intensive1() {
		CC c = new CC();
		c.cc10();
		c.cc11();
		c.cc12();
		c.cc1("", "", "", "");
		dummy1();
		dummy2();
		dispersed();
		new FieldDeclaration().getTest1();
		new FieldDeclaration().getTest2();
		new MiscStructures().b();
		for (int i = 0; i < 2; i++) {
			new Noav().threePlusOneExternal();
			if (true) {

			}
		}
	}

	public void intensive2() {
		CC c = new CC();
		c.cc10();
		c.cc11();
		c.cc12();
		c.cc1("", "", "", "");
		for (int i = 0; i < 2; i++) {
			c.cc9();
			if (true) {

			}
		}
	}

	public void dummy1() {

	}

	public void dummy2() {

	}
}
