package pageobject

import org.openqa.selenium.WebDriver

class Home(private val driver: WebDriver) : PageObject(driver) {

    val URL: String = "http://automationpractice.com/index.php"

    fun open() {
        driver.get(URL)
    }

}