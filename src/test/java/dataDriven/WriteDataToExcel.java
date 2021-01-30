package dataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WriteDataToExcel {
	public WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	Row row;
	Cell cell;

	@BeforeTest
	public void initialization() {
		System.setProperty("webdriver.chrome.driver", "E:\\Testing\\Webdriver\\Chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}

	@Test
	public void testData() throws IOException {

		FileInputStream file = new FileInputStream(
				"E:\\Testing\\SeleniumAutomation\\NewCucumber\\DataDriven\\Utilities\\orangeHRM_scenarios.xlsx");
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(2);
		String sheetName = sheet.getSheetName();
		System.out.println("Sheet Name is :" + sheetName);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			cell = sheet.getRow(i).getCell(0);
			cell.setCellType(CellType.STRING);
			driver.findElement(By.cssSelector("input#txtUsername")).clear();
			driver.findElement(By.cssSelector("input#txtUsername")).sendKeys(cell.getStringCellValue());
			// import Password
			cell = sheet.getRow(i).getCell(1);
			driver.findElement(By.cssSelector("input#txtPassword")).clear();
			driver.findElement(By.cssSelector("input#txtPassword")).sendKeys(cell.getStringCellValue());
			driver.findElement(By.cssSelector("input.button")).click();

			FileOutputStream fos = new FileOutputStream(
					"E:\\\\Testing\\\\SeleniumAutomation\\\\NewCucumber\\\\DataDriven\\\\Utilities\\\\orangeHRM_scenarios.xlsx");

			String message = "Success";
			sheet.getRow(i).createCell(2).setCellValue(message);
			workbook.write(fos);
			new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='welcome']")))
					.click();
			new WebDriverWait(driver, 40)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Logout')]"))).click();
		}

	}

	@AfterTest
	public void teardown() {
		driver.close();
	}

}
