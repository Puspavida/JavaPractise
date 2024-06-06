import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



//written for wait resuable fucntionality and keep the color 

public class wait {
	public static long waitDuration;
	
	public static void main(String[] args){
		System.setProperty("webdriver.chrome.driver", "D://chromedriver-win64//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		/*long currentTime = System.currentTimeMillis();
		System.out.println(currentTime);
		waitDuration = !System.getProperty("TIMEOUT","").isEmpty()?Long.valueOf(System.getProperty("TIMEOUT")):3000;
		while(System.currentTimeMillis()-currentTime<waitDuration){
			WebElement tisko =driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
			if(tisko.isDisplayed()) {
				tisko.sendKeys("Book");
				driver.close();
			}*/

		WebElement giftcard=driver.findElement(By.xpath("//*[@id='nav-xshop'] //*[contains(text(),'Today')]"));
		waitElement(driver, giftcard);
		giftcard.click();
		
		String color = driver.findElement(By.xpath("//*[contains(text(),'Safari Ray')][2]")).getCssValue("color");
		String hex = Color.fromString(color).asHex();//convert Hexa decimal
		System.out.println(hex);
		}
	
		public static void waitElement(WebDriver driver, WebElement att) {
			WebDriverWait waitEle = new WebDriverWait(driver,Duration.ofSeconds(36));
			boolean result = waitEle.until(ExpectedConditions.visibilityOf(att)).isDisplayed();
			
						
			System.out.println("Result: "+result);
			
			
		}

	}

