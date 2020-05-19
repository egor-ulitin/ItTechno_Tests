package ItTechno;

import org.openqa.selenium.*;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;
import java.io.File;
    public class WebDriverListener implements WebDriverEventListener {
        @Attachment(value = "Screenshot")
        public static byte[] takeScreenshot(WebDriver driver, By by) {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(by));

            try {
                Thread.sleep(170);
            }catch (Exception ignored) {
            }

            byte[] screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            jse.executeScript("arguments[0].style.border=''", driver.findElement(by));
            return screen;
        }

        @Attachment(value = "Exception screenshot")
        public byte[] takeScreenshot(WebDriver driver) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }

        public void beforeAlertAccept(WebDriver driver) {

        }

        public void afterAlertAccept(WebDriver driver) {

        }

        public void afterAlertDismiss(WebDriver driver) {

        }

        public void beforeAlertDismiss(WebDriver driver) {

        }

        public void beforeNavigateTo(String url, WebDriver driver) {

        }

        public void afterNavigateTo(String url, WebDriver driver) {

        }

        public void beforeNavigateBack(WebDriver driver) {

        }

        public void afterNavigateBack(WebDriver driver) {

        }

        public void beforeNavigateForward(WebDriver driver) {

        }

        public void afterNavigateForward(WebDriver driver) {

        }

        public void beforeNavigateRefresh(WebDriver driver) {

        }

        public void afterNavigateRefresh(WebDriver driver) {

        }

        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            try {
                takeScreenshot(driver, by);
            } catch (Exception ignore){}
        }

        public void afterFindBy(By by, WebElement element, WebDriver driver) {

        }

        public void beforeClickOn(WebElement element, WebDriver driver) {

        }

        public void afterClickOn(WebElement element, WebDriver driver) {

        }

        public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

        }

        public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

        }

        public void beforeScript(String script, WebDriver driver) {

        }

        public void afterScript(String script, WebDriver driver) {

        }

        public void beforeSwitchToWindow(String windowName, WebDriver driver) {

        }

        public void afterSwitchToWindow(String windowName, WebDriver driver) {

        }

        public void onException(Throwable throwable, WebDriver driver) {
            try {
                takeScreenshot(driver);
            } catch (Exception ignore) {}
        }

        public <X> void beforeGetScreenshotAs(OutputType<X> target) {

        }

        public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

        }

        public void beforeGetText(WebElement element, WebDriver driver) {

        }

        public void afterGetText(WebElement element, WebDriver driver, String text) {
        }
    }

