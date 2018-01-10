package com.vasservice.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vasservice.outputsenders.OutputXMLSender;


@RestController
public class XMLController {
	
	@Autowired
	private OutputXMLSender outputXMLSender;

	@PostMapping(value = "/xmldata", headers = ("content-type=text/xml"))
	public HttpStatus acceptXML (@RequestBody String request){
		if(outputXMLSender.SendOutputData(request)) return HttpStatus.ACCEPTED;
	     return HttpStatus.NOT_ACCEPTABLE;
	  }
}
