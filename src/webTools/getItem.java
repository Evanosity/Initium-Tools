package webTools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//this class accepts an item ID and returns a formatted string of that item's relevant information.

public class getItem {
	
	private static String[]slotList= {"Helmet","Chest","Shirt","Gloves","Legs","Boots","Ring","Ring","Neck"};
	
    public static String getItemInfo(String ID, iDrive source){
    	//if our ID is null, we catch it and return a blank item.
    	if(ID==null) {
    		return "0/0/0/0/0/0/NA";
    	}
    	
    	//these three lines tell chromium to go to the proper webpage
    	WebDriver driver=source.getDriver(); 	
    	String target="https://www.playinitium.com/odp/viewitemmini?itemId="+ID;
    	driver.get(target);
    	
    	String exile=""; //this is the string that we return, full of all the item info.
    	
    	if(!driver.findElement(By.tagName("p")).getText().contains("Cosmetic")){
    		if(!driver.findElements(By.name("dexterityPenalty")).isEmpty()) {
            	exile+=driver.findElement(By.name("dexterityPenalty")).getText().split("\n")[1].split("%")[0]+"/";
        	}
        	else {
        		exile+="0/";
        	}
        	exile+=driver.findElement(By.name("blockChance")).getText().split("\n")[1].split("%")[0]+"/";
        	exile+=driver.findElement(By.name("damageReduction")).getText().split("\n")[1]+"/";
        	
        	exile+=findModifier(driver.findElement(By.name("blockBludgeoningCapability")).getText().split("\n")[1])+"/";
        	exile+=findModifier(driver.findElement(By.name("blockPiercingCapability")).getText().split("\n")[1])+"/";
        	exile+=findModifier(driver.findElement(By.name("blockSlashingCapability")).getText().split("\n")[1])+"/";

        	exile+=driver.findElement(By.name("durability")).getText().split("\n")[1].split("/")[0]+"/";
        	
        	if(!driver.findElements(By.name("strengthModifier")).isEmpty()) {
            	exile+=driver.findElement(By.name("strengthModifier")).getText().split("\n")[1].split("%")[0]+"/";
        	}
        	if(!driver.findElements(By.name("intelligenceModifier")).isEmpty()) {
            	exile+=driver.findElement(By.name("intelligenceModifier")).getText().split("\n")[1].split("%")[0]+"/";
        	}
        	
        	exile+=driver.findElement(By.name("itemName")).getText()+"/";
        	exile+=driver.findElement(By.tagName("p")).getText().split(" ")[0];
    	}
    	
    	return exile;
    }
    
    //this method changes a string damage modifier into a number.
    private static String findModifier(String original) {
    	if(original.equals("None")) {
    		return "0";
    	}
    	else if(original.equals("Minimal")) {
    		return "0.5";
    	}
    	else if(original.equals("Poor")) {
    		return "0.75";
    	}
    	else if(original.equals("Average")) {
    		return "1";
    	}
    	else if(original.equals("Good")) {
    		return "1.5";
    	}
    	else if(original.equals("Excellent")) {
    		return "2";
    	}
    	return "";
    }
    
    //currently unused;
    private static boolean isArmor(String slotName) {
    	for(int i=0;i!=slotList.length;i++) {
    		if(slotList[i].contains(slotName)) {
    			return true;
    		}
    	}
    	return false;
    }
}