package running;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driverInstance.DriverInstance;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.ResourceBundle;

public class MortgagePageSteps {

    WebDriver driver;
    DriverInstance dInstance=new DriverInstance();
    ResourceBundle config = ResourceBundle.getBundle("config");

//####################### GIVEN #################################

    @Given("^User is on Page with mortgage calculator$")
    public void userIsOnPageWithMortgageCalculator() {
        driver = dInstance.generateDriverInstance();
        driver.manage().window().maximize();
        driver.get(config.getString("appURL"));
    }

// ###################### WHEN ##################################

    @When("^User scroll to the Mortgage calculator$")
    public void userScrollToTheMortgageCalculator() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement calculator = driver.findElement(By.id("calc"));
        js.executeScript("arguments[0].scrollIntoView();", calculator);
    }

    @And("^Click on the circle 'i' button opposite of 'Использовать материнский капитал' checkbox$")
    public void clickOnTheCircleIButton() {
        driver.findElement(By.xpath("//div[text()='Использовать материнский капитал']/span/span/button")).click();

    }

    @When("^User enter property price equals \"([^\"]*)\" rubles$")
    public void userEnterPropertyPriceEqualsPriceRubles(String arg0) throws InterruptedException {
        WebElement price_data = driver.findElement(By.id("estateCost"));
        Actions builder = new Actions(driver);
        Actions sendData = builder
                .moveToElement(price_data)
                .doubleClick()
                .sendKeys(arg0);
        sendData.perform();
        Thread.sleep(1000);

    }

    @And("^User enter amount of downpayment equals \"([^\"]*)\" rubles$")
    public void userEnterAmountOfDownpaymentEqualsDownpaymentRubles(String arg0) {
        WebElement downpayment = driver.findElement(By.id("initialFee"));
        Actions builder = new Actions(driver);
        Actions mouseOverHome = builder
                .moveToElement(downpayment)
                .doubleClick()
                .sendKeys(arg0);
        mouseOverHome.perform();

    }
    @When("^User click on button 'Заполнить заявку'$")
    public void userClickOnButton() {
        driver.findElement(By.id("mortgage-benefits-button")).click();
    }

    @When("^User enter last name$")
    public void userEnterFirstName() throws InterruptedException {
        Thread.sleep(500);
        WebElement lastN;
        if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_ab_form"))){
            lastN = driver.findElement(By.xpath("//*[@name='lastName']"));
            lastN.click();
            lastN.sendKeys("Иванов");
            lastN.sendKeys(Keys.TAB);
        }else if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_form"))) {
            System.out.println("No implementations for the testing form located at the " + driver.getCurrentUrl());
            dInstance.closeDriverInstance(driver);

