package ilabassessment;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TestClass extends TestBaseClass {
   public TestClass() {
      super();
   }
   
   @Test
   public void fillForm() throws InterruptedException 
   {
      // Navigate to iLAB website
      driver.get(getFormData(2));
      TimeUnit.SECONDS.sleep(7);
      
      // Accept Cookies if option is present on the screen
      clickElement("/html/body/div[3]/div[1]/div/div/div[2]/button[3]", "Cookies Accept Button");
      
      
      // Click on Careers link
      clickElement("//*[@id=\"et-main-area\"]/footer/div/div[1]/div[2]/div[1]/div[2]/div/p/span[1]/a", "Careers hyperlink");
      TimeUnit.SECONDS.sleep(5);

      // Click on South Africa link
      clickElement("//*[@id=\"post-285\"]/div/div/div/div[3]/div[2]/div[2]/div", "South Africa hyperlink");
      TimeUnit.SECONDS.sleep(5);

      // Click on the first available job link
      clickElement("//*[@id=\"post-280245\"]/div/div/div/div[2]/div[2]/div/div/div/ul/li[1]/a", "First available job hyperlink");
      TimeUnit.SECONDS.sleep(5);

      // Wait for Hubspot iframe form to load
      navigateToForm();
      
      // Enter first name
      sendKeys("firstname", "First name textbox", getFormData(3));
      TimeUnit.SECONDS.sleep(2);
      
      // Enter last name
      sendKeys("lastname", "Last name textbox", getFormData(4));
      TimeUnit.SECONDS.sleep(2);
      
      // Enter email
      sendKeys("email", "Email textbox", getFormData(5));
      TimeUnit.SECONDS.sleep(2);

      // Generate a 10-digit phone number in the required format
      String phone = getRandomNumber();
      sendKeys("phone", "Phone number textbox", phone);
      TimeUnit.SECONDS.sleep(2);

      // Click on SEND button
      clickElement("/html/body/div/form/div[1]/div[2]/input","Send Button");
      TimeUnit.SECONDS.sleep(2);

      // Validate that the message is displayed
      System.out.println(" ");
      if (isMessageDisplayed("/html/body/div/form/fieldset[5]/div/ul/li/label")) { System.out.println("Message is displayed and it is as follows: " + getFormData(6)); }
      
   }
   
   // Will Generate random 10 digit number
   public String getRandomNumber() 
   { 
	   Random random = new Random();
	   return String.format("08%d", 10000000 + random.nextInt(100000000)); 
   }
   
   @AfterTest
   public void teardown() 
   {
	   // End of test
	   endTest();
   }

   public static void main(String[] args) throws InterruptedException {
	   TestNG testNG = new TestNG();
	   testNG.setTestClasses(new Class[] { TestClass.class });
	   testNG.run();
   }
}
