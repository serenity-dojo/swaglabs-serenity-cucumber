package swaglabs;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.WithDriver;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SerenityRunner.class)
public class ExtensionTest {

    @Managed
    WebDriver driver;

    @Test
    public void testExtension() throws InterruptedException {
        driver.get("chrome-extension://mgijmajocgfcbeboacabfgobmjgjcoja/browser_action.html");
        driver.findElement(By.id("query-field")).sendKeys("define");
        driver.findElement(By.id("define-btn")).click();
        Thread.sleep(5000);
        String headword = driver.findElement(By.className("headword")).getText();
    }

    @Test
    public void testBasicExtension() throws InterruptedException {
        ChromeOptions opt =  new ChromeOptions();
        List<File> extensionFiles = List.of(new File("src/test/resources/extensions/4.2.4_0.crx"));
        opt.addExtensions(extensionFiles);

//        opt.addExtensions(new File("src/test/resources/extensions/4.2.4_0.crx"));
        ChromeDriver driver =  new ChromeDriver(opt);
        driver.get("chrome-extension://mgijmajocgfcbeboacabfgobmjgjcoja/browser_action.html");
        driver.findElement(By.id("query-field")).sendKeys("define");
        driver.findElement(By.id("define-btn")).click();
        Thread.sleep(5000);
        String headword = driver.findElement(By.className("headword")).getText();
        System.out.println(headword);

    }
}
