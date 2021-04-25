package pageobject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory

open class PageObject(driver: WebDriver) {
    init {
        PageFactory.initElements(AjaxElementLocatorFactory(driver, 10), this)
    }
}