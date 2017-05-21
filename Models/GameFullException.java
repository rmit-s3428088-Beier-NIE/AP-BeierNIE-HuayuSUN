//Wrote by Huayu Sun
package Models;

public class GameFullException extends Exception {

	public GameFullException() {
	}

	public GameFullException(String message) {
		super(message);
	}

	public GameFullException(Throwable cause) {
		super(cause);
	}

	public GameFullException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
