import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import pageobject.Home
import kotlin.test.assertEquals

class AutomationPracticeTests {

    private lateinit var driver: WebDriver
    private lateinit var home: Home

    @BeforeMethod
    fun setUp() {
        driver = ChromeDriver()
        driver.manage().window().maximize()

        home = Home(driver)
    }

    @Test
    fun test_home_checkUrl() {
        home.open()

        val expected = home.URL
        val actual = driver.currentUrl

        assertEquals(expected, actual)
    }

    @AfterMethod
    fun tearDown() {
        driver.quit()
    }
}