package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DespegarAlojamientosPage {
	@FindBy(css = "input[placeholder='Ingresá una ciudad, alojamiento o punto de interés']")
	WebElement destinoAlojamiento;
	
	@FindBy(css ="*[class='ac-wrapper -desktop -show sbox-ab-ls']")
	WebElement menuAlojamiento;
	
	@FindBy (css = "input[placeholder='Entrada']")
	WebElement entradaAlojamiento;
	
	@FindBy(css = "input[placeholder='Salida']")
	WebElement salidaAlojamiento;
	
	@FindBy (css = " input.sbox5-3-second-input")
	WebElement habitacionAlojamiento;
	
	@FindBy (css = "*.sbox5-monthgrid-datenumber.sbox5-monthgrid-datenumber-29:nth-child(30) > *.sbox5-monthgrid-datenumber-number") // xpath = "//*[@id='component-modals']//div[29]/div[1]"
	WebElement fechaEntradaAlojamiento; 
	
	@FindBy (xpath = "//*[@id='component-modals']//div[2]/div[3]/div[13]/div[1]")
	WebElement fechaSalidaAlojamiento;
	
	@FindBy (css = "button.sbox5-3-btn.-primary.-md > em.btn-text")
	WebElement btnAplicarFecha;
	
	@FindBy (css = "i[class='login-incentive--close shifu-3-icon-close -eva-3-mr-md']")
	WebElement iniciarSesion;
	
	@FindBy (xpath = "//em[contains(text(),'Buscar')]") //(css = "button.sbox5-box-button-ovr.sbox5-3-btn.sbox5-button.-secondary.-icon.-lg > *.btn-text")
	WebElement btnBuscar;
	
	@FindBy (css = "*.sbox5-3-steppers.-md > button.steppers-icon-right.stepper__icon") //xpath = "//*[@id='component-modals']//div[1]/button[2]"
	WebElement btnAddAdults; 
	
	@FindBy (xpath = "//*[@id='component-modals']//div[2]/div[2]//button[2]") 
	WebElement btnAddMenors;
	
	@FindBy (css = "select[class='select']")
	WebElement edadMenorSelector;
	
	@FindBy (xpath = "//em[contains(text(),'Entendí')]")
	WebElement btnCookies;
	
	
	private WebDriver driver = null;
	private WebDriverWait wait = null;

	
	public DespegarAlojamientosPage (WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,10); 
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
	public void escribirDestino(String destinoText) throws InterruptedException {
		wait.until(ExpectedConditions.invisibilityOf(iniciarSesion)); 
		this.destinoAlojamiento.click();
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //acá uso implicitwaits para que le de tiempo al menú de aparecer
		Thread.sleep(1000); //lo activo cuando no le gusta la implicitlyWait para esperar el menú
		this.destinoAlojamiento.sendKeys(destinoText);
		this.wait.until(ExpectedConditions.visibilityOf(menuAlojamiento));
		this.destinoAlojamiento.sendKeys(Keys.ENTER);
	}
	
	public void seleccionarFechas() {
		this.wait.until(ExpectedConditions.visibilityOf(entradaAlojamiento));
		this.entradaAlojamiento.click();
		
		this.wait.until(ExpectedConditions.visibilityOf(fechaEntradaAlojamiento));
		this.fechaEntradaAlojamiento.click();
		
		this.wait.until(ExpectedConditions.visibilityOf(salidaAlojamiento));
		this.salidaAlojamiento.click();
		
		this.wait.until(ExpectedConditions.visibilityOf(fechaSalidaAlojamiento));
		this.fechaSalidaAlojamiento.click();
		
		this.btnAplicarFecha.click();
	}
	
	public void seleccionarCantidadPersonas() {
		this.wait.until(ExpectedConditions.visibilityOf(habitacionAlojamiento));
		this.habitacionAlojamiento.click();
		  
		this.wait.until(ExpectedConditions.visibilityOf(btnAddAdults));
		this.btnAddAdults.click();
		this.btnAddMenors.click();
		  
		this.wait.until(ExpectedConditions.visibilityOf(edadMenorSelector));
		this.edadMenorSelector.click();
		this.edadMenorSelector.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}
	
	
	public DespegarResultsPage buscarAlojamiento() {
		this.btnBuscar.click();
		return new DespegarResultsPage(this.driver);
	}
	
	public void validarBtns() {
		List<WebElement> listaBotones = driver.findElements(By.cssSelector("ul.header-list-products > li"));
		System.out.println("Validando que los botones que redirigen a cada sección tengan el texto visible...");
		for  (WebElement btn: listaBotones ) {
			WebElement textElement = btn.findElement(By.xpath("//label"));
			Assert.assertTrue(textElement.isDisplayed(), "El texto no es visible");
			
		}
		
	}
	
}
