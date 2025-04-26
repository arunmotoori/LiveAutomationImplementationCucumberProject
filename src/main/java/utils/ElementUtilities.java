package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtilities {
	
	WebDriver driver;
	
	public ElementUtilities(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnElement(WebElement element) {
		if(element.isDisplayed() && element.isEnabled()) {
			element.click();
		}
	}
	
	public void enterTextIntoElement(WebElement element,String text) {
		if(isElementDisplayed(element) && element.isEnabled() && !"true".equalsIgnoreCase(element.getDomProperty("readOnly"))){
			element.clear();
			element.sendKeys(text);
		}
	}
	
	public boolean isElementDisplayed(WebElement element) {
		
		boolean b = false;
		
		try {
			b = element.isDisplayed();
		}catch(NoSuchElementException e) {
			b = false;
		}
		
		return b;
		
	}
	
	public String getElementText(WebElement element) {
		String text = "";
		if(isElementDisplayed(element)) {
			text = element.getText();
		}
	    return text;
	}

}
