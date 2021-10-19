package pageobject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.CacheLookup
import org.openqa.selenium.support.FindBy

class Footer(driver: WebDriver) : PageObject(driver) {

    @CacheLookup
    @FindBy(xpath = "//span[contains(text(), '(347) 466-7432')]")
    private lateinit var phoneNumber: WebElement

    @CacheLookup
    @FindBy(xpath = "//li[contains(.,\"Selenium Framework, Research Triangle\")]")
    private lateinit var address: WebElement

    @CacheLookup
    @FindBy(xpath = "//a[contains(.,\"support@seleniumframework.com\")]")
    private lateinit var email: WebElement

    fun hasStorePhoneNumber() = phoneNumber.isDisplayed

    fun hasAddress(): Boolean = address.isDisplayed

    fun hasEmail(): Boolean = email.isDisplayed

}