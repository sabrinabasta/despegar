package com.trabajopractico1.testing.TrabajoPractico1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
 

public class DespegarTest {
	
	//Declaraciones
	WebDriver driver;
	WebDriverWait wait;
	
	
	//Locators
	By btnAlojamientosLocator = By.cssSelector("i[class='shifu-icon-product shifu-3-icon-hotels']");
	By destinoLocator = By.cssSelector("input[placeholder='Ingresá una ciudad, alojamiento o punto de interés']");
	By menuLocator = By.cssSelector("div[class='ac-wrapper -desktop -show sbox-ab-ls']");
	By entradaLocator = By.cssSelector("input[placeholder='Entrada']");
	By salidaLocator = By.cssSelector("input[placeholder='Salida']");
	By habitacionesLocator = By.xpath("//div[@id='BACKGROUND_MESSAGE']//div[2]/input[1]"); 
	By fechaEntradaLocator = By.xpath("//div[@id='component-modals']//div[29]/div[1]"); 
	By fechaSalidaLocator = By.xpath("//div[@id='component-modals']//div[2]/div[3]/div[13]/div[1]"); 
	By btnBuscar = By.xpath("//em[contains(text(),'Buscar')]");
	By iniciarSesionLocator = By.cssSelector("i[class='login-incentive--close shifu-3-icon-close -eva-3-mr-md']");
	By addAdultsLocator = By.xpath("//div[@id='component-modals']//div[1]/button[2]"); 
	By addMenorsLocator = By.xpath("//div[@id='component-modals']//div[2]/div[2]//button[2]"); 
	By edadMenorLocator = By.cssSelector("select[class='select']");
	By alojamientoLocator = By.cssSelector("div[class='cluster-description cluster-description-top']");
	By experienciasCloseLocator = By.cssSelector("i[class='tooltip-close eva-3-icon-close']");
	By btnModificarLocator = By.xpath("//em[contains(text(),'Modificar')]");
	
	
  @BeforeClass
  public void setUp() throws Exception {
	  //Se crea el driver y se le hace un get a la url donde se va a realizar el test
	  System.setProperty("webdriver.chrome.driver", "./resources/chromedriver/chromedriver.exe" );
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://www.despegar.com.ar/hoteles/"); //url de la pestaña de alojamientos de despegar
	  
	  //Se crea un objeto wait
	  wait = new WebDriverWait(driver,10);
	  	  
  }
  
  @Test
  public void alojamientoTest() throws InterruptedException {
	  
	  String mainTab = driver.getWindowHandle();
	  
	  //Si se visualiza el elemento destinoLocator...
	  if (driver.findElement(destinoLocator).isDisplayed()) {
		  
		  
		  driver.findElement(iniciarSesionLocator).click();
		  
		  driver.findElement(destinoLocator).click();
		  driver.findElement(destinoLocator).click();
		  driver.findElement(destinoLocator).clear();
		  driver.findElement(destinoLocator).sendKeys("Córdoba ");
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(menuLocator));
		  driver.findElement(destinoLocator).sendKeys(Keys.ENTER);
		  wait.until(ExpectedConditions.invisibilityOfElementLocated(menuLocator));
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(entradaLocator));
		  driver.findElement(entradaLocator).click();
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(fechaEntradaLocator));
		  driver.findElement(fechaEntradaLocator).click();
		  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(salidaLocator));
		  driver.findElement(salidaLocator).click();
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(fechaSalidaLocator));
		  driver.findElement(fechaSalidaLocator).click();
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(habitacionesLocator));
		  driver.findElement(habitacionesLocator).click();
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(addAdultsLocator));
		  driver.findElement(addAdultsLocator).click();
		  driver.findElement(addMenorsLocator).click();
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(edadMenorLocator));
		  driver.findElement(edadMenorLocator).click();
		  driver.findElement(edadMenorLocator).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		  
		  driver.findElement(btnBuscar).click();
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(alojamientoLocator));
		  driver.findElement(experienciasCloseLocator).click();
		  driver.findElement(alojamientoLocator).click(); //El locator alojamientoLocator identifica a más de un elemento de la página pero al utilizar findElement elige el primero que encuentra
		  
		  System.out.println("Main tab: " + mainTab);
		  Set<String> handles = driver.getWindowHandles(); //Se crea un set de strings con los identificadores de las pestañas del navegador
		  for(String actual: handles) {
			  System.out.println("Handles ID: " + actual);
			  
			  if(!actual.equalsIgnoreCase(mainTab)) {
				  System.out.println("Changing Tab");
				  driver.switchTo().window(actual); //Se cambia el foco a la pestaña actual
			  }
		  }
		  
		  assertTrue(driver.findElement(btnModificarLocator).isDisplayed()); //Se valida en la pestaña actual que se visualice el botón modifcar
		      
	  }
	  //Si no se visualiza el elemento destinoLocator...
	  else {
		  System.out.print("No se encontró");
	  }
  }

  @AfterClass
  public void tearDown() throws Exception {
	  
	  //Se cierra la instancia del controlador web, se cierran todas las ventanas.
	  driver.quit(); 

  }
  
}
