package tests.testng;

import org.testng.annotations.Test;
import web.pages.GoogleHomePage;

public class SampleTest extends BaseTest {

    @Test
    public void homePageTest1() {
        GoogleHomePage googleHomePage = new GoogleHomePage();
        googleHomePage.open();
        googleHomePage.search("iphone");
    }

    @Test
    public void homePageTest2() {
        GoogleHomePage googleHomePage = new GoogleHomePage();
        googleHomePage.open();
        googleHomePage.search("samsung");
    }

    @Test
    public void homePageTest3() {
        GoogleHomePage googleHomePage = new GoogleHomePage();
        googleHomePage.open();
        googleHomePage.search("007");
    }
}
