# Coffee Corner Application
## Overview
The following repo contains java application for Charlene's Coffee Corner, implemented as a POC for a technical interview.

## Requirements
Recently, Charlene decided to open her very own little coffee shop on a busy street corner.
Being the careful entrepreneur, she decided to start off with a limited offering, with the option to expand her choice of products, as business goes
Her Offering
- Coffee (small, medium, large) 2.50 CHF, 3.00 CHF, 3.50 CHF
- Bacon Roll 4.50 CHF
- Freshly squeezed orange juice (0.25l) 3.95 CHF
  Extras:
- Extra milk 0.30 CHF
- Foamed milk 0.50 CHF
- Special roast coffee 0.90 CHF
  Bonus Program
  Charlene's idea is to attract as many regular‘s as possible to have a steady turnaround.
  She decides to offer a customer stamp card, where every 5th beverage is for free.
  If a customer orders a beverage and a snack, one of the extra's is free.
  Objective:
  Write a simple program whose output is formatted like a receipt you would receive at a supermarket.
  The input to the program is a list of products the shopper wants to purchase (large coffee with extra milk, small coffee with special roast, bacon roll, orange juice

## Assumption
- If a customer orders a beverage and a snack, one of the extra's is free, the first extra added to the list will be for free

## Business logic
- We save the available products in a products.json file
- We process the products.json file and convert the items into business objects of type Product
- The main application shows the client the list of available products, each product has an identifier


-  [0] EXIT menu
-  [1] Coffee (small) 2.5 CHF
-  [2] Coffee (medium) 3.0 CHF
-  [3] Coffee (large) 3.5 CHF
-  [4] Bacon Roll 4.5 CHF
-  [5] Freshly squeezed orange juice (0,25l) 3.95 CHF
-  [6] Extra milk 0.3 CHF
-  [7] Foamed milk 0.5 CHF
-  [8] Special roast coffee 0.9 CHF

-  Introduce products numbers separated by ',' without blanks and press ENTER key (Example: 1,3,4). Available selections are:
-  1
-  2
-  3
-  4
-  5
-  6
-  7
-  8
- if the user chooses option 0, the program is exited
- Otherwise, the user enters their selection with the indicated format
- We create the Order business object that contains the products selected by the customer
- The processOrder method is invoked that calculates the free beverages, free extras, number of beverages, number of snacks, total amount, discounts and amount to pay.
- The printInvoice method is invoked to display the invoice

## Getting Started with this Project

* clone the repo https://github.com/benjmed/CoffeeCorner.git
* You should have a JDK installed, version 8 or higher
* You should have a Maven installed, version 4 or higher, Maven should be availableon your PATH or IDE
* I use Intellig IDE, import as maven project, then execute mvn clean install
* If you have some issue width another IDE "file not found! products.json", please go to Project settings, Modules, add Ressource Folders to sources directory
* Run Main class CornerCoffeeApplication

## Integration tests
* Execute mvn clean install
* shouldProcessOrderWithOneSmallCoffeeAndOneExtra: The client choose one small coffee with extra milk, then the total amount should be 2.8 CHF
* shouldProcessOrderWithOneLargeCoffeeAndOneBaconRoll: The client choose one large coffee and one bacon roll, then the total amount should be 8 CHF
* shouldProcessOrderWithOneLargeCoffeeWithExtraMilkAndOneBaconRoll: The client choose one large coffee with extra milk and one bacon roll, then the total amount should be 8 CHF
* shouldProcessOrderWithFourSmallCoffeeAndOneOrangeJuice: The client choose 4 small coffee and one Freshly squeezed orange juice, then the total amount should be 10 CHF and the 5th beverage is for free
* shouldProcessOrderWithFiveSmallCoffeeAndSixMediumCoffee: The client choose 5 small coffee and 6 medium coffee, then the 5th small coffee and the 10th medium coffee are for free, the total amount should be 25 CHF
* shouldProcessOrderWithOneSmallCoffeeWithExtraMilkAndOneBaconRoll: The client choose one small coffee with extra milk and one bacon roll, then the extra is for free, the total amount should be 7 CHF
* shouldProcessOrderWithOneSmallCoffeeWithTwoExtraMilkAndOneBaconRoll: The client choose one small coffee with 2 extra milk and one bacon roll, then one extra is for free, the total amount should be 7.3 CHF



