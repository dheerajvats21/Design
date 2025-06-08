package com.example.Cart;

import com.example.Cart.dtos.ItemAddedDto;
import com.example.Cart.entities.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartApplicationTests {

	@LocalServerPort
	private int port;

	private RestTemplate template = new RestTemplate();


	ResponseEntity<ItemAddedDto> addItem(String userID, String itemID, Integer quantity) {
		String url = String.format("http://localhost:%s/cart/additem?userId=%s&itemId=%s%s", port, userID, itemID,
				quantity != null ? "&quantity=" + quantity : "" );
		return template.postForEntity(url, null, ItemAddedDto.class);

	}

	ResponseEntity<String> viewCart(String userID) {
		String url = "http://localhost:" + port + "/cart/getcart/" + userID;
		return template.getForEntity(url, String.class);
	}

	ResponseEntity<String> clearCart(String userID) {
		String url = "http://localhost:" + port + "/cart/clearcart/" + userID;
		return template.exchange(url, HttpMethod.DELETE, null, String.class);
	}

	ResponseEntity<String> removeItem(String userID, String itemId) {
		String url = String.format("http://localhost:%s/cart/removeitem/%s/%s", port, userID, itemId);
		return template.exchange(url, HttpMethod.DELETE, null, String.class);

	}

	ResponseEntity<String> updateItem(String userID, String itemID, Integer quantity) {
		String url = String.format("http://localhost:%s/cart/updatequantity/%s/%s/%s",
				port, userID, itemID, quantity);
		return template.exchange(url, HttpMethod.PUT,null, String.class);
	}
	@Test
	void testAddItem() {
		ResponseEntity<ItemAddedDto> response = addItem("1","1",2);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals("item added successfully", response.getBody().getResponse());
	}

	@Test
	void testViewCart() {
		addItem("1","1",2);
		ResponseEntity<String> response = viewCart("1");
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
		System.out.println(response.getBody());
//		Assertions.assertEquals("item added successfully", response.getBody().getResponse());
	}

	@Test
	void testRemoveItem() {
		addItem("1","1",2);
		addItem("1","2",null);
		ResponseEntity<String> responseViewCart = viewCart("1");
		System.out.println("before removal " + responseViewCart.getBody());

		ResponseEntity<String> response = removeItem("1", "2");
		System.out.println("The response of remove " + response.getBody());
		responseViewCart = viewCart("1");
		System.out.println("after removal " + responseViewCart.getBody());

	}

	@Test
	void testClearCart() {
		addItem("1","1",2);
		addItem("1","2",null);
		addItem("2","1",null);
		addItem("2","2",3);

		ResponseEntity<String> responseViewCart = viewCart("1");
		System.out.println("before removal for user 1 " + responseViewCart.getBody());
		responseViewCart = viewCart("2");
		System.out.println("before removal for user 2 " + responseViewCart.getBody());

		clearCart("1");
		responseViewCart = viewCart("1");
		System.out.println("after removal for user 1 " + responseViewCart.getBody());
		responseViewCart = viewCart("2");
		System.out.println("after removal for user 2 " + responseViewCart.getBody());
	}

	@Test
	void testUpdate() {
		addItem("1","1",2);
		addItem("1","2",null);
		addItem("2","1",null);
		addItem("2","2",3);

		ResponseEntity<String> responseViewCart = viewCart("1");
		System.out.println("before update for user 1 " + responseViewCart.getBody());
		responseViewCart = viewCart("2");
		System.out.println("before update for user 2 " + responseViewCart.getBody());

		updateItem("1", "1", 1);
		updateItem("2", "1", 0);

		responseViewCart = viewCart("1");
		System.out.println("after update for user 1 " + responseViewCart.getBody());
		responseViewCart = viewCart("2");
		System.out.println("after update for user 2 " + responseViewCart.getBody());

	}

	@Test
	void testMultiThreadAdd() throws InterruptedException {

		Executor service = Executors.newFixedThreadPool(10);
		CountDownLatch countDownLatch= new CountDownLatch(10);


		for (int i = 0; i < 10; ++i) {

			service.execute(() -> {
				try {
					addItem("1", "1", null);
				} finally {
					countDownLatch.countDown();
				}
			});

		}
		countDownLatch.await();
		ResponseEntity<String> responseViewCart = viewCart("1");

		System.out.println("see" + responseViewCart.getBody());
	}

}
