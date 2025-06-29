package com.pluralsight.bddfundamentals.airport;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassengerPolicy {
    private Flight economyFlight;
    private Flight businessFlight;
    private Flight premiumFlight;
    private Passenger mike;
    private Passenger john;

    @Given("^there is an economy flight$")
    public void thereIsAnEconomyFlight() throws Throwable {
        economyFlight = new EconomyFlight("1");
    }

    @When("^we have a usual passenger$")
    public void weHaveAUsualPassenger() throws Throwable {
        mike  = new Passenger("Mike", false);
    }

    @Then("^you can add and remove him from an economy flight$")
    public void youCanAddAndRemoveHimFromAnEconomyFlight() throws Throwable {
        assertAll("Verify all conditions for a usual passenger and an economy flight",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertEquals(true, economyFlight.addPassenger(mike)),
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertTrue(economyFlight.getPassengersSet().contains(mike)),
                () -> assertEquals(true, economyFlight.removePassenger(mike)),
                () -> assertEquals(0, economyFlight.getPassengersSet().size())
        );
    }

    @When("^we have a VIP passenger$")
    public void weHaveAVIPPassenger() throws Throwable {
        john = new Passenger("John", true);
    }

    @Then("^you can add him but cannot remove him from an economy flight$")
    public void youCanAddHimButCannotRemoveHimFromAnEconomyFlight() throws Throwable {
        assertAll("Verify all conditions for a VIP passenger and an economy flight",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertEquals(true, economyFlight.addPassenger(john)),
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertTrue(economyFlight.getPassengersSet().contains(john)),
                () -> assertEquals(false, economyFlight.removePassenger(john)),
                () -> assertEquals(1, economyFlight.getPassengersSet().size())
        );
    }

    @Given("^there is an business flight$")
    public void thereIsAnBusinessFlight() throws Throwable {
        businessFlight = new BusinessFlight("2");
    }

    @Then("^you cannot add or remove him from a business flight$")
    public void youCannotAddOrRemoveHimFromABusinessFlight() throws Throwable {
        assertAll("Verify all conditions for a usual passenger and a business flight",
                () -> assertEquals(false, businessFlight.addPassenger(mike)),
                () -> assertEquals(0, businessFlight.getPassengersSet().size()),
                () -> assertEquals(false, businessFlight.removePassenger(mike)),
                () -> assertEquals(0, businessFlight.getPassengersSet().size())
        );
    }

    @Then("^you can add him but cannot remove him from a business flight$")
    public void youCanAddHimButCannotRemoveHimFromABusinessFlight() throws Throwable {
        assertAll("Verify all conditions for a VIP passenger and a business flight",
                () -> assertEquals(true, businessFlight.addPassenger(john)),
                () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                () -> assertEquals(false, businessFlight.removePassenger(john)),
                () -> assertEquals(1, businessFlight.getPassengersSet().size())
        );
    }

    @Given("^there is an premium flight$")
    public void thereIsAnPremiumFlight() throws Throwable {
        premiumFlight = new PremiumFlight("3");
    }

    @Then("^you cannot add or remove him from a premium flight$")
    public void youCannotAddOrRemoveHimFromAPremiumFlight() throws Throwable {
        assertAll("Verify all conditions for a usual passenger and a premium flight",
                () -> assertEquals(false, premiumFlight.addPassenger(mike)),
                () -> assertEquals(0, premiumFlight.getPassengersSet().size()),
                () -> assertEquals(false, premiumFlight.removePassenger(mike)),
                () -> assertEquals(0, premiumFlight.getPassengersSet().size())
        );
    }

    @Then("^you can add and remove him from a premium flight$")
    public void youCanAddAndRemoveHimFromAPremiumFlight() throws Throwable {
        assertAll("Verify all conditions for a VIP passenger and a premium flight",
                () -> assertEquals(true, premiumFlight.addPassenger(john)),
                () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                () -> assertEquals(true, premiumFlight.removePassenger(john)),
                () -> assertEquals(0, premiumFlight.getPassengersSet().size())
        );
    }

    @And("^you cannot add a usual passenger to an economy flight more than once$")
    public void youCannotAddAUsualPassengerToAnEconomyFlightMoreThanOnce() throws Throwable {
        for (int i=0; i<10; i++) {
            economyFlight.addPassenger(mike);
        }
        assertAll("Verify a usual passenger can be added to an economy flight only once",
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertTrue(economyFlight.getPassengersSet().contains(mike)),
                () -> assertTrue(new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName().equals("Mike"))
        );
    }

    @And("^you cannot add a VIP passenger to an economy flight more than once$")
    public void youCannotAddAVIPPassengerToAnEconomyFlightMoreThanOnce() throws Throwable {
        for (int i=0; i<10; i++) {
            economyFlight.addPassenger(john);
        }

        assertAll("Verify a VIP passenger can be added to an economy flight only once",
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertTrue(economyFlight.getPassengersSet().contains(john)),
                () -> assertTrue(new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName().equals("John"))
        );
    }

    @And("^you cannot add a VIP passenger to a business flight more than once$")
    public void youCannotAddAVIPPassengerToABusinessFlightMoreThanOnce() throws Throwable {
        for (int i=0; i<10; i++) {
            businessFlight.addPassenger(john);
        }

        assertAll("Verify a VIP passenger can be added to a business flight only once",
                () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                () -> assertTrue(businessFlight.getPassengersSet().contains(john)),
                () -> assertTrue(new ArrayList<>(businessFlight.getPassengersSet()).get(0).getName().equals("John"))
        );
    }

    @And("^you cannot add a VIP passenger to a premium flight more than once$")
    public void youCannotAddAVIPPassengerToAPremiumFlightMoreThanOnce() throws Throwable {
        for (int i=0; i<10; i++) {
            premiumFlight.addPassenger(john);
        }

        assertAll("Verify a VIP passenger can be added to a premium flight only once",
                () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                () -> assertTrue(premiumFlight.getPassengersSet().contains(john)),
                () -> assertTrue(new ArrayList<>(premiumFlight.getPassengersSet()).get(0).getName().equals("John"))
        );
    }
}
