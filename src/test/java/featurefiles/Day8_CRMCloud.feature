Feature: CRM Cloud application

Scenario Outline: CRM test case with example

Given Go to crmcould website
And Give username as admin and password as admin
And Choose theme as Claro Theme
And Go to Sales and Marketting and click Sales Home
And Click Create contact
And Select <Title> and type <First Name>, <Last Name>, <Email> and <PhNo>
And Select Lead Source as <LeadSource> and Sales
And Fill the <Address>, <City>, <State>, <Country> and <PostalCode> and click Save. Check if the <First Name> <Last Name> is already available
And Mouse over on Today's Activities and click Meetings
And Type Subject as Project Status Status as Planned and Time as tomorrow 3 p.m
And Click Add paricipants, add your created <First Name> <Last Name> and click Save
When Click contacts under Sales and Marketting, search the <First Name> <Last Name> and click the name from the result
Then Check weather the Meeting is assigned to the <First Name> <Last Name>

Examples:
|Title|First Name|Last Name|Email|PhNo|LeadSource|BusinessRoles|Address|City|State|Country|PostalCode|
|Mr.|Vinoth|Venugopal|sample@gmail.com|9000000000|Public Relations|Sales|44, Krish Dwaraka, Agananooru St, Metha Nagar, Rajakilpakkam|Chennai|Tamil Nadu|India|600073|
|Mrs.|Ramya|Priyadarshini|sample1@gmail.com|9000000001|Public Relations|Sales|44, Krish Dwaraka, Agananooru St, Metha Nagar, Rajakilpakkam|Chennai|Tamil Nadu|India|600073|