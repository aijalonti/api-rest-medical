package br.com.aijalon.medical.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.aijalon.medical.dto.ErrorResponse;

@ControllerAdvice
public class RestHandlerException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllExeptions(Exception e, WebRequest request){
		
		ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage(),
						request.getDescription(false));
		
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceBadRequestException.class)
	public final ResponseEntity<ErrorResponse> handleResourceBadRequestException(ResourceBadRequestException e, WebRequest request){
		
		ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request){
		
		ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}