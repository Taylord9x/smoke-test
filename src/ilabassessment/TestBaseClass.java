package ilabassessment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.AWTException;	
import java.awt.Robot;	
import java.awt.event.KeyEvent;	


public class TestBaseClass {
   protected WebDriver driver;
   private XSSFSheet sheet;
   private XSSFWorkbook workbook;

   public TestBaseClass() 
   {    
      try 
      {
         File file = new File("formData.xlsx");
         FileInputStream fis = new FileInputStream(file);
         workbook = new XSSFWorkbook(fis);
         sheet = workbook.getSheetAt(0);
      } catch (IOException e) {
         System.out.println("Could not read excel file");
      }
      
      // Check which browser will the test be running on
   	  if (getFormData(1).equals("chrome")) 
   	  {
   		  // Sets up my chrome driver
   	      System.setProperty("webdriver.chrome.driver", "G:\\chromedriver\\chromedriver.exe");
   	      driver = new ChromeDriver();
   	  }
   	  else if (getFormData(1).equals("msedge")) 
   	  {
   		  // Sets up my microsoft edge driver
	   	  System.setProperty("webdriver.edge.driver", "G:\\msedgedriver\\msedgedriver.exe");
	   	  driver = new EdgeDriver();
   	  }
   	  
      driver.manage().window().maximize();
   }

   protected void clickElement(String xpath, String description) throws InterruptedException 
   {
	   // Using robot class to scroll down so that South Africa hyperlink because available in the DOM
	   if (description == "South Africa hyperlink") 
	   {
		   Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  // Robot class throws AWT Exception	
	       int counter = 7;
	       while (counter >0) 
	       {
	    	   Thread.sleep(2000); // Thread.sleep throws InterruptedException	
	           robot.keyPress(KeyEvent.VK_DOWN);
	           counter--;
	       }
	   }
	  // Check if the element is present first on the screen before clicking it
	  if (isElementVisible(xpath,0)) 
      {
		  // This will click on an Element based on it's provided xpath
		  WebElement element = driver.findElement(By.xpath(xpath));
	      element.click();
      }
	  else 
	  {
		  System.out.println("Cannot locate the "+description+" from the screen.");
		  driver.quit();
	  }
    
   }

   protected void navigateToForm()
   {
	   WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds((long) 10.0));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//DIV[@id='register']")));
       driver.switchTo().frame("hs-form-iframe-0");
   }
   
   protected void sendKeys(String name, String description, String keys) 
   {
      // This will send keystrokes to be typed in by the test
	  if (isElementVisible(name,1)) 
	  {
		  WebElement element = driver.findElement(By.name(name));
		  element.sendKeys(keys);
	  }
	  else 
	  {
		  System.out.println("Cannot locate the "+description+" from the screen.");
		  driver.quit();
	  }
   }
   
   protected boolean isElementVisible(String elementSelector, int selector) 
   {
	   boolean elementDisplayed = false;
	   if (selector == 0) { elementDisplayed = driver.findElement(By.xpath(elementSelector)).isDisplayed(); }
	   else if (selector == 1) { elementDisplayed = driver.findElement(By.name(elementSelector)).isDisplayed(); }
	   return elementDisplayed;
   }
   
   protected String getFormData(int row) 
   {
      // This will get the data from the specified row and column in the excel file
      return sheet.getRow(row).getCell(1).getStringCellValue();
   }

   protected boolean isMessageDisplayed(String xpath) 
   {
      //Under here we will check whether the error message is displayed
      if (isElementVisible(xpath,0)) 
      {
    	  WebElement element = driver.findElement(By.xpath(xpath));
    	  String displayedText = element.getText();
    	  if (displayedText.equals(getFormData(6))) { return true; }
    	  else
    	  {
    		  System.out.println("The expected text does not match the one that is displayed on the page.");
    		  driver.quit();
    		  return false;
    	  }
      }
      else 
      {
    	  System.out.println("Cannot locate the error message on the screen.");
		  driver.quit();
		  return false;
      }

   }
   
   protected void endTest() { driver.quit(); }
   
}

