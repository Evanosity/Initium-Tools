package webTools;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class iDrive{
	public static WebDriver driver;
	
	/**
	 * 
	 * @param drivePath - default "C:\chromedriver.exe"
	 * @param username
	 * @param password
	 */
	public iDrive(String drivePath, String username, String password) {
		
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		driver = new ChromeDriver();
    	
        String baseUrl = "http://playinitium.com";
        //username= "";
        //password= "";
        
        driver.get(baseUrl);

        //this switches to the login page, and then waits for it to load.
        driver.findElement(By.className("login-signup-switch")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//this will load your email
		List<WebElement> usernameBox = driver.findElements(By.name("email"));
		WebElement[]usernameArray=usernameBox.toArray(new WebElement[usernameBox.size()]);
		usernameArray[1].sendKeys(username);

		//this will load your password
		List<WebElement> passwordBox = driver.findElements(By.name("password"));
		WebElement[]passwordArray=passwordBox.toArray(new WebElement[passwordBox.size()]);
		passwordArray[1].sendKeys(password);
		
		//this logs you in
		List<WebElement> loginBox = driver.findElements(By.className("landing-login-button"));
		WebElement[]loginBut=loginBox.toArray(new WebElement[loginBox.size()]);
		loginBut[1].click();
	}
	/**
	 * 
	 */
	public static void stop() {
		driver.close();
	}
	
    /**
     * Returns the parent of an element. Useful for items, when the information is hidden.
     * @param child
     * @return
     */
    public static WebElement getParent(WebElement child, WebDriver driver) {
		WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].parentNode;", child);
		return parent;
    }
}