package org.openjfx;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class Test {
    MonitorSize size = new MonitorSize();
    Configs conf = new Configs();
    ChromeDriver driver;
    WebDriverWait wait;
    Long documentNumber;
    String contractId;
    Boolean elementExist;
    String mobileScript = "arguments[0].setAttribute('href', '"+conf.urlMobile+"')";

    void firstPassengerM() { //adult mobile version
        driver.findElement(By.xpath("//fieldset/*[@placeholder='Фамилия *']")).sendKeys("Иванов");
        driver.findElement(By.xpath("//fieldset/*[@placeholder='Имя *']")).sendKeys("Иван");
        driver.findElement(By.xpath("//fieldset//div/div[1]/*[@placeholder='Отчество *']")).sendKeys("Иванович");
        driver.findElement(By.xpath("//fieldset//div/div[1]/*[@title='День']/option[2]")).click(); //birthday
        driver.findElement(By.xpath("//fieldset//div/div[2]/div/div/*[@title='Месяц']/option[2]")).click(); //month
        driver.findElement(By.xpath("//fieldset//div/div[3]/div/*[@title='Год']/option[24]")).click(); //year
        driver.findElement(By.xpath("//fieldset/*[@class='select identity_card placeholder']/select/option[2]")).click(); //citizenship
        driver.findElement(By.xpath("//fieldset/*[@class='select identity_card']/select/option[2]")).click(); //document
        driver.findElement(By.xpath("//fieldset//div/*[@placeholder='Номер документа *']")).sendKeys("1234 123456"); //document number
    }

    void secondPassengerM() { //child
        driver.findElement(By.cssSelector(".text.add-passenger")).click();
        driver.executeScript("window.scroll(0,100);");
        driver.findElement(By.xpath("//fieldset[2]/*[@placeholder='Фамилия *']")).sendKeys("Иванов");
        driver.findElement(By.xpath("//fieldset[2]/*[@placeholder='Имя *']")).sendKeys("Игорь");
        driver.findElement(By.xpath("//*[@id='booking']/div/div/fieldset[2]//div/div[2]/span")).click(); //without patronymic
        driver.findElement(By.xpath("//fieldset[2]//div/div[1]/*[@title='День']/option[2]")).click();
        driver.findElement(By.xpath("//fieldset[2]//div/div[2]/div/div/*[@title='Месяц']/option[2]")).click();
        driver.findElement(By.xpath("//fieldset[2]//div/div[3]/div/*[@title='Год']/option[8]")).click();
        driver.findElement(By.xpath("//fieldset[2]/*[@class='select identity_card placeholder']/select/option[2]")).click();
        driver.findElement(By.xpath("//fieldset[2]/*[@class='select identity_card']/select/option[4]")).click();
        driver.findElement(By.xpath("//fieldset[2]//div/*[@placeholder='Номер документа *']")).sendKeys("123456789");
    }

    void thirdPassengerM() { //child free
        driver.findElement(By.cssSelector(".text.add-passenger")).click();
        driver.findElement(By.xpath("//fieldset[3]/*[@placeholder='Фамилия *']")).sendKeys("Иванов");
        driver.findElement(By.xpath("//fieldset[3]/*[@placeholder='Имя *']")).sendKeys("Илья");
        driver.findElement(By.xpath("//fieldset[3]//div/div[1]/*[@placeholder='Отчество *']")).sendKeys("Иванович");
        driver.findElement(By.xpath("//fieldset[3]//div/div[1]/*[@title='День']/option[2]")).click();
        driver.findElement(By.xpath("//fieldset[3]//div/div[2]/div/div/*[@title='Месяц']/option[2]")).click();
        driver.findElement(By.xpath("//fieldset[3]//div/div[3]/div/*[@title='Год']/option[3]")).click();
        driver.findElement(By.xpath("//fieldset[3]/*[@class='select identity_card placeholder']/select/option[2]")).click();
        driver.findElement(By.xpath("//fieldset[3]/*[@class='select identity_card']/select/option[3]")).click();
        driver.findElement(By.xpath("//fieldset[3]//div/*[@placeholder='Номер документа *']")).sendKeys("IV-БЮ 123456");
    }

    void passengerSeatsM() { //seats desktop version
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(900)); //change timeout due "try"
            try {
                driver.findElement(By.xpath("//fieldset//div/*[@class='select placeholder']/select/option[2]")); //check possibility to take a place
                elementExist = true;
            } catch (Exception e) {
                elementExist = false;
                System.out.println("Warning: There are not places for passengers");
            }
            if (elementExist) {
                driver.findElement(By.xpath("//fieldset//div/*[@class='select placeholder']/select/option[2]")).click(); //first place
                driver.findElement(By.xpath("//fieldset[2]//div/*[@class='select placeholder']/select/option[3]")).click(); //second place
                try {
                    driver.findElement(By.xpath("//*[@id='booking']/div/div/fieldset[3]/label/span")).click(); //without place
                } catch (Exception e) { //если тарифа без места нет
                    driver.findElement(By.xpath("//fieldset[3]//div/*[@class='select placeholder']/select/option[4]")).click(); //third place
                    System.out.println("Warning: Ticket without place can`t be chosen");
                }
            } 
            if (!elementExist) { //there are no seats, but there is a tariff
                try {
                    driver.findElement(By.xpath("//*[@id='booking']/div/div/fieldset[3]/label/span")).click(); //tariff without place
                } catch (Exception ex) {
                    System.out.println("Warning: Free ticket can`t be chosen");
                }
            }
    }

    void passengerTariffsM() { //tariffs desktop version
        try {
            driver.findElement(By.xpath("//fieldset[3]/*[@class='row']/div[1]/div/select/option[2]")).click();
        } catch (Exception e) {
            System.out.println("Warning: First passenger`s tariff can`t be chosen");
        }
        try {
            driver.findElement(By.xpath("//fieldset[2]/*[@class='row']/div[1]/div/select/option[1]")).click();
        } catch (Exception e) {
            System.out.println("Warning: Second passenger`s tariff can`t be chosen");
        }
        try {
            driver.findElement(By.xpath("//fieldset[1]/*[@class='row']/div[1]/div/select/option[2]")).click();
        } catch (Exception e) {
            System.out.println("Warning: Third passenger`s tariff can`t be chosen");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
    }

    void passengerBaggageM() { //tariffs desktop version
        try {
            driver.findElement(By.xpath("//fieldset[1]/*[@class='row']/button")).click();
        } catch (Exception e) {
            System.out.println("Warning: First passenger`s tariff can`t be chosen");
        }
        try {
            driver.findElement(By.xpath("//fieldset[2]/*[@class='row']/button")).click();
        } catch (Exception e) {
            System.out.println("Warning: Second passenger`s tariff can`t be chosen");
        }
        try {
            driver.findElement(By.xpath("//fieldset[3]/*[@class='row']/button")).click();
        } catch (Exception e) {
            System.out.println("Warning: Third passenger`s tariff can`t be chosen");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
    }

    void insuranceM() { //insurance mobile version
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(900));
        try {
            driver.findElement(By.xpath("//*[@id='booking']/fieldset[4]/label/span[1]")).click();
            driver.findElement(By.xpath("//*[@id='booking']/fieldset[3]/div/label/span[1]")).click(); //with insurance /div/div/label/span
            driver.findElement(By.xpath("//*[@id='booking']/fieldset[4]/button")).click();
        } catch (Exception e) { //if there is no insurance
            driver.findElement(By.xpath("//*[@id='booking']/fieldset[3]/label/span[1]")).click();
            driver.findElement(By.xpath("//*[@id='booking']/fieldset[3]/button")).click();
            System.out.println("Warning: Insurance can`t be chosen");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
    }

    void firstPassengerD() { //adult desktop version
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset/div/div[1]/div[1]/input")).sendKeys("Иванов");
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset/div/div[1]/div[2]/input")).sendKeys("Иван");
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset/div/div[1]/div[3]/input")).sendKeys("Иванович");
        driver.findElement(By.name("birthdate-day")).sendKeys("01"); //birthday
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset/div/div[2]/div[1]/div/div[2]/div/select/option[2]")).click(); //month
        driver.findElement(By.name("birthdate-year")).sendKeys("2000"); //year
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset/div/div[2]/div[2]/div/select/option[2]")).click(); //citizenship
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset/div/div[2]/div[3]/div/select/option[2]")).click(); //document
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset/div/div[2]/div[4]/input")).sendKeys("1234 123456"); //document number
    }

    void secondPassengerD() { //child
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[3]/div/button")).click();
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[2]/div[2]/div[1]/div[1]/input")).sendKeys("Иванов");
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[2]/div[2]/div[1]/div[2]/input")).sendKeys("Игорь");
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[2]/div[2]/div[1]/div[3]/span/span[2]")).click();
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[2]/div[2]/div[2]/div[1]/div/div[1]/input")).sendKeys("01");
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[2]/div[2]/div[2]/div[1]/div/div[2]/div/select/option[2]")).click();
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[2]/div[2]/div[2]/div[1]/div/div[3]/input")).sendKeys("2016");
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[2]/div/div[2]/div[2]/div/select/option[2]")).click();
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[2]/div[2]/div[2]/div[3]/div/select/option[4]")).click();
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[2]/div[2]/div[2]/div[4]/input")).sendKeys("123456789");
    }

    void thirdPassengerD() { //child free
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[3]/div/button")).click();
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[1]/div[1]/input")).sendKeys("Иванов");
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[1]/div[2]/input")).sendKeys("Илья");
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[1]/div[3]/input")).sendKeys("Иванович");
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[2]/div[1]/div/div[1]/input")).sendKeys("01");
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[2]/div[1]/div/div[2]/div/select/option[2]")).click();
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[2]/div[1]/div/div[3]/input")).sendKeys("2020");
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div/div[2]/div[2]/div/select/option[2]")).click();
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[2]/div[3]/div/select/option[3]")).click();
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[2]/div[4]/input")).sendKeys("IV-БЮ 123456");
    }

    void passengerSeatsD() throws Exception { //seats desktop version
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(900)); //change timeout due "try"
        try {
            driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[1]/div[2]/div[4]/div[1]/div/div/div/div/div/div/select")); //check possibility to take a place
            elementExist = true;
        } catch (Exception e) {
            elementExist = false;
            System.out.println("Warning: There are not places for passengers");
        }
        if (elementExist) {
            driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[1]/div[2]/div[4]/div[1]/div/div/div/div/div/div/select")).click();
            driver.findElement(By.cssSelector(".seat-box.seat-widget__seat.seat-box_free.pointer")).click(); //first place
            Thread.sleep(600);
            driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[2]/div[2]/div[4]/div[1]/div/div/div/div/div/div/select")).click();
            driver.findElement(By.cssSelector(".seat-box.seat-widget__seat.seat-box_free.pointer")).click(); //second place
            try {
                driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[3]/div/div/div/label/span[1]/span")).click(); //without place
            }
            catch (Exception e) { //если тарифа без места нет
                driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[4]/div[1]/div/div/div/div/div/div/select")).click();
                driver.findElement(By.cssSelector(".seat-box.seat-widget__seat.seat-box_free.pointer")).click(); //third place
                System.out.println("Warning: Ticket without place can`t be chosen");
            }
        } 
        if (!elementExist) { //there are no seats, but there is a tariff
            try {
                driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[3]/div/div/div/label/span[1]/span")).click(); //tariff without place
            } catch (Exception ex) {
                System.out.println("Warning: Free ticket can`t be chosen");
            }
        }
    }

    void passengerTariffsD() { //tariffs desktop version
        try {
            driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[3]/div[2]/div[4]/div[2]/div/div/div/div/div/select/option[2]")).click();
        } catch (Exception e) {
            System.out.println("Warning: First passenger`s tariff can`t be chosen");
        }
        try {
            driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[2]/div[2]/div[4]/div[2]/div/div/div/div/div/select/option[1]")).click();
        } catch (Exception e) {
            System.out.println("Warning: Second passenger`s tariff can`t be chosen");
        }
        try {
            driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[2]/fieldset[1]/div[2]/div[4]/div[2]/div/div/div/div/div/select/option[2]")).click();
        } catch (Exception e) {
            System.out.println("Warning: Third passenger`s tariff can`t be chosen");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
    }

    void passengerBaggageD() { //tariffs desktop version
        try {
            driver.findElement(By.xpath("//fieldset[3]/div[2]/div[4]/div[3]/div/div/div[3]/span")).click();
        } catch (Exception e) {
            System.out.println("Warning: First passenger`s tariff can`t be chosen");
        }
        try {
            driver.findElement(By.xpath("//fieldset[2]/div[2]/div[4]/div[3]/div/div/div[3]/span")).click();
        } catch (Exception e) {
            System.out.println("Warning: Second passenger`s tariff can`t be chosen");
        }
        try {
            driver.findElement(By.xpath("//fieldset[1]/div[2]/div[4]/div[3]/div/div/div[3]/span")).click();
        } catch (Exception e) {
            System.out.println("Warning: Third passenger`s tariff can`t be chosen");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
    }

    void insuranceD() { //insurance desktop version
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(900));
        try {
            driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[6]/div[2]/div/label/span[1]/span")).click();
            driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[5]/div/label/span[1]/span")).click(); //with insurance /div/div/div/label/span[1]/span
            driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[6]/div[4]/button")).click();
        } catch (Exception e) { //if there is no insurance
            driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[5]/div[2]/div/label/span[1]/span")).click();
            driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div[3]/div[5]/div[4]/button")).click();
            System.out.println("Warning: Insurance can`t be chosen");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
    }

    void paymentSystem() throws Exception { //payment systems
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("avtovokzaly"))); //waiting for the exit from the avtovokzaly
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1800));
        String url = driver.getCurrentUrl();
        if (url.contains("checkout.sandbox.gateline.net")) { //GateLine
            driver.findElement(By.name("order_cc_number")).sendKeys(conf.cardGateline); //test card number
            driver.findElement(By.name("order_cc_expire")).sendKeys("1224");
            driver.findElement(By.name("order_cc_cvv")).sendKeys("111");
            driver.findElement(By.name("order_client_cardholder")).sendKeys("Ivanov Ivan");
            driver.findElement(By.cssSelector("button.std-btn")).click();
            System.out.println("Info: Gateline payment system was chosen");
        } if (url.contains("sbergate.com")) { //GateLine
            driver.findElement(By.name("pan")).sendKeys(conf.cardSberpay); //test card number
            driver.findElement(By.name("expiry")).sendKeys("1224");
            driver.findElement(By.name("cvc")).sendKeys("123");
            driver.findElement(By.cssSelector(".styles_solid__1fLFs")).click();
            driver.findElement(By.name("password")).sendKeys("12345678");
            System.out.println("Info: SberPay payment system was chosen");
        } if (url.contains("qr.nspk.ru")) { //GateLine
            Thread.sleep(60000);
            System.out.println("Info: SPB Gateline payment system was chosen");
            driver.navigate().back();
        } if (url.contains("securepayments.tinkoff.ru")) {
            Thread.sleep(1500);
            try { //SPB Tinkoff
                driver.findElement(By.xpath("//tui-island/section/tui-expand/div/div/eacq-card-form/form/div[3]/tui-checkbox-labeled/label/tui-checkbox/tui-primitive-checkbox/div/input")).click();
                driver.findElement(By.xpath("//tui-island/section/tui-expand/div/div/eacq-card-form/form/section/eacq-email-on-demand/div/div/tui-checkbox-labeled/label/tui-checkbox/tui-primitive-checkbox/div/input")).click();
                driver.findElement(By.xpath("//tui-island/section/tui-expand/div/div/eacq-card-form/form/div[1]//tui-input-card-grouped/div/div[1]/label/input")).sendKeys(conf.cardTinkoff); //test card number
                driver.findElement(By.xpath("//tui-island/section/tui-expand/div/div/eacq-card-form/form/div[1]//tui-input-card-grouped/div/div[2]/label/input")).sendKeys("1224");
                driver.findElement(By.xpath("//tui-island/section/tui-expand/div/div/eacq-card-form/form/div[1]//tui-input-card-grouped/div/div[3]/label/input")).sendKeys("111");
                driver.findElement(By.xpath("//tui-island/section/tui-expand/div/div/eacq-card-form/form/div[4]/button")).click();
                System.out.println("Info: SPB Tinkoff payment system was chosen");
                } catch (Exception ex) { //Tinkoff
                driver.findElement(By.xpath("//tui-island/section/tui-expand/div/div/eacq-card-form/form/section/eacq-email-on-demand/div/div/tui-checkbox-labeled/label/tui-checkbox/tui-primitive-checkbox/div/input")).click();
                driver.findElement(By.xpath("//tui-island/section/tui-expand/div/div/eacq-card-form/form/div[1]//tui-input-card-grouped/div/div[1]/label/input")).sendKeys(conf.cardTinkoff);//test card number
                driver.findElement(By.xpath("//tui-island/section/tui-expand/div/div/eacq-card-form/form/div[1]//tui-input-card-grouped/div/div[2]/label/input")).sendKeys("1224");
                driver.findElement(By.xpath("//tui-island/section/tui-expand/div/div/eacq-card-form/form/div[1]//tui-input-card-grouped/div/div[3]/label/input")).sendKeys("111");
                driver.findElement(By.xpath("//tui-island/section/tui-expand/div/div/eacq-card-form/form/div[2]/button")).click();
                System.out.println("Info: Tinkoff payment system was chosen");
           }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
        wait.until(ExpectedConditions.urlContains("avtovokzaly"));
    }

    void calculateNumber() { //random number for documents
        long leftEdge = 100000L;
        long rightEdge = 999999L;
        documentNumber = leftEdge + (long) (Math.random() * (rightEdge - leftEdge));
    }

    void activateContract () throws Exception {
        Thread.sleep(600);
        String contractUrl = driver.getCurrentUrl();
        int start = contractUrl.indexOf("id-");
        contractId = contractUrl.substring(start + 3);
        Thread.sleep(600);
        driver.get(conf.urlContracts);
        Thread.sleep(600);
        driver.findElement(By.xpath("//tr[@data-contract="+contractId+"]/td[7]/button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@data-contract="+contractId+"]/td[8]/button"))).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//tr[@data-contract="+contractId+"]/td[8]/button[2]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@data-contract="+contractId+"]/td[9]/button"))).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//tr[@data-contract="+contractId+"]/td[10]/a")).click();
    }

    void testStart() { //start of each test
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options); //run ChromeDriver
        driver.manage().window().setSize(new Dimension(size.width - size.width / 5, size.height)); //resolution
        driver.manage().window().setPosition(new Point(size.width / 5, 0)); //resolution
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000)); //waiting for elements
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(25000)); //waiting for the page to load
        driver.manage().timeouts().scriptTimeout(Duration.ofMillis(25000)); //waiting for scripts
        wait = new WebDriverWait(driver, (Duration.ofMillis(25000))); //method for waiting
    }

    void testEnd() throws Exception { //end of each test
        Thread.sleep(2100);
        driver.quit();
    }

    void bookingMobile(String from, String to) throws Exception {
        testStart();
        driver.get(conf.urlDesktop);
        WebElement href = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#copyright > div.text-right > a"))); //take a link
        JavascriptExecutor js = driver;
        js.executeScript(mobileScript, href); //put own link
        driver.findElement(By.cssSelector(".alternativeVersion > span")).click(); //switch to mobile version
        driver.findElement(By.id("from")).sendKeys(from);
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='search']/fieldset[1]/div/div[3]/div[1]")).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("to"))).sendKeys(to);
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='search']/fieldset[1]/div/div[4]/div[1]")).click();
        driver.findElement(By.id("searchFormSubmit")).click();
        driver.findElement(By.cssSelector(".buy-button.ok")).click(); //first selling route
        driver.findElement(By.cssSelector(".active .date-holder")).click(); //first active date
        firstPassengerM ();
        secondPassengerM ();
        thirdPassengerM ();
        passengerSeatsM ();
        passengerTariffsM ();
        passengerBaggageM();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='booking']/fieldset[2]/button"))).click();
        driver.findElement(By.id("booking-phone-id")).sendKeys(conf.phone);
        driver.findElement(By.id("booking-email-id")).sendKeys(conf.mail);
        insuranceM ();
        driver.findElement(By.cssSelector(".action.js-rebook.nowrap")).click(); //rebooking
        driver.findElement(By.xpath("//*[@id='message']/div[2]/input[1]")).click();
        driver.findElement(By.xpath("//*[@id='container']/div[1]/div[1]/div")).click();
        driver.findElement(By.cssSelector(".alternativeVersion")).click();
        testEnd();
    }

    void buyingMobile(String from, String to) throws Exception {
        testStart();
        driver.get(conf.urlDesktop);
        WebElement href = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#copyright > div.text-right > a")));
        JavascriptExecutor js = driver;
        js.executeScript(mobileScript, href);
        driver.findElement(By.cssSelector(".alternativeVersion > span")).click();
        driver.findElement(By.id("navCustomerArea")).click(); //authorization
        wait.until(ExpectedConditions.elementToBeClickable(By.name("userName"))).sendKeys(conf.userLogin);
        driver.findElement(By.name("password")).sendKeys(conf.userPassword);
        driver.findElement(By.name("submit")).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='container']/div[1]/div[1]/div")).click();
        driver.findElement(By.id("from")).sendKeys(from);
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='search']/fieldset[1]/div/div[3]/div[1]")).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("to"))).sendKeys(to);
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='search']/fieldset[1]/div/div[4]/div[1]")).click();
        driver.findElement(By.id("searchFormSubmit")).click();
        driver.findElement(By.cssSelector(".buy-button.ok")).click();
        driver.findElement(By.cssSelector(".active .date-holder")).click();
        firstPassengerM ();
        secondPassengerM ();
        thirdPassengerM ();
        passengerSeatsM ();
        passengerTariffsM ();
        passengerBaggageM();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='booking']/fieldset[2]/button"))).click();
        driver.findElement(By.id("booking-phone-id")).sendKeys(conf.phone);
        driver.findElement(By.id("booking-email-id")).sendKeys(conf.mail);
        insuranceM ();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pay-start-row"))); //waiting for payment systems
        driver.executeScript("window.scroll(0,1000);");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(900));
        try {
            driver.findElement(By.xpath("//*[@id='cart']/div[6]/div[5]/p/span")).click();
        } catch (Exception e) {
            System.out.println("Warning: There are no hidden payment systems");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
        List<WebElement> links = driver.findElements(By.cssSelector(".pay-start-row")); //list of payment systems
        String orderUrl = driver.getCurrentUrl(); //take order url
        links.get(new Random().nextInt(links.size())).click(); //random choice of payment systems
        paymentSystem ();
        driver.get(conf.urlDesktop); //fast passage
        driver.findElement(By.id("navCustomerArea")).click();
        driver.findElement(By.xpath("//*[@id='container']/div[1]/div[2]/a[2]/span[2]")).click();
        driver.findElement(By.xpath("//*[@id='container']/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]/span/span")).click(); //delete the first passenger
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ok.dialog"))).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='container']/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]/span/span")).click(); //delete the second passenger
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ok.dialog"))).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='container']/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]/span/span")).click(); //delete the third passenger
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ok.dialog"))).click();
        Thread.sleep(600);
        driver.get(orderUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Вернуть заказ')]"))).click();
        driver.findElement(By.name("reason")).click();
        driver.findElement(By.xpath("//option[contains(text(),'Отмена поездки')]")).click();
        List<WebElement> elementList = driver.findElements(By.cssSelector(".checkbox"));
        elementList.getLast().click();
        driver.findElement(By.cssSelector("[data-original-label='Вернуть билеты']")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("navCustomerArea")).click();
        driver.findElement(By.xpath("//*[@id='container']/div[1]/div[2]/a[5]/span[2]")).click(); //exit
        driver.findElement(By.cssSelector(".alternativeVersion")).click();
        testEnd();
    }

    void bookingDesktop(String from, String to) throws Exception {
        testStart();
        driver.get(conf.urlDesktop);
        Thread.sleep(600);
        driver.findElement(By.name("from")).sendKeys(from);
        Thread.sleep(900);
        driver.findElement(By.xpath("//*[@id='searchBar']/div[3]/div/div[1]")).click();
        driver.findElement(By.name("to")).sendKeys(to);
        Thread.sleep(900);
        driver.findElement(By.xpath("//*[@id='searchBar']/div[3]/div[2]/div")).click();
        driver.findElement(By.xpath("//*[@id='searchBar']/div[2]/div[4]/div[2]/div/div[2]")).click(); //chose tomorrow
        driver.findElement(By.cssSelector(".buy-button.button.button-green")).click(); //first selling route
        firstPassengerD();
        secondPassengerD ();
        thirdPassengerD ();
        Thread.sleep(300);
        passengerSeatsD ();
        passengerTariffsD ();
        passengerBaggageD();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button.button-green.booking-form__submit"))).click();
        driver.findElement(By.name("tel")).sendKeys(conf.phone);
        driver.findElement(By.name("email")).sendKeys(conf.mail);
        insuranceD ();
        driver.findElement(By.cssSelector(".gi.gi-square-edit")).click(); //rebooking
        driver.findElement(By.xpath("//*[@id='cart-order']/div[5]/div/div[3]")).click(); //admit
        driver.findElement(By.xpath("//*[@id='headerContent']/div[2]")).click();
        testEnd();
    }

    void buyingDesktop(String from, String to) throws Exception {
        testStart();
        driver.get(conf.urlDesktop);
        driver.findElement(By.xpath("//*[@id='login']/span[2]")).click(); //authorization
        driver.findElement(By.name("userName")).sendKeys(conf.userLogin);
        driver.findElement(By.name("password")).sendKeys(conf.userPassword);
        driver.findElement(By.name("submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='action-link description logout']")));
        driver.findElement(By.xpath("//*[@id='headerContent']/div[2]")).click();
        Thread.sleep(300);
        driver.findElement(By.name("from")).sendKeys(from);
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='searchBar']/div[3]/div/div[1]")).click();
        driver.findElement(By.name("to")).sendKeys(to);
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='searchBar']/div[3]/div[2]/div[1]")).click();
        driver.findElement(By.xpath("//*[@id='searchBar']/div[2]/div[4]/div[2]/div/div[2]")).click();
        driver.findElement(By.cssSelector(".buy-button.button.button-green")).click();
        firstPassengerD ();
        secondPassengerD ();
        thirdPassengerD ();
        Thread.sleep(300);
        passengerSeatsD ();
        passengerTariffsD ();
        passengerBaggageD();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button.button-green.booking-form__submit"))).click();
        driver.findElement(By.name("tel")).sendKeys(conf.phone);
        driver.findElement(By.name("email")).sendKeys(conf.mail);
        insuranceD ();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".va-middle")));
        driver.executeScript("window.scroll(0,1000);");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(900));
        try {
            driver.findElement(By.xpath("//*[@id='content']/div[6]/div[3]/div[1]/div/span")).click();
        } catch (Exception e) {
            System.out.println("Warning: There are no hidden payment systems");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
        List<WebElement> links = driver.findElements(By.xpath("//*[@title='Вы будете перенаправлены на сайт платёжной системы']"));
        String orderUrl = driver.getCurrentUrl();
        links.get(new Random().nextInt(links.size())).click();
        Thread.sleep(1800);
        paymentSystem ();
        Thread.sleep(6000);
        driver.get(conf.urlDesktop+"passenger/list");
        driver.findElement(By.xpath("//*[@id='content']/table/tbody[3]/tr[1]/td[4]/a/span[2]")).click(); //delete the first passenger
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='passenger-list']/div[5]/div/div[3]"))).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='content']/table/tbody[3]/tr[1]/td[4]/a/span[2]")).click(); //delete the second passenger
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='passenger-list']/div[5]/div/div[3]"))).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='content']/table/tbody[3]/tr[1]/td[4]/a/span[2]")).click(); //delete the third passenger
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='passenger-list']/div[5]/div/div[3]"))).click();
        Thread.sleep(600);
        driver.get(orderUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Вернуть заказ')]"))).click();
        driver.findElement(By.name("reason")).click();
        driver.findElement(By.xpath("//option[contains(text(),'Отмена поездки')]")).click();
        driver.findElement(By.cssSelector(".checkbox")).click();
        driver.findElement(By.cssSelector("[data-original-label='Вернуть билеты']")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector(".logout")).click(); //exit
        testEnd();
    }

    void createCompany() throws Exception { //creation of a company by a carrier
        testStart();
        driver.get(conf.urlLk);
        driver.findElement(By.name("userName")).sendKeys(conf.carrierLogin);
        driver.findElement(By.name("password")).sendKeys(conf.carrierPassword);
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.xpath("//*[@id='index-index']/div[1]/div[1]/nav/ul/li[1]/a")).click(); //companies
        driver.findElement(By.xpath("//*[@id='company-list']/div[1]/ul/a")).click(); //new company
        driver.findElement(By.xpath("//*[@id='company-add']/div[1]/div[3]/form/div[1]/div/div/button/span[1]")).click();
        driver.findElement(By.xpath("//*[@id='company-add']/div/div[3]/form/div/div/div/div/ul/li[2]/a")).click(); //country
        driver.findElement(By.xpath("//*[@id='company-add']/div[1]/div[3]/form/div[2]/div/div/button/span[1]")).click();
        driver.findElement(By.xpath("//*[@id='company-add']/div[1]/div[3]/form/div[2]/div/div/div/ul/li[2]")).click(); //company type
        calculateNumber ();
        String number = Long.toString(documentNumber); //random number
        driver.findElement(By.name("reg_name")).sendKeys("Тестовый №"+number); //company name
        driver.findElement(By.name("company_brand_name")).sendKeys("TEST"); //brand
        driver.findElement(By.xpath("//*[@id='company-add']/div/div[3]/form/div[6]/div/div/div/div/label[2]")).click();
        driver.findElement(By.name("phone[0][phone_number]")).sendKeys("9009009090");
        driver.findElement(By.name("phone[0][phone_explanation]")).sendKeys("Диспетчер");
        driver.findElement(By.name("company_address")).sendKeys("улица Пушкина");
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='_autoId_20__ifr']"))); //comments
        driver.findElement(By.xpath("//body[@id='tinymce']/p")).sendKeys("Компания создана автоматически для тестирования");
        driver.switchTo().defaultContent(); //main page
        driver.findElement(By.name("company_site")).sendKeys("https://www.avtovokzaly.ru");
        driver.findElement(By.name("submit")).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div/ul/li[2]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='document-list']/dl/dd/ul/li[1]/button"))).click(); //add INN
        calculateNumber ();
        String inn = documentNumber +"000000";
        driver.findElement(By.name("number")).sendKeys(inn); //INN number
        driver.findElement(By.id("_autoId_7_")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(900));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.findElement(By.xpath("//*[@id='document-list']/dl/dd/ul/li[1]/button")).click(); //add OGRNIP
        calculateNumber ();
        String ogrnip = documentNumber +"000000000";
        driver.findElement(By.name("number")).sendKeys(ogrnip); //OGRNIP number
        driver.findElement(By.id("_autoId_7_")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.findElement(By.xpath("//*[@id='document-list']/dl/dd/ul/li[1]/button")).click(); //add Insurance
        calculateNumber ();
        String insurance = Long.toString(documentNumber);
        driver.findElement(By.name("number")).sendKeys(insurance);
        driver.findElement(By.name("insurance_company")).sendKeys("Альфа");
        Thread.sleep(300);
        driver.findElement(By.name("from_date")).click(); //start date
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div/table/tbody/tr/td")).click(); //first date in window
        driver.findElement(By.name("to_date")).click(); //end date
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div/table/tbody/tr[6]/td[7]")).click(); //last date in window
        driver.findElement(By.id("_autoId_10_")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.findElement(By.xpath("//*[@id='document-list']/dl/dd/ul/li/ul/li[1]/button")).click(); //add License
        calculateNumber ();
        String license = Long.toString(documentNumber);
        driver.findElement(By.name("number")).sendKeys(license); //License number
        Thread.sleep(300);
        driver.findElement(By.name("from_date")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div/table/tbody/tr/td")).click();
        driver.findElement(By.name("to_date")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div/table/tbody/tr[6]/td[7]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
        driver.findElement(By.id("_autoId_9_")).sendKeys(conf.filePath);
        driver.findElement(By.id("_autoId_10_")).click();
        Thread.sleep(600);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div[2]/ul/li[3]/a"))).click();
        driver.findElement(By.name("create_timetable")).click();
        driver.findElement(By.id("_autoId_30_")).click();
        driver.findElement(By.id("_autoId_35_")).click();
        driver.findElement(By.id("_autoId_40_")).click();
        driver.findElement(By.id("_autoId_45_")).click();
        driver.findElement(By.id("_autoId_50_")).click();
        driver.findElement(By.id("_autoId_56_")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[3]/div[3]/div/div[2]/div[9]")).click(); //work start
        driver.findElement(By.xpath("//*[@id='company-edit']/div[3]/div[3]/div/div[3]/div[1]")).click();
        driver.findElement(By.id("_autoId_57_")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[4]/div[3]/div/div[2]/div[21]")).click(); //work end
        driver.findElement(By.xpath("//*[@id='company-edit']/div[4]/div[3]/div/div[3]/div[1]")).click();
        driver.findElement(By.id("_autoId_58_")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[3]/div/div[2]/div[14]")).click(); //break start
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[3]/div/div[3]/div[1]")).click();
        driver.findElement(By.id("_autoId_59_")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[6]/div[3]/div/div[2]/div[15]")).click(); //break end
        driver.findElement(By.xpath("//*[@id='company-edit']/div[6]/div[3]/div/div[3]/div[1]")).click();
        driver.findElement(By.xpath("//*[@id='timetable-container']/table/tbody/tr[10]/td[2]/div/div/button/span")).click();
        driver.findElement(By.xpath("//*[@id='timetable-container']/table/tbody/tr[10]/td[2]/div/div/div/ul/li[39]/a")).click(); //timezone
        driver.findElement(By.id("_autoId_76_")).click();
        driver.findElement(By.id("_autoId_77_")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div[2]/ul/li[4]")).click();
        Thread.sleep(600);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sales']/div/div[1]/ul/li[2]/a"))).click(); //agent contract
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sales-requisites']/form/div[2]/div[2]/p"))).click(); //create contract
        Thread.sleep(600);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-edit']/div[8]/div/div/div[2]/form/div[2]/div[2]/p"))).click(); //create organization
        wait.until(ExpectedConditions.elementToBeClickable(By.name("inn"))).sendKeys(inn);
        Thread.sleep(600);
        driver.findElement(By.name("ogrn")).sendKeys(ogrnip);
        driver.findElement(By.name("legal_address_index")).sendKeys("170000");
        driver.findElement(By.name("legal_address_text")).sendKeys("улица Пушкина");
        driver.findElement(By.name("post_address_index")).sendKeys("170000");
        driver.findElement(By.name("post_address_text")).sendKeys("улица Пушкина");
        driver.findElement(By.name("post_address_recipient")).sendKeys("Тестировщик");
        driver.findElement(By.xpath("//button[contains(text(),'Добавить организацию')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.name("partner_delegate_fio"))).sendKeys("Тестировщик Т.Т.");
        driver.findElement(By.name("partner_delegate_position")).sendKeys("тестировщик");
        driver.findElement(By.name("bank_account[bank]")).sendKeys("Неальфа");
        driver.findElement(By.name("bank_account[bik]")).sendKeys("111111111");
        driver.findElement(By.name("bank_account[rs]")).sendKeys("40802810229120001111");
        driver.findElement(By.name("bank_account[ks]")).sendKeys("11111111111111111111");
        driver.findElement(By.cssSelector("#_autoId_18_.form-control")).sendKeys("9009009090");
        driver.findElement(By.cssSelector("#_autoId_20_.btn-primary.btn")).click();
        Thread.sleep(600);
        driver.findElement(By.cssSelector("#_autoId_3_.btn-primary.btn")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Правила возврата')]"))).click();//return rules
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[3]/div/div[1]/div[4]/div/div[2]/div/div[3]/form/div[2]/div/div/label[2]"))).click();
        driver.findElement(By.cssSelector("#_autoId_14_.btn-primary.btn")).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div/ul/li[5]/a")).click();
        driver.findElement(By.xpath("//*[@id='bus-units-and-drivers']/table[1]/tbody/tr/td/button")).click();
        driver.findElement(By.name("model")).sendKeys("ПАЗ");
        driver.findElement(By.name("gos_number")).sendKeys("е111ее");
        driver.findElement(By.cssSelector("#_autoId_3_.btn-primary.btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(900));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.findElement(By.xpath("//*[@id='bus-units-and-drivers']/table[2]/tbody/tr/td/button")).click();
        driver.findElement(By.name("fio")).sendKeys("Иваныч");
        driver.findElement(By.cssSelector("#_autoId_2_.btn-primary.btn")).click();
        testEnd();
    }

    void createRoute() throws Exception { //creation of a route by a carrier
        testStart();
        driver.get(conf.urlLk);
        driver.findElement(By.name("userName")).sendKeys(conf.carrierLogin);
        driver.findElement(By.name("password")).sendKeys(conf.carrierPassword);
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.xpath("//*[@id='index-index']/div[1]/div[1]/nav/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id='route-list']/div[1]/ul/a")).click();
        driver.findElement(By.name("company")).sendKeys("Тестовый"); //company name
        driver.findElement(By.xpath("//*[@id='add-route-form']/div[2]/div/div/div/span[1]/div/div/div")).click(); //first company
        driver.findElement(By.name("locality_from")).sendKeys("Тверь"); //from
        driver.findElement(By.xpath("//*[@id='add-route-form']/div[5]/div[1]/div/span/div/div/div[1]")).click();
        driver.findElement(By.xpath("//*[@id='add-route-form']/div[6]/div[1]/div[1]/div/div/span[1]/div/div/div[4]")).click(); //particular city
        driver.findElement(By.name("locality_to")).sendKeys("Новосокольники"); //to
        driver.findElement(By.xpath("//*[@id='add-route-form']/div[5]/div[3]/div/span/div/div/div")).click();
        driver.findElement(By.xpath("//*[@id='add-route-form']/div[6]/div[1]/div[2]/div/div/span[1]/div/div/div[1]")).click(); //first city
        driver.findElement(By.name("duration_hours")).sendKeys("1"); //hours
        driver.findElement(By.name("duration_minutes")).sendKeys("10"); //minutes
        driver.findElement(By.xpath("//*[@id='add-route-form']/div[9]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id='route']/div[2]/div[2]/button[2]")).click();
        driver.findElement(By.id("_autoId_9_")).click();
        driver.findElement(By.xpath("//*[@id='route-master']/div[3]/div[3]/div/div[2]/div[1]")).click(); //departure time
        driver.findElement(By.xpath("//*[@id='route-master']/div[3]/div[3]/div/div[3]/div[2]")).click();
        driver.findElement(By.id("_autoId_10_")).click();
        driver.findElement(By.cssSelector(".btn-group:nth-child(2) > .btn-group:nth-child(2) > .btn")).click(); //work days
        driver.findElement(By.xpath("//*[@id='route-master']/div[4]/div[2]/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id='departures-form']/fieldset/div[2]/div/div/button")).click(); //add second departure time
        driver.findElement(By.xpath("//*[@id='departures-form']/fieldset/div[2]/div/div/input")).click();
        driver.findElement(By.xpath("//*[@id='route-master']/div[4]/div[3]/div/div[2]/div[23]")).click(); //departure time
        driver.findElement(By.xpath("//*[@id='route-master']/div[4]/div[3]/div/div[3]/div[2]")).click();
        driver.findElement(By.xpath("//*[@id='departures-form']/fieldset/div[2]/div[2]/div/textarea")).click();
        driver.findElement(By.cssSelector(".btn-group:nth-child(2) > .btn-group:nth-child(3) > .btn")).click(); //weekend
        driver.findElement(By.xpath("//*[@id='route-master']/div[5]/div[2]/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id='frequency']/div[2]/div[2]/button[2]")).click();
        driver.findElement(By.xpath("//*[@id='price-container']/div/table/tbody/tr[2]/td/div/input")).sendKeys("10"); //fare
        driver.findElement(By.cssSelector(".panel:nth-child(3) .panel-title")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.name("baggage_price_included"))).click(); //luggage fare included
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#priceTab > .panel .panel-title"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='discount-container']/div/div[2]/button/span[2]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".col-xs-8 .caret"))).click();
        driver.findElement(By.cssSelector(".dropdown-menu:nth-child(2) > li:nth-child(3) .text")).click(); //discount for 12 year-olds
        driver.findElement(By.id("_autoId_7_")).click();
        Thread.sleep(300);
        driver.findElement(By.cssSelector(".col-xs-8 .caret")).click();
        driver.findElement(By.cssSelector(".dropdown-menu:nth-child(2) > li:nth-child(3) .text")).click(); //discount for 5 year-olds
        driver.findElement(By.id("_autoId_7_")).click();
        driver.executeScript("window.scroll(0,1000);");
        driver.findElement(By.xpath("//*[@id='priceTab']/div[2]/div[2]/button[2]")).click();
        driver.findElement(By.name("route_number[0][number]")).sendKeys("111");
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[2]/div/div/button/span[2]/span")).click();
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[2]/div/div/div/ul/li[2]/a")).click(); //bus
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[3]/div/div/button/span[2]/span")).click();
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[3]/div/div/div/ul/li[2]/a")).click(); //driver
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[4]/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[4]/div/div/div/ul/li[21]/a")).click(); //places
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[5]/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[5]/div/div/div/ul/li[2]/a")).click(); //soft seats
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[6]/div/div/div/label")).click(); //wi-fi
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[7]/div/div/div/label")).click(); //toilet
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[8]/div/div/div/label")).click(); //conditioner
        driver.findElement(By.name("rules_transport_animals_and_baggage")).sendKeys("Животных в багаже не провозить");
        driver.findElement(By.name("info")).sendKeys("Руки из окна не высовывать");
        driver.findElement(By.name("phone[0][phone_number]")).sendKeys("89009009099");
        driver.findElement(By.name("phone[0][phone_explanation]")).sendKeys("Недиспетчер");
        driver.findElement(By.xpath("//*[@id='additionally']/form/div[13]/div[2]/button[2]")).click(); //saving
        driver.findElement(By.xpath("//*[@id='route-edit']/div/div[3]/div/ul/li[5]/a")).click(); //back to additionally
        driver.findElement(By.xpath("//div[@id='additionalTab']/div[2]/div/h4")).click();
        driver.findElement(By.id("_autoId_27_")).sendKeys(conf.filePath); //add file
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("_autoId_28_"))).click(); //saving
        testEnd();
    }

    void salesActivation() throws Exception { //sales activation
        testStart();
        driver.get(conf.urlLk);
        driver.findElement(By.name("userName")).sendKeys(conf.adminLogin);
        driver.findElement(By.name("password")).sendKeys(conf.adminPassword);
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.xpath("//*[@id='index-index']/div[1]/div[1]/nav/ul/li[1]/a")).click();
        driver.findElement(By.name("name")).sendKeys("ИП Тестовый", Keys.ENTER); //set name, push Enter
        driver.findElement(By.xpath("//*[@id='company-list']/div[1]/div[3]/table/tbody/tr/td[8]/a")).click();
        Thread.sleep(600);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Документы и разрешения')]"))).click(); //go to admit documents
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-info:nth-child(2) .btn-success"))).click(); //admit first document
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-info:nth-child(3) .btn-success"))).click(); //admit second document
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-info:nth-child(4) .btn-success"))).click(); //admit third document
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-info:nth-child(5) .btn-success"))).click(); //admit fourth document
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id='panelPermission']/div/button")).click(); //add permissions
        Thread.sleep(1200);
        driver.findElement(By.id("_autoId_8_")).click(); //intercity routes
        driver.findElement(By.name("interurban[date_start]")).click(); //start date permission
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[1]/table/tbody/tr[1]/td[1]")).click(); //fist date
        driver.findElement(By.name("interurban[date_end]")).click(); //end date permission
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[1]/table/tbody/tr[6]/td[7]")).click(); //last date
        Thread.sleep(300);
        driver.findElement(By.cssSelector("label > #_autoId_12_")).click(); //suburban routes
        driver.findElement(By.name("suburban[date_start]")).click(); //start date permission
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[1]/table/tbody/tr[1]/td[1]")).click(); //fist date
        driver.findElement(By.name("suburban[date_end]")).click(); //end date permission
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[1]/table/tbody/tr[6]/td[7]")).click(); //last date
        driver.findElement(By.cssSelector(".modal-footer > #_autoId_15_")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Агентский договор №')]")).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='info']/form/div[14]/div/div/button"))).click(); //contract`s status
        driver.findElement(By.xpath("//*[@id='info']/form/div[14]/div/div/div/ul/li[3]")).click(); //contract`s status
        driver.findElement(By.id("_autoId_30_")).sendKeys("11111"); //1s
        driver.findElement(By.id("_autoId_31_")).click();
        activateContract();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Перевозчики по договору')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-list']/div[1]/div[3]/table/tbody/tr/td[8]/a"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div/ul/li[4]/a"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Включить продажи')]"))).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[4]/div/div/div[3]/button[1]")).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Включить продажи от имени перевозчика')]"))).click();
        driver.findElement(By.id("_tmpIdagreement")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-edit']/div[4]/div/div/div[3]/button[1]"))).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-edit']/div[1]/div[1]/nav[2]/div/span"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-edit']/div[1]/div[1]/nav[2]/div/div/a"))).click();
        driver.findElement(By.cssSelector(".btn-info")).click(); //edit first route
        driver.findElement(By.xpath("//*[@id='route-edit']/div[1]/div[3]/div[1]/ul/li[5]/a")).click(); //sales
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='sales']/div[1]/button")).click(); //on sales
        driver.findElement(By.cssSelector(".btn-warning:nth-child(1)")).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='sales']/div[3]/button")).click(); //on sales
        driver.findElement(By.cssSelector(".text-justify")).click();
        driver.findElement(By.cssSelector(".btn-warning:nth-child(1)")).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='sales']/form/div/div[2]/div[3]/div/div/div/label[2]")).click(); //set free seats
        driver.findElement(By.name("seat_diapason")).sendKeys("1-20");
        driver.findElement(By.cssSelector(".validate > #_autoId_10_")).click();
        testEnd();
    }

    void deleteCompany() throws Exception { //company and route deletion by administrator
        testStart();
        driver.get(conf.urlLk);
        driver.findElement(By.name("userName")).sendKeys(conf.adminLogin);
        driver.findElement(By.name("password")).sendKeys(conf.adminPassword);
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.xpath("//*[@id='index-index']/div[1]/div[1]/nav/ul/li[1]/a")).click();
        driver.findElement(By.name("name")).sendKeys("ИП Тестовый", Keys.ENTER); //set name, push Enter
        driver.findElement(By.xpath("//*[@id='company-list']/div[1]/div[3]/table/tbody/tr/td[8]/a")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div/ul/li[4]/a")).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Отключить продажи от имени перевозчика')]"))).click();
        driver.findElement(By.cssSelector(".btn-warning")).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Отключить продажи')]"))).click();
        driver.findElement(By.id("_tmpIdreason")).sendKeys("Тест");
        driver.findElement(By.cssSelector(".btn-warning")).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Документы и разрешения')]"))).click();
        Thread.sleep(600);
        driver.findElement(By.cssSelector(".bg-success:nth-child(2) .btn-danger > .glyphicon")).click(); //refuse first document
        driver.findElement(By.cssSelector(".form-group > #_autoId_1_")).sendKeys("Тест");
        driver.findElement(By.cssSelector(".form-group > #_autoId_2_")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(900));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.findElement(By.cssSelector(".bg-success:nth-child(2) .btn-danger > .glyphicon")).click(); //refuse second document
        driver.findElement(By.cssSelector(".form-group > #_autoId_1_")).sendKeys("Тест");
        driver.findElement(By.cssSelector(".form-group > #_autoId_2_")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.findElement(By.cssSelector(".decline-document")).click(); //refuse third document
        driver.findElement(By.cssSelector(".form-group > #_autoId_1_")).sendKeys("Тест");
        driver.findElement(By.cssSelector(".form-group > #_autoId_2_")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.findElement(By.cssSelector(".decline-document")).click(); //refuse fourth document
        driver.findElement(By.cssSelector(".form-group > #_autoId_1_")).sendKeys("Тест");
        driver.findElement(By.cssSelector(".form-group > #_autoId_2_")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.findElement(By.cssSelector(".btn-permission-delete")).click(); //delete permission
        driver.findElement(By.cssSelector(".modal-footer > .btn-primary")).click();
        driver.findElement(By.cssSelector(".btn-company-delete")).click(); //refuse request selling
        driver.findElement(By.name("action-comment")).sendKeys("Тест");
        driver.findElement(By.cssSelector(".btn-warning")).click();
        testEnd();
    }

    void createAll() throws Exception { //full creation of company and route by admin
        testStart();
        driver.get(conf.urlLk);
        driver.findElement(By.name("userName")).sendKeys(conf.adminLogin);
        driver.findElement(By.name("password")).sendKeys(conf.adminPassword);
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.xpath("//*[@id='index-index']/div[1]/div[1]/nav/ul/li[1]/a")).click(); //company
        driver.findElement(By.xpath("//*[@id='company-list']/div[1]/ul/a")).click(); //new company
        driver.findElement(By.xpath("//*[@id='company-add']/div[1]/div[3]/form/div[1]/div/div/button/span[1]")).click();
        driver.findElement(By.xpath("//*[@id='company-add']/div/div[3]/form/div/div/div/div/ul/li[2]/a")).click(); //country
        driver.findElement(By.xpath("//*[@id='company-add']/div[1]/div[3]/form/div[2]/div/div/button/span[1]")).click();
        driver.findElement(By.xpath("//*[@id='company-add']/div[1]/div[3]/form/div[2]/div/div/div/ul/li[2]/a/span[1]")).click(); //company type
        calculateNumber ();
        String number = Long.toString(documentNumber); //random number
        driver.findElement(By.name("reg_name")).sendKeys("Тестовый №"+number); //name
        driver.findElement(By.name("company_brand_name")).sendKeys("TEST"); //brand
        driver.findElement(By.xpath("//*[@id='company-add']/div/div[3]/form/div[6]/div/div/div/div/label[2]")).click();
        driver.findElement(By.name("phone[0][phone_number]")).sendKeys("89009009090");
        driver.findElement(By.name("phone[0][phone_explanation]")).sendKeys("Диспетчер");
        driver.findElement(By.name("company_address")).sendKeys("улица Пушкина");
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='_autoId_20__ifr']"))); //comments
        driver.findElement(By.xpath("//body[@id='tinymce']/p")).sendKeys("Компания создана автоматически для тестирования");
        driver.switchTo().defaultContent(); //main page
        driver.findElement(By.name("company_site")).sendKeys("https://www.avtovokzaly.ru");
        driver.findElement(By.name("submit")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("company_account"))).sendKeys("09385");
        driver.findElement(By.name("submit")).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div[2]/ul/li[2]/a")).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='document-list']/dl/dd/ul/li[1]/button"))).click(); //add INN
        calculateNumber ();
        String inn = documentNumber +"000000";
        driver.findElement(By.name("number")).sendKeys(inn); //INN number
        driver.findElement(By.xpath("/html/body/div[4]/div/div/form/div[3]/div/button[2]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(900));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.findElement(By.xpath("//*[@id='document-list']/dl/dd/ul/li[1]/button")).click(); //add OGRNIP
        calculateNumber ();
        String ogrnip = documentNumber +"000000000";
        driver.findElement(By.name("number")).sendKeys(ogrnip); //OGRNIP number
        driver.findElement(By.xpath("/html/body/div[4]/div/div/form/div[3]/div/button[2]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.findElement(By.xpath("//*[@id='document-list']/dl/dd/ul/li[1]/button")).click(); //add Insurance
        calculateNumber ();
        String insurance = Long.toString(documentNumber);
        driver.findElement(By.name("number")).sendKeys(insurance);
        driver.findElement(By.name("insurance_company")).sendKeys("Альфа"); 
        Thread.sleep(300);
        driver.findElement(By.name("from_date")).click(); //start date
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div/table/tbody/tr/td")).click(); //first date in window
        driver.findElement(By.name("to_date")).click(); //end date
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div/table/tbody/tr[6]/td[7]")).click(); //last date in window
        driver.findElement(By.id("_autoId_10_")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.findElement(By.xpath("//*[@id='document-list']/dl/dd/ul/li/ul/li[1]/button")).click(); //add License
        calculateNumber ();
        String license = Long.toString(documentNumber);
        driver.findElement(By.name("number")).sendKeys(license); //License number
        Thread.sleep(300);
        driver.findElement(By.name("from_date")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div/table/tbody/tr[1]/td[1]")).click();
        driver.findElement(By.name("to_date")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div/table/tbody/tr[6]/td[7]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
        driver.findElement(By.name("file_collection[0][file]")).sendKeys(conf.filePath); //add file
        driver.findElement(By.id("_autoId_10_")).click();
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div[2]/ul/li[3]/a")).click();
        driver.findElement(By.name("create_timetable")).click();
        driver.findElement(By.id("_autoId_30_")).click();
        driver.findElement(By.id("_autoId_35_")).click();
        driver.findElement(By.id("_autoId_40_")).click();
        driver.findElement(By.id("_autoId_45_")).click();
        driver.findElement(By.id("_autoId_50_")).click();
        driver.findElement(By.id("_autoId_56_")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[3]/div[3]/div/div[2]/div[9]")).click(); //work start
        driver.findElement(By.xpath("//*[@id='company-edit']/div[3]/div[3]/div/div[3]/div[1]")).click();
        driver.findElement(By.id("_autoId_57_")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[4]/div[3]/div/div[2]/div[21]")).click(); //work end
        driver.findElement(By.xpath("//*[@id='company-edit']/div[4]/div[3]/div/div[3]/div[1]")).click();
        driver.findElement(By.id("_autoId_58_")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[3]/div/div[2]/div[14]")).click(); //brake start
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[3]/div/div[3]/div[1]")).click();
        driver.findElement(By.id("_autoId_59_")).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[6]/div[3]/div/div[2]/div[15]")).click(); //brake end
        driver.findElement(By.xpath("//*[@id='company-edit']/div[6]/div[3]/div/div[3]/div[1]")).click();
        driver.findElement(By.xpath("//*[@id='timetable-container']/table/tbody/tr[10]/td[2]/div/div/button/span")).click();
        driver.findElement(By.xpath("//*[@id='timetable-container']/table/tbody/tr[10]/td[2]/div/div/div/ul/li[39]/a")).click(); //timezone
        driver.findElement(By.id("_autoId_76_")).click();
        driver.findElement(By.id("_autoId_77_")).click();
        driver.executeScript("window.scroll(0,-500);"); //scroll down
        driver.findElement(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div[2]/ul/li[5]")).click();
        driver.findElement(By.xpath("//*[@id='additionally']/div/div[1]/ul/li[2]/a")).click(); //go to buses and drivers
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(900));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bus-units-and-drivers']/table[1]/tbody/tr/td/button"))).click();
        driver.findElement(By.name("model")).sendKeys("ПАЗ");
        driver.findElement(By.name("gos_number")).sendKeys("е111ее");
        driver.findElement(By.cssSelector(".modal-footer > #_autoId_3_")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bus-units-and-drivers']/table[2]/tbody/tr/td/button"))).click();
        driver.findElement(By.name("fio")).sendKeys("Иваныч");
        driver.findElement(By.cssSelector(".modal-footer > #_autoId_2_")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[class='modal-dialog']")));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
        driver.findElement(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div/ul/li[2]/a")).click(); //go to admit documents
        Thread.sleep(300);
        driver.findElement(By.cssSelector(".bg-info:nth-child(2) .btn-success")).click(); //admit first document
        Thread.sleep(300);
        driver.findElement(By.cssSelector(".bg-info:nth-child(3) .btn-success")).click(); //admit second document
        Thread.sleep(300);
        driver.findElement(By.cssSelector(".bg-info:nth-child(4) .btn-success")).click(); //admit third document
        Thread.sleep(300);
        driver.findElement(By.cssSelector(".bg-info:nth-child(5) .btn-success")).click(); //admit fourth document
        driver.findElement(By.xpath("//*[@id='panelPermission']/div/button")).click(); //add permissions
        Thread.sleep(900);
        driver.findElement(By.id("_autoId_8_")).click(); //intercity routes
        driver.findElement(By.name("interurban[date_start]")).click(); //start date permission
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[1]/table/tbody/tr[1]/td[1]")).click(); //fist date
        driver.findElement(By.name("interurban[date_end]")).click(); //end date permission
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[1]/table/tbody/tr[6]/td[7]")).click(); //last date
        Thread.sleep(300);
        driver.findElement(By.cssSelector("label > #_autoId_12_")).click(); //suburban routes
        driver.findElement(By.name("suburban[date_start]")).click(); //start date permission
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[1]/table/tbody/tr[1]/td[1]")).click(); //fist date
        driver.findElement(By.name("suburban[date_end]")).click(); //end date permission
        driver.findElement(By.xpath("//*[@id='company-edit']/div[5]/div[1]/table/tbody/tr[6]/td[7]")).click(); //last date
        driver.findElement(By.cssSelector(".modal-footer > #_autoId_15_")).click();
        driver.executeScript("window.scroll(0,-500);"); //скроллим страницу вверх
        driver.findElement(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div/ul/li[4]")).click();
        Thread.sleep(600);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sales']/div/div[1]/ul/li[2]/a"))).click(); //agent contract
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sales-requisites']/form/div[2]/div[2]/p"))).click(); //create contract
        Thread.sleep(600);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-edit']/div[4]/div/div/div[2]/form/div[2]/div[2]/p"))).click(); //create organization
        wait.until(ExpectedConditions.elementToBeClickable(By.name("legal_address_index"))).sendKeys("170000");
        driver.findElement(By.name("legal_address_text")).sendKeys("улица Пушкина");
        driver.findElement(By.name("post_address_index")).sendKeys("170000");
        driver.findElement(By.name("post_address_text")).sendKeys("улица Пушкина");
        Thread.sleep(300);
        driver.findElement(By.name("post_address_recipient")).sendKeys("Тестировщик");
        driver.findElement(By.xpath("//button[contains(text(),'Добавить организацию')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.name("partner_delegate_fio"))).sendKeys("Тестировщик Т.Т.");
        driver.findElement(By.name("partner_delegate_position")).sendKeys("тестировщик");
        driver.findElement(By.name("bank_account[bank]")).sendKeys("Неальфа");
        driver.findElement(By.name("bank_account[bik]")).sendKeys("111111111");
        driver.findElement(By.name("bank_account[rs]")).sendKeys("40802810229120001111");
        driver.findElement(By.name("bank_account[ks]")).sendKeys("11111111111111111111");
        driver.findElement(By.xpath("//div[8]/div/fieldset/div[1]/div[1]/div/input")).sendKeys("9009009090");
        driver.findElement(By.cssSelector("#_autoId_21_.btn-primary.btn")).click();
        Thread.sleep(600);
        driver.findElement(By.cssSelector("#_autoId_3_.btn-primary.btn")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Правила возврата')]"))).click();//return rules
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[3]/div/div[1]/div[4]/div/div[2]/div/div[3]/form/div[2]/div/div/label[2]"))).click();
        driver.findElement(By.cssSelector("#_autoId_14_.btn-primary.btn")).click();
        Thread.sleep(600);
        driver.findElement(By.linkText("Добавить маршрут")).click(); //add route
        driver.findElement(By.name("locality_from")).sendKeys("Тверь"); //from
        driver.findElement(By.xpath("//*[@id='add-route-form']/div[3]/div[1]/div/span/div/div/div[1]")).click();
        driver.findElement(By.xpath("//*[@id='add-route-form']/div[4]/div[1]/div[1]/div/div/span[1]/div/div/div[4]")).click(); //particular city
        driver.findElement(By.name("locality_to")).sendKeys("Новосокольники"); //to
        driver.findElement(By.xpath("//*[@id='add-route-form']/div[3]/div[3]/div/span/div/div/div")).click();
        driver.findElement(By.xpath("//*[@id='add-route-form']/div[4]/div[1]/div[2]/div/div/span[1]/div/div/div[1]")).click(); //first city
        driver.findElement(By.id("_autoId_19_")).click();
        driver.findElement(By.xpath("//*[@id='route-add']/div[3]/div[3]/div/div[2]/div[11]")).click(); //departure time
        driver.findElement(By.xpath("//*[@id='route-add']/div[3]/div[3]/div/div[3]/div[2]")).click();
        driver.findElement(By.id("_autoId_20_")).click();
        driver.findElement(By.cssSelector(".btn-group:nth-child(2) > .btn-group:nth-child(2) > .btn")).click(); //work days
        driver.findElement(By.xpath("//*[@id='route-add']/div[4]/div[2]/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id='add-route-form']/fieldset/div[2]/div/div/button")).click(); //add second departure time
        driver.findElement(By.xpath("//*[@id='add-route-form']/fieldset/div[2]/div[1]/div")).click();
        driver.findElement(By.xpath("//*[@id='route-add']/div[4]/div[3]/div/div[2]/div[17]")).click(); //departure time
        driver.findElement(By.xpath("//*[@id='route-add']/div[4]/div[3]/div/div[3]/div[2]")).click();
        driver.findElement(By.xpath("//*[@id='add-route-form']/fieldset/div[2]/div[2]/div/textarea")).click();
        driver.findElement(By.cssSelector(".btn-group:nth-child(2) > .btn-group:nth-child(3) > .btn")).click(); //weekend
        driver.findElement(By.xpath("//*[@id='route-add']/div[5]/div[2]/div/div/button")).click();
        driver.findElement(By.name("duration_hours")).sendKeys("1"); //hours
        driver.findElement(By.name("duration_minutes")).sendKeys("10"); //minutes
        driver.findElement(By.name("departure_price")).sendKeys("10"); //fare
        driver.findElement(By.name("additional_info")).sendKeys("Маршрут создан автоматически для тестирования");
        driver.findElement(By.name("submit")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='route-edit']/div[1]/div[3]/div[1]/ul/li[2]/a"))).click(); //discounts
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#priceTab > .panel:nth-child(2) .panel-title"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.name("baggage_price_included"))).click(); //luggage fare included
        wait.until(ExpectedConditions.elementToBeClickable(By.id("_autoId_3_"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='priceTab']/div[3]/div[1]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='discount-container']/div/div[2]/button/span[2]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".col-xs-8 > .btn-group > .btn"))).click();
        driver.findElement(By.xpath("//*[@id='discount-container']/div/div[3]/div/form/div[2]/div/div/div/div/div/ul/li[3]/a/span")).click(); //discount for 12 year-olds
        driver.findElement(By.id("_autoId_7_")).click();
        Thread.sleep(300);
        driver.findElement(By.cssSelector(".col-xs-8 > .btn-group > .btn")).click();
        driver.findElement(By.xpath("//*[@id='discount-container']/div/div[3]/div/form/div[2]/div/div/div/div/div/ul/li[3]/a/span")).click(); //discount for 5 year-olds
        driver.findElement(By.id("_autoId_7_")).click();
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id='route-edit']/div[1]/div[3]/div[1]/ul/li[4]")).click();
        driver.findElement(By.name("route_number[0][number]")).sendKeys("111");
        driver.findElement(By.xpath("//*[@id='info-container']/div/form/div[3]/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id='info-container']/div/form/div[3]/div/div/div/ul/li[2]/a")).click(); //bus
        driver.findElement(By.xpath("//*[@id='info-container']/div/form/div[4]/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id='info-container']/div/form/div[4]/div/div/div/ul/li[2]/a")).click(); //driver
        driver.findElement(By.xpath("//*[@id='info-container']/div/form/div[5]/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id='info-container']/div/form/div[5]/div/div/div/ul/li[21]/a")).click(); //places
        driver.findElement(By.xpath("//*[@id='info-container']/div/form/div[6]/div/div/button")).click();
        driver.findElement(By.xpath("//*[@id='info-container']/div/form/div[6]/div/div/div/ul/li[2]/a")).click(); //soft seats
        driver.findElement(By.xpath("//*[@id='info-container']/div/form/div[7]/div/div/div/label")).click(); //wi-fi
        driver.findElement(By.xpath("//*[@id='info-container']/div/form/div[8]/div/div/div/label")).click(); //toilet
        driver.findElement(By.xpath("//*[@id='info-container']/div/form/div[9]/div/div/div/label")).click(); //conditioner
        driver.findElement(By.name("rules_transport_animals_and_baggage")).sendKeys("Животных в багаже не провозить");
        driver.findElement(By.name("phone[0][phone_number]")).sendKeys("89009009099");
        driver.findElement(By.name("phone[0][phone_explanation]")).sendKeys("Недиспетчер");
        driver.findElement(By.id("_autoId_25_")).click(); //saving
        Thread.sleep(600);
        driver.findElement(By.xpath("//div[@id='additionalTab']/div[2]/div[1]")).click();
        Thread.sleep(300);
        driver.findElement(By.id("_autoId_27_")).sendKeys(conf.filePath); //add file
        driver.findElement(By.id("_autoId_28_")).click();
        driver.findElement(By.xpath("//*[@id='route-edit']/div[1]/div[1]/nav[2]/div/a[2]/span[2]")).click(); //sales
        Thread.sleep(600);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Агентский договор №')]"))).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='info']/form/div[14]/div/div/button"))).click(); //contract`s status
        driver.findElement(By.xpath("//*[@id='info']/form/div[14]/div/div/div/ul/li[3]")).click(); //contract`s status
        driver.findElement(By.id("_autoId_30_")).sendKeys("11111"); //1s
        driver.findElement(By.id("_autoId_31_")).click();
        activateContract();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Перевозчики по договору')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-list']/div[1]/div[3]/table/tbody/tr/td[8]/a"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-edit']/div[1]/div[3]/div/ul/li[4]/a"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Включить продажи')]"))).click();
        driver.findElement(By.xpath("//*[@id='company-edit']/div[4]/div/div/div[3]/button[1]")).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Включить продажи от имени перевозчика')]"))).click();
        driver.findElement(By.id("_tmpIdagreement")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-edit']/div[4]/div/div/div[3]/button[1]"))).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-edit']/div[1]/div[1]/nav[2]/div/span"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='company-edit']/div[1]/div[1]/nav[2]/div/div/a"))).click();
        driver.findElement(By.cssSelector(".btn-info")).click(); //edit first route
        driver.findElement(By.xpath("//*[@id='route-edit']/div[1]/div[3]/div[1]/ul/li[5]/a")).click(); //sales
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='sales']/div[1]/button")).click(); //on sales
        driver.findElement(By.cssSelector(".btn-warning:nth-child(1)")).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='sales']/div[3]/button")).click(); //on sales
        driver.findElement(By.cssSelector(".text-justify")).click();
        driver.findElement(By.cssSelector(".btn-warning:nth-child(1)")).click();
        Thread.sleep(600);
        driver.findElement(By.xpath("//*[@id='sales']/form/div/div[2]/div[3]/div/div/div/label[2]")).click(); //set free seats
        driver.findElement(By.name("seat_diapason")).sendKeys("1-20");
        driver.findElement(By.cssSelector(".validate > #_autoId_10_")).click();
        testEnd();
    }
}