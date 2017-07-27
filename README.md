#Final Project - CS:GO Helper
Main goals:

CS:GO Helper is an app designed to be a speedy companion to Counter-Strike: Global Offensive players.

The main usage is for players to use the app in-between game rounds or matches.
It provides players with a fast and clean interface to look up strategies on the game's various locales.

As a secondary function, it allows a player to look at most of their important statistics.
The statistics page provides a player with valuable insight into their strengths and weaknesses in the game.

Secondary goals:

An in-game 'buy' calculator allows players to visualize their desired loadout's cost.
This allows someone to quickly assemble a proper loadout given a specific in-game budget.

An additional bonus is provided by showing the player their inventory items' ("skins") monetary value.

##Due 08/25-27:
Prepare This Readme. 

* Decide on the Idea.
* Fill out the requirements section and the use case section.
* Make sure you discuss the idea and milestones with me on the first day, or anytime you decide to pivot your project.
* You should be able to answer the following every meeting.
	1. What have I done since our last meeting
	2. What I plan do to till the next meeting
	3. Are there any issues that I am stuck on and need help.

##App requirements(listed formally):

Primary goals:
1) Utility strategies for at least two maps (de_mirage, de_inferno)

    Interface must be fast and legible
    1) Player scrolls through map list [RecyclerView]
    2) Identifies map, then taps left side for CT and right for T [CardView]
    3) A list of areas of effect for that map is displayed [RecyclerView]
    4) Player taps the desired area of effect (no more than 5) [GridView?]
    5) Player presented with reliable guides on that area's popular plays
    
    
2) Personal player statistics via Valve's official WebAPI for CS:GO

    Speed not a concern here, but legibility is
    1) Shows Steam icon
    2) Shows KDR
    3) Shows USP-S and Glock-17 statistics (to show a player's pistol round abilities)
    4) Shows headshot rate
    5) Shows statistics for favorite guns
    6) All stats displayed at once for screenshotability
    
Secondary goals:
3) Personal player inventory monetary value (first)

    Should be trivial, almost the same as HW2
    1) We query the API for a player's inventory
    2) Show the player's CS:GO inventory in a GridView
    3) When the player taps their gallery icon, show a detailed fragment for name and price
    
4) In-game loadout sketcher (second)

    Maybe not as trivial to do via UI
    1) Show loadout possibilities (all pistols available for specific team, rifles, etc.)
    2) Allow player to build to budget or without budget (i.e. grey out items players can't afford)

##App Use cases

1) Helps a player to look up map-specific strategies rapidly 
2) Helps a player to quickly plan appropiate in-game loadouts
3) Helps a player to study their statistics, highlighting their strengths and weaknesses
4) Helps a player to understand their inventory's real-world monetary value
5) Helps a player to learn the game and more easily enjoy the experience

##Due 07/25:
* Basic project Setup
* Setup an apporiate git ignore.
* setup and test github pushes.
###Scrum meeting notes:


##Due 07/27:
* Setup UI.
* Build out all the layout XMLs.
    * Most completed, not sure what other layouts are required
###Scrum meeting notes:



##Due 08/01:
* UI setup.
###Scrum meeting notes:


---
##Due(minor) 08/03:
//TODO
###Scrum meeting notes:
//TODO

---
##Due 08/08:
//TODO
###Scrum meeting notes:
//TODO

---
##Due(minor) 08/10:
//TODO
###Scrum meeting notes:
//TODO

---
##Demo Day 08/tentative:
//TODO

##Final Submission 08/tentative:
//TODO

---

---
##Notes

* Your requirements and intended features may change during scrums. Make sure you update this Readme if that happens(I encourage changes in the requirements).
* Make sure you attend class regularly and show me the completed requirements in class.
