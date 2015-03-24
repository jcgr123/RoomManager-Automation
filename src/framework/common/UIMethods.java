package framework.common;

import java.io.File; 
 import java.io.IOException; 
 
 import org.apache.commons.io.FileUtils; 
 import org.openqa.selenium.By; 
 import org.openqa.selenium.NoSuchElementException; 
 import org.openqa.selenium.OutputType; 
 import org.openqa.selenium.TakesScreenshot; 
 import org.openqa.selenium.WebDriver; 
 import org.openqa.selenium.WebElement; 
 import org.openqa.selenium.interactions.Actions; 


import framework.selenium.SeleniumDriverManager;

/**
 * 
 * @author Ruben Blanco
 *
 */
public class UIMethods {
	static WebDriver driver = SeleniumDriverManager.getManager().getDriver();	

	public static void doubleClick(WebElement webElement) {
		Actions action = new Actions(driver);
		action.doubleClick(webElement);
		action.perform();
	}

	public static boolean isElementPresent(By element) {
		boolean present;
		try {			
			driver.findElement(element);
			present=true;
		} catch (NoSuchElementException ex) {
			present = false;
		}
		return present;
	}

	public static void refresh() {
		driver.navigate().refresh();
	}

	/**
	 * This method is a workaround to wait for mask to disappear for Chrome
	 * @param webElement
	 */
	public static void waitForMaskDisappears(WebElement webElement) {
		boolean value = false;
		while (value == false) {
			try {
				webElement.click();
				value = true;
			}
			catch (Exception e) {
				value = false;
			}
		} 
	}

	/** 
	 * [YA]This method takes screenshots 
	 * @param filePath 
	 * @param fileName 
	 * @throws IOException 
	 */ 
	public static void takeScreenShot(String filePath, String fileName) throws IOException { 
		try { 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			FileUtils.copyFile(scrFile, new File(filePath + fileName)); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 
}
