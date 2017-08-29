package test.example;

import org.testng.annotations.Test;


public class TestNg1 extends BaseTestNg{
	
	
	public TestNg1(){
		System.out.println("Inside of constructor for TestNg1");
	}
	

	@Test
	public static void test1(){
		System.out.println("Inside of @Test in TestNg1");

	}

	@Test
	public static void test3(){
		System.out.println("Inside of @Test 2nd time in TestNg1");

	}
	
}
