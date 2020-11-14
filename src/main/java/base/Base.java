package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	WebDriver driver;
	Properties prop;

	public WebDriver init_driver(String browsername) {
		if (browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"E://Testing//Webdriver//chromedriver_win32//chromedriver.exe");
			if (prop.getProperty("headless").equals("yes")) {
				// headlessmode
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			} else {
				driver = new ChromeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			}
		}

		return driver;
	}// init

	public Properties init_properties() {
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(
					"E:\\Testing\\SeleniumAutomation\\BarcodeTest\\KDF\\src\\main\\java\\config\\config.properties");
			try {
				prop.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;
	}// initProp

}
