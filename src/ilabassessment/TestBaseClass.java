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

public class TestBaseClass {
   protected WebDriver driver;
   private XSSFSheet sheet;
   private XSSFWorkbook workbook;

   public TestBaseClass() 
   {
      // Sets up my chrome driver
      System.setProperty("webdriver.chrome.driver", "G:\\chromedriver\\chromedriver.exe");
      driver = new ChromeDriver();
      driver.manage().window().maximize();
      try 
      {
         File file = new File("formData.xlsx");
         FileInputStream fis = new FileInputStream(file);
         workbook = new XSSFWorkbook(fis);
         sheet = workbook.getSheetAt(0);
      } catch (IOException e) {
         System.out.println("Could not read excel file");
      }
   }

   protected void clickElement(String xpath) 
   {
      // This will click on an Element based on it's provided xpath
	  WebElement element = driver.findElement(By.xpath(xpath));
      element.click();
   }

   protected void sendKeys(String xpath, String keys) 
   {
      // This will send keystrokes to be typed in by the test
	  WebElement element = driver.findElement(By.xpath(xpath));
      element.sendKeys(keys);
   }

   protected String getFormData(int row) 
   {
      // This will get the data from the specified row and column in the excel file
      return sheet.getRow(row).getCell(1).getStringCellValue();
   }

   protected boolean isMessageDisplayed(String xpath) 
   {
      //Under here we will check whether the error message is displayed
      WebElement element = driver.findElement(By.xpath(xpath));
      return element.isDisplayed();
   }
}

