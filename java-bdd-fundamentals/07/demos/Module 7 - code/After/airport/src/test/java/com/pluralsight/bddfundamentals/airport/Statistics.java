package com.pluralsight.bddfundamentals.airport;

import com.pluralsight.bddfundamentals.mileage.Mileage;
import com.pluralsight.bddfundamentals.mileage.StatisticsUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Statistics {
    private Passenger mike;
    private Passenger john;
    private Mileage mileage;

    @Given("^there is a usual passenger$")
    public void thereIsAUsualPassenger() throws Throwable {
        mike = new Passenger("Mike", false);
        mileage  = new Mileage();
    }

    @And("^there is a VIP passenger$")
    public void thereIsAVIPPassenger() throws Throwable {
        john = new Passenger("John", true);
    }

    @When("^the usual passenger travels distances (\\d+) and (\\d+) and (\\d+)$")
    public void theUsualPassengerTravelsDistancesDistanceAndDistanceAndDistance(int distance1, int distance2, int distance3) throws Throwable {
        mileage.addMileageToList(mike, distance1);
        mileage.addMileageToList(mike, distance2);
        mileage.addMileageToList(mike, distance3);
    }

    @And("^the VIP passenger travels distances (\\d+) and (\\d+) and (\\d+)$")
    public void theVIPPassengerTravelsDistancesDistanceAndDistanceAndDistance(int distance4, int distance5, int distance6) throws Throwable {
        mileage.addMileageToList(john, distance4);
        mileage.addMileageToList(john, distance5);
        mileage.addMileageToList(john, distance6);
    }

    @Then("^the total travel distance should be (\\d+)$")
    public void theTotalTravelDistanceShouldBeTotal_distance(int total_distance) throws Throwable {
        assertEquals(total_distance, StatisticsUtil.totalDistance(mileage));
    }

    @When("^the usual passenger travels distances (\\d+) and (\\d+) and (\\d+) and (\\d+)$")
    public void theUsualPassengerTravelsDistancesDistanceAndDistanceAndDistance(int distance1, int distance2, int distance3, int distance4) throws Throwable {
        mileage.addMileageToList(mike, distance1);
        mileage.addMileageToList(mike, distance2);
        mileage.addMileageToList(mike, distance3);
        mileage.addMileageToList(mike, distance4);
    }

    @And("^the VIP passenger travels distances (\\d+) and (\\d+)$")
    public void theVIPPassengerTravelsDistancesDistanceAndDistanceAndDistance(int distance5, int distance6) throws Throwable {
        mileage.addMileageToList(john, distance5);
        mileage.addMileageToList(john, distance6);
    }

    @Then("^the minimum travel distance should be (\\d+)$")
    public void theMinimumTravelDistanceShouldBeMinimum_distance(int minimum_distance) throws Throwable {
        assertEquals(minimum_distance, StatisticsUtil.minimumDistance(mileage));
    }

    @And("^the maximum travel distance should be (\\d+)$")
    public void theMaximumTravelDistanceShouldBeMaximum_distance(int maximum_distance) throws Throwable {
        assertEquals(maximum_distance, StatisticsUtil.maximumDistance(mileage));
    }
}
