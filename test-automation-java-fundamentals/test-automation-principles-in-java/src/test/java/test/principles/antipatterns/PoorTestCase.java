package test.principles.antipatterns;

import defcoding.FlightSearchService;
import defcoding.InvalidDateFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static defcoding.FlightSearchService.flightSearch;

public class PoorTestCase {

    private static final FlightSearchService searchService = flightSearch();

    @DataProvider
    public static Object[][] invalidInput() {
        return new Object[][]{
                // invalid from
                {null, "Atlanta", "15-10-2019", 1},
                {"", "Atlanta", "15-10-2019", 1},
                {" ", "Atlanta", "15-10-2019", 1},

                // invalid to
                {"New York", null, "15-10-2019", 1},
                {"New York", "", "15-10-2019", 1},
                {"New York", " ", "15-10-2019", 1},

                // invalid date
                {"New York", "Atlanta", "", 1},
                {"New York", "Atlanta", " ", 1},
                {"New York", "Atlanta", "15/10-2019", 1},
                {"New York", "Atlanta", "15-10/2019", 1},
                {"New York", "Atlanta", "15102019", 1},
                {"New York", "Atlanta", "15-1-2019", 1},
                {"New York", "Atlanta", "15-10-20", 1},

                // invalid passenger num
                {"New York", "Atlanta", "15-10-2019", 0},
                {"New York", "Atlanta", "15-10-2019", 8},
        };
    }

    @Test(dataProvider = "invalidInput")
    // fails on some iterations as expected
    void searchRejectsInvalidFromOrInvalidToOrInvalidDateOrInvalidPassengerNumber(String from, String to, String date, int passengerNum) {
        Assert.assertThrows(IllegalArgumentException.class, () -> searchService.search(from, to, date, passengerNum));
    }



    @Test(dataProvider = "invalidInput")
    // fails on fewer iterations as expected
    void searchFailsCopy(String from, String to, String date, int passengerNum) {

        if (date.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d") /* ||  or if something else */) {
            Assert.assertThrows(IllegalArgumentException.class, () -> searchService.search(from, to, date, passengerNum));
        } else {
            Assert.assertThrows(InvalidDateFormatException.class, () -> searchService.search(from, to, date, passengerNum));
        }
    }























}
