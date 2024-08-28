package com.nttdata.stepsdefinitions;

import com.nttdata.steps.SauceLabsSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
//Aqui enlazamos las definiciones de los pasos en el archivo feature, con los metodos definidos el archivo steps
public class SauceLabsStepDef {

    @Steps
    SauceLabsSteps sauceLabsSteps;

    @Given("estoy en la aplicación de SauceLabs")
    public void estoyEnLaAplicacion() {
        sauceLabsSteps.abrirAplicacion();
    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void validoQueCarguenProductosEnGaleria() {
        sauceLabsSteps.validarProductosEnGaleria();
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agregoProducto(int unidades, String producto) {
        sauceLabsSteps.seleccionarProducto(producto);
        sauceLabsSteps.agregarProductoAlCarrito(unidades);
    }

    @Then("valido que el carrito actualice correctamente con el producto {string} y la cantidad {int}")
    public void validoQueElCarritoActualiceCorrectamente(String producto, int unidades) {
        sauceLabsSteps.accederAlCarrito();
        sauceLabsSteps.validarProductoEnCarrito(producto, unidades);
    }

}
