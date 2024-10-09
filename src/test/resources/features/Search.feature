# new feature
# Tags: optional

Feature: Search from the main page

    Scenario: Search for iPhone and filter offers by price
        Given The user opens the main page
        When The user enters "iphone" into the search bar and click the Search button
        And The user should see a block with three elements and first element of the block should be active
        When The user selects the price filter and sets the maximum price to "1000" rubles
        Then The user should be on a page with the URL that starts with "https://www.farpost.ru/vladivostok/tech/communication/cellphones/+/Apple+iPhone/?price_max=1000&query=iphone"
        And The block from the previous step should not be present
        And There should be no offers priced higher than 1000 rubles on the page