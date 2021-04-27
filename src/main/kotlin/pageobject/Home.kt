package pageobject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.CacheLookup
import org.openqa.selenium.support.FindBy

class Home(private val driver: WebDriver) : PageObject(driver) {

    val URL: String = "http://automationpractice.com/index.php"

    @CacheLookup
    @FindBy(id = "search_query_top")
    private lateinit var searchBar: WebElement

    @CacheLookup
    @FindBy(css = "text='Blouse'")
    private lateinit var blouseItem: WebElement

    fun open() {
        driver.get(URL)
    }

    fun searchFor(query: String) {
        if (query.isBlank())
            throw IllegalArgumentException("The query can not be null, empty or blank.")

        searchBar.apply {
            sendKeys(query)
            submit()
        }
    }

    fun isBlouseDisplayed(): Boolean {
        return blouseItem.isDisplayed
    }

}