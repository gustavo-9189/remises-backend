package com.remises.controller;
 
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.remises.global.Constantes;

public class ClienteControllerTest {

	private static final String CTRL = "cliente";
	
	@Test
	public void existPathNot404() throws ClientProtocolException, IOException {

	   HttpUriRequest request = new HttpGet(Constantes.URI + CTRL);
	   HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

	   assertThat(
	     httpResponse.getStatusLine().getStatusCode(),
	     equalTo(HttpStatus.SC_OK)
	   );
	}

}
