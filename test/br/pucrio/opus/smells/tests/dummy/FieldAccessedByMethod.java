package br.pucrio.opus.smells.tests.dummy;

import org.apache.commons.io.FileUtils;

public class FieldAccessedByMethod extends SuperDummy {
	
	private String f;
	
	private String b;
	
	private Object c;
	
	private Object d;
	
	private FileUtils noBinding;
	
	@Override
	protected void a() {
		super.a();
	}
	
	private void k() {
		
	}
	
	void m() {
		this.k();
	}
	
	protected void n() {
		System.out.println(d);
	}

	public void x() {
		int kkk = 0;
		System.out.println(kkk);
		FileUtils a = noBinding;
		System.out.println(this.b + c + super.a + a);
	}
	
	public void y() {
		System.out.println(f);
	}
	
	public void z() {
		System.out.println(c + f);
	}
}
