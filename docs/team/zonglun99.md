# Chai Zong Lun - Project Portfolio Page

## Overview
Fridget is a fridge management software to help keep track of all your items in the fridge and also encourage you to live a healthy lifestyle.
The user interacts with Fridget through a Command Line Interface (CLI). It is written in Java.

## Summary of Contributions

### Code Contributions

#### New Feature: Added the ability to call for help command within the program.
- **What it does**: Allows the user to retrieve all the available commands with just a simple line "help" and a link to
  the user guide to obtain more information.


- **Justification**: This feature is necessary in the case whereby a user forgets what command and command format to user,
  typing a "help" is sufficed to guide the user on every single command.


- **Highlights**: This feature supports all existing commands and also commands to be added in the future. It also aims
  to help users kickstart their journey when using Fridget.

#### New Feature: Added the ability to print health notification reminders.
- **What it does**: Timely notification is printed every 4 hours when Fridget is in operation, to remind users to eat healthily.


- **Justification**: This feature is required since we want users to live a healthy lifestyle, it is necessary to send reminders,
  to ensure that users continue to monitor and eat more fruits and vegetables.


- **Highlights**: Timely reminder sent out every 4 hours since it was last printed when Fridget is in operation.

#### New Feature: Added the ability to print expiry notification reminders.
- **What it does**: Timely notification is printed every 4 hours when Fridget is in operation, to remind users to quickly finish up
all their expiring items (within 7 days) and discard any expired items in the Fridge.


- **Justification**: This feature improves the product significantly because a user using this product will want to be reminded
when their items are expiring or expired to better manage their fridge and reduce food waste.


- **Highlights**: Timely reminder sent out every 4 hours since it was last printed when Fridget is in operation, by
accessing the data stored from user input.

#### Code contributed: [RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=zonglun99&tabRepo=AY2122S1-CS2113T-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### Team contribution

#### Project Management:
- Created the skeleton code to kick-start the project.
- Maintained issue tracker.
- Vetted and commented on most of the pull requests from my teammates.
- Help to test features implemented by teammates and inform them of potential bugs.
- Assisted teammates to fix potential bugs.

### Documentation contribution:

#### User Guide:
  - Added documentation for features `help` and `notifs`.
  - Added documentation for things to note, featuring expected outcomes of certain commands.

#### Developer Guide:
  - Added documentation for features `help` and `notifs`.
  - Added class diagram for all Command classes.
  - Added class diagram for Parser class.
  - Added Fridget initialisation sequence diagram.
  - Update user stories for all versions.
  - Added manual testing guide for most commands.
  - Added non-functional requirements and glossary.
  
