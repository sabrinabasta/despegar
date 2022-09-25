package pageObjects;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DespegarResultsPage {

	@FindBy (css = "i[class='tooltip-close eva-3-icon-close']")
	WebElement btnCloseExperiencias;
	
	@FindBy (css = "*[class='cluster-description cluster-description-top']")
	WebElement primerAlojamiento;
	
	private WebDriver driver = null;
	private String mainTab = null;
	private WebDriverWait wait = null;
	
	public DespegarResultsPage(WebDriver driver) {
		this.driver = driver;
		this.wait =  new WebDriverWait(driver,10);
		this.mainTab = this.driver.getWindowHandle();
		PageFactory.initElements(driver, this);
	}
	
	public  void cambioDePestaña() {
		  System.out.println("Main tab: " + mainTab);
		  Set<String> handles = driver.getWindowHandles(); //Se crea un set de strings con los identificadores de las pestañas del navegador
		  for(String actual: handles) {
			  System.out.println("Handles ID: " + actual);
			  
			  if(!actual.equalsIgnoreCase(mainTab)) {
				  System.out.println("Changing Tab");
				  driver.switchTo().window(actual); //Se cambia el foco a la pestaña actual
			  }
		  }
	}
	
	public void closeExperiencias() {
		this.wait.until(ExpectedConditions.visibilityOf(btnCloseExperiencias));
		this.btnCloseExperiencias.click(); 
		
	}
	public DespegarAlojamientoSeleccionadoPage seleccionarAlojamiento() {
		this.primerAlojamiento.click();
		return new DespegarAlojamientoSeleccionadoPage(this.driver);
	}
}
