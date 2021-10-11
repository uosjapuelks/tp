# User Guide

## Introduction

Fridget is a <strong> desktop app for managing items in the fridge, built to be used via Command Line Interface </strong> (CLI). If you consistently update the app, housekeeping of items in the fridge will be more efficient.


## Quick Start

1. Ensure that you have Java 11 or above installed on your computer.
2. Download the latest `Fridget.jar` from [here](https://github.com/AY2122S1-CS2113T-W12-4/tp/releases/download/v1.0/fridget.jar).
3. Copy the file into the folder you want store Fridget's data.
4. Double-click the file to launch the app. An interface that looks something like the following image will appear.
5. Type your command in the command box and press Enter to execute it.
    * Some example commands you can try:
        - help: lists all commands and input format in a separate window.
        - reset: prompts the user to confirm clearing all data.
        - exit: close the app.
6. Congratulations! You should be good to go!


## Features 

Feature | Command Format |
-----------|-----------------
Add an item | `add <ITEM_NAME> /<EXPIRY_DATE>`
Remove an item | `remove <ITEM_NAME>`
Get help | `help`
Reset all items | `reset`
List all items | `list -<OPTIONAL_SORT_TYPE>`
Find expiring items | `expiring`  
See notifications | `notifs`
Exit Fridget | `exit`


### Add an item into Fridget: `add`
Add an item into Fridget's ledger.

Format: add <ITEM_NAME> /<EXPIRY_DATE>

* The `ITEM_NAME` can be in a natural language format.
* The `EXPIRY_DATE` must be in the `YYYY-MM-DD` format.  

Example of usage:

```markdown
userInput: add burger /2021-11-11
__________________________________________
You have successfully added:
    burger | Qty: 1 | 11 Nov 2021
__________________________________________
```

```markdown
userInput: add burger /2021-11-11
__________________________________________
You have successfully increased the quantity of:
    burger | Qty: 1->2 | 11 Nov 2021
__________________________________________
```

### Remove an item from Fridget: `remove`
Remove an item from Fridget.

Format: remove <ITEM_NAME>

* The `ITEM_NAME` can be in a natural language format.
* The `ITEM_NAME` should be the same or be a subset of the name of the item you are trying to remove.

Example of usage:

<ins>If there is only one item with a name containing `ITEM_NAME`</ins>
```markdown
userInput: remove burger
__________________________________________
You have successfully removed:
    burger | Qty: 1 | 11 Nov 2021
__________________________________________
```

<ins>If there are multiple items with a name containing `ITEM_NAME`</ins>
```markdown
__________________________________________
userInput: remove burger
__________________________________________
Which item would you like to be removed? Type the index of the item below.
    1. burger | Qty: 1 | 11 Nov 2021
    2. burger | Qty: 1 | 23 Sep 2021
__________________________________________
userInput: 1
__________________________________________
You have successfully removed:
    burger | Qty: 1 | 11 Nov 2021
__________________________________________
```

<ins>If there are multiple quantities of the same item</ins>
```
__________________________________________
userInput: remove burger
__________________________________________
Which item would you like to be removed? Type the index of the item below.
    1. burger | Qty: 3 | 11 Nov 2021
    2. burger | Qty: 1 | 23 Sep 2021
__________________________________________
userInput: 1
__________________________________________
There are 3 items, how many would like to remove?
__________________________________________
userInput: 2
__________________________________________
You have successfully removed:
    burger | Qty: 2 | 11 Nov 2021
__________________________________________
```


### List all items in Fridget: `list`
List all items stored in Fridget.

Format: list -<OPTIONAL_SORT_TYPE>

* `list` Lists in Alphabetical order of the Item Name.
* `list -e` Lists items in ascending order of Expiry Dates.
* `list -r` Lists items in descending order the item entered the ledger.

Example of usage:

```markdown
userInput: list
____________________________________________________
List sorted by item name:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. frozen duck | Qty: 1 | 10 Oct 2022
    3. Yoghurt cake | Qty: 1 | 15 Oct 2021
____________________________________________________
```
```
userInput: list -e
____________________________________________________
List sorted by expiry date:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
    3. frozen duck | Qty: 1 | 10 Oct 2022
____________________________________________________
```
```
userInput: list -r
__________________________________________
List sorted by earliest added:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
    3. frozen duck | Qty: 1 | 10 Oct 2022
__________________________________________
```
### Lists all items expiring soon: `expiring`
Lists all items that are expired or expiring. Items are considered to be expiring if the expiry date is less than 7 days away from today.

Format: expiring

* `expiring`

Example of usage:

```markdown
userInput: expiring
____________________________________________________
Expiring/Expired Items:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
____________________________________________________
```

### Stop Fridget: `exit`
Safely shut down Fridget.

Format: exit

- `exit`

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

- `help`

Example of usage:
```
userInput: help
__________________________________________
Generating command list...
__________________________________________
List of commands available:

add <INGREDIENT_NAME> </EXPIRY_DATE(format: yyyy-mm-dd)> [eg. add bacon /2022-11-11]
-> Adds an ingredient and its expiry date to the ingredient list.

find <KEYWORD> [eg. find bacon]
-> Find and print all ingredient associated with keyword.

remove <INGREDIENT_NAME> [eg. delete bacon]
-> Removes the ingredient from the ingredient list.

list -e
-> List out all the ingredients starting with earliest expiry date.

list -r
-> List out all the ingredients based on the order added.

list
-> List out all the ingredients in alphabetical order.

expiring
-> Prints out ingredients expiring within a week

notifs
-> Toggle the notification on or off depending on previous state. Default mode is on.

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


### See notifications: `notifs`
Display notification reminder scheduled at 6 hours interval. Can be toggled on or off. 

Format: notifs

Example of usage:
````
userInput: notifs
__________________________________________
Notification off!
__________________________________________
userInput: notifs
__________________________________________
Notification on!
__________________________________________
````
````
 ___________________________________________________
|                                                |X||
|      ___            _         __        ______    |
|     / _ \___ __ _  (_)__  ___/ /__ ____/ / / /    |
|    / , _/ -_)  ' \/ / _ \/ _  / -_) __/_/_/_/     |
|   /_/|_|\__/_/_/_/_/_//_/\_,_/\__/_/ (_|_|_)      |
|   =============================================   |
|                                                   |
|    "If you don't take time to take care of your   |
|    health now, you're gonna have to make time     |
|    for feeling sick and tired later."             |
|                                                   |
|            Always remember to eat more            |
|              Fruits and Vegetables!               |
|___________________________________________________|
````

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}