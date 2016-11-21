package br.pucrio.opus.smells.util;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;

public class ConsoleProgressMonitor implements IProgressMonitor {

	private float currentProgress = 0;
	
	private int totalWork;
	
	private Set<Integer> reportedProgresses;
	
	@Override
	public void beginTask(String name, int totalWork) {
		this.reportedProgresses = new HashSet<>();
		this.totalWork = totalWork;
		System.out.println("Starting: " + name + ". Items to process: "+ totalWork);
	}

	@Override
	public void done() {
		System.out.println("Complete!");
	}

	@Override
	public void internalWorked(double work) {
		
	}

	@Override
	public boolean isCanceled() {
		return false;
	}

	@Override
	public void setCanceled(boolean value) {
		
	}

	@Override
	public void setTaskName(String name) {
		
	}

	@Override
	public void subTask(String name) {
		
	}

	@Override
	public void worked(int work) {
		currentProgress += work;
		float percentage = (currentProgress / this.totalWork) * 100;
		int bucket = (int)percentage;
		if (bucket % 10 == 0 && !this.reportedProgresses.contains(bucket)) {
			this.reportedProgresses.add(bucket);
			System.out.println(percentage + "% complete");
		}
	}

}
