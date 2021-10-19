import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import pageobject.Footer
import pageobject.Home
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AutomationPracticeTests {
    private lateinit var footer: Footer
    private lateinit var driver: WebDriver
    private lateinit var home: Home

    @BeforeMethod
    fun setUp() {
        driver = ChromeDriver()
        driver.manage().window().maximize()

        footer = Footer(driver)
        home = Home(driver, footer)
        home.open()
    }

    @Test
    fun home_CheckUrl_ShouldBeHomePage() {
        val expected = home.URL
        val actual = driver.currentUrl

        assertEquals(expected, actual)
    }

    @Test
    fun home_SearchForExistingProduct_ShouldFindProduct() {
        home.searchFor("blouse")

        assertTrue(home.isProductFound())
    }

    @Test
    fun home_SearchNonExistingProduct_ShouldNotDisplayAnyProducts() {
        home.searchFor("test")

        assertTrue(home.areNoProductsFound())
    }

    @Test
    fun home_AddProductToCart_ShouldDisplayProductInCart() {
        home.searchFor("blouse")
        home.addProductToCart()

        TODO("Find a better Selector to check if the item was successfully added to the cart")
        assertTrue(home.isProductAddedToCart())
    }

    @Test
    fun testhome_FooterInfo_ShouldShowStoreInformation() {
        assertTrue(home.footer.hasStorePhoneNumber() and home.footer.hasAddress() and home.footer.hasEmail())
    }

    @AfterMethod
    fun tearDown() {
        driver.quit()
    }
}