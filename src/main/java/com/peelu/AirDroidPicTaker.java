package com.peelu;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.text.SimpleDateFormat;
import java.util.Date;



public class AirDroidPicTaker {
    private static final String USERNAME = "email_here";
    private static final String PWD = "password_here";
    private static final Logger LOGGER = Logger.getLogger(AirDroidPicTaker.class.getName());
    public static final String CAMERA_WARNING_ICON_XPATH = "/html/body/div[1]/div[2]/div[1]/div[22]/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div/button";
    public static final String CAMERA_WARNING_ICON_XPATH2 = "/html/body/div[1]/div[1]/div[1]/div[22]/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div/button";
    public static final String CAMERA_WARNING_CLASS_NAME = "mod-camera-firstTipOk";
    public static final String TAKE_SHOT_CLASS_NAME = "icon-save-to-pc";
    public static final String TRY_AGAIN_LINK_XPATH2 = "/html/body/div[1]/div[2]/div[1]/div[22]/div[1]/div/div[2]/div[2]/div/div/div/div[3]/div/a";
    public static final String TRY_AGAIN_LINK_XPATH = "/html/body/div[1]/div[2]/div[1]/div[21]/div[1]/div/div[2]/div[2]/div/div/div/div[3]/div/a";
    public static final String TRY_AGAIN_CSS_SELECTOR = ".mod-camera-startTip > a:nth-child(1)";
    public static final String TAKE_SHOT_ICON_XPATH = "/html/body/div[1]/div[2]/div[1]/div[21]/div[1]/div/div[2]/div[2]/div/div/div/div[8]/table/tbody/tr/td[2]/table/tbody/tr/td[1]/div/a[1]/span";
    public static final String TAKE_SHOT_ICON_XPATH2= "/html/body/div[1]/div[1]/div[1]/div[21]/div[1]/div/div[2]/div[2]/div/div/div/div[8]/table/tbody/tr/td[2]/table/tbody/tr/td[1]/div/a[1]/span";
//    /html/body/div[1]/div[2]/div[1]/div[22]/div[1]/div/div[2]/div[2]/div/div/div/div[8]/table/tbody/tr/td[2]/table/tbody/tr/td[1]/div/a[1]/span
    public static final String CAMERA_APP_ICON_XPATH = "//*[@id=\"icon_1_0_78\"]";
    public static final String ROTATE_ICON_XPATH = "/html/body/div[1]/div[1]/div[1]/div[21]/div[1]/div/div[2]/div[2]/div/div/div/div[8]/table/tbody/tr/td[3]/a";
    public static final String ROTATE_ICON_XPATH2 = "/html/body/div[1]/div[2]/div[1]/div[21]/div[1]/div/div[2]/div[2]/div/div/div/div[8]/table/tbody/tr/td[3]/a";
    public static final String ROTATE_ICON_CSS = ".mod-camera-opRotate";
    public static final String CAMERA_WINDOW = "/html/body/div[1]/div[2]/div[1]/div[21]";
    public static final String CAMERA_WINDOW2 = "/html/body/div[1]/div[1]/div[1]/div[21]";
    public static final String CAMERA_WINDOW_CSS_SELECTOR = ".window";
    public static final String patternHeight = "height: (.+?)px";
    public static final String patternWidth = "width: (.+?)px";



    public static void main(String[] args) throws InterruptedException {
        LOGGER.debug("Step -1: started Script");
        WebDriver webDriver = null;
        System.setProperty("webdriver.gecko.driver", "/Users/deepanshu/Downloads/geckodriver");
        try {
            webDriver = new FirefoxDriver(getFireFoxOptions());
            LOGGER.debug("Step 0: started FireFox");
            webDriver.get("http://web.airdroid.com");
            System.out.println("Step 1: opened page http://web.airdroid.com");
            LOGGER.debug("Step 1: opened page http://web.airdroid.com");
            WebElement emailWebElement = webDriver.findElement(By.xpath("//*[@id=\"widget_widget_61\"]/div[2]/div/div[3]/div[1]/div[1]/div[1]/div[1]/input"));
            emailWebElement.sendKeys(USERNAME);
            WebElement pwdWebElement = webDriver.findElement(By.xpath("//*[@id=\"widget_widget_61\"]/div[2]/div/div[3]/div[1]/div[1]/div[1]/div[2]/input"));
            pwdWebElement.sendKeys(PWD);
            WebElement submitButton = webDriver.findElement(By.xpath("//*[@id=\"widget_widget_61\"]/div[2]/div/div[3]/div[1]/div[1]/div[1]/div[2]/button"));
            submitButton.click();
            LOGGER.debug("Step 2: logged In");
            Thread.sleep(1000);
            while(!isPresent(CAMERA_WARNING_ICON_XPATH, webDriver) && !isPresent(CAMERA_WARNING_ICON_XPATH2, webDriver)) {
                waitAndClickXpath(CAMERA_APP_ICON_XPATH, webDriver, "Button 1: cameraAppIcon");
                break;
            }
            if(isPresent(By.id("alert_box_yes_btn"), webDriver)) {
                waitAndClick(By.id("alert_box_yes_btn"), webDriver, "Privacy Accept");
            }
            LOGGER.debug("Step 3: opened camera app");
//            clickFirstClickable(CAMERA_WARNING_ICON_XPATH, CAMERA_WARNING_ICON_XPATH2, webDriver, "Button2 : cameraWarningIcon1", "Button2 : cameraWarningIcon2");
            waitAndClick(By.className(CAMERA_WARNING_CLASS_NAME), webDriver, "Button2 : cameraWarningIcon");
            LOGGER.debug("Step 4: clicked on the camera warniong icon");
            Thread.sleep(3000);
            int count = 0;
            while (count < 5 && !isPresent(By.className(TAKE_SHOT_CLASS_NAME), webDriver) && !isPresent(TAKE_SHOT_ICON_XPATH, webDriver)) {
                if(isPresent(By.cssSelector(TRY_AGAIN_CSS_SELECTOR), webDriver) || isPresent(TRY_AGAIN_LINK_XPATH, webDriver)) {
                    LOGGER.debug("Step 5: takeShotIcon not loading, finding try again link");
//                    waitAndClickXpath(TRY_AGAIN_LINK_XPATH, webDriver, "Button 3: Try again button");
                    waitAndClick(By.cssSelector(".mod-camera-startTip > a:nth-child(1)"), webDriver, "Button 3: Try again button");
                }
                count++;
                LOGGER.debug("Step 6: clicked try again link");
            }
            rotateIfNeccessary(webDriver);
            Thread.sleep(10000);
            waitAndClick(By.className(TAKE_SHOT_CLASS_NAME), webDriver, "Button 4: takeShotIcon");
//            clickFirstClickable(TAKE_SHOT_ICON_XPATH, TAKE_SHOT_ICON_XPATH2, webDriver, "Button 4: takeShotIcon1", "Button 4: takeShotIcon2");
            LOGGER.debug("Step 7: clicked takeShotIcon on " + currentTime());
            LOGGER.debug("Step 8: waiting for 40 seconds for download to complete");
        } catch (Exception e) {
            LOGGER.debug(e);
            e.printStackTrace();
        } finally {
            Thread.sleep(40000);
            webDriver.close();
            LOGGER.debug("Step 9: closed browser");
            LOGGER.debug("==============================");
        }
    }

