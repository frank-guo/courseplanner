package cmpt470.group7.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="You are not authorized")
public class UnauthorizedException extends RuntimeException {

	/**
	 * auto generated
	 */
	private static final long serialVersionUID = -8150240519576645790L;

}
