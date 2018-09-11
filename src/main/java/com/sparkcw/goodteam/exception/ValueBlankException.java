package com.sparkcw.goodteam.exception;

public class ValueBlankException extends RuntimeException{
	
	public ValueBlankException(){
		super();
	}
	
	public ValueBlankException(String message){
		super(message);
	}
}
