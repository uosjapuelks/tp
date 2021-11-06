# User Guide

<hr/>

## Introduction

Fridget is a <strong> desktop app built to be used via Command Line Interface </strong> (CLI). Fridget is an easier way for you to keep track of the items in your fridge. It is convenient to add, remove, and find items stored in your fridge. Fridget also reminds you when items are nearing expiry.

If you want to start taking charge of your fridge and stop wasting your food, Fridget is for you.

- Forget scribbling your items on a piece of paper. 
- Forget throwing your food away when it goes bad.
- Forget expensive smart fridges that are more trouble than they are worth.

Use Fridget today.

This guide is meant for you to quickly get started with Fridget so you can stop worrying about expired food and start enjoying life.

<hr/>

## Legend

`add ITEM_NAME /EXPIRY_DATE` - Texts inside `this` box refers to either input text by the user or output from Fridget in the terminal.
It could also refer to file names like `Fridget.jar`.

`ITEM_NAME` or `EXPIRY_DATE` - Texts which have been capitalized refer to parameters which the user should type in. For example:
A command like `add ITEM_NAME /EXPIRY DATE` would actually be typed by the user as `add burger /2021-11-11`.

<hr/>

## Quick Start

1. Ensure that you have Java 11 or above installed on your computer.
2. Download the latest `Fridget.jar` from [here](https://github.com/AY2122S1-CS2113T-W12-4/tp/releases/download/v1.0/fridget.jar).
3. Copy the file into the folder you want to store Fridget's data.
4. Launch the terminal on your computer. You can refer to [this website](https://towardsdatascience.com/a-quick-guide-to-using-command-line-terminal-96815b97b955) on how to launch the terminal on your specific OS (Windows/Mac/Linux).
5. Type in `java -jar Fridget.jar` into the terminal to launch the app.
6. Type your command in the command box and press Enter to execute it.
    * Some example commands you can try:
        - help: lists all commands and input format.
        - reset: prompts the user to confirm clearing all data.
        - exit: close the app.
7. Congratulations! You should be good to go!
<div style="background-image: linear-gradient(180deg, #d8eff3, #d1ecf1);  padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em">
:bulb: <b>Tip:</b>
You can key in "<b>java -version</b>" into your terminal to check your version of java.
</div>


This guide has been arranged according to the features offered by Fridget.
You can see the list of features in the Features section below.

<hr/>

## Features

Feature | Command Format |
-----------|-----------------
[Add an item](#add-an-item-into-fridget-add) | `add ITEM_NAME /EXPIRY_DATE`
[Remove an item](#remove-an-item-from-fridget-remove) | `remove ITEM_NAME`
[Get help](#get-help-help) | `help`
[Reset all items](#reset-all-items-reset) | `reset`
[Reset shopping list](#reset-all-items-in-shopping-list-shopreset) | `shopreset`
[List all items](#list-all-items-in-fridget-list) | `list -OPTIONAL_SORT_TYPE`
[List items in shopping list](#list-items-in-shopping-list-shoplist) | `shoplist`
[Find an item](#find-items-stored-in-fridget-find) | `find KEYWORD`
[List expiring items](#lists-all-items-expiring-soon-expiring) | `expiring`  
[See notifications](#see-notifications-notifs) | `notifs`
[Exit Fridget](#stop-fridget-exit) | `exit`

<hr/>

### Command Syntax

As you use this guide, you may see some commands with specific punctuation or parameters. This guide explains what they mean, and how you should use them.

For simplicity's sake, the number `2147483647` shall be referred to henceforth as `INT_MAX`.

Command Option | Description |
-----------|-----------------
`ITEM_NAME` | The `ITEM_NAME` can be any series of characters or digits of less than length `INT_MAX`. Additionally, `ITEM_NAME` should not include the following terms: `;`,`/`, or <code>&#124;</code>. <br/><br/> More explanation can be found [here](#add-an-item-into-fridget-add).
`EXPIRY_DATE` | The `EXPIRY_DATE` must be of the format `YYYY-MM-DD`.
`OPTIONAL_SORT_TYPE` | The `OPTIONAL_SORT_TYPE` must be in lowercase format to be recognised properly. Hence, you should use `r` or `e`, <b>NOT </b> `R` or `E`.
`KEYWORD` | This `KEYWORD` can be the exact same as the name of the item you are trying to remove. You can also input a `KEYWORD` that is contained within the item you are trying to delete.

<hr/>

### Add an item into Fridget: `add`
Use this command to add your items into Fridget.

Format: `add ITEM_NAME /EXPIRY_DATE`

Constraints:
- Do not use `/`, `|`, or `;` in the `ITEM_NAME`.
  - `/` is used to demarcate the `EXPIRY_DATE`.
  - `|` is used for Fridget's own secret purposes.
  - `;` is used for adding multiple items at once. More instructions can be found below.

<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd);  padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em">
:exclamation: 
The <b>EXPIRY_DATE</b> must be in the <ins>YYYY-MM-DD</ins> format.  
</div>

<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd);  padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em">
:exclamation: 
The <b>EXPIRY_DATE</b> should not be earlier than the current day.  
</div>

**Example of usage:**

<ins>If you are adding an item not in Fridget:</ins>

```markdown
USER INPUT: add burger /2021-11-11
__________________________________________
What quantity of [burger | 2021-11-11] would you like to add?
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully added:
burger | Qty: 1 | 11 Nov 2021
__________________________________________
```

<ins>If you are adding an item already in Fridget:</ins>

```markdown
USER INPUT: add burger /2021-11-11
__________________________________________
What quantity of [burger | 2021-11-11] would you like to add?
__________________________________________
USER INPUT: 9
__________________________________________
You have successfully increased the quantity of:
burger | Qty: 1->10 | 11 Nov 2021
__________________________________________
```

<ins>For advanced users:</ins>

If you want to add multiple items at the same time, separate each item with a semicolon: `;`.

**For example:**

```bash
USER INPUT: add burger /2021-11-11; chicken /2023-11-11
__________________________________________
What quantity of [burger | 2021-11-11] would you like to add?
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully increased the quantity of:
    burger | Qty: 10->11 | 11 Nov 2021
__________________________________________
What quantity of [chicken | 2023-11-11] would you like to add?
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully added:
    chicken | Qty: 1 | 11 Nov 2023
__________________________________________
```

<hr/>

### Remove an item from Fridget: `remove`
Use this command to remove items from Fridget.

Format: `remove ITEM_NAME`

<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd);  padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em">
:exclamation:
The <b>ITEM_NAME</b> can be in a <ins>natural language</ins> format. 
</div>

<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em; word-wrap: break-word">
:exclamation:
The <b>ITEM_NAME</b> should be the <ins>same</ins> as the name of the item you are trying to remove. 
</div>

<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em; word-wrap: break-word">
:exclamation:
The <b>ITEM_NAME</b> can also be a <ins>part of the name</ins> of the item. 
</div>

**Example of usage:**

<ins>If there is only one item with a name containing `ITEM_NAME`</ins>:
```markdown
USER INPUT: remove burger
__________________________________________
You have successfully removed:
    burger | Qty: 1 | 11 Nov 2021
__________________________________________
```

<ins>If there are multiple items with a name containing `ITEM_NAME`</ins>:
```markdown
USER INPUT: remove burger
__________________________________________
Which item would you like to be removed? Type the index of the item below.
    1. burger | Qty: 1 | 11 Nov 2021
    2. burger | Qty: 1 | 23 Sep 2021
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully removed:
    burger | Qty: 1 | 11 Nov 2021
__________________________________________
```

<ins>If there are multiple quantities of the same item</ins>:
```
USER INPUT: remove burger
__________________________________________
Which item would you like to be removed? Type the index of the item below.
    1. burger | Qty: 3 | 11 Nov 2021
    2. burger | Qty: 1 | 23 Sep 2021
__________________________________________
USER INPUT: 1
__________________________________________
There are 3 items, how many would like to remove?
__________________________________________
USER INPUT: 2
__________________________________________
You have successfully removed:
    burger | Qty: 2 | 11 Nov 2021
__________________________________________
```

<ins>Upon removing, if there are no items left with the same name in the fridge</ins>:
```
USER INPUT: remove burger
__________________________________________
You have successfully removed:
    burger | Qty: 1 | 23 Sep 2021
__________________________________________
You have ran out of burger. Would you like to add it to your shopping list? (Y/N)
__________________________________________
USER INPUT: Y
__________________________________________
How many items would you like to buy?
__________________________________________
USER INPUT: 1
__________________________________________
You have successfully added:
    burger | Qty: 1
__________________________________________
```
<hr/>

### Get help: `help`

Use this command to see the list of commands from within Fridget.

Format: `help`

**Example of usage:**
```
USER INPUT: help
__________________________________________
List of commands available:

add ITEM_NAME /EXPIRY_DATE(format: yyyy-mm-dd) [eg. add bacon /2022-11-11]
-> Adds an item and its expiry date to the item list. Input quantity after being prompt.

find KEYWORD [eg. find bacon]
-> Find and print all item associated with keyword.

remove ITEM_NAME [eg. delete bacon]
-> Removes the item from the item list.

list -e
-> List out all the items starting with earliest expiry date.

list -r
-> List out all the items based on the order added.

list
-> List out all the items in alphabetical order.

shoplist
-> List out all the items in the shopping list in alphabetical order.

expiring
-> Prints out items that have expired or are expiring within a week.

update ITEM_NAME [eg. update egg]
-> Prompts a quantity change for the specified item name.

notifs
-> Toggle the notification on or off depending on previous state. Default mode is on.

reset
-> Deletes all the previous item entries from the reader.

shopreset
-> Deletes all the previous item entries in the shopping list.

exit
-> Exits the program.

For more information about each command please visit our User Guide.
Link: https://ay2122s1-cs2113t-w12-4.github.io/tp/UserGuide.html
__________________________________________
```

<hr/>

### Reset all items: `reset`

Use this command to reset all the items in your fridge.

Format: `reset`

**Example of usage:**

<ins>If you decide to not reset in the end:</ins>

```
USER INPUT: reset
__________________________________________
Are you sure you want to reset everything in the fridge? (Y/N)
__________________________________________
USER INPUT: N
__________________________________________
Shutting down the command...
__________________________________________
```

<ins>If you are sure you want to reset:</ins>

```
USER INPUT: reset
__________________________________________
Are you sure you want to reset everything in the fridge? (Y/N)
__________________________________________
USER INPUT: Y
__________________________________________
Item list has been reset successfully.
__________________________________________
```

<hr/>

### Reset all items in shopping list: `shopreset`

Use this command to reset all the items in your shopping list.

Format: `shopreset`

**Example of usage:**

<ins>If you decide to not reset in the end:</ins>

```
USER INPUT: shopreset
__________________________________________
Are you sure you want to reset everything in the shopping list? (Y/N)
__________________________________________
USER INPUT: N
__________________________________________
Shutting down the command...
__________________________________________
```

<ins>If you are sure you want to reset:</ins>

```
USER INPUT: shopreset
__________________________________________
Are you sure you want to reset everything in the shopping list? (Y/N)
__________________________________________
USER INPUT: Y
__________________________________________
Shopping list has been reset successfully.
__________________________________________
```

<hr/>

### List all items in Fridget: `list`

This commands lists all items in Fridget, in the order that you prefer.

Format: `list -OPTIONAL_SORT_TYPE`

* `list` Lists items in alphabetical order.
* `list -e` Lists items in ascending order of expiry dates.
* `list -r` Lists items in order of how recently it was added.

**Example of usage:**

<ins> If you want to list items in alphabetical order: </ins>

```markdown
USER INPUT: list
__________________________________________
List sorted by item name:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. frozen duck | Qty: 1 | 10 Oct 2022
    3. Yoghurt cake | Qty: 1 | 15 Oct 2021
__________________________________________
```

<ins> If you want to list items in ascending order of expiry dates: </ins>

```
USER INPUT: list -e
__________________________________________
List sorted by expiry date:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
    3. frozen duck | Qty: 1 | 10 Oct 2022
__________________________________________
```

<ins> If you want to list items in order of how recently they were added: </ins>

```
USER INPUT: list -r
__________________________________________
List sorted by earliest added:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
    3. frozen duck | Qty: 1 | 10 Oct 2022
__________________________________________
```

<hr/>

### List items in shopping list: `shoplist`

Use this command to list all the items in your shopping list.

Format: `shoplist`

**Example of usage:**

```
USER INPUT: shoplist
__________________________________________
List sorted by item name:
    1. Burger | Qty: 1
    2. Chicken | Qty: 3
    3. Fish | Qty: 2
__________________________________________
```

<hr/>

### Find items stored in Fridget: `find`

Use this command to easily find an item in Fridget by name.

Format: `find KEYWORD`

<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em; word-wrap: break-word">
:exclamation:
The <b>KEYWORD</b> should be the <ins>same</ins> as the name of the item you are trying to remove. 
</div>

<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em; word-wrap: break-word">
:exclamation:
The <b>KEYWORD</b> can also be a <ins>part of the name</ins> of the item. 
</div>

**Example of usage:**

```
USER INPUT: find burger
__________________________________________
These are the matching items:
    1. burger | Qty: 1 | 23 Sep 2030
__________________________________________
USER INPUT: find apple
__________________________________________
No matching item found!
__________________________________________
```

<hr/>

### Lists all items expiring soon: `expiring`

Use this command to get a list of all expiring items.

<div style="display: inline-block;background-image: linear-gradient(180deg, #d8eff3, #d1ecf1);  padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em">
:bulb: <b>Tip:</b>
Only items expiring within 7 days will be shown. Expired items will be shown too.
</div>

Format: `expiring`

**Example of usage:**

```markdown
USER INPUT: expiring
__________________________________________
Expiring/Expired Items:
    1. fish Cake | Qty: 2 | 12 Dec 2020
    2. Yoghurt cake | Qty: 1 | 15 Oct 2021
__________________________________________
```

<hr/>

### See notifications: `notifs`

Use this command to turn notifications on or off. There are a total of 2 reminders, health and expiry.

* Health: To remind you to eat healthier.
* Expiry: To remind you that items are expiring and/or expired.

<div style="display: inline-block;background-image: linear-gradient(180deg, #d8eff3, #d1ecf1);  padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em">
:bulb: <b>Tip:</b>
Notifications are scheduled by default to be turned on every 4 hours.
</div>

Format: notifs

**Example of usage:**
````
USER INPUT: notifs
__________________________________________
Turning notification off!
__________________________________________
USER INPUT: notifs
__________________________________________
Turning notification on!
__________________________________________
````
**Example of health reminder notice:**
````
 ___________________________________________________
|                      HEALTH!                      |
|===================================================|
|      ___            _         __        ______    |
|     / _ \___ __ _  (_)__  ___/ /__ ____/ / / /    |
|    / , _/ -_)  ' \/ / _ \/ _  / -_) __/_/_/_/     |
|   /_/|_|\__/_/_/_/_/_//_/\_,_/\__/_/ (_|_|_)      |
|   ---------------------------------------------   |
|                                                   |
|    "If you don't take time to take care of your   |
|    health now, you're gonna have to make time     |
|    for feeling sick and tired later."             |
|                                                   |
|            Always remember to eat more            |
|              Fruits and Vegetables!               |
|___________________________________________________|
````
**Example of expiry reminder notice:**
````
__________________________________________
 ___________________________________________________
|                      EXPIRY!                      |
|===================================================|
|      ___            _         __        ______    |
|     / _ \___ __ _  (_)__  ___/ /__ ____/ / / /    |
|    / , _/ -_)  ' \/ / _ \/ _  / -_) __/_/_/_/     |
|   /_/|_|\__/_/_/_/_/_//_/\_,_/\__/_/ (_|_|_)      |
|___________________________________________________|
 >> Items expired! Please discard ASAP:
     1. apple | Qty: 1 | 01 Nov 2021
     2. chicken | Qty: 1 | 03 Nov 2021
 --------------------------------------------------- 
 >> Please use them before they expire:
     1. bacon | Qty: 1 | 11 Nov 2021
|___________________________________________________|
__________________________________________
````

<hr/>

### Stop Fridget: `exit`

Use this command when you are done using Fridget. Fridget will help you to remember everything you did.

Format: `exit`

**Example of usage:**

```markdown
USER INPUT: exit
__________________________________________
We'll help you remember everything you told us :)
See you again!~~
__________________________________________
```

<hr/>

## Things to note

<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding: 1rem; margin: 1rem; margin-left: 0; margin-left: 0; border-radius: 1em; word-wrap: break-word">
:exclamation: For commands help, expiring, reset, shopreset, shoplist, list, and notifs, adding a space and random text behind will still trigger the command.
</div>

Example:
```
USER INPUT: notifs ajsdfasf
__________________________________________
Turning notification off!
____________________________________________
USER INPUT: reset jadfgasgdka
__________________________________________
Are you sure you want to reset everything in the item list? (Y/N)
__________________________________________
```
<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em; word-wrap: break-word">
:exclamation: If no item is recorded, Fridget will prompt you to get help.
</div>

Example:
```
USER INPUT: find a
__________________________________________
You currently have nothing in your fridge.
Input "help" to get started!
__________________________________________
USER INPUT: expiring
__________________________________________
You currently have nothing in your fridge.
Input "help" to get started!
__________________________________________
```

<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em; word-wrap: break-word">
:exclamation: You are not allowed to add items into the shopping list manually.
</div>

<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em; word-wrap: break-word">
:exclamation: When adding items into the fridge with the add command, Fridget will automatically remove added items from the shopping list if they exist.
</div>

<div style="display: inline-block;background-image: linear-gradient(180deg, #fff5d5, #fff3cd); padding: 1rem; margin: 1rem; margin-left: 0; border-radius: 1em; word-wrap: break-word">
:exclamation: All numerical inputs are limited between "0" to "21474836467". Numbers outside this range are invalid inputs.
</div>
