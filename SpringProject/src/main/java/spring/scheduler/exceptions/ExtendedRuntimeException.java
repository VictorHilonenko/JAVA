package spring.scheduler.exceptions;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtendedRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private ErrorsEnum anError;
	private Optional<Exception> originalException;
	
	public ExtendedRuntimeException(ErrorsEnum anError) {
		this.anError = anError;
		this.originalException = Optional.empty();
	}
	
	public ExtendedRuntimeException(ErrorsEnum anError, Exception originalException) {
		this.anError = anError;
		this.originalException = Optional.of(originalException);
	}
}