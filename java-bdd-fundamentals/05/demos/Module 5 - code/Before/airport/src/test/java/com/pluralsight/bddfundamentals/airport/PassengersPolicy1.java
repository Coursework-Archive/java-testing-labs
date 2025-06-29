package com.pluralsight.bddfundamentals.airport;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassengersPolicy1 {
    private Flight economyFlight;
    private Passenger mike;


    @Given("^there is an economy flight$")
    public void thereIsAnEconomyFlight() {
        economyFlight = new EconomyFlight("1");
        
    }

    @When("^we have a usual passenger$")
    public void weHaveAUsualPassenger() {
        mike = new Passenger("Mike", false);
    }

    @Then("^you can add and remove him from an economy flight$")
    public void youCanAddAndRemoveHimFromAnEconomyFlight() {
        assertAll("Verify all conditions for usual passenger and an economy flight",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertEquals(true, economyFlight.addPassenger(mike)),
                () -> assertEquals(1, economyFlight.getPassengersList().size()),
                () -> assertEquals("Mike", economyFlight.getPassengersList().get(0).getName()),
                () -> assertEquals(true, economyFlight.removePassenger(mike)),
                () -> assertEquals(0, economyFlight.getPassengersList().size())
                );
    }
}
