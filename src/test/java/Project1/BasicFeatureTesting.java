package Project1;

import java.time.Duration;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicFeatureTesting {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	@BeforeTest
	void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://test-bams.web.app/");
		js =(JavascriptExecutor) driver;
		
	}
	@Test(priority = 1)
	void login() {
		driver.findElement(By.id("email")).sendKeys("test101@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Test@101");
		driver.findElement(By.xpath("//button[text()='Log In']")).click();
	}
	@Test(priority = 2)
	void dashbord() {
		String title= driver.getTitle();
		Assert.assertEquals(title,"Rai-Kpsr-Bams");
	}
	
	@Test(priority = 3)
	void addStudentUsingForm() throws InterruptedException{
	
		WebElement mangeStud = driver.findElement(By.xpath("//span[normalize-space()='Manage Student']"));
		js.executeScript("arguments[0].click()", mangeStud);
		driver.findElement(By.xpath("//div[@class='MuiGrid-root css-1ceokph']//button[@type='button']")).click();
		driver.findElement(By.name("admissionNo")).sendKeys(alphNumberic());
		driver.findElement(By.name("firstName")).sendKeys(generatedString());
		driver.findElement(By.name("lastName")).sendKeys(generatedString());
		driver.findElement(By.name("fathersName")).sendKeys(generatedString());
		driver.findElement(By.xpath("//div[@class='MuiGrid-root css-97e1ul']//div[3]//div[2]//div[1]//div[1]//div[1]")).click();
		driver.findElement(By.xpath("//li[normalize-space()='CLASS 2']")).click();
		driver.findElement(By.xpath("//div[@class='MuiGrid-root css-97e1ul']//div[3]//div[3]//div[1]//div[1]//div[1]")).click();
		driver.findElement(By.xpath("//li[normalize-space()='A']")).click();
		driver.findElement(By.xpath("//div[4]//div[1]//div[1]//div[1]//div[1]")).click();
		driver.findElement(By.xpath("//li[normalize-space()='M']")).click();
		driver.findElement(By.name("mobileNo")).sendKeys(alphNumberic());
		driver.findElement(By.xpath("//div[4]//div[3]//div[1]//div[1]//div[1]")).click();
		driver.findElement(By.xpath("//li[normalize-space()='BUS BUS NO 01 CG-04E-2663']")).click();
		driver.findElement(By.name("dateOfBirth")).click();
	}
	
	@Test(priority = 4)
	void logout() throws InterruptedException {
		WebElement logOut = driver.findElement(By.xpath("//span[normalize-space()='Log Out']"));
		js.executeAsyncScript("arguments[0].click()", logOut);
		wait.until(ExpectedConditions.alertIsPresent());
		Actions a = new Actions(driver);
		WebElement resource = driver.findElement(By.xpath("//button[normalize-space()='Ok']"));
		a.moveToElement(resource).perform();
		}
		
		
	public String generatedString() {
		String genString = RandomStringUtils.randomAlphabetic(8);
		return genString;
	}
	String alphNumberic() {
		String genAlphNum = RandomStringUtils.randomAlphanumeric(8);
		return genAlphNum;
		
	}
	long Number() {
		long RandomNum = (long) (Math.random()*Math.pow(10,10));
		
		return RandomNum;
		
	}
	
@AfterTest
	void tearDown() throws InterruptedException {
	Thread.sleep(400);
	driver.close();
	}

}
