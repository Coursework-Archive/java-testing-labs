Feature: Passengers Policy
	The company follows a policy of adding and removing passengers,
	depending on the passenger type and on the flight type

Scenario: Economy flight, usual passenger
	Given there is an economy flight
	When we have a usual passenger
	Then you can add and remove him from an economy flight