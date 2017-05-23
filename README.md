# KnightTravails  

This is a problem I solved a while ago and I am now making it available on Github.


## Problem definition
Given a standard 8 x 8 chessboard where each position is indicated in algebraic notation (with the lower left corner being a1), design a script that accepts two or more arguments.

The first argument indicates the starting position of the knight. The second argument indicates the ending position of the knight. Any additional arguments indicate positions that are forbidden to the knight.

Return an array indicating the shortest path that the knight must travel to get to the end position without landing on one of the forbidden squares. If there is no valid path to the destination return nil.

Note: That in the majority of cases it would be possible to have more then one valid path.

## Solution
Use a Breadth-First Search which guarantees that the path found is the shortest.  

![Screenshot](images/Board.JPG?raw=true) ![Screenshot](images/Screenshot.JPG?raw=true)  