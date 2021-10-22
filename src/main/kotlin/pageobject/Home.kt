package pageobject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.CacheLookup
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class Home(private val driver: WebDriver, val footer: Footer) : PageObject(driver) {

    val URL: String = "http://automationpractice.com/index.php"

    @CacheLookup
    @FindBy(id = "search_query_top")
    private lateinit var searchBar: WebElement

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"center_column\"]/ul/li/div/div[2]/h5/a")
    private lateinit var itemBlouse: WebElement

    @CacheLookup
    @FindBy(className = "alert-warning")
    private lateinit var noResultsWarning: WebElement

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"add_to_cart\"]/button/span")
    private lateinit var buttonAddToCart: WebElement

    @CacheLookup
    @FindBy(css = "[title='Proceed to checkout']")
    private lateinit var iconOk: WebElement

    private val wait: WebDriverWait = WebDriverWait(driver, Duration.ofSeconds(10))

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

    fun isProductFound(): Boolean {
        return itemBlouse.isDisplayed
    }

    fun areNoProductsFound(): Boolean {
        return noResultsWarning.isDisplayed
    }

    fun addProductToCart() {
        itemBlouse.click()
        buttonAddToCart.click()
    }

    fun isProductAddedToCart(): Boolean {
        wait.until(ExpectedConditions.elementToBeClickable(iconOk))
        return iconOk.isDisplayed
    }

}