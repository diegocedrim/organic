package br.pucrio.opus.tests.dummy;

import java.util.Collection;
import java.util.Iterator;

public class AnonymousClass {
	
	public String publicFieldA;
	
	public String publicFieldB;
	
	public String publicFieldC;

	protected class InnerClass {
		protected Collection<String> list = new Collection<String>() {

			@Override
			public int size() {
				return 0;
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public boolean contains(Object o) {
				return false;
			}

			@Override
			public Iterator<String> iterator() {
				return null;
			}

			@Override
			public Object[] toArray() {
				return null;
			}

			@Override
			public <T> T[] toArray(T[] a) {
				return null;
			}

			@Override
			public boolean add(String e) {
				return false;
			}

			@Override
			public boolean remove(Object o) {
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				return false;
			}

			@Override
			public boolean addAll(Collection<? extends String> c) {
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				return false;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				return false;
			}

			@Override
			public void clear() {
				
			}
		};
	}
}
