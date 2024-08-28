package com.nttdata.screens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import java.util.List;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Esta clase maneja la interacción con la pantalla de la aplicación y define los localizadores y métodos que interactúan con ellos,

public class SauceLabsScreen extends PageObject {


    //Localizador que muestra el titulo de la APP
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/mTvTitle")
    private WebElement tituloApp;

    // Localizadores de los productos en la galería para la assertion
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\" and @text=\"Sauce Labs Backpack\"]")
    private WebElement productoEnGaleria;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productRV")
    private WebElement vistaProductos;

    @AndroidFindBy(xpath = "//android.widget.ImageView")
    private List<WebElement> productosEnGaleria;


    // Localizadores de la galería de productos
    //Localizador producto Sauce Labs Backpack
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sauce Labs Backpack\"]")
    private WebElement productoBackpack;

    //Localizador producto Sauce Bolt T-Shirt
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sauce Labs Bolt T-Shirt\"]")
    private WebElement productoTShirt;

    //Localizador producto Sauce Bike Light
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Sauce Labs Bike Light\"]")
    private WebElement productoBikeLight;


    //Localizadores de botones
    //Localizador boton Incrementar cantidad
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/plusIV")
    private WebElement btnIncrementar;

    //Localizador boton Agregar Carrito
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt")
    private WebElement btnAgregarCarrito;

    //Localizador del icono del carrito
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartIV")
    private WebElement iconocarrito;

    // Localizador del nombre del producto en el carrito

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/titleTV")
    private WebElement nombreProductoEnCarrito;

    // Localizador de la cantidad de productos en el carrito
    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/noTV")
    private WebElement cantidadProductoEnCarrito;


       //METODOS
    public void validarPantallaPrincipal() {
        /// Validamos que el elemento esté visible confirmando que estamos en la pantalla principal
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOf(tituloApp));
        shouldBeVisible(tituloApp);
    }

    public WebElement obtenerListaProductos() {
        return vistaProductos;
    }

    public List<WebElement> obtenerProductosEnGaleria() {
        return productosEnGaleria;
    }


//    // Método para seleccionar un producto desde la galería
    public void seleccionarProducto(String nombreProducto) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        boolean productoEncontrado = true;



        try {
            switch (nombreProducto) {
                case "Sauce Labs Bolt T-Shirt":
                    wait.until(ExpectedConditions.elementToBeClickable(productoTShirt));
                    productoTShirt.click();
                    break;
                case "Sauce Labs Backpack":
                    wait.until(ExpectedConditions.elementToBeClickable(productoBackpack));
                    productoBackpack.click();
                    break;
                case "Sauce Labs Bike Light":
                    wait.until(ExpectedConditions.elementToBeClickable(productoBikeLight));
                    productoBikeLight.click();
                    break;
                default:
                    productoEncontrado = false;
                    System.out.println("Producto no encontrado: " + nombreProducto);
                    break;
            }

            if (productoEncontrado) {
                System.out.println("Producto seleccionado correctamente: " + nombreProducto);
            }
        } catch (Exception e) {
            System.out.println("ENCONTRAMOS UN BUG: La aplicación se cerró al intentar seleccionar el producto: " + nombreProducto);
            e.printStackTrace();
        }

    }

    public void incrementarCantidad(int unidades) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(ExpectedConditions.elementToBeClickable(btnIncrementar));

        for (int i = 1; i < unidades; i++) {
           btnIncrementar.click();
        }
    }

    public void agregarProductoAlCarrito(int unidades) {
        incrementarCantidad(unidades);  // Llama al método incrementarCantidad para aumentar las unidades
        btnAgregarCarrito.click();
    }

    // Método para acceder al carrito
    public void accederAlCarrito() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOf(iconocarrito));
        iconocarrito.click();
    }

    public boolean validarProductoEnCarrito(String nombreProducto, int unidadesEsperadas) {
        String nombreProductoActual = nombreProductoEnCarrito.getText();
        int cantidadActual = Integer.parseInt(cantidadProductoEnCarrito.getText());

        if (!nombreProductoActual.equals(nombreProducto)) {
            System.out.println("ENCONTRAMOS UN BUGS: El producto en el carrito no coincide con el producto esperado. Producto esperado: " + nombreProducto + ", Producto actual: " + nombreProductoActual);
            return false;
        }

        if (cantidadActual != unidadesEsperadas) {
            System.out.println("ENCONTRAMOS UN BUGS:: La cantidad de unidades en el carrito no coincide con la cantidad esperada. Cantidad esperada: " + unidadesEsperadas + ", Cantidad actual: " + cantidadActual);
            return false;
        }

        return true;
    }

}
