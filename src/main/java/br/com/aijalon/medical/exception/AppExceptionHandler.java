package br.com.aijalon.medical.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExeptions(Exception e, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(),
						request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceBadRequestException.class)
	public final ResponseEntity<ExceptionResponse> handleResourceBadRequestException(ResourceBadRequestException e, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	

}