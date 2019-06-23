package com.credit.tiaa;

import com.credit.tiaa.controller.CCGenerationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

	@Autowired
	private CCGenerationController ccGenerationController;

	@Test
	public void contextLoads() {
		assertThat(ccGenerationController).isNotNull();
	}

}
