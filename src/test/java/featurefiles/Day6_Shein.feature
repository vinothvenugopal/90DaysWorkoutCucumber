Feature: Jeans size validation
Scenario: Shein Jeans size selection validation test case

Given open shein website
And Mouseover on Clothing and click Jeans
And Choose Black under Jeans product count
And check size as medium
And check whether the color is black
And Click first item to Add to Bag 
And Click the size as M abd click Submit
And Click view Bag 
When Check the size is Medium or not
Then Close the browser