//            driver.findElement(By.xpath("//*[@name='surname']")).click();
//            driver.findElement(By.xpath("//*[@name='surname']")).sendKeys("Иванов");
//            lastN = driver.findElement(By.xpath("//*[@name='surname']"));
//            lastN.sendKeys(Keys.TAB);
        }

    }

    @And("^Enter first name$")
    public void enterLastName() throws InterruptedException {
        Thread.sleep(500);
        WebElement firstN;
        if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_ab_form"))) {
            firstN = driver.findElement(By.xpath("//input[@name='firstName']"));
            firstN.click();
            firstN.sendKeys("Иван");
            firstN.sendKeys(Keys.TAB);
         }else if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_form"))) {
            firstN =  driver.findElement(By.xpath("//*[@name='name']"));
            firstN.click();
            firstN.sendKeys("Иван");
            firstN.sendKeys(Keys.TAB);
        }
        Thread.sleep(500);
    }

    @And("^Enter middle name and sex \\(if present\\)$")
    public void enterMiddleName() throws InterruptedException {
        Thread.sleep(500);
        WebElement middleN;
        if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_ab_form"))) {
            middleN = driver.findElement(By.xpath("//*[@name='middleName']"));
            middleN.click();
            middleN.sendKeys("Иванович");
            middleN.sendKeys(Keys.TAB);
            driver.findElement(By.xpath("//*[text()='Мужской']")).click();

        } else if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_form"))){
            driver.findElement(By.xpath("//input[@name='default_checkbox']/../div[1]")).click();
             middleN = driver.findElement(By.xpath("//*[@name='patronymic']"));
             middleN.click();
             middleN.sendKeys("Иванович");
             middleN.sendKeys(Keys.TAB);
        }
        Thread.sleep(500);
    }

    @And("^enter email$")
    public void enterEmail() throws InterruptedException {
        Thread.sleep(500);
        WebElement emailField;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_ab_form"))) {
            emailField = driver.findElement(By.xpath("//*[@name='email']"));
            emailField.click();
            emailField.sendKeys("ivanovii@gmail.com");
            js.executeScript("arguments[0].scrollIntoView();", emailField);
        }else if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_form"))) {
            driver.findElement(By.xpath("div[text()='Электронная почта']")).click();
            driver.findElement(By.xpath("div[text()='Электронная почта']")).sendKeys("ivanovii@gmail.com");
        }
        Thread.sleep(500);
    }

    @And("^Select region from drop-down list$")
    public void selectRegionFromDropDownList() throws InterruptedException {
        Thread.sleep(500);
        WebElement subject;
        if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_ab_form"))) {
            subject = driver.findElement(By.xpath("//*[@name='region']"));
            subject.click();
            driver.findElement(By.xpath("//div[@id='mortgage-first-step-form']//li[1]")).click();
           // subject.sendKeys(Keys.TAB);
        }else if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_form"))) {
            subject = driver.findElement(By.xpath("//*[@name='region']"));
            subject.click();
            subject.sendKeys("Москва и МО");
            subject.sendKeys(Keys.ARROW_DOWN);
            subject.click();
        }
        Thread.sleep(500);
    }

    @And("^Check checkbox to processing of personal data$")
    public void checkCheckboxToProcessingOfPersonalData() throws InterruptedException {
        Thread.sleep(500);
        if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_ab_form"))) {
            driver.findElement(By.xpath("//input[@name='confirmed']/../div[1]")).click();
        }else if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_form"))) {
            driver.findElement(By.xpath("input[@name='agreement']")).click();
        }
    }

    @And("^Click on button 'Продолжить'$")
    public void clickOnButton() throws InterruptedException {
        Thread.sleep(500);
        if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_ab_form"))) {
            driver.findElement(By.xpath("//button[text()='Продолжить']")).click();
        }else if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_form"))) {
            driver.findElement(By.xpath("input[@name='agreement']")).click();
        }
        Thread.sleep(500);
    }

    @When("^User click on the link 'условия'$")
    public void userClickOnTheLink() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement conditions = driver.findElement(By.xpath("//span[text()='условиями']"));
        js.executeScript("arguments[0].scrollIntoView();", conditions);
        conditions.click();

    }

// ###################### THEN #####################################

    @Then("^Pop-up window is displayed with text \"([^\"]*)\"$")
    public void popUpWindowIsDisplayedWithText(String arg0) throws Throwable {
        Assert.assertTrue(String.valueOf(driver.findElement(By.xpath("//div[text()='"+arg0+"']"))),true);
        dInstance.closeDriverInstance(driver);
    }


    @Then("^User should see error message$")
    public void userShouldSeeMessage() throws Throwable {
        Assert.assertTrue(String.valueOf(driver.findElement(By.xpath("//div[contains(text(),'Альфа-Банк не выдаёт ипотечные кредиты меньше 600')]"))),true);
        dInstance.closeDriverInstance(driver);
    }

    @Then("^User should automatically scroll down to filling application section$")
    public void userShouldAutomaticallyScrollDownToFillingApplicationSection() {
        if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_ab_form"))) {
            Assert.assertTrue(Boolean.parseBoolean(String.valueOf(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_ab_form")))));
        }else if(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_form"))) {
            Assert.assertTrue(Boolean.parseBoolean(String.valueOf(driver.getCurrentUrl().equalsIgnoreCase(config.getString("complete_house_form")))));
        }

        dInstance.closeDriverInstance(driver);
    }


    @Then("^User should see error message under phone field$")
    public void userShouldSeeErrorMessageUnderPhoneField() {
        Assert.assertTrue(String.valueOf(driver.findElement(By.xpath("//div[text()='Поле обязательно для заполнения']"))),true);
        dInstance.closeDriverInstance(driver);
    }


    @Then("^User can read personal processing rules$")
    public void userCanReadPersonalProcessingRules() {
        Assert.assertTrue(String.valueOf(driver.findElement(By.xpath("//*[@xmlns='http://www.w3.org/2000/svg']/../../div/h5"))),true);
        dInstance.closeDriverInstance(driver);
    }
}
