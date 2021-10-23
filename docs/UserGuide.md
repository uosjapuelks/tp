# User Guide

## Introduction

Fridget is a <strong> desktop app built to be used via Command Line Interface </strong> (CLI). Fridget is an easier way for you to keep track of the items in your fridge. It is convenient to add, remove, and find items stored in your fridge. Fridget also reminds you when items are nearing expiry.

If you want to start taking charge of your fridge and stop wasting your food, Fridget is for you.

- Forget scribbling your items on a piece of paper. 
- Forget throwing your food away when it goes bad.
- Forget expensive smart fridges that are more trouble than they are worth.

Use Fridget today.

This guide is meant for you to quickly get started with Fridget so you can stop worrying about expired food and start enjoying life.

### Legend:

`add ITEM_NAME /EXPIRY_DATE` - Texts inside `this` box refers to either input text by the user or output from Fridget in the terminal.
It could also refer to file names like `Fridget.jar`.

`ITEM_NAME` or `EXPIRY_DATE` - Texts which have been capitalized refer to parameters which the user should type in. For example:
A command like `add ITEM_NAME /EXPIRY DATE` would actually be typed by the user as `add burger /2021-11-11`.

## Quick Start

1. Ensure that you have Java 11 or above installed on your computer.
2. Download the latest `Fridget.jar` from [here](https://github.com/AY2122S1-CS2113T-W12-4/tp/releases/download/v1.0/fridget.jar).
3. Copy the file into the folder you want store Fridget's data.
4. Launch the terminal on your computer. You can refer to [this website](https://towardsdatascience.com/a-quick-guide-to-using-command-line-terminal-96815b97b955) on how to launch the terminal on your specific OS (Windows/Mac/Linux).

<div markdown="span" class="alert alert-primary">:bulb: Tip:
You can key in "java -version" to check your version of java.
</div>

5. Type in `java -jar Fridget.jar` into the terminal to launch the app.
6. Type your command in the command box and press Enter to execute it.
    * Some example commands you can try:
        - help: lists all commands and input format.
        - reset: prompts the user to confirm clearing all data.
        - exit: close the app.
7. Congratulations! You should be good to go!

This guide has been arranged according to the features offered by Fridget.
You can see the list of features in the Features section below.


## Features 

Feature | Command Format |
-----------|-----------------
Add an item | `add ITEM_NAME /EXPIRY_DATE`
Remove an item | `remove ITEM_NAME`
Get help | `help`
Reset all items | `reset`
List all items | `list -OPTIONAL_SORT_TYPE`
Find an item | `find KEYWORD`
List expiring items | `expiring`  
See notifications | `notifs`
Exit Fridget | `exit`


### Add an item into Fridget: `add`
Use this command to add your items into Fridget.

Format: add ITEM_NAME /EXPIRY_DATE

<div markdown="span" class="alert alert-warning">:exclamation: Caution:
The ITEM_NAME can be in a natural language format.
</div>
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
Use this command to remove items from Fridget.

Format: remove ITEM_NAME

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

add INGREDIENT_NAME /EXPIRY_DATE(format: yyyy-mm-dd) [eg. add bacon /2022-11-11]
-> Adds an ingredient and its expiry date to the ingredient list.

find KEYWORD [eg. find bacon]
-> Find and print all ingredient associated with keyword.

remove INGREDIENT_NAME [eg. delete bacon]
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

* `reset`

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


### List all items in Fridget: `list`
List all items stored in Fridget.

Format: list -OPTIONAL_SORT_TYPE or list OPTIONAL_SORT_TYPE

* `list` Lists in Alphabetical order of the Item Name.
* `list -e` Lists items in ascending order of Expiry Dates.
* `list -r` Lists items in descending order the item entered the ledger.

Example of usage:

```markdown
userInput: list
__________________________________________
List sorted by item name:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. frozen duck | Qty: 1 | 10 Oct 2022
    3. Yoghurt cake | Qty: 1 | 15 Oct 2021
__________________________________________
```
```
userInput: list -e
__________________________________________
List sorted by expiry date:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
    3. frozen duck | Qty: 1 | 10 Oct 2022
__________________________________________
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

### Find items stored in Fridget: `find`
Find all items associated with the keyword.

Format: find KEYWORD

* The `KEYWORD` should be the same as the name of the item you are looking for.
* The `KEYWORD` can also be a part of the name of the item.

```
userInput: find burger
__________________________________________
These are the matching ingredients:
    1. burger | Qty: 1 | 23 Sep 2030
__________________________________________
userInput: find apple
__________________________________________
No matching ingredient found!
__________________________________________
```

### Lists all items expiring soon: `expiring`
Lists all items that are expired or expiring. Items are considered to be expiring if the expiry date is less than 7 days away from today.

Format: expiring

* `expiring`

Example of usage:

```markdown
userInput: expiring
__________________________________________
Expiring/Expired Items:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
__________________________________________
```



### See notifications: `notifs`
Display notification reminder scheduled at 6 hours interval. Can be toggled on or off. 

Format: notifs

* `notifs`

Example of usage:
````
userInput: notifs
__________________________________________
Turning notification off!
__________________________________________
userInput: notifs
__________________________________________
Turning notification on!
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

### Stop Fridget: `exit`
Safely shut down Fridget.

Format: exit

- `exit`

Example of usage:

```markdown
userInput: exit
__________________________________________
We'll help you remember everything you told us :)
See you again!~~
__________________________________________
```


## Things to note

1. For commands help, expiring, reset and notifs, adding a space and random String behind will still trigger the command.

Example:
```
userInput: notifs ajsdfasf
__________________________________________
Turning notification off!
____________________________________________
userInput: reset jadfgasgdka
__________________________________________
Are you sure you want to reset everything in the ingredient list? (Y/N)
__________________________________________
```

2. If no item is recorded, Fridget will prompt you to get help.

Example:
```
userInput: find a
__________________________________________
You currently have nothing in your fridge.
Input "help" to get started!
__________________________________________
userInput: expiring
__________________________________________
You currently have nothing in your fridge.
Input "help" to get started!
__________________________________________
```
