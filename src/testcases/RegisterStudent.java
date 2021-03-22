package testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utility.Constants;
import utility.ExcelUtilities;

public class RegisterStudent {
	
	static WebDriver driver;	
	static ExcelUtilities excelutil=new ExcelUtilities();
	static String excelpath=Constants.path+Constants.excel;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",Constants.driverpath);
		 driver=new ChromeDriver();
		 
		 driver.get(Constants.url);
		 
		 driver.manage().window().maximize();
		 
		    WebElement firstName=driver.findElement(By.id("firstName"));
	        WebElement lastName=driver.findElement(By.id("lastName"));
	        WebElement email=driver.findElement(By.id("userEmail"));
	        WebElement genderMale= driver.findElement(By.id("gender-radio-1"));
	        WebElement genderfemale= driver.findElement(By.id("gender-radio-2"));
	        WebElement mobile=driver.findElement(By.id("userNumber"));
	        WebElement address=driver.findElement(By.id("currentAddress"));
	        WebElement submitBtn=driver.findElement(By.id("submit"));
	        
	        excelutil.setExcelFile(excelpath, "Sheet1");
	        for(int i=1;i<=excelutil.getrowcount();i++)
	        {
	        	firstName.sendKeys(excelutil.getcelldata(i,0));
	            lastName.sendKeys(excelutil.getcelldata(i,1));
	            email.sendKeys(excelutil.getcelldata(i,2));
	             mobile.sendKeys(excelutil.getcelldata(i,4));
	            address.sendKeys(excelutil.getcelldata(i,5));
	            
	            String gender=excelutil.getcelldata(i,3);
	            
	            if(gender.equals("male"))
	            {
	            	JavascriptExecutor exec= (JavascriptExecutor)driver;
	            	exec.executeScript("arguments[0].click()",genderMale);
	            	System.out.println(gender);
	            }
	            else if(gender.equals("female"))
	            {
	            	JavascriptExecutor exec= (JavascriptExecutor)driver;
	            	exec.executeScript("arguments[0].click()",genderfemale);
	            	System.out.println(gender);
	            }
	            submitBtn.click();
	            WebElement confirmationMessage = driver.findElement(By.xpath("//div[text()='Thanks for submitting the form']"));
	            
	            if (confirmationMessage.isDisplayed()) {
	                // if the message is displayed , write PASS in the excel sheet using the method of ExcelUtils
	                excelutil.setcellvalue(i,6,"PASS",excelpath);
	               } else {
	                   //if the message is not displayed , write FAIL in the excel sheet using the method of ExcelUtils
	            	   excelutil.setcellvalue(i,6,"FAIL",excelpath);
	               }
	    
	               //close the confirmation popup
	               WebElement closebtn=driver.findElement(By.id("closeLargeModal"));
	               closebtn.click();
	            
	               //wait for page to come back to registration page after close button is clicked
	               driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
	           }
	           //closing the driver
	           driver.quit();
		

	}

}
