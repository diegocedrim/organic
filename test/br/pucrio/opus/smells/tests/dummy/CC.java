package br.pucrio.opus.smells.tests.dummy;

import java.util.Arrays;

public class CC {

	public void cc1() {
		return;
	}
	
	public void cc2() {
		if ("2" != new String()) {
			
		} else {
			
		}
	}
	
	public void cc3() {
		for (int i = 0; i < 1; i++) {
			if ("2" != new String()) {
				
			} else {
				
			}
		}
	}
	
	public void cc4() {
		while (true) {
			for (int i = 0; i < 1; i++) {
				if ("2" != new String()) {
					
				} else {
					
				}
			}
		}
	}
	
	public void cc5() {
		do {
			
		} while("2" != new String());
		
		while (true) {
			for (int i = 0; i < 1; i++) {
				if ("2" != new String()) {
					
				} else {
					
				}
			}
		}
	}
	
	public void cc6() {
		do {
			switch (4) {
				case 1:break;
			}
		} while("2" != new String());
		
		while (true) {
			for (int i = 0; i < 1; i++) {
				if ("2" != new String()) {
					
				} else {
					
				}
			}
		}
	}
	
	public void cc7() {
		do {
			switch (4) {
				case 1:break;
				case 2:break;
			}
		} while("2" != new String());
		
		while (true) {
			for (int i = 0; i < 1; i++) {
				if ("2" != new String()) {
					
				} else {
					
				}
			}
		}
	}
	
	public void cc8() {
		do {
			switch (4) {
				case 1:break;
				case 2:break;
				default: break;
			}
		} while("2" != new String());
		
		while (true) {
			for (int i = 0; i < 1; i++) {
				if ("2" != new String()) {
					
				} else {
					
				}
			}
		}
	}
	
	public void cc9() {
		try {
		} catch (NullPointerException n) {
			
		}
		do {
			switch (4) {
				case 1:break;
				case 2:break;
				default: break;
			}
		} while("2" != new String());
		
		while (true) {
			for (int i = 0; i < 1; i++) {
				if ("2" != new String()) {
					
				} else {
					
				}
			}
		}
	}
	
	public void cc10() {
		try {
		} catch (NullPointerException n) {
			
		} catch (Exception n) {
			
		}
		do {
			switch (4) {
				case 1:break;
				case 2:break;
				default: break;
			}
		} while("2" != new String());
		
		while (true) {
			for (int i = 0; i < 1; i++) {
				if ("2" != new String()) {
					
				} else {
					
				}
			}
		}
	}
	
	public void cc11() {
		
		for(String a : Arrays.asList("a")) {
			System.out.println(a);
		}
		
		try {
		} catch (NullPointerException n) {
			
		} catch (Exception n) {
			
		}
		do {
			switch (4) {
				case 1:break;
				case 2:break;
				default: break;
			}
		} while("2" != new String());
		
		while (true) {
			for (int i = 0; i < 1; i++) {
				if ("2" != new String()) {
					
				} else {
					
				}
			}
		}
	}
}
