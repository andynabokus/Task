package com.vasservice.outputsenders;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vasservice.appconstants.VasServiceConstants;

public class OutputXMLSender  implements OutputSender<String> {

	public OutputXMLSender() {}

	@Override
	public boolean SendOutputData(String data) {
		
		RestTemplate restTemplate =  new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> httpEntity = new HttpEntity<String>(data, headers);
		final ResponseEntity<String> response = restTemplate.postForEntity(VasServiceConstants.outputURL, httpEntity, String.class);
		
		 if(response.getStatusCode().equals(HttpStatus.ACCEPTED)) {
			return true;
		}
		 return false;
	}




}
