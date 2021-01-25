package isa20.back.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
       
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		
		ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.NOT_FOUND, "Resource Not Found" ,details);
		
		return ResponseEntityBuilder.build(err);
	}
	
}
