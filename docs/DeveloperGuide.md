# Developer Guide

## Acknowledgements

* Inspired by [AddressBook-Level3 (AB3) DG](https://se-education.org/addressbook-level3/DeveloperGuide.html#architecture).
* Additional library used include [JANSI](https://github.com/fusesource/jansi).

## Setting Up Fridget
Refer to User Guide section on [Quick Start](https://ay2122s1-cs2113t-w12-4.github.io/tp/UserGuide.html#:~:text=be%20more%20efficient.-,Quick%20Start,-Ensure%20that%20you).

## Architecture

### Command
![image info](./umlDiagrams/Command.png)
The Command class is inherited by all other command class with their specific functionalities.

The Command class contain an execute() method which is inherited by all other classes to execute the
function specified by their names.

## Design & implementation

### Architecture Components

![image info](./umlDiagrams/Architecture.png)

This diagram illustrates the basic concept that underlies Fridget.

Each rectangle above represents a class that exists to make Fridget work. 
The larger folders represent the main purpose of the classes inside it.
Their functions are as follows:

#### **Front End**

The front end aims to handle:
- Reading <ins>inputs</ins> from the user
- Sending <ins>outputs</ins> to the user

##### `Ui`

The Ui manages the entirety of the front end. 
- It collects user input and stores it.
- It also prints any necessary output to the terminal.

#### **Core**

The core aims to understand and execute the user's commands.

#### `Parser`

The Parser collects information from the user's input in a way that is usable by other classes within Fridget.

#### `IngredientList`

This class keeps track of all items currently stored within Fridget, and can be easily manipulated.

#### `ShoppingList`

This class keeps track of all items the user may want to shop for, and can be easily manipulated.

#### `Command`

There is a Command class for each possible command the user could execute.
Each Command class controls the Ui, Parser, IngredientList, and ShoppingList so the User's intended outcome is achieved.

#### **Database**

The Database stores all info that is needed on a permanent basis. This may
include info such as the contents of IngredientList. Most info in the Database
is stored after any changes, and is usually retrieved when Fridget is turned
on.

#### `Storage`

The Storage class takes charge of storing items after every command, and
retrieving them upon startup.

#### Fridget

#### `Fridget`

Fridget initialises all classes upon startup, and initiates the user feedback loop as shown below.

![image info](./umlDiagrams/UserFeedbackLoop.png)

### Architecture Logic

The overall flow within Fridget occurs in three stages:

- Startup
- Execution
- Shutdown

#### Startup

1. The `main` method in Fridget creates a new instance of `Fridget`.
2. This new instance creates new instances of `Ui`, `Parser`,`IngredientList`, `ShoppingList`, and `Storage`.
3. `Fridget.run()` is called, which asks `Storage` to check stored files in the directory: `fridgetData`.
4. `IngredientList` and `ShoppingList` is updated based on the info obtained.
5. `Fridget.run()` initiates the user feedback loop, to obtain input from the user.

#### Execution

1. The user types in an input into the `Ui`. `Parser` is used to extract a `Command` from the user's input.
2. `Command.execute()` is called to execute the command.
3. The `Command` takes control of the `Ui`, `IngredientList`, and `ShoppingList` to achieve the intended outcome the user requires.
4. Once `Command.execute()` has ceased, the `Ui` awaits further input from the user.

#### Shutdown

1. When the `Ui` receives an input containing `exit`, `Parser` extracts an `ExitCommand`.
2. This `ExitCommand` prints a message to let the user know Fridget is shutting down.
3. `Fridget` recognises that `ExitCommand.exitNotRequired()` is not false, and shuts down Fridget safely.

### Adding Items Into Fridget

#### Main Objectives:

The functionality to add items is bound by two main objectives:
* Make adding items convenient for the user
* Parse enough information about the ingredient to benefit the user in the future

As a result, the current iteration requires two pieces of info from the user:
* The name of the ingredient
* The expiry date of the ingredient

This is done to minimize the input required from the user per entry, while maximising future uses with the data.

Future uses include:
* Finding if an ingredient exists by searching for its name
* Combining ingredients with similar names and expiry together
* Sort all ingredients by expiry date
* Remind users of expiring ingredients by name

#### Overall Sequence:

![image info](./umlDiagrams/AddSequence.png)

Step 1:

This step is almost always initiated by Fridget, but could potentially be done by another class in the future.

Step 2 & 3:

The parseIngredientForAdding() method is called in Parser, with the currentUserInput as a parameter.
The parser returns an Ingredient with the name and expiry date specified in the user input. 

Step 4:

If the user uses the ' | ' character in the ingredient's name, an exception is thrown.
This is done as the ' | ' may confuse the Storage class.

Step 5 & 6:

The Ingredient is added to IngredientList.
IngredientList returns a value signifying the current quantity of Ingredient in IngredientList.

Step 7 & 8:

If the Ingredient already exists in Fridget's IngredientList, print a message with the Ui to signify that the quantity of the existing item has increased to the user.

Step 9 & 10:

If the ingredient does not exist, print a message using Ui to signify to the user that a new Ingredient has been added into Fridget.

Step 11:

The execution of the execute() method ends.

### Removing Items From Fridget

### Getting Help Manual 

#### Main Objectives:

The objective of a help manual in Fridget is to ensure users are equipped with the basic knowledge to get 
started with Fridget.

The idea behind an inbuilt help manual is to ensure Fridget is convenient and reduce the heavy reference of 
the User Guide.

Future uses include:
* More in-depth coverage of all basic functions.
* Interactive component to explore capabilities of each commands.

#### Sequence of execution:

![image info](./umlDiagrams/HelpSequence.png)

Step 1: 

The execution of the Help Command is initiated by Fridget.

Step 2 & 3: 

The printLine() method is called in the Ui which prints out the String that is input into the method, 
in this case it is the help manual.

Step 4:

The execution of the execute() method ends.

### Resetting Item List In Fridget

#### Main Objectives:

The objective of the reset functionality is to provide users an easy way to remove all the items in Fridget.

#### Overall Sequence:

![image info](./umlDiagrams/ResetSequence.png)

Step 1: 

The execution of this step is initiated by Fridget.

Step 2 & 3: 

If the user double confirms the reset command, the resetList() method is called in the IngredientList and resets 
the ingredient list by overwriting it with a new ingredient list.

Step 4 & 5:

The printResetMessage() method is called in the Ui which prints a String stating that the ingredient list has been reset.

Step 6:

The execution of the execute() method ends.

### Listing Items In Fridget

#### Main Objectives:

The functionality to list items is bound by two main objectives:
* Allow users to easily view the contents of Fidget.
* Allow users to choose their preferred sort type.

As a result, the current iteration optionally requires an additional info from the user:
* The sort type of the list.

This additional is optional as the default settings sorts the list by ingredient name.

#### Overall Sequence:

![image info](./umlDiagrams/ListSequence.png)

Step 1:

This step is almost always initiated by Fridget, but could potentially be done by another class in the future.

Step 2 & 3:

The parseSortTypeForList() method is called in Parser, with the currentUserInput as a parameter.
The parser returns a String as sortType with the detected sort type in the user input.

Step 4 & 5:

The getIngredientList() method is called in IngredientList, with the sortType as a parameter.
The IngredientList returns an ArrayList<Ingredient> as listOfIngredients that are sorted according to the sort type.

Step 6 & 7:

The printListMessage() method is called in Ui, with the listOfIngredient, and sortType as parameters.
The Ui prints out the list of Ingredients for the user.

Step 8:

The execution of the execute() method ends.

### Configuring Notifications 

#### Main Objectives:

The objective of the notification based on current functionality is to print out a reminder notification 
to remind users to eat healthily and inform them of expiring or expired items.

Current implementation allow users to toggle the notifications on or off, with reminders sent out at intervals of 4 hours.

The purpose of implementing time interval and ability to toggle is to prevent excessive notification printing. 

#### Sequence of execution:
![image info](./umlDiagrams/NotificationSequence.png)

Step 1:

This step is initiated by Fridget to initialise Notification class.

Step 2 & 3:

The printNotification() method is called which will run if notification is turned on and time interval from previous
notification is longer than 4 hours. After satisfying the command, printHealthNotification() and printExpiryNotification()
is executed to print out the notifications. All methods called in these steps are called in Notification.

Step 4:

Health and expiry notification is printed when method is called and a new date and time is recorded into the log file.

Step 5:

This step is initiated by Fridget to run execute() method.

Step 6

If isNotificationOn() is true, the method setNotificationStatus(false), called in Notification class, is executed to set notification status 
to false in order to turn off printing of notification.

Step 7 & 8:

The printLine() method is called in the Ui, to print a message to notify user that notification has been switched off.

Step 9:

If isNotificationOn() is false, the method setNotificationStatus(true), called in Notification class, is executed to set notification status
to true in order to turn on printing of notification.

Step 10 & 11:

The printLine() method is called in the Ui, to print a message to notify user that notification has been switched off.

Step 12:

The execution of the execute() method ends, while notification continues to run until program termination.

### Exiting From Fridget

## Product scope
### Target user profile

<b>Tech-savvy Families</b> who cook at home, buy groceries, and own a fridge

### Value proposition

Nowadays, fridges are operated manually. Hence, users need to go through item by item manually to perform any function related to their fridge, for example:
* Figuring out what food is close to expiring 
* Figuring out if they have enough ingredients for a certain recipe 
* Figuring out if they are any recipes that they can make with their ingredients 
* Adding/removing items into/from the fridge and storing it in a ledger
* Find out items that need to be bought soon

This CLI based application hopes to automate a lot of the tasks users have relating to the fridge, for example:
* Automated reminder for items nearing expiry or items requiring replenishment 
* Automated housekeeping: Storing items that are added/removed in a central ledger 
* Automated shopping list: Listing out missing ingredients for meals that users want to cook


## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|busy house-husband|easily find out if I have a certain item in the fridge|spend less time checking through my Fridge|
|v1.0|busy house-husband|easily add or remove an item into the Fridget|spend less time writing down and scratching out items on a written ledger|
|v1.0|busy house-husband|easily find out expiry date of ingredient|spend less time going through every ingredient.|
|v1.0|frugal house-husband|know what foods are about to expire|quickly use them before they become inedible|
|v1.0|perfectionist house-husband|reset the list of items in Fridget|start using Fridget after not using it for a while, or after initial testing with fake items|
|v1.0|new user|have a manual instructions|easily navigate through the cli app|
|v1.0|fitness fanatic|have health-focused reminders for family members to eat more fruits and healthy snacks|my family can be healthy together|
|v2.0|busy house-husband|check if any ingredient requires me to replenish|easily curate my shopping list|
|v2.0|lazy house-husband|update the amount that is left of the item|know if it is very urgent to consume when it nears expiry|
|v2.0|lazy house-husband|I want to be able to add multiple items at once|type lesser commands to add them in|
|v2.1|forgetful house-husband|be reminded on items nearing expiry|use it before its too late|


## Non-Functional Requirements

1. Fridget should operate on most OS with Java `11` installed.
2. A user with a fast typing speed should be able to fully optimize the usage of Fridget.
3. Coloured dates should be able to display on most OS.

## Glossary

* **OS**: Windows, Linux, OS X

## Instructions For Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

### Launch And Shutdown
1. Initial launch
   1. Download the jar file, [Fridget.jar](https://github.com/AY2122S1-CS2113T-W12-4/tp/releases/download/v1.0/fridget.jar) and copy into empty folder.
   2. Double-click the file to launch the app or enter command `java -jar Fridget.jar` into command window to start the program.

2. Shutting down
   1. A typical file shutdown would be using `exit` command. Items added will be retained and saved in the text file and loaded on next launch.
   2. Exiting by closing the window is permitted but not encouraged.

### Adding Items
1. Adding item to Fridget.
   1. Adding format to follow would be, `add ITEM_NAME /EXPIRY_DATE`.
   2. ITEM_NAME is the parameter for the name and EXPIRY_DATE will be in the format of /yyyy-mm-dd
2. Test cases: 
   1. `add bacon /2021-12-12`
      Expected: Message will be printed to inform you item is added. Type command `list` to view the item added to Fridget.
   2. `add bacon`
      Expected: Error message printed to prompt that there is lack of expiry date. No item added.
   3. `add /2021-11-11`
      Expected: Error message printed to indicate the lack of item name. No item will be added.
   4. `add bacon /2021-13-13`
      Expected: Error message to inform that date is in the wrong format. No item will be added.
   5. `add | /2021-12-12`
      Expected: Error message to inform user to not input '|' in item name. No item will be added.

### Deleting Items
1. Removing item from Fridget.
   1. Removing item format will be `remove ITEM_NAME`.
   2. ITEM_NAME is the parameter for the name.
   3. If there are multiple item with same name, Fridget prompts you to choose the index of item to remove.
   4. If there are multiple quantity of an item, Fridget prompts you to input quantity to remove.
2. Prerequisites: There should be a prepare list of items in Fridget.
   1. Add 3 bacon of different dates. (eg. 2021-11-11, 2021-12-12)
   2. Add 3 egg of same date.
3. Test Cases:
   1. `remove bacon`
   Expected: Prompt from Fridget to ask which of the item from a list to remove. Type 1, and first bacon from
   the list will be removed. Type `list` to confirm the right bacon (correct date) is removed.
   2. `remove egg`
   Expected: Prompt to type in the quantity to remove, type in 2 and a message will be printed to
   notify that 2 eggs have been removed. Type `list` to check quantity of egg.
   3. `remove`
   Expected: Error message printed to indicate the lack of item name. No item will be removed.
   4. `remove chicken`
   Expected: Error message printed to inform you no such item exists.

### Listing Items
1. Listing all items from Fridget.
   1. Format for list will be `list` or `list -r` or `list -e`
   2. `list` list by alphabetical order.
   3. `list -e` list by ascending order of expiry date.
   4. `list -r` list by how recent item is added.
2. Prerequisites: There should be a prepared list of items.
   1. Add bacon of date 2022-12-12
   2. Add apple of date 2023-12-12
   3. add cucumber of date 2021-12-12
3. Test cases:
   1. `list` 
   Expected: Items will be listed out in alphabetical order.
   2. `list -e` 
   Expected: Items will be listed out in ascending order of expiry date.
   3. `list -r`
   Expected: Items will be listed out by how recent they are added.

### Finding Items
1. Finding items from Fridget.
   1. Format for finding item will be `find ITEM_NAME`
   2. ITEM_NAME is the parameter for the name.
2. Prerequisites: There should be a prepared list of items.
   1. Add bacon of date 2022-12-12
   2. Add bacon of date 2023-12-12
   3. add cucumber of date 2021-12-12
3. Test cases: 
   1. `find bacon`
   Expected: A list of the item associated with the name will be printed.
   2. `find chicken`
   Expected: Error message will be printed to inform that no matching item is found.
   3. `find`
   Expected: Error message will prompt for user to input ITEM_NAME. No items will be printed.

### List Expiring Items
1. Listing all expired and expiring items.
   1. Format for listing expiring items will be `expiring`
2. Prerequisites: There should be a prepared list of items.
   1. Add bacon of date 2021-10-10
   2. Add egg of date within 7 days from today's date.
   3. Add duck of date 2022-10-10
3. Test cases:
   1. `expiring`
   Expected: A list containing all items expiring within 7 days or expired will be listed.

### Saving And Loading Data
1. Saving and loading data is automated as long as file is not corrupted.
   1. To track your data in terms of a text file, open up fridgetData folder to access savedList.txt.
   2. Do not modify any items in the text file which could cause corruption to file.
   
2. Dealing with missing file.
   1. In general, a missing file will not result in any error as files will be generated automatically.
   2. To simulate a missing file, simple run and exit program, locate the fridgetData folder and delete the savedList.txt file.