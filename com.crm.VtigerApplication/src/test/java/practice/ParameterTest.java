package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParameterTest {
	@Test
	public void credentials() throws Throwable {
		String browser = System.getProperty("browser");
		String url = System.getProperty("url");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
		
		System.out.println(browser);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		WebDriver driver=null;
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		} else {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		} 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(10000);
		WebElement hover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions= new Actions(driver);
		actions.moveToElement(hover).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}
}
