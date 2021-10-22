import io.github.bonigarcia.wdm.WebDriverManager
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import pageobject.Footer
import pageobject.Home

class AutomationPracticeTests {
    private lateinit var driver: WebDriver
    private lateinit var home: Home

    @BeforeMethod
    fun setUp() {
        WebDriverManager.chromedriver().setup()
        driver = ChromeDriver()
        driver.manage().window().maximize()

        home = Home(driver, Footer(driver))
        home.open()
    }

    @Test
    fun home_CheckUrl_ShouldBeHomePage() {
        val expected = home.URL
        val actual = driver.currentUrl

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun home_SearchForExistingProduct_ShouldFindProduct() {
        home.searchFor("blouse")

        assertThat(home.isProductFound()).isTrue
    }

    @Test
    fun home_SearchNonExistingProduct_ShouldNotDisplayAnyProducts() {
        home.searchFor("test")

        assertThat(home.areNoProductsFound()).isTrue
    }

    @Test
    fun home_AddProductToCart_ShouldDisplayProductInCart() {
        home.searchFor("blouse")
        home.addProductToCart()

        assertThat(home.isProductAddedToCart()).isTrue
    }

    @Test
    fun home_FooterInfo_ShouldShowStoreInformation() {
        assertThat(
            home.footer.hasStorePhoneNumber() and
                    home.footer.hasAddress() and
                    home.footer.hasEmail()
        ).isTrue
    }

    @AfterMethod
    fun tearDown() {
        driver.quit()
    }
}