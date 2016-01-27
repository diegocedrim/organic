package br.pucrio.opus.smells.resources.exceptions;

public class NotSourceDirException extends RuntimeException {

	private static final long serialVersionUID = 6851017978312026140L;

	public NotSourceDirException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotSourceDirException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotSourceDirException(String message) {
		super(message);
	}

	public NotSourceDirException(Throwable cause) {
		super(cause);
	}

}
