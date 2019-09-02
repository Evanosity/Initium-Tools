package webTools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class getItem {
	//doesnt work yet
	//Need to figure out how to find an element based on its parent and its name
    private static String getItem(String ID, WebDriver driver){
    	
    	String target="https://www.playinitium.com/odp/viewitemmini?itemId="+ID;
    	driver.get(target);
    	
    	String exile="";
    	
    	WebElement A=driver.findElement(By.name("dexterityPenalty"));
    	String B=A.findElement(By.xpath("..main-item-subnote")).getText();
    	
    	
    	
    	exile+=driver.findElement(By.name("dexterityPenalty")).getText()+"/";
    	exile+=driver.findElement(By.name("blockChance")).getText()+"/";
    	System.out.println(B);
    	
    	return exile;
    }
}
