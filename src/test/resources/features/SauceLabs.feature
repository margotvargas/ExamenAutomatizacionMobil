Feature: Sauce Labs App

  @AgregarCarrito
  Scenario Outline: Validar la funcionalidad del carrito de compras
    Given estoy en la aplicación de SauceLabs
    And valido que carguen correctamente los productos en la galeria
    When agrego <unidades> del siguiente producto "<producto>"
    Then valido que el carrito actualice correctamente con el producto "<producto>" y la cantidad <unidades>

    Examples:
      | producto                | unidades |
      | Sauce Labs Backpack     | 1        |
      | Sauce Labs Bolt T-Shirt | 1        |
      | Sauce Labs Bike Light   | 2        |

    #Consideraciones:
    #Se genero el reporte en serenity. Ruta:/target/site/serenity/index.html
    #Se ejecutaron los test desde el archivo runner: CucumberTestSuite
    #En el caso de los 3 escenarios:
      #-El primer escenario Sauce Labs Backpack ejecuta correctamente
      #-El segundo escenario Sauce Labs Bolt T-Shirt falla en la validación de la cantidad, ya que hay un bugs en el app que por defecto al dar clic en boton cantidad aumenta a 10 unid.
      #-El tercer escenario Sauce Labs Bike Light también falla debido a un bug en el app, al dar tap en la seleccion del producto, se cierra la aplicacion.