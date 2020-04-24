package com.mec.util;

public class FrameIsNullException extends Exception {
	private static final long serialVersionUID = -3337459141505096414L;

	public FrameIsNullException() {
	}

	public FrameIsNullException(String message) {
		super(message);
	}

	public FrameIsNullException(Throwable cause) {
		super(cause);
	}

	public FrameIsNullException(String message, Throwable cause) {
		super(message, cause);
	}

	public FrameIsNullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
