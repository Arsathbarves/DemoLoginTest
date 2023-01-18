package loginPage;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPage {
	
	
	
	

	static String sort;
	public WebDriver driver;

	// Browser setup
	@BeforeMethod
	public void BrowserSetup() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://sakshingp.github.io/assignment/login.html");
	}

//Test a login page with valid credential
	@Test
	public void TestSuccessfulLogin() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("testuser");
		driver.findElement(By.id("password")).sendKeys("testpassword");
		driver.findElement(By.id("log-in"));
		driver.findElement(By.id("log-in")).click();

		driver.findElement(By.id("amount")).click();
		;

		// Validate sort operation

		List<WebElement> columnname = driver.findElements(By.xpath("//tr/td[5]"));

		String[] beforesort = new String[columnname.size()];
		for (int i = 0; i < columnname.size(); i++) {
			beforesort[i] = columnname.get(i).getText().trim();

		}
		for (int i = 0; i < columnname.size(); i++) {
			Arrays.sort(beforesort);
			sort = beforesort[i];

		}

		columnname = driver.findElements(By.xpath("//tr/td[5]"));
		String[] aftersort = new String[columnname.size()];
		for (int i = 0; i < columnname.size(); i++) {
			aftersort[i] = columnname.get(i).getText().trim();

		}

		for (int i = 0; i < columnname.size(); i++) {
			Arrays.sort(aftersort);
			sort = aftersort[i];

		}

		Assert.assertEquals(aftersort, beforesort);
	}

	// Test a login page blank credential
	@Test
	public void TestFailedLogin() {
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("log-in")).click();

	}

	// Test a login page with user name only
	@Test
	public void TestFailedLogin1() {
		driver.findElement(By.id("username")).sendKeys("testuser");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.id("log-in")).click();

	}

	// Test a login page with user password only
	@Test
	public void TestFailedLogin2() {
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("testpassword");
		driver.findElement(By.id("log-in")).click();

	}

//Browser close
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}

