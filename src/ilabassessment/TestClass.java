package ilabassessment;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestClass extends TestBaseClass {
   public TestClass() {
      super();
   }

   public void fillForm() throws InterruptedException 
   {
      // Navigate to iLAB website
      driver.get("https://www.ilabquality.com/");
      TimeUnit.SECONDS.sleep(5);
      
      // Accept Cookies
      clickElement("/html/body/div[3]/div[1]/div/div/div[2]/button[3]");
      
      // Click on Careers link
      clickElement("//*[@id=\"et-main-area\"]/footer/div/div[1]/div[2]/div[1]/div[2]/div/p/span[1]/a");
      TimeUnit.SECONDS.sleep(5);

      // Click on South Africa link
      clickElement("//*[@id=\"post-285\"]/div/div/div/div[3]/div[2]/div[2]/div");
      TimeUnit.SECONDS.sleep(5);

      // Click on the first available job link
      clickElement("//*[@id=\"post-280245\"]/div/div/div/div[2]/div[2]/div/div/div/ul/li[1]/a");
      TimeUnit.SECONDS.sleep(5);

      // Enter first name
      sendKeys("//*[@id=\"firstname-91269253-d0ea-4409-a821-873cda679554\"]", getFormData(1));
      TimeUnit.SECONDS.sleep(5);
      
      // Enter last name
      sendKeys("//*[@id=\"lastname-91269253-d0ea-4409-a821-873cda679554\"]", getFormData(2));
      TimeUnit.SECONDS.sleep(5);
      
      // Enter email
      sendKeys("//*[@id=\"email-91269253-d0ea-4409-a821-873cda679554\"]", getFormData(3));
      TimeUnit.SECONDS.sleep(5);

      // Generate a 10-digit phone number in the required format
      String phone = getRandomNumber();
      sendKeys("//*[@id=\"phone-91269253-d0ea-4409-a821-873cda679554\"]", phone);
      TimeUnit.SECONDS.sleep(5);

      // Click on SEND button
      clickElement("//*[@id=\"hsForm_91269253-d0ea-4409-a821-873cda679554\"]/div[1]/div[2]/input");
      TimeUnit.SECONDS.sleep(5);

      // Validate that the message is displayed
      boolean isMessageDisplayed = isMessageDisplayed("//*[@id=\"hsForm_91269253-d0ea-4409-a821-873cda679554\"]/fieldset[5]/div/ul/li/label");
      System.out.println("Message displayed: " + isMessageDisplayed);
   }
   
   // Will Generate random 10 digit number
   public String getRandomNumber() 
   { 
	   Random random = new Random();
	   return String.format("08%d", 10000000 + random.nextInt(100000000)); 
   }

   public static void main(String[] args) throws InterruptedException {
      TestClass testClass = new TestClass();
      testClass.fillForm();
   }
}

