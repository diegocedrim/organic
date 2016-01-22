package br.pucrio.opus.smells.tests.dummy;

import java.util.List;

public class FieldDeclaration {

	private String test1;
	
	public String test2;
	
	protected int test3;
	
	transient String test4;
	
	final String test5 = "";
	
	static List<String> list1;

	public String getTest1() {
		return test1;
	}

	public void setTest1(String test1) {
		this.test1 = test1;
	}

	public String getTest2() {
		return test2;
	}

	public void setTest2(String test2) {
		this.test2 = test2;
	}

	public int getTest3() {
		return test3;
	}

	public void setTest3(int test3) {
		this.test3 = test3;
	}

	public String getTest4() {
		return test4;
	}

	public void setTest4(String test4) {
		this.test4 = test4;
	}

	public static List<String> getList1() {
		return list1;
	}

	public static void setList1(List<String> list1) {
		FieldDeclaration.list1 = list1;
	}

	public String getTest5() {
		return test5;
	}
	
	
}
