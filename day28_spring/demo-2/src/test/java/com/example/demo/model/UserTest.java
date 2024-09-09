package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	private static final Logger Logger = LogManager.getLogger(UserTest.class);
	
	public static User user;
	
	public UserTest() {
		this.user = new User("1001", "KIM", "Kim@naver.com");
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// assertTrue(user != null);
		System.out.println("BeforeAll ... ");
		Logger.info("@@BeforeAll ... ");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterAll ... " + user.toString());
		Logger.info("@AfterAll ... " + user.toString());
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("@BeforeEach ... " + user.toString());
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("@AfterEach ... " + user.toString());
	}

	@Test
	void testIsValid() {
		assertTrue(user.isValid());
	}

	@Test
	void testUserBean() {
		user = User.builder().id("0001").name("LEE").email("lee@korea.com").build();
		assertTrue(user.isValid());
	}

}
