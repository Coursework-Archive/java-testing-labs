package java.com.pluralsight.bddfundamentals.airport;

import java.com.pluralsight.bddfundamentals.airport.EconomyFlight;
import java.com.pluralsight.bddfundamentals.airport.Flight;
import java.com.pluralsight.bddfundamentals.airport.Passenger;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassengerPolicy {
    private Flight economyFlight;
    private Passenger mike;


    @Test
    @Given("^there is an economy flight$")
    public void thereIsAnEconomyFlight() {
        economyFlight = new EconomyFlight("1");
    }

    @Test
    @When("^we have a usual passenger$")
    public void weHaveAUsualPassenger() {
        mike = new Passenger("Mike", false);
    }

    @Test
    @Then("^you can add and remove him from an economy flight$")
    public void youCanAddAndRemoveHimFromAnEconomyFlight() throws Throwable{

        assertAll("Verify all conditions for a usual passenger and an economy flight",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertEquals(true, economyFlight.addPassenger(mike)),
                () -> assertEquals(1, economyFlight.getPassengersList().size()),
                () -> assertEquals("Mike", economyFlight.getPassengersList().get(0).getName()),
                () -> assertEquals(0, economyFlight.getPassengersList().size())

        );
    }
}
