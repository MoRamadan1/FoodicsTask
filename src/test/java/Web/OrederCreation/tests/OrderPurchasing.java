package Web.OrederCreation.tests;

import Web.OrederCreation.pages.HomePage;
import Web.Utilities.utilites;
import org.testng.annotations.Test;

public class OrderPurchasing extends utilites {

    @Test
    public void grabbingAllItemsUnder() throws InterruptedException {

        HomePage hp = new HomePage();
        hp.navigateToAllVideoGames() ;
        hp.applyFilteringCriteria();
        hp.applySortingCriteria();
        hp.addItemsUnder15KToCart();
        hp.navigateToCartAndCheckOverallPrice();
        hp.proceedToOrder();

    }
}
