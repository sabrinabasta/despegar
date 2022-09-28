package com.trabajopractico1.testing.TrabajoPractico1;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.driver.DriverFactory;

import pageObjects.DespegarAlojamientoSeleccionadoPage;
import pageObjects.DespegarAlojamientosPage;
import pageObjects.DespegarResultsPage;

public class DespegarTest2 extends DriverFactory {
	WebDriver driver = null;
	private DespegarAlojamientosPage alojamientosPage;
	
	@BeforeMethod(alwaysRun = true)
	public void setUp(ITestContext context) throws Exception {
		String navegadorTestSuite = context.getCurrentXmlTest().getParameter("Navegador");
		String navegador = navegadorTestSuite != null ? navegadorTestSuite : "CHROME";
		driver = DriverFactory.LevantarBrowser(navegador, "https://www.despegar.com.ar/hoteles/" );
		alojamientosPage = new DespegarAlojamientosPage(driver);
	}
	
	@DataProvider (name = "Data Provider Despegar")
	public Object [][] dpMetodo(){
		return new Object [][] {{"Bariloche"}, {"Ushuaia"}, {"Salta"}};
	}
	
	@Test(groups = {"grupo1"}, dataProvider = "Data Provider Despegar", description = "Validar el resultado de la busqueda de alojamiento")

	public void ValidarAlojamientoDespegar(String destinoText) throws Exception {

		
		
		
		if (alojamientosPage.destinoEsVisible()) {
			
			alojamientosPage.cerrarIniciarSesionYCookies();
			
			alojamientosPage.escribirDestino(destinoText);
			alojamientosPage.seleccionarFechas();
			alojamientosPage.seleccionarCantidadPersonas();
			
			DespegarResultsPage resultsAlojamientosPage = alojamientosPage.buscarAlojamiento();
			
			resultsAlojamientosPage.closeExperiencias();

			
			DespegarAlojamientoSeleccionadoPage alojamientoSeleccionadoPage = resultsAlojamientosPage.seleccionarAlojamiento();
			
			resultsAlojamientosPage.cambioDePestaña();
			
			Assert.assertTrue(alojamientoSeleccionadoPage.botonVisible(), "El botón no está visible.");
		}
		else {
			System.out.print("No se encontró");
		}
		
	} 
	 
	@Test (groups = {"grupo1","grupo2"}, description = "Validar botones de cambio de sección")
	public void ValidarBotonesDespegar() {
		alojamientosPage.validarBtns();
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		//Se cierra la instancia del controlador web, se cierran todas las ventanas.
		DriverFactory.CloseBrowser(driver); 
	}

}
