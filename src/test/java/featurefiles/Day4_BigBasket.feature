Feature: BigBasket test case

Scenario: BigBasket Test Case_001

Given Go to BigBasket website
And mouse over on  Shop by Category
And Go to FOODGRAINS, OIL & MASALA and RICE & RICE PRODUCTS
And Click on BOILED & STEAM RICE
And Get the URL of the page and check with site navigation link
And Choose the Brand as bb Royal
And Go to Ponni Boiled Rice and select 10kg bag from Dropdown
And Click Add button
And Go to search box and type Dal
And Add Toor Arhar Dal 2kg and set Qty 2 from the list
And click Address
And Select CHennai as City, Alandur-600016,Chennai as Area  and click Continue
And Mouse over on My Basket take a screen shot
When Click View Basket and Checkout
Then Click the close button and close the browser