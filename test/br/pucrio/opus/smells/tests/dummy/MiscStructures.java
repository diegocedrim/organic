package br.pucrio.opus.smells.tests.dummy;

import javax.annotation.Resource;

public class MiscStructures extends SuperDummy {
	
	@Resource
	private String field;
	
	static {
		System.out.println("testing initializer");
//		Runnable r2 = () -> System.out.println("Hello world two!");
	}
	
	public void a() {
		super.a = "2";
		System.out.println(a);
		System.out.println(Integer.MAX_VALUE);
		throw new RuntimeException();
	}
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		L1: System.out.println("");
		java.lang.Math m;
		try {
			args[0] = "";
			assert args[0] == ""; 
			while (true) {
				break;
			}
			while (true) {
				continue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("o/");
		}
		
		for (int i = 0; i < 10; i++) {
			System.err.println("inside for");
			if (i != 0) {
				System.err.println("inside for");
			} else {
				System.err.println("inside for");
			}
		}
		
		do {
			System.err.println("inside do while");
		} while(true);
	}
}
