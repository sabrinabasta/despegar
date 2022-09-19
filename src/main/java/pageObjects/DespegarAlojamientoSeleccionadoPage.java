package pageObjects;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DespegarAlojamientoSeleccionadoPage {

	@FindBy (xpath = "//em[contains(text(),'Modificar')]")
	WebElement btnModificar;
	
	private WebDriver driver = null;
	private String mainTab = null;
	public DespegarAlojamientoSeleccionadoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean botonVisible() {
		return this.btnModificar.isDisplayed();
	}	
	

}
