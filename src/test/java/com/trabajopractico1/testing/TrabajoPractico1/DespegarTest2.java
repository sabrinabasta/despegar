package com.trabajopractico1.testing.TrabajoPractico1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.driver.DriverFactory;

import pageObjects.DespegarAlojamientoSeleccionadoPage;
import pageObjects.DespegarAlojamientosPage;
import pageObjects.DespegarResultsPage;

public class DespegarTest2 extends DriverFactory {
	WebDriver driver = null;
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.LevantarBrowser("EDGE", "https://www.despegar.com.ar/hoteles/" );
		
	}
	
	@Test(description = "Validar el resultado de la busqueda de alojamiento")

	public void ValidarAlojamientoDespegar() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,10);
		String mainTab = driver.getWindowHandle();

		DespegarAlojamientosPage alojamientosPage = new DespegarAlojamientosPage(driver);
		//DespegarResultsPage resultsAlojamientosPage = new DespegarResultsPage(driver); 
		//DespegarAlojamientoSeleccionadoPage alojamientoSeleccionadoPage = new DespegarAlojamientoSeleccionadoPage(driver);
		
		if (alojamientosPage.destinoEsVisible()) {
			alojamientosPage.cerrarIniciarSesionYCookies();
			
			alojamientosPage.escribirDestino(wait);
			alojamientosPage.seleccionarFechas(wait);
			alojamientosPage.seleccionarCantidadPersonas(wait);
			
			DespegarResultsPage resultsAlojamientosPage = alojamientosPage.buscarAlojamiento();
			
			resultsAlojamientosPage.closeExperiencias(wait);
			
			DespegarAlojamientoSeleccionadoPage alojamientoSeleccionadoPage = resultsAlojamientosPage.seleccionarAlojamiento();
			
			resultsAlojamientosPage.cambioDePestaña(driver, mainTab);
			
			Assert.assertTrue(alojamientoSeleccionadoPage.botonVisible(), "El botón no está visible.");
		}
		else {
			System.out.print("No se encontró");
		}
		
	} 
	
	@AfterMethod
	public void tearDown() throws Exception {
		//Se cierra la instancia del controlador web, se cierran todas las ventanas.
		DriverFactory.CloseBrowser(driver); 
	}

}
