package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
 public static  WebDriver driver;
 // To launch driver
 public static WebDriver getDriver() {
	 // WebDriverManager.chromedriver().setup();
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\ASUS\\eclipse-workspace\\LennoxTask\\driver\\chromedriver.exe");
	 
	 driver=new ChromeDriver();
	 return driver;	
 }

 // To launch url
 public static void launchUrl(String url) {
	driver.get(url);
	driver.manage().window().maximize();
 }
 
 //To set the value for sendkeys
 public static void enterText(WebElement element, String data) {
	element.sendKeys(data);
 }
 
 // methods to  click button functionality
 public static void btnClk(WebElement  element) {
	element.click();
 }
 
//Method for scrolldown using javascript
 public static void scrolldown(WebElement element) {
	JavascriptExecutor executor=(JavascriptExecutor)driver;
	executor.executeScript("arguments[0].scrollIntoView(false)", element);
 }
 
//Method for setAttribute using JavaScriptExecutor
 public static void setValue(String data,WebElement element) {
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		executor.executeScript("arguments[0].setAttribute('value','"+data+"')", element);
	 }
 
// To read the value from excel
 public static String readExcel(int row ,int cel) throws IOException {
	File file = new File("C:\\Users\\ASUS\\eclipse-workspace\\LennoxTask\\Excel\\Updated.xlsx");	
	FileInputStream inputStream = new FileInputStream(file);
	Workbook workbook=new XSSFWorkbook(inputStream);
	Sheet sheet = workbook.getSheet("Sheet1");
	Row rows = sheet.getRow(row);
	Cell cell = rows.getCell(cel);
	String value = cell.getStringCellValue();
  return value;
 }
 
//To write the value in excel
 public static void writeExcel(int row, int cel, String data) throws IOException {
	File file = new File("C:\\Users\\ASUS\\eclipse-workspace\\LennoxTask\\Excel\\Updated.xlsx");
	FileInputStream inputStream = new FileInputStream(file);
	Workbook workbook=new XSSFWorkbook(inputStream);
	Sheet sheet = workbook.getSheet("Sheet1");
	Row rows = sheet.getRow(row);
	Cell cell = rows.createCell(cel);
	cell.setCellValue(data);
	FileOutputStream outputStream = new FileOutputStream(file);
	workbook.write(outputStream);
 }
 
//To select by value
 public static void select(WebElement element,String data) {
	Select select=new Select(element);
	select.selectByVisibleText(data);
 }
 
 //Methor for wait
 public static void waitvisibleEle(WebElement ele) {
	 WebDriverWait w1=new WebDriverWait(driver,60);
	 w1.until(ExpectedConditions.elementToBeClickable(ele));

}
 
 
 public static void waitUntillClickable( WebElement element) {
    WebDriverWait wait=new WebDriverWait(driver,90);
    wait.until(ExpectedConditions.elementToBeClickable(element));
 }
 
 public static void waitTime() throws InterruptedException {
	Thread.sleep(3000);
		}
	
	
	

}
