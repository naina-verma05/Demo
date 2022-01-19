import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class CheckBrokenLinks {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "E:\\NANCY\\BrowseDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		HttpURLConnection huc = null;
		int respCode = 200;
		
		
		// go to homepage
		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

		// maximize the Window
		driver.manage().window().maximize();

		// get all the elements that have anchor tag
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());

		Iterator<WebElement> it = links.iterator();

		// Identify and Validate URLs
		// This step is about checking if a certain URL belongs to a third-party domain
		// or if it is empty/null.
		// The code below will retrieve the href of the anchor tag and store it in the
		// URL variable.

		while (it.hasNext()) {
			String url = it.next().getAttribute("href");

			// If the URL is null or Empty, skip the steps after this.
			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
			}

			// If the URL belongs to the main domain, continue. If it belongs to a third
			// party domain, skip the steps after this.

			if (!url.startsWith("https://www.rahulshettyacademy.com/AutomationPractice/")) {
				System.out.println("URL belongs to another domain, skipping it.");
			}

			// Send HTTP request
			// Methods in the HttpURLConnection class will send HTTP requests and capture
			// the HTTP response code.
			// Therefore, the output of openConnection() method (URLConnection) is type
			// casted to HttpURLConnection.

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();

				respCode = huc.getResponseCode();
				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					System.out.println(url + " is a valid link");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		driver.quit();
	}

}
