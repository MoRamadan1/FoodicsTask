package Web.OrederCreation.pages;

import Web.Utilities.utilites;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class HomePage extends utilites {

    // Constants
    private static final double MAX_PRICE = 15000.00;
    private static final int SCROLL_PIXELS = 1000;
    private static final int TYPE_DELAY_MILLIS = 500;
    private static final int WAIT_TIMEOUT_SECONDS = 10;


    // Locators
    @FindBy(xpath = "//a[@href='javascript: void(0)']")
    private WebElement hamburgerMenuButton;

    @FindBy(xpath = "//a[@class='hmenu-item hmenu-compressed-btn' and @aria-label='See All Categories']")
    private WebElement seeAllCategoriesButton;

    @FindBy(xpath = "//a[div[text()='Video Games']]")
    private WebElement videoGamesMenu;

    @FindBy(xpath = "//h1/b[text()='Video Games']")
    private WebElement videoGamesHeader;

    @FindBy(xpath = "//span[text()='New']")
    private WebElement newLabel;

    @FindBy(xpath = "//h2[@class='a-size-medium-plus a-spacing-none a-color-base a-text-bold']")
    private WebElement resultsHeader;

    @FindBy(css = "a[aria-label='Apply the filter Free Shipping to narrow results'] i[class='a-icon a-icon-checkbox']")
    private WebElement freeShippingCheckBox;

    @FindBy(xpath = "//span[text()='Sort by:']")
    private WebElement sortByLabel;

    @FindBy(xpath = "//a[@class='a-dropdown-link' and text()='Price: High to Low']")
    private WebElement priceHighToLowOption;

    @FindBy(xpath = "//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-button-accessibility s-pagination-separator']")
    private WebElement nextPageLink;

    @FindBy(id = "nav-cart")
    public WebElement cartLink;

    @FindBy(css = ".a-size-medium.a-color-base.sc-price.sc-white-space-nowrap")
    private WebElement priceAmount;

    @FindBy(id = "sc-subtotal-amount-buybox")
    private WebElement priceElement;

    @FindBy(name = "proceedToRetailCheckout")
    private WebElement proceedToCheckOutPage;

    @FindBy(xpath = "//a[contains(text(), 'Add a new delivery address')]")
    private WebElement addNewDeliveryAddressButton;

    @FindBy(name = "address-ui-widgets-enterAddressFullName")
    private WebElement addressFullName;

    @FindBy(name = "address-ui-widgets-enterAddressPhoneNumber")
    private WebElement addressPhoneNumber;

    @FindBy(name = "address-ui-widgets-enterAddressLine1")
    private WebElement streetName;

    @FindBy(name = "address-ui-widgets-enterAddressLine2")
    private WebElement buildingName;

    @FindBy(id = "address-ui-widgets-addr-details-res-radio-input")
    private WebElement addressDetailsResRadioInput;

    @FindBy(css = "input[data-testid='bottom-continue-button']")
    private WebElement bottomContinueButton;

    private double overAllPrice = 0;

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    /******************************** Methods ******************************************/

    public void navigateToAllVideoGames() {
        clickElement(hamburgerMenuButton);
        clickElement(seeAllCategoriesButton);
        clickElement(videoGamesMenu);
        clickOnAllVideoGames();
        assertElementDisplayed(videoGamesHeader, "Video Games header");
        assertTextEquals(videoGamesHeader, "Video Games", "Header text mismatch.");
    }

    public void clickOnAllVideoGames() {
        WebElement allVideoGamesLink = getDriver().findElement(By.xpath("//a[contains(text(),'All Video Games')]"));
        clickElementUsingJS(allVideoGamesLink);
    }

    public void applyFilteringCriteria() {
        clickElement(newLabel);
        assertElementDisplayed(resultsHeader, "Results header");
        assertTextEquals(resultsHeader, "Results", "Header text mismatch.");
        clickElement(freeShippingCheckBox);
    }

    public void applySortingCriteria() {
        clickElement(sortByLabel);
        clickElement(priceHighToLowOption);
    }

    public void addItemsUnder15KToCart() throws InterruptedException {
        boolean nextPageNeeded = true  ;
        List<WebElement> parentElements = getDriver().findElements(By.cssSelector(
                ".puisg-col.puisg-col-4-of-12.puisg-col-8-of-16.puisg-col-12-of-20.puisg-col-12-of-24.puis-list-col-right"
        ));

        for (int i = 0; i < parentElements.size(); i++) {
            WebElement parent = parentElements.get(i);
            if (isPriceVisible(parent) && isAddToCartVisible(parent) && getPrice(parent) < MAX_PRICE) {

                addItemToCart(parent);
                System.out.println("Price of item : " + getPrice(parent));
                overAllPrice += getPrice(parent);
                System.out.println("Overall Price: " + overAllPrice);
                nextPageNeeded = false ;
                System.out.println("next page needed flag : " + nextPageNeeded);
            }

            if ((i + 1) % 3 == 0) {
                scrollDown(SCROLL_PIXELS);
            }
        }
        if(nextPageNeeded == true )
        {
            System.out.println("entered here ");
//            clickElement(nextPageLink);
            addItemsUnder15KToCart();
        }
    }

    public void navigateToCartAndCheckOverallPrice() {
        String calculatedPrice = String.format("%.2f", overAllPrice);
        clickElement(cartLink);
        String expectedPrice = getPriceAsDouble();
        Assert.assertEquals(calculatedPrice, expectedPrice, "Price mismatch.");
    }

    public void proceedToOrder() throws InterruptedException {
        clickElement(proceedToCheckOutPage);
        clickElement(addNewDeliveryAddressButton);
        fillAddressForm("Mohamed Ramadan", "01000362778", "temp street", "tempoo street2");
        selectCity("Cai");
        selectDistrict("Maa");
        clickElement(addressDetailsResRadioInput);
//        clickElement(bottomContinueButton);
    }

    /******************************** Helper Methods ******************************************/

    private void clickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private void clickElementUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    private void assertElementDisplayed(WebElement element, String elementName) {
        Assert.assertTrue(element.isDisplayed(), elementName + " is not displayed.");
    }

    private void assertTextEquals(WebElement element, String expectedText, String errorMessage) {
        Assert.assertEquals(element.getText(), expectedText, errorMessage);
    }

    private void scrollDown(int pixels) {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0, " + pixels + ");");
    }

    private String getPriceAsDouble() {
        String priceText = priceElement.getText();
        String numericValue = priceText.replaceAll("[^\\d.]", "");
        double price = Double.parseDouble(numericValue);
        return String.format("%.2f", price);
    }

    private void fillAddressForm(String fullName, String phoneNumber, String street, String building) {
        addressFullName.sendKeys(fullName);
        addressPhoneNumber.sendKeys(phoneNumber);
        streetName.sendKeys(street);
        buildingName.sendKeys(building);
    }

    private void selectCity(String city) throws InterruptedException {
        WebElement cityInput = waitForElement(By.name("address-ui-widgets-enterAddressCity"));
        typeWithDelay(cityInput, city, TYPE_DELAY_MILLIS);
        selectFirstAutoCompleteOption();
    }

    private void selectDistrict(String district) throws InterruptedException {
        WebElement districtInput = waitForElement(By.name("address-ui-widgets-enterAddressDistrictOrCounty"));
        typeWithDelay(districtInput, district, TYPE_DELAY_MILLIS);
        selectFirstAutoCompleteOption();
    }

    private WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void selectFirstAutoCompleteOption() {
        List<WebElement> options = getDriver().findElements(By.cssSelector("#address-ui-widgets-autocompleteResultsContainer>li"));
        options.get(0).click();
    }

    private void typeWithDelay(WebElement element, String text, int delayMillis) {
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private boolean isAddToCartVisible(WebElement parent) {
        return isElementVisible(parent, By.cssSelector("button[name='submit.addToCart']"));
    }

    private boolean isPriceVisible(WebElement parent) {
        return isElementVisible(parent, By.cssSelector(".a-price-whole"));
    }

    private boolean isElementVisible(WebElement parent, By childLocator) {
        try {
            WebElement element = parent.findElement(childLocator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private double getPrice(WebElement parent) {
        WebElement priceWhole = parent.findElement(By.cssSelector(".a-price-whole"));
        String priceText = priceWhole.getText().replace(",", "").replace(".", "");
        return Double.parseDouble(priceText);
    }

    private void addItemToCart(WebElement parent) {
        WebElement addToCartButton = parent.findElement(By.cssSelector("button[name='submit.addToCart']"));
        clickElement(addToCartButton);
        System.out.println("Added to Cart successfully.");
    }
}