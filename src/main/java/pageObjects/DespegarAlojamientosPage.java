package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class DespegarAlojamientosPage {
	private static final String element = null;

	@FindBy(css = "input[placeholder='Ingresá una ciudad, alojamiento o punto de interés']")
	WebElement destinoAlojamiento;
	
	@FindBy(css ="div[class='ac-wrapper -desktop -show sbox-ab-ls']")
	WebElement menuAlojamiento;
	
	@FindBy (css = "input[placeholder='Entrada']")
	WebElement entradaAlojamiento;
	
	@FindBy(css = "input[placeholder='Salida']")
	WebElement salidaAlojamiento;
	
	@FindBy (css = " input.sbox5-3-second-input")
	WebElement habitacionAlojamiento;
	
	@FindBy (xpath = "//*[@id='component-modals']//div[29]/div[1]")
	WebElement fechaEntradaAlojamiento;
	
	@FindBy (xpath = "//*[@id='component-modals']//div[2]/div[3]/div[13]/div[1]") //ver de acortar el locator
	WebElement fechaSalidaAlojamiento;
	
	@FindBy (css = "button.sbox5-3-btn.-primary.-md > em.btn-text")
	WebElement btnAplicarFecha;
	
	@FindBy (css = "i[class='login-incentive--close shifu-3-icon-close -eva-3-mr-md']")
	WebElement iniciarSesion;
	
	@FindBy (xpath = "//em[contains(text(),'Buscar')]") //ver de cambiar a css
	WebElement btnBuscar;
	
	@FindBy (xpath = "//div[@id='component-modals']//div[1]/button[2]") //buscar cambiar a css
	WebElement btnAddAdults;
	
	@FindBy (xpath = "//div[@id='component-modals']//div[2]/div[2]//button[2]") //buscar cambiar a css
	WebElement btnAddMenors;
	
	@FindBy (css = "select[class='select']")
	WebElement edadMenorSelector;
	
	@FindBy (xpath = "//em[contains(text(),'Entendí')]")
	WebElement btnCookies;
	
	private WebDriver driver = null;
	public DespegarAlojamientosPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean destinoEsVisible() {
		return this.destinoAlojamiento.isDisplayed();
	}
	
	
	public void clickDestino() {
		this.destinoAlojamiento.click();
	}
	
	public void cerrarIniciarSesionYCookies() {
		this.iniciarSesion.click();
		this.btnCookies.click();
	}
	public void escribirDestino(WebDriverWait wait) {
		this.destinoAlojamiento.click();
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.destinoAlojamiento.click();
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.destinoAlojamiento.sendKeys("Bariloche ");
		wait.until(ExpectedConditions.visibilityOf(menuAlojamiento));
		this.destinoAlojamiento.sendKeys(Keys.ENTER);
	}
	
	public void seleccionarFechas( WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOf(entradaAlojamiento));
		this.entradaAlojamiento.click();
		
		wait.until(ExpectedConditions.visibilityOf(fechaEntradaAlojamiento));
		this.fechaEntradaAlojamiento.click();
		
		wait.until(ExpectedConditions.visibilityOf(salidaAlojamiento));
		this.salidaAlojamiento.click();
		
		wait.until(ExpectedConditions.visibilityOf(fechaSalidaAlojamiento));
		this.fechaSalidaAlojamiento.click();
		
		this.btnAplicarFecha.click();
	}
	
	public void seleccionarCantidadPersonas(WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOf(habitacionAlojamiento));
		this.habitacionAlojamiento.click();
		  
		wait.until(ExpectedConditions.visibilityOf(btnAddAdults));
		this.btnAddAdults.click();
		this.btnAddMenors.click();
		  
		wait.until(ExpectedConditions.visibilityOf(edadMenorSelector));
		this.edadMenorSelector.click();
		this.edadMenorSelector.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}
	
	
	public DespegarResultsPage buscarAlojamiento() {
		this.btnBuscar.click();
		//btnBuscar.submit();
		return new DespegarResultsPage(this.driver);
	}
	
}
