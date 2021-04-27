import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import pageobject.Home
import java.time.Duration
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AutomationPracticeTests {

    private lateinit var driver: WebDriver
    private lateinit var home: Home
    private lateinit var wait: WebDriverWait

    @BeforeMethod
    fun setUp() {
        driver = ChromeDriver()
        driver.manage().window().maximize()
        wait = WebDriverWait(driver, 5)

        home = Home(driver)
        home.open()
    }

    @Test
    fun test_home_checkUrl() {
        val expected = home.URL
        val actual = driver.currentUrl

        assertEquals(expected, actual)
    }

    @Test
    fun test_home_searchForItem() {
        home.searchFor("blouse")

        assertTrue(home.isBlouseDisplayed())
    }

    @Test
    fun test_home_searchForNoItem() {
        home.searchFor("test")

        assertTrue(home.areNoResultsFound())
    }

    @Test
    fun test_home_addItemToCart() {
        home.searchFor("blouse")
        home.addItemToCart()

        assertTrue(home.isItemAddedToCart())
    }

    @AfterMethod
    fun tearDown() {
        driver.quit()
    }
}