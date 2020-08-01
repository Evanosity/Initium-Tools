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
    public static String[]getChar(String ID, iDrive source){
    	
    	String target="http://playinitium.com/odp/viewcharactermini?characterId="+ID;
    	
    	WebDriver driver=source.getDriver();
    	
    	driver.get(target);
    	
    	String[]exile=new String[17];
    	
    	//this loads the character's information into memory.
    	exile[0]=driver.findElement(By.className("character-display-box-info")).getText().split("\n")[0];
    	exile[1]=driver.findElement(By.name("strength")).getText();   
    	exile[2]=driver.findElement(By.name("dexterity")).getText();    	
    	exile[3]=driver.findElement(By.name("intelligence")).getText();
    	exile[4]=driver.findElement(By.className("character-display-box-info")).getText().split("\n")[1];
    	
    	//this loads the item IDs associated with this character, in equip order, into memory.
    	List<WebElement> itemList=driver.findElements(By.className("main-item-name"));
		WebElement[]itemIDS=itemList.toArray(new WebElement[itemList.size()]);
		
		
		for(int i=0;i!=itemIDS.length;i++){
			itemIDS[i]=iDrive.getParent(itemIDS[i],driver);
			exile[i+5]=itemIDS[i].getAttribute("rel").split("=")[1];
			
			WebElement x=iDrive.getParent(itemIDS[i], driver);
			WebElement y=iDrive.getParent(x, driver);
			
		}

    	return exile;
    }
}
