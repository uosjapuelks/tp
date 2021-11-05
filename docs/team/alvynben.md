# Alvin Ben Abraham - Project Portfolio Page

## Overview

Nowadays, users need to go through every ingredient in their fridge manually to perform any function related to their fridge. Fridget is a CLI-based application that hopes to automate a lot of the tasks users have relating to the fridge, so that users are able to cook easily and eat healthily.

## Summary of Contributions

### Code Contributed

[<button> Click me to see Alvin's RepoSense page! </button>](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=alvynben)

### Enhancements Implemented

#### <ins>New Feature: Added the ability to add items into Fridget</ins>

Question | Answer
------|-------
What does it do?| Allows the user to add an item (or multiple) into Fridget. 
Why is it needed?| It is the backbone of the entire product. The first step for the user will always be to add the items in their fridge into Fridget, so that Fridget can help to keep track of it.

This enhancement went through a few iterations. Originally, it only allowed the adding of one item at a time. In the next iteration, the enhancement was improved so that the user could add multiple items at the same time. However, the problem was that each individual item could only be added with a quantity of 1. The final iteration involved enabling the user to control the quantity of each item to be added.

The final product is a tour de force of simplified code, which attempts to make the code used to add the product be as lightweight and maintainable as possible, by following good coding standards like SLAP.

It has to deal with all possible errors that could be introduced into the system, so that it does not affect other functions down the road. Hence, this enhancement needed to catch multiple errors from different sources such as incorrect date-time formatting, or wrong syntax inputs by the user. This code was potentially the most vulnerable function compared to the rest, and extra care had to be placed to reduce the capability for users to introduce error while enabling them the freedom to do what they needed to do.

<hr/>

#### <ins>New Feature: Added the ability to remove items from Fridget</ins>

Question | Answer
------|-------
What does it do?| Allows the user to remove an item from Fridget by name.
Why is it needed?| It allows users to remove items they do not need or have used. It is a requirement as Fridget acts as a ledger for the user's fridge.

This enhancement originally involved the ability to remove an item from Fridget, one item at a time. This was originally implemented by me. However, working together with Bryan Elmer, this enhancement was eventually upgraded to remove any quantity from an item as required by the user.

This function involved multiple stages of inputs from the users, so each stage had to be carefully considered so that all incorrect inputs were caught and dealt with.

<hr/>

### Contributions to Team-Based Tasks
* Set up the GitHub Team Org/Repo
* Setup Github and Grade Workflows
* Maintained Issue Tracker (in conjunction with rest of team)

### Review/Mentoring Contributions
* Helped to review a considerable amount of PRs and catch a few errors
* Assisted to fix bugs or potential vulnerabilities in other's code

### Contributions to the User Guide

### Contributions to the Developer Guide

