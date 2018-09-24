package selenium;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FeedbackTest {
	
	
	public static String username="rene1test";
	public static String password="testing123";
	public static String tagasiside="";
	public static void main(String[] args) {
		
		randomTagasiside(); //initialize tagasiside
		testChrome(); //
	}
	
	// To make the test runnable multiple times a random text is needed. If we would use a constant value, the test might not reveal problems.
	public static void randomTagasiside() {
	    String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    Random rand= new Random();
	    for(int i=0;i<10;i++) {
	    	tagasiside +=characters.charAt(rand.nextInt(characters.length()));
	    }
	}
	public static void testChrome() {
		
		//Open browser
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\renep\\Downloads\\chromedriver_win32\\chromedriver.exe");
		final WebDriver driver=new ChromeDriver();
		driver.get("https://kask6iktundubkorras.sayat.me/");
		
		//Log in using given credentials
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[1]/ul[2]/li[2]/a")).click(); 				
		driver.findElement(By.id("fburl_d")).sendKeys(username);												
	    driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form[2]/div/input[1]")).sendKeys(password);	
	    driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form[2]/div/button")).click(); 	
	    giveFeedback(driver);
	};
		
		public static void giveFeedback(WebDriver driver) {
			
			//Give feedback to the user
		    driver.get("https://kask6iktundubkorras.sayat.me/test2pringi");
		    driver.findElement(By.name("write")).sendKeys(tagasiside);
		    driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[3]/button")).click();
		    
		    //Check if feedback was saved
		    driver.get("https://kask6iktundubkorras.sayat.me/rene1test/sent");
		    driver.findElements(By.className("post-answer-text"));
		    if(driver.findElements(By.className("post-answer-text")).get(0).getText().equals(tagasiside)) {
		    	System.out.println("Test successful!");	
		    	System.out.println("Feedback added:" + tagasiside);
		    }else {
		    	System.out.println("Test failed!");
		    }
		    driver.close();
		}
}
