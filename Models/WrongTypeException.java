//Wrote by Huayu Sun
package Models;

public class WrongTypeException extends Exception {

	public WrongTypeException() {
	}

	public WrongTypeException(String message) {
		super(message);
	}

	public WrongTypeException(Throwable cause) {
		super(cause);
	}

	public WrongTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
