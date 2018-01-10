package com.vasservice.outputsenders;

public interface OutputSender<T> {
	
	public  boolean SendOutputData(T data);

}
