package com.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;


public class DriverFactory {
	  public enum browsers{
		   CHROME, EDGE
	  };
	  
	  public static  WebDriver LevantarBrowser(String browserName, String URL) {
		  WebDriver driver = null;
		  switch(browsers.valueOf(browserName)) {
		  case CHROME:
		  {
			  System.setProperty("webdriver.chrome.driver","C:\\Users\\Usuario\\Documents\\Drivers\\chromedriver.exe");
			  Reporter.log("Abrir Navegador Chrome");
			  driver = new ChromeDriver();
			  break;
		  }
		  case EDGE:
		  {
			  System.setProperty("webdriver.edge.driver", "C:\\Users\\Usuario\\Documents\\Drivers\\msedgedriver.exe");
			  Reporter.log("Abrir Navegador Edge");
			  driver = new EdgeDriver();
			  break;
			  
		  }
		  default:{
			  Reporter.log("No se selecciono correctamente un navegador. Se inicia Chrome por defecto.");
			  System.setProperty("webdriver.chrome.driver","C:\\Users\\Usuario\\Documents\\Drivers\\chromedriver.exe");
			  Reporter.log("Abrir Navegador Chrome");
			  driver = new ChromeDriver();
			  break;			  
		  }
		}
		  
		//maximizar navegador
		  driver.manage().window().maximize();
		  
		//Navegar a la página
		  driver.get(URL);
		  
		  return driver;
	  }
	  
	  public static void CloseBrowser(WebDriver driver) {
		  driver.quit();
	  }

}
