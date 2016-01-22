package br.pucrio.opus.smells.metrics.calculators;

public class TargetTypeNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 2165504323480722829L;

	public TargetTypeNotAllowedException() {
		super();
	}

	public TargetTypeNotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public TargetTypeNotAllowedException(String message) {
		super(message);
	}

	public TargetTypeNotAllowedException(Throwable cause) {
		super(cause);
	}

}
