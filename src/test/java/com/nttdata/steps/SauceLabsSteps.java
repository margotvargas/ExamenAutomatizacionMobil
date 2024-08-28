package com.nttdata.steps;

import java.util.List;
import org.openqa.selenium.WebElement;
import com.nttdata.screens.SauceLabsScreen;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.assertj.core.api.Assertions.assertThat;



public class SauceLabsSteps {

    SauceLabsScreen sauceLabsScreen;

    @Step("Abrir la aplicación de SauceLabs")
    public void abrirAplicacion() {
        sauceLabsScreen.validarPantallaPrincipal();
        //Serenity.takeScreenshot(); // Toma una captura de pantalla en este punto
    }

    @Step("Validar que los productos se carguen correctamente en la galería")
    public void validarProductosEnGaleria() {
        WebDriverWait wait = new WebDriverWait(sauceLabsScreen.getDriver(), 10);
        WebElement productRecyclerView = wait.until(ExpectedConditions.visibilityOf(sauceLabsScreen.obtenerListaProductos()));

        // Verifica si la galería tiene productos visibles
        List<WebElement> productos = sauceLabsScreen.obtenerProductosEnGaleria();
        if (productos.size() > 0) {
            System.out.println("Los productos se cargaron correctamente en la galería.");
        } else {
            System.out.println("No se encontraron productos en la galería.");
        }
    }

    @Step("Seleccionar el producto {0} desde la galería")
    public void seleccionarProducto(String nombreProducto) {
        sauceLabsScreen.seleccionarProducto(nombreProducto);
    }

    @Step("Agregar {0} unidades del producto seleccionado al carrito")
    public void agregarProductoAlCarrito(int unidades) {
        sauceLabsScreen.agregarProductoAlCarrito(unidades);
    }

    @Step("Acceder al carrito")
    public void accederAlCarrito() {
        sauceLabsScreen.accederAlCarrito();
    }

    @Step("Validar que el producto {0} y la cantidad {1} sean correctos en el carrito")
    public void validarProductoEnCarrito(String nombreProducto, int unidadesEsperadas) {
        boolean esCorrecto = sauceLabsScreen.validarProductoEnCarrito(nombreProducto, unidadesEsperadas);
        assertThat(esCorrecto).as("El producto en el carrito es correcto").isTrue();
    }

}
