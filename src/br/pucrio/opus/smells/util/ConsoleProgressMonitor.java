package br.pucrio.opus.smells.util;

import org.eclipse.core.runtime.IProgressMonitor;

public class ConsoleProgressMonitor implements IProgressMonitor {

	private int currentProgress = 0;
	
	@Override
	public void beginTask(String name, int totalWork) {
		System.out.println("Starting: " + totalWork + " files");
	}

	@Override
	public void done() {
		System.out.println("Complete");
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
		System.out.println(currentProgress + " complete");
	}

}
