package test.principles.antipatterns;

import defcoding.FlightSearchService;
import defcoding.InvalidDateFormatException;
import org.openqa.selenium.InvalidArgumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static defcoding.FlightSearchService.flightSearch;

public class RefactoredTest {

    private static final FlightSearchService searchService = flightSearch();

    @DataProvider
    public static Object[][] invalidFromToInput() {
        return new Object[][]{
                // invalid from
                {null, "Atlanta"},
                {"", "Atlanta"},
                {" ", "Atlanta"},

                // invalid to
                {"New York", null},
                {"New York", ""},
                {"New York", " "},
        };
    }

    @Test(dataProvider = "invalidFromToInput")
    void searchRejectsInvalidDestinations(String from, String to) {
        Assert.assertThrows(IllegalArgumentException.class, () -> searchService.search(from, to, "15-10-2019", 1));
    }

    @DataProvider
    public static Object[][] invalidDateInput() {
        return new Object[][]{
                {"15/10-2019"},
                {"15-10/2019"},
                {"15102019"},
                {"15-1-2019"},
                {"15-10-20"}
        };
    }

    @Test(dataProvider = "invalidDateInput")
    void searchRejectsInvalidDateFormat(String date) {
        Assert.assertThrows(InvalidDateFormatException.class, () -> searchService.search("New York", "Atlanta", date, 1));
    }

    @DataProvider
    public static Object[][] emptyStringInput() {
        return new Object[][]{
                {""},
                {" "},
        };
    }

    @Test(dataProvider = "emptyStringInput")
    void searchRejectsEmptyDate(String date) {
        Assert.assertThrows(IllegalArgumentException.class, () -> searchService.search("New York", "Atlanta", date, 1));
    }

    @DataProvider
    public static Object[][] invalidPassengerNum() {
        return new Object[][]{
                {0}, {8},
        };
    }

    @Test(dataProvider = "invalidPassengerNum")
    void searchRejectsPassengerNumOutOfRange(int passengerNum) {
        Assert.assertThrows(IllegalArgumentException.class, () -> searchService.search("New York", "Atlanta", "15-10-2019", passengerNum));
    }
}
