package webTools;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class getCharacter {
	   /**
     * This method will turn a character ID into a string array of all the information about that character.
     * @param ID
     * @param driver
     * @return
     */
    private static String[]getCharacter(String ID, WebDriver driver){
    	
    	String target="http://playinitium.com/odp/viewcharactermini?characterId="+ID;
    	
    	driver.get(target);
    	
    	String[]exile=new String[16];
    	
    	exile[0]=driver.findElement(By.className("character-display-box-info")).getText();
    	exile[1]=driver.findElement(By.name("strength")).getText();   
    	exile[2]=driver.findElement(By.name("dexterity")).getText();    	
    	exile[3]=driver.findElement(By.name("intelligence")).getText();  
    	
    	List<WebElement> itemList=driver.findElements(By.className("main-item-name"));
		WebElement[]itemIDS=itemList.toArray(new WebElement[itemList.size()]);
		
		for(int i=0;i!=itemIDS.length;i++){
			itemIDS[i]=iDrive.getParent(itemIDS[i],driver);
			exile[i+4]=itemIDS[i].getAttribute("rel");
		}

    	return exile;
    }
}
