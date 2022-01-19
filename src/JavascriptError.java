import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class JavascriptError {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "E:\\NANCY\\BrowseDrivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		
		driver.findElement(By.cssSelector("a[href*='angular']")).click();
		
		driver.findElement(By.linkText("Selenium")).click();
		
		driver.findElement(By.cssSelector(".add-to-cart")).click();
		
		driver.findElement(By.linkText("Cart")).click();
		
		driver.findElement(By.id("exampleInputEmail1")).clear();
		
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");
		
		LogEntries entry = driver.manage().logs().get(LogType.BROWSER); //get logEntries object
		List<LogEntry> logs = entry.getAll(); //return type of getall is List - returns all logs in the list
		
		//iterating through list and printing each log
		for(LogEntry e: logs) {
			System.out.println(e.getMessage());
		}
	}

}
