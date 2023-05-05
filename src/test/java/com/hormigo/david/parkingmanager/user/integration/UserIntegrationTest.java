package com.hormigo.david.parkingmanager.user.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hormigo.david.parkingmanager.user.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    @MockBean
    private UserService userService;
    @Value("${local.server.port}")
    private  int port;
    private static ChromeDriver chromeDriver;
    @BeforeAll
    public static void prepareWebDriver() {

        System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        chromeDriver = new ChromeDriver(options);
        
    }
    @Test
    public void checkUserListIsDspd(){
      String url="http://localhost:" + port + "/users";
      chromeDriver.get(url);
      WebElement createButton = chromeDriver.findElement(By.id("users-button-create"));
      String actualTitle = chromeDriver.getTitle();
      WebElement table = chromeDriver.findElement(By.className("table"));
      WebElement actualh1= chromeDriver.findElement(By.id("usser-title"));
      WebElement userButton = chromeDriver.findElement(By.id("to-users-link"));
      WebElement drawButton = chromeDriver.findElement(By.id("to-draws-link"));
      WebElement homeButton = chromeDriver.findElement(By.id("to-home-link"));

      assertAll("Comprobar que se muestra el contenido de la pagina /users",
      () -> {assertNotNull(createButton);},
      () -> {assertEquals("Usuarios",actualTitle);},
      () -> {assertNotNull(table);},
      () -> {assertEquals("Usuarios", actualh1);},
      () -> {assertNotNull(userButton);},
      () -> {assertNotNull(drawButton);},
      () -> {assertNotNull(homeButton);});
      chromeDriver.quit();
    }
    @Test
  public void checkClickHome(){
    String url="http://localhost:" + port + "/users";
      chromeDriver.get(url);
      chromeDriver.findElement(By.id("to-draws-link")).click();
      String actualTitle = chromeDriver.getTitle();
      WebElement actualh1 = chromeDriver.findElement(By.id("home-tittle"));
      WebElement userButton = chromeDriver.findElement(By.id("to-users-link"));
      WebElement drawButton = chromeDriver.findElement(By.id("to-draws-link"));
      WebElement homeButton = chromeDriver.findElement(By.id("to-home-link"));

      assertAll("Comprobar que se muestra el contenido de la pagina /home",
      () -> {assertEquals("Bienvenidos al CPIFP Los Camaleones", actualh1);},
      () -> {assertEquals("CPIFP Los Camaleones", actualTitle);},
      () -> {assertNotNull(userButton);},
      () -> {assertNotNull(drawButton);},
      () -> {assertNotNull(homeButton);}
      );
      chromeDriver.quit();

  }
  @Test
  public void checkClickDrawButton(){
    String url="http://localhost:" + port + "/users";
      chromeDriver.get(url);
      chromeDriver.findElement(By.id("to-draws-link")).click();
      WebElement createDrawButton = chromeDriver.findElement(By.id("create-draw-button"));
    WebElement actualh1 = chromeDriver.findElement(By.id("h1-draw"));
    String actualTitle = chromeDriver.getTitle();
    WebElement table= chromeDriver.findElement(By.id("draw-list-table"));
    assertAll("Comprobacion de que el boton de sorteos te lleva a sorteos",
    () -> {assertNotNull(createDrawButton);},
    () -> {assertEquals("Sorteos", actualTitle);},
    () -> {assertEquals("Sorteos",actualh1);},
    () -> {assertNotNull(table);});
    chromeDriver.quit();
  }
  @Test
  public void checkClickUserButton(){
    String url="http://localhost:" + port + "/users";
      chromeDriver.get(url);
      chromeDriver.findElement(By.id("to-user-link")).click();
      WebElement createButton = chromeDriver.findElement(By.id("users-button-create"));
      String actualTitle = chromeDriver.getTitle();
      WebElement table = chromeDriver.findElement(By.className("table"));
      WebElement actualh1= chromeDriver.findElement(By.id("usser-title"));
      assertAll("Comprobar de que el boton usuarios te lleva a usuarios",
      () -> {assertNotNull(createButton);},
      () -> {assertEquals("Usuarios",actualTitle);},
      () -> {assertNotNull(table);},
      () -> {assertEquals("Usuarios", actualh1);});
      chromeDriver.quit();
  }
  @Test
  public void checkCreateNewUserButton(){
    String url="http://localhost:" + port + "/users";
      chromeDriver.get(url);
      WebElement correoElectronicoForm = chromeDriver.findElement(By.id("user-create-label-email"));
    WebElement nombreForm = chromeDriver.findElement(By.id("user-create-label-name"));
    WebElement primerApellidoForm = chromeDriver.findElement(By.id("user-create-label-lastname1"));
    WebElement segundoApellidoForm = chromeDriver.findElement(By.id("user-create-label-lastname2"));
    WebElement roleForm = chromeDriver.findElement(By.id("user-create-label-role"));
    WebElement submitButton = chromeDriver.findElement(By.id("user-create-button-submit"));
    WebElement actualh1 = chromeDriver.findElement(By.id("usser-form-title"));
    WebElement userButton = chromeDriver.findElement(By.id("to-users-link"));
    WebElement drawButton = chromeDriver.findElement(By.id("to-draws-link"));
    WebElement homeButton = chromeDriver.findElement(By.id("to-home-link"));
    assertAll("Comprobar que al darle al boton de crear usuario, nos muestre la página del formulario",
    () -> {assertEquals("user-create-label-lastname2", segundoApellidoForm);},
    () -> {assertEquals("user-create-label-lastname1", primerApellidoForm);},
    () -> {assertEquals("user-create-label-role",roleForm);},
    () -> {assertEquals("Crear nuevo usuario", actualh1);},
    () -> {assertEquals("user-create-label-name", nombreForm);},
    () -> {assertEquals("user-create-label-email",correoElectronicoForm);},
    () -> {assertNotNull(submitButton);},
    () -> {assertNotNull(userButton);},
    () -> {assertNotNull(drawButton);},
    () -> {assertNotNull(homeButton);});

    chromeDriver.quit();
  }
}

