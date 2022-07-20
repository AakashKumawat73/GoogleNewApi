package com.example.news.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

	public String getResult(String topic) throws IOException {  
	    String responseBody = "";
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

        //forming the url based on the user given topic 
		String url = "https://news.google.com/search?q="+topic+"&hl=en-IN&gl=IN&ceid=IN:en";
        HttpGet httpget = new HttpGet(url);
        
        // Created a custom response handler
        ResponseHandler < String > responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        //Executing the get request
         responseBody = httpclient.execute(httpget, responseHandler);
        //System.out.println(responseBody);
    }
		//Returning the responseBody 
		return responseBody;
}
	
	}

