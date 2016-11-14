package br.pucrio.opus.smells.gson;

import java.util.Observable;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ObservableExclusionStrategy implements ExclusionStrategy {

	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}
	
	public boolean shouldSkipField(FieldAttributes field) {
		return field.getDeclaringClass().equals(Observable.class) && ( field.getName().equals("obs") || field.getName().equals("changed"));
	}
}