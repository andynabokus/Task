package com.vasservice.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vasservice.outputsenders.OutputJSONSender;

@RestController
public class JSONController {
	
	@Autowired
	private OutputJSONSender outputJSONSender;

	@PostMapping(value = "/jsondata", headers = ("content-type=application/json"))
	public HttpStatus acceptJSON(@RequestBody String request) {
		
		if(outputJSONSender.SendOutputData(request)) return HttpStatus.ACCEPTED;

		return HttpStatus.NOT_ACCEPTABLE;
	}

}
