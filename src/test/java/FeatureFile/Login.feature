Feature: ECOM Application

@smoke
  Scenario Outline: HomePage
  Given user launch the Url
  When user select the "<drop>" from dropodown
  Then user enter "<search>" in search box
  
  Examples:
  |drop|search|
  |Baby|toys|
  |Books|velpaari|
  |Music|cd|
  
 
 @smoke 
  Scenario: ResultsPage
  When select the first product
  And check the selected product text with first product
  Then add the product to the cart
  And Take Screenshot
  Then go to cart
  
  @smoke
  Scenario: cartpage
  When check selectedproducttext with shoppingproductcarttext
  
  
  
 
   

