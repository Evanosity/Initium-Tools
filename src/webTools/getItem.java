package webTools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//this class accepts an item ID and returns a formatted string of that item's relevant information.
//I need to fix this to return proper weapon stats.

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
    	
    	//if this is a weapon, we need to return two lines of text. o well.
    	if(!isArmor(driver.findElement(By.tagName("p")).getText().split(" ")[0])) {
    		exile+=getWeaponInfo(source)+"\n";
    	}
    	
    	//first, the system checks if the item is a cosmetic. if it is, we split.
    	if(!driver.findElement(By.tagName("p")).getText().contains("Cosmetic")){
    		//then, we check if there is a dex penalty field. if not, we assume its 0. (damn legacy items)
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
        	
        	exile+=driver.findElement(By.name("itemName")).getText()/*+"/"*/;
        	//exile+=driver.findElement(By.tagName("p")).getText().split(" ")[0];
    	}
    	
    	return exile;
    }
    
    private static String getWeaponInfo(iDrive source) {
    	WebDriver driver=source.getDriver(); 	
    	String exile="";
    	
    	exile+=driver.findElement(By.name("weaponDamage")).getText().split("\n")[1].split("/")[0]+"/";
    	exile=exile.replace("D", "/"); //this is to separate the dice number and the sides of dice.
    	exile+=driver.findElement(By.name("weaponDamageCriticalChance")).getText().split("\n")[1].split("/")[0].split("%")[0]+"/";
    	exile+=driver.findElement(By.name("weaponDamageCriticalMultiplier")).getText().split("\n")[1].split("/")[0].split("x")[0]+"/";
    	
    	String damageTypes=driver.findElement(By.name("weaponDamageType")).getText().split("\n")[1].split("/")[0];
    	
    	//this parses the full damage type and turns it into what the system can read. also, this is where it notates 2h weapons.
    	damageTypes=damageTypes.toLowerCase();
    	if(damageTypes.contains("blu")) {
    		exile+="b";
    	}
    	if(damageTypes.contains("sla")) {
    		exile+="s";
    	}
    	if(damageTypes.contains("pie")) {
    		exile+="p";
    	}
    	if(driver.findElement(By.tagName("p")).getText().contains("2")) {
    		exile+="t";
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
    
    //check to see if its a piece of armor; really, if its a weapon or not.
    private static boolean isArmor(String slotName) {
    	for(int i=0;i!=slotList.length;i++) {
    		if(slotList[i].contains(slotName)) {
    			return true;
    		}
    	}
    	return false;
    }
}