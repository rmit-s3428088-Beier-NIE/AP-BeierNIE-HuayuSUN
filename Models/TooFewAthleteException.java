//Wrote by Huayu Sun
package Models;

public class TooFewAthleteException extends Exception {

	public TooFewAthleteException() {
	}

	public TooFewAthleteException(String message) {
		super(message);
	}

	public TooFewAthleteException(Throwable cause) {
		super(cause);
	}

	public TooFewAthleteException(String message, Throwable cause) {
		super(message, cause);
	}

	public TooFewAthleteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
