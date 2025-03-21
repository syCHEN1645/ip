# Rook User Guide

## Introduction

Rook is a task management chatbot with a simple command-based interface. It is designed to help users to organise their tasks efficiently. Rook allows users to add, mark, unmark, delete, and search for tasks easily. It supports different task types, including to-dos, deadlines, and events. Whether to manege deadlines or keep track of everyday to-dos, Rook is at your service!

## Adding a to-do: *todo*
Adds a simple to-do task.

Format: 

    todo <task description>

Example:

    todo Buy groceries

## Adding a deadline: *deadline*
Adds a task with a specified deadline.

Format: 
    
    deadline <task description> /by <deadline>

Example: 

    deadline Submit report /by 2024-12-30T23:59

    deadline Wake up /by NOW

## Adding an event: *event*
Adds an event with a start and end time.

Format: 

    event <task description> /from <start time> /to <end time>

Example:

    event Team meeting /from 2025-01-09T19:00 /to 2025-01-09T20:00
    
    event Visit friend /from 2025-03-03T17:30 /to to be confirmed

## Listing all tasks: *list*
Displays all tasks currently stored.

Format: 
    
    list

## Marking a task as completed: *mark*
Marks a specified task as done.

Format:

    mark <task number>

Example:

    mark 2

## Marking a task as incomplete: *unmark*
Marks a specified task as not done.

Format: 

    unmark <task number>

Example:

    unmark 2

## Deleting a task: *delete*
Removes a specified task.

Format: 

    delete <task number>

Example: 

    delete 3

## Finding tasks: *find*
Searches for tasks containing a specific word or phrase.

Format: 

    find <keyword>

Example: 
    
    find groceries 
    
    find pay bill

## Exiting Rook: *bye*
Closes the chatbot.

Format: 

    bye

## Notes
### Time format
Any time (e.g. deadline time, start time, end time) can be written in standard format or text description. 
The standard time format is:

    yyyy-mm-ddThh:mm

where time is in 24-hour format. 

  e.g. 
      
    2025-02-03T08:30 
      
    2024-11-23T19:30  

If users want to use standard format, the exact format will need to be followed. 

### Local file
The saved data is stored in a local .txt file (data/saved.txt). 
Please refrain from editing this file, as manual changes may corrupt the stored data.
