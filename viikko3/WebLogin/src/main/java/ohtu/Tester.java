package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.Random;


public class Tester {

/*
public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        // ...

        driver.quit();
    }
*/

    public static void main(String[] args) {
        
        System.setProperty("webdriver.chrome.driver", "/home/olli/Ohjelmointi/Ohtu/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        Random rand = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys("polle" + rand.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("koninkaulus2");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("koninkaulus2");
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        sleep(3);

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(3);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(3);

        driver.quit();
    }
 

  
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
