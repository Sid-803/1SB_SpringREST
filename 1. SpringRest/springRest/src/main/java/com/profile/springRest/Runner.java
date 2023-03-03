package com.profile.springRest;

import java.util.Arrays;
import java.util.List;

import com.profile.springRest.entity.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Runner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(Runner.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        saveInv();
        getAllInvoices();
		getOneInvoice();
		updateInvoice();
        deleteInvoice();
    }

    private void saveInv(){
        //1.Application URL
        String url = "http://localhost:8080/api/invoices";

        //2. Send JSON data as Body - RequestBody as JSON
        String body = "{\"name\":\"INV1\"," +
                "\"amount\":234.11," +
                "\"number\":\"INVOICE11\"," +
                "\"receivedDate\":\"28-10-2020\"," +
                "\"type\":\"Normal\"," +
                "\"vendor\":\"ADHR001\"," +
                "\"comments\" :\"On Hold\"}";

        //3. HttpHeader object - body type(here JSON)
        HttpHeaders headers = new HttpHeaders();

        //4. Setting headers
        headers.setContentType(MediaType.APPLICATION_JSON);

        //5. RequestEntity object - accepts the passed body and format
        HttpEntity<String> request = new HttpEntity<String>(body,headers);

        //6. HTTp call and store response (url, response type)
        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST,request,String.class);

        //7. Print details
        logger.info("Response Body : {}", response.getBody());
        logger.info("Status code value : {}",response.getStatusCodeValue());
        logger.info("Status code : {}", response.getStatusCode());

    }

    private void getAllInvoices() {
        String url = "http://localhost:8080/api/invoices";
        ResponseEntity<Invoice[]> response = restTemplate.getForEntity(url,Invoice[].class);
        //	ResponseEntity<Invoice[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Invoice[].class);
        Invoice[] invs = response.getBody();
        List<Invoice> list = Arrays.asList(invs);

        logger.info("Response Body : {}", list);
        logger.info("Status code value : {}", response.getStatusCodeValue());
        logger.info("Status code : {}", response.getStatusCode());
        logger.info("Headers {} :", response.getHeaders());
    }

    private void getOneInvoice() {
        String url = "http://localhost:8080/api/invoices/{id}";
        //	ResponseEntity<String> response= restTemplate.getForEntity(url, String.class, 9);
        ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.GET, null, String.class, 7);
        logger.info("Response Body : {}", response.getBody());
        logger.info("Status code value : {}", response.getStatusCodeValue());
        logger.info("Status code : {}",response.getStatusCode());
    }

    private void updateInvoice() {
        String url = "http://localhost:8080/api/invoices/{id}";
        String body = "{\"name\":\"INV13\",\"amount\":888}";
        // Request Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // requestEntity = Body + header
        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        //	restTemplate.put(url, requestEntity, 7);
        ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class, 7);
        logger.info("Response Body : {}", response.getBody());
        logger.info("Status code value : {}", response.getStatusCodeValue());
        logger.info("Status code : {}",response.getStatusCode());
        logger.info("Response Headers : {}", response.getHeaders());
    }

    private void deleteInvoice() {
        String url = "http://localhost:8080/api/invoices/{id}";
        //	restTemplate.delete(url, 6);
        ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.DELETE, null, String.class,5);
        logger.info("Response Body : {}", response.getBody());
        logger.info("Status code value : {}", response.getStatusCodeValue());
        logger.info("Status code : {}",response.getStatusCode());
        logger.info("Response Headers : {}", response.getHeaders());
    }
}
