package com.sparkcw.goodteam.exception;

public class ValueDuplicateException extends RuntimeException{
	
	public ValueDuplicateException(){
		super();
	}
	
	public ValueDuplicateException(String message){
		super(message);
	}
}
