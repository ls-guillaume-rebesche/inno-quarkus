package com.lightspeed.quarkus.loadtester;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class LoadtesterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadtesterApplication.class, args);
    }

    @Component
    public class AppStartupRunner implements ApplicationRunner {

        private RestTemplate restTemplate;
        private String url = "http://localhost:8080/customer";
        private static final long WAITING_IN_LOOP = 5;

        @Override
        public void run(ApplicationArguments args) {
            Thread thread = new Thread(() -> {
                restTemplate = new RestTemplate();

                createCustomer("customer 1");
                createCustomer("customer 2");
                createCustomer("customer 3");
                createCustomer("customer 4");
                log.info("4 customers created");

                while (true) {

                    try {
                        Thread.sleep(WAITING_IN_LOOP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Customer customer5 = createCustomer("customer 5");
                    deleteCustomer(customer5.getId());
                    List<Customer> customers = getCustomers();

                    log.info("customer {} created, then deleted, then all customers ({}) loaded", customer5.getId(), customers.size());
                }
            });
            thread.start();
        }

        private List<Customer> getCustomers() {
            ResponseEntity<List<Customer>> response = restTemplate.exchange(
                url, HttpMethod.GET, new HttpEntity<>(getHttpHeaders()), new ParameterizedTypeReference<List<Customer>>() {
                });
            return response.getBody();
        }

        private Customer createCustomer(String name) {
            Customer customer = new Customer();
            customer.setName(name);
            HttpEntity httpEntity = new HttpEntity<>(customer, getHttpHeaders());
            ResponseEntity<Customer> response = restTemplate.exchange(
                url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Customer>() {
                });
            return response.getBody();
        }

        private void deleteCustomer(long Id) {
            restTemplate.exchange(url + "/" + Id, HttpMethod.DELETE, new HttpEntity<>(getHttpHeaders()), Void.class);
        }

        private HttpHeaders getHttpHeaders() {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            headers.setContentType(MediaType.APPLICATION_JSON);
            return headers;
        }
    }
}