    private static void rotateIfNeccessary(WebDriver webDriver) {
        WebElement cameraWindow = null;
        if(isPresent(By.cssSelector(CAMERA_WINDOW_CSS_SELECTOR), webDriver)){
            cameraWindow = webDriver.findElement(By.cssSelector(CAMERA_WINDOW_CSS_SELECTOR));
        } else if(isPresent(CAMERA_WINDOW, webDriver)) {
            cameraWindow = webDriver.findElement(By.xpath(CAMERA_WINDOW));
        } else if(isPresent(CAMERA_WINDOW2, webDriver)) {
            cameraWindow = webDriver.findElement(By.xpath(CAMERA_WINDOW2));
        }
        if(cameraWindow!=null) {
            int height = cameraWindow.getSize().getHeight();
            int width = cameraWindow.getSize().getWidth();
//            String style = cameraWindow.getAttribute("style");
//            Pattern patternH = Pattern.compile(patternHeight);
//            Pattern patternW = Pattern.compile(patternWidth);
//            Matcher matcherHeight = patternH.matcher(style);
//            Matcher matcherWidth = patternW.matcher(style);
//            if (matcherHeight.find() && matcherWidth.find()) {
//                String height = matcherHeight.group(1);
//                String width = matcherWidth.group(1);
                LOGGER.debug("height: " + height + " width: " + width);
                if (Integer.valueOf(height) > Integer.valueOf(width)) {
                    // rotate
//                    clickFirstClickable(ROTATE_ICON_XPATH, ROTATE_ICON_XPATH2,
//                            webDriver, "Button 4: rotate1", "Button 4: rotate2");
                    waitAndClick(By.cssSelector(ROTATE_ICON_CSS), webDriver, "Button 4 : rotate");
                }

        }
    }

    private static void waitAndClick(By identifier, WebDriver webDriver, String buttonName) {
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.elementToBeClickable(identifier));
        WebElement webElement = webDriver.findElement(identifier);
        webElement.click();
        LOGGER.debug("Clicked: " + buttonName);
    }

    private static void waitAndClickXpath(String xpath, WebDriver webDriver, String buttonName) {
        waitAndClick(By.xpath(xpath), webDriver, buttonName);

// new WebDriverWait(webDriver, 3).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
//        WebElement webElement = webDriver.findElement(By.xpath(xpath));
//        webElement.click();
//        LOGGER.debug("Clicked: " + buttonName);
    }

        private static FirefoxOptions getFireFoxOptions () {
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("browser.download.folderList", 2);
            options.addPreference("browser.download.dir", "/Users/deepanshu/GoogleDrive/sunsetPics");
            options.addPreference("browser.download.useDownloadDir", true);
            options.addPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg");
            return options;
        }

        private static String currentTime () {
            long yourmilliseconds = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            Date resultdate = new Date(yourmilliseconds);
            return sdf.format(resultdate);
        }

    private static void clickFirstClickable(String xpath1, String xpath2, WebDriver webDriver, String buttonName1, String buttonName2){
        if(isPresent(xpath1, webDriver)) {
            waitAndClickXpath(xpath1, webDriver, buttonName1);
        } else if(isPresent(xpath2, webDriver)) {
            waitAndClickXpath(xpath2, webDriver, buttonName2);
        }
    }


    private static boolean isPresent(String xpath, WebDriver webDriver) {
        return isPresent(By.xpath(xpath), webDriver);
    }


    private static boolean isPresent(By identifier, WebDriver webDriver) {
        boolean present = false;
        try {
            new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOfElementLocated(identifier));
            present = true;
        } catch (TimeoutException e) {
            present = false;
        }
        return present;
    }
}
