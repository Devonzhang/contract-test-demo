package com.learn.graphql;

import com.learn.graphql.client.BookFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients(clients = {BookFeignClient.class})
class GraphqlApplicationTests {

	@Test
	void contextLoads() {
	}

}
