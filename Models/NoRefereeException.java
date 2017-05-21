//Wrote by Huayu Sun
package Models;

public class NoRefereeException extends Exception {

	public NoRefereeException() {
	}

	public NoRefereeException(String message) {
		super(message);
	}

	public NoRefereeException(Throwable cause) {
		super(cause);
	}

	public NoRefereeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRefereeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
