## Introduction

This is a production line software. Where an employee can login to their account and add new item to the to the system.
The next step is the employee can record production and it is adding to the the system with an unique Serial number.

## Brief Description

In this software


## Getting Started

Make sure that JRE is installed:
* Java version "1.8.x_xx" (or a higher version)

Clone from Github:
* Click on "Clone or download".
* Either Download the Zip file or clone the HTTPS.
* If you Download the Zip
* Unzip and open project in the IDE

Clone From IDE:
* File > New > Project From version control > Enter URL/HTTP > Choose the directory for project > clone

Creating Path to Database:
* Database tab > Plus sign > Data source from path > Choose directory for database (ex: Lib Folder) > h2 > test connection > apply
* Check the database password inside the properties folder.

Linking Jar file:
* File > Project structure > Modules > Dependencies > Find and Choose jar file > apply


## Built With

* Intellij

* Scene Builder

* JavaFx GUI, Database H2, SQL

* Jfeonix

# Author 

Caio Botelho

## Project History

# Week 1

Created the GUI of for the project in scenebuilder

# Week 2

Start working on the product abstract class

# Week4

Created the enums for ItemType and Item interfaces
The first two tabs were tested
both tabs work

# Week 5

Worked on the database to connect the database to the project
linked database to the project with H2 and res folder
Product Line tab to add product to the product table works

# Week 6

 Worked on the controller to insert all the fxml buttons.
 In the produce table a list genarate from the product table and then allow user to select 
 and add serial numbers and quantity to another database call production record.
 It populates the list on the first tab 
 
 # Week 7 
 Audio Player class has been created and the Audio player driver was tested
 Product table was populated
 
 # Week 8
 
 This week I was able to finish most of the methods of my database manager class.
 allows for easy access and manipualtion of database within classes and the program in general
 The programs takes the input from user and save it to the product table.
 
 # week 9
 Worked on movie player and multimedia control, movie player driver.
 Also createf the Multimedia Control and ScreenSpec interfaces.
 implement ScreenSpec in Screen class
 
 # Week 10
 With every new product added to the table I gave them a unique Id and set quantity to 0
 
 # Week 11
 
 Tested the movieplayer driver.
 On the product line tab I was abkle to generate a list view for all the product that had been created
 and let a user select a product and select/enter a number into the choice box to a record the quantity that had been produced.
 generates serial number for that product
 
 # Week 12
 
 Worked on the anchor pane within Product to adjust lining
 worked on table view and columns within it 
 added columns to Product table
 
 
 # Week 14
 
Created a text file for the database login 
created database username and password in resoure folder
Push the product information to the prouct table view section

# Week 15

Created the employee class,
Employee can create their own account,

# Week 16

- Created a Method called reverseString(String password):
- Javadoc comments


## Key Programming Concepts Utilized
**OOP**

* Objects: Building blocks of object-oriented. Each object is a programming unit consisting of data (instance variables) and functionality (instance methods).
* Class: A blue-print, a type that defines the implementation of a particular kind of object.
* Inheritance: The concept of classes automatically containing the variables and methods defined in their supertypes.
* Polymorphism: Polymorphism allows us to perform a single action in different ways. polymorphism allows you to define one interface and have multiple implementations.
* Abstract: Keyword used in a class definition to specify that a class is not to be instantiated, but rather inherited by other classes.
* Interface: keyword used to define a collection of method definitions and constant values. It can later be implemented by classes that define this interface with the "implements" keyword.
* Enum: A keyword used to declare an enumerated type. Legal values consist of a fixed set of constants
* Lambda Expressions: enable you to treat functionality as method argument, or code as data.
* Recursion: The process in which a function calls itself directly or indirectly is called recursion and the corresponding function is called as recursive function.
* Implement: A keyword that is included in the class declaration to specify any interfaces that are implemented by the current class.
* Overload: Using one identifier to refer to multiple items in the same scope.
* Methods:A function defined in a class.
* Fields: A data member of a class.
* Extends: Class X extends class Y to add functionality, either by adding fields or methods to class Y, or by overriding methods of class Y.

**SQL Database**

* Insert: Insert new records in database.
* Into: Insert new records in table.
* Values: What needs to be inserted.
* Select: Select data from a database.
* From: Select data from a column.
* Asterisk: Choose ALL values.
