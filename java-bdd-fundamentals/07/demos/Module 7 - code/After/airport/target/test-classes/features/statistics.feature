Feature: Statistics feature
  The company builds statistics about the flights, the passengers and the distances they travel

  Scenario Outline: Calculate total distance
    Given there is a usual passenger
    And there is a VIP passenger
    When the usual passenger travels distances <distance1> and <distance2> and <distance3>
    And the VIP passenger travels distances <distance4> and <distance5> and <distance6>
    Then the total travel distance should be <total_distance>

    Examples:
      | distance1 | distance2 | distance3|  distance4 | distance5 | distance6 | total_distance |
      |     349   |     319   |    623   |     164    |    234    |    233    |         1922   |
      |     312   |     356   |    135   |     420    |    123    |    441    |         1787   |
      |     223   |     786   |    503   |     275    |    221    |    231    |         2239   |
      |     482   |      98   |    591   |     158    |    127    |    228    |         1684   |
      |     128   |     176   |    304   |     320    |    101    |    541    |         1570   |

  Scenario Outline: Calculate minimum and maximum distance
    Given there is a usual passenger
    And there is a VIP passenger
    When the usual passenger travels distances <distance1> and <distance2> and <distance3> and <distance4>
    And the VIP passenger travels distances <distance5> and <distance6>
    Then the minimum travel distance should be <minimum_distance>
    And the maximum travel distance should be <maximum_distance>

    Examples:
      | distance1 | distance2 | distance3|  distance4 | distance5 | distance6 | minimum_distance |  maximum_distance |
      |     349   |     319   |    623   |     164    |    234    |    233    |         164      |         623       |
      |     312   |     356   |    135   |     420    |    123    |    441    |         123      |         441       |
      |     223   |     786   |    503   |     275    |    221    |    231    |         221      |         786       |
      |     482   |      98   |    591   |     158    |    127    |    228    |          98      |         591       |
      |     128   |     176   |    304   |     320    |    101    |    541    |         101      |         541       |
