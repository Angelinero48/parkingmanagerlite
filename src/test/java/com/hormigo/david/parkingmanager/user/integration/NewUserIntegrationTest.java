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

public class NewUserIntegrationTest {
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
  public void checkNewUserIsDspd(){
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

    assertAll("Comprobar que se muestra el contenido del formulario para la creacion de usuarios",
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
