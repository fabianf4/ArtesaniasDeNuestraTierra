package co.edu.usa.ArtesaniasDeNuestraTierra.category;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CategoryExceptionHandler {
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException e){
	        
	        Map<String, String> errors = new HashMap<>();
	        e.getBindingResult().getFieldErrors().forEach(error -> {
	            errors.put(error.getField(), error.getDefaultMessage());
	        });

	        return errors;

	    }

}
