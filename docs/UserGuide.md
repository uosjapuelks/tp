# User Guide

## Introduction

Fridget is a <strong> desktop app for managing items in the fridge, built to be used via Command Line Interface </strong> (CLI). If you consistently update the app, housekeeping of items in the fridge will be more efficient.


## Quick Start

1. Ensure that you have Java 11 or above installed on your computer.
2. Download the latest `Fridget.jar` from [here](https://github.com/AY2122S1-CS2113T-W12-4/tp).
3. Copy the file into the folder you want store Fridget's data.
4. Double-click the file to launch the app. An interface that looks something like the following image will appear.
5. Type your command in the command box and press Enter to execute it.
    * Some example commands you can try:
        - help: lists all commands and input format in a separate window.
        - reset: prompts the user to confirm clearing all data.
        - exit: close the app.
6. Congratulations! You should be good to go!


## Features 

Feature | Command | Command Format |
-----------|----------|-----------------
Add an item into Fridget |`add` | add <item_name> /<expiry_date>
Remove an item from Fridget | `remove` | remove <item_name>
Get help | `help` | help
Reset all items | `reset` | reset
List in Alphabetical order |`list` | list
List by expiry |`list` | list -e
List least recent | `list` | list -r
expiring | `expiring` | expiring 
See notifications | `notifs` | notifs
Exit Fridget | `exit` | exit


### Add an item into Fridget: `add`
Add an item into Fridget's ledger.

Format: add <ITEM_NAME> /<EXPIRY_DATE>

* The `ITEM_NAME` can be in a natural language format.
* The `EXPIRY_DATE` must be in the `YYYY-MM-DD` format.  

Example of usage:

```markdown
userInput: add burger /2021-09-23
____________________________________________________
You have successfully added:
burger | 2021-09-23
____________________________________________________
```


### Add an item into Fridget: `list`
List items in Fridget's ledger.

Format: list -<OPTIONAL_SortType>

* `list` Lists in Alphabetical order of the Item Name.
* `list -e` Lists items in ascending order of Expiry Dates.
* `list -r` Lists items in descending order the item entered the ledger.

Example of usage:

```markdown
userInput: list
____________________________________________________
Here are the list of items in your fridge:
< Listing items in Alphabetical order >
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. frozen duck | Qty: 1 | 10 Oct 2022
    3. Yoghurt cake | Qty: 1 | 15 Oct 2021
____________________________________________________
```
```
userInput: list -e
____________________________________________________
Here are the list of items in your fridge:
< Listing earliest [Expiry Date] first >
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
    3. frozen duck | Qty: 1 | 10 Oct 2022
____________________________________________________
```

### Add an item into Fridget: `expiring`
Lists expired and expiring items that is in Fridget's Ledger.

Format: expiring

* `expiring` Lists in Alphabetical order of the Item Name.

Example of usage:
(Assuming Today is 10 Oct 2021)

```markdown
userInput: expiring
____________________________________________________
Items/ingredients expired or expiring within a week
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
____________________________________________________
```
_Items are considered to be expiring if the expiry date is 7 days away from today._

### Add an item into Fridget: `exit`
Safely close the program.

Format: exit

Example of usage:

```markdown
userInput: exit
____________________________________________________
We'll help you remember everything you told us :)
See you again!~~
____________________________________________________
```

### Get help: `help`
Prints all available commands in Fridget.

Format: help

Example of usage:
```
userInput: help
__________________________________________
Generating command list...
__________________________________________
List of commands available:

add <name of ingredient> </expiryDate(format: yyyy-mm-dd)> [eg. add bacon /2022-11-11]
-> Adds an ingredient and its expiry date to the ingredient list.

remove <name of ingredient> [eg. delete bacon]
-> Removes the ingredient from the ingredient list.

list
-> List out all the ingredients in the ingredient list.

reset
-> Deletes all the previous ingredient entries from the reader.

exit
-> Exits the program.
__________________________________________
```


### Reset all items: `reset`
Resets all your items in the ingredient list.

Format: reset

Example of usage:
```
userInput: reset
__________________________________________
Are you sure you want to reset everything in the ingredient list? (Y/N)
__________________________________________
userInput: N
__________________________________________
Abort reset command.
__________________________________________
```
```
userInput: reset
__________________________________________
Are you sure you want to reset everything in the ingredient list? (Y/N)
__________________________________________
userInput: Y
__________________________________________
Ingredient list has been reset successfully.
__________________________________________
```


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}