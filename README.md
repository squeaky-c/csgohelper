#CS:GO Helper
A San Francisco State University final project

#Video link

https://www.youtube.com/watch?v=UVK-aX5M-z0

by Samuel Flores

The following text was written as a class assignment; it is kept here to provide context for the current state of the application.

Main goals:

CS:GO Helper is an app designed to be a speedy companion to Counter-Strike: Global Offensive players.

The main usage is for players to use the app in-between game rounds or matches.
It provides players with a fast and clean interface to look up strategies on the game's various maps.

As a secondary function, it allows a player to look at their inventory.

Secondary goals:

A statistics view.

An in-game 'buy' calculator allows players to visualize their desired loadout's cost.
This allows someone to quickly assemble a proper loadout given a specific in-game budget.

An additional bonus is provided by showing the player their inventory items' ("skins") monetary value.

Primary goals:
1) Utility strategies for at least two maps (de_mirage, de_inferno)

    Interface must be fast and legible
    1) Player scrolls through map list [RecyclerView]
    2) Identifies map, then taps left side for CT and right for T [CardView]
    3) A list of areas of effect for that map is displayed [RecyclerView]
    4) Player taps the desired area of effect (no more than 5) [GridView?]
    5) Player presented with reliable guides on that area's popular plays

2) Personal player inventory

    Should be trivial, almost the same as HW2
    1) We query the API for a player's inventory
    2) Show the player's CS:GO inventory in a GridView

Secondary goals:

3) Personal player statistics via Valve's official WebAPI for CS:GO

    Speed not a concern here, but legibility is
    1) Shows Steam icon
    2) Shows KDR
    3) Shows USP-S and Glock-17 statistics (to show a player's pistol round abilities)
    4) Shows headshot rate
    5) Shows statistics for favorite guns
    6) All stats displayed at once for screenshotability

4) In-game loadout sketcher (second)

    Maybe not as trivial to do via UI
    1) Show loadout possibilities (all pistols available for specific team, rifles, etc.)
    2) Allow player to build to budget or without budget (i.e. grey out items players can't afford)

##App Use cases

1) Helps a player to look up map-specific strategies rapidly
2) Helps a player to quickly plan appropiate in-game loadouts
3) Helps a player to study their statistics, highlighting their strengths and weaknesses
4) Helps a player to show off their inventory
5) Helps a player to learn the game and more easily enjoy the experience

