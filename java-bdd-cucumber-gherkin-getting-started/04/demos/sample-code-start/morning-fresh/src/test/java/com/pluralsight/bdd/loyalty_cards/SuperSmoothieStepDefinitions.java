package com.pluralsight.bdd.loyalty_cards;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SuperSmoothieStepDefinitions {

    private DrinkCatalog drinkCatalog = new DrinkCatalog();
    private SuperSmoothiesSchema superSmoothieSchema = new SuperSmoothiesSchema(drinkCatalog);
    private MorningFreshnessMember michael;

    @Given("the following drink categories:")
    public void theFollowingDrinkCategories(List<Map<String,String>> drinkCategories) {
        drinkCategories.stream().forEach(
                drinkCategory -> {
                    String drink = drinkCategory.get("Drink");
                    String category = drinkCategory.get("Category");
                    Integer points = Integer.parseInt(drinkCategory.get("Points"));

                    drinkCatalog.addDrink(drink, category);
                    superSmoothieSchema.setPointsPerCategory(category, points);
                }

        );

    }

    @Given("^(.*) is a Morning Freshness Member$")
    public void michaelIsAMorningFreshnessMember(String name) {
        michael = new MorningFreshnessMember(name, superSmoothieSchema);
    }

    @When("^(.*) purchases (\\d+) (.*) drinks?")
    public void michael_purchases_Apple_and_Kale_drinks(String name,
                                                        Integer amount,
                                                        String drink) {
        michael.orders(amount, drink);
    }

    @Then("he should earn {int} points")
    public void he_should_earn_points(Integer expectedPoints) {
        assertThat(michael.getPoints()).isEqualTo(expectedPoints);
    }




}
