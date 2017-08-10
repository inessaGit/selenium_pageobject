package test.example;


import org.testng.annotations.Test;

public class TestNg2 extends BaseTestNg {
	
	public TestNg2(){
		System.out.println("Inside of constructor for TestNg2");
	}
	
	@Test
	public static void test2(){
		System.out.println("Inside of @Test in TestNg2");

	}
	
	@Test
	public static void test3(){
		System.out.println("Inside of @Test 2nd time in TestNg2");

	}
	
	@Test
	public static void test4(){
		System.out.println("Inside of @Test 3d time in TestNg2");

	}

}