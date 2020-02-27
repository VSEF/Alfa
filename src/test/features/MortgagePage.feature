Feature: Mortgage Page functionality validating

  Scenario: Maternal capital validating
    Given User is on Page with mortgage calculator
    When User scroll to the Mortgage calculator
    And Click on the circle 'i' button opposite of 'Использовать материнский капитал' checkbox
    Then Pop-up window is displayed with text "Выплачивается семьям, у которых больше одного ребенка, если родители не пользовались другой поддержкой от государства."

   Scenario: The bank does not issue mortgage in amount less than 600 thousand rubles
     Given User is on Page with mortgage calculator
     And User scroll to the Mortgage calculator
     When User enter property price equals "670000" rubles
     And User enter amount of downpayment equals "670000" rubles
     Then User should see error message

   Scenario: User can move to filling application by clicking on button 'Заполнить заявку'
     Given User is on Page with mortgage calculator
     When User click on button 'Заполнить заявку'
     Then User should automatically scroll down to filling application section

   Scenario: User can't submit Mortgage Application form without entering Phone number
     Given User is on Page with mortgage calculator
     And User click on button 'Заполнить заявку'
     When User enter last name
     And Enter first name
     And Enter middle name and sex (if present)
     And enter email
     And Select region from drop-down list
     And Check checkbox to processing of personal data
     And Click on button 'Продолжить'
     Then User should see error message under phone field

    Scenario: User can read personal processing rules
      Given User is on Page with mortgage calculator
      And User click on button 'Заполнить заявку'
      When User click on the link 'условия'
      Then User can read personal processing rules