import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import pageobject.Home
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AutomationPracticeTests {

    private lateinit var driver: WebDriver
    private lateinit var home: Home

    @BeforeMethod
    fun setUp() {
        driver = ChromeDriver()
        driver.manage().window().maximize()

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

    @AfterMethod
    fun tearDown() {
        driver.quit()
    }
}