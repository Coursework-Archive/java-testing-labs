package com.pluralsight.bddfundamentals.airport;

import com.pluralsight.bddfundamentals.milage.Mileage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusPolicy {
    private Passenger mike;
    private Passenger john;
    private Mileage mileage;

    @Given("^we have a usual passenger with a mileage$")
    public void weHaveAUsualPassengerWithAMileage() throws Throwable {
        mike  = new Passenger("Mike", false);
        mileage  = new Mileage();
    }

    @When("^the usual passenger travels (\\d+) and (\\d+) and (\\d+)$")
    public void theUsualPassengerTravelsAndAnd(int mileage1, int mileage2, int mileage3) throws Throwable {
        mileage.addMileage(mike, mileage1);
        mileage.addMileage(mike, mileage2);
        mileage.addMileage(mike, mileage3);
    }

    @Then("^the bonus points of the usual passenger should be (\\d+)$")
    public void theBonusPointsOfTheUsualPassengerShouldBe(int points) throws Throwable {
        mileage.calculateGivenPoints();
        assertEquals(points, mileage.getPassengersPointsMap().get(mike).intValue());
    }

    @Given("^we have a VIP passenger with a mileage$")
    public void weHaveAVIPPassengerWithAMileage() throws Throwable {
        john  = new Passenger("John", true);
        mileage  = new Mileage();
    }

    @When("^the VIP passenger travels (\\d+) and (\\d+) and (\\d+)$")
    public void theVIPPassengerTravelsAndAnd(int mileage1, int mileage2, int mileage3) throws Throwable {
        mileage.addMileage(john, mileage1);
        mileage.addMileage(john, mileage2);
        mileage.addMileage(john, mileage3);
    }

    @Then("^the bonus points of the VIP passenger should be (\\d+)$")
    public void theBonusPointsOfTheVIPPassengerShouldBe(int points) throws Throwable {
        mileage.calculateGivenPoints();
        assertEquals(points, mileage.getPassengersPointsMap().get(john).intValue());
    }
}
