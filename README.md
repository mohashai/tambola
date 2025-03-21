# tambola


Tambola is a game that is widely played across the world. To participate in a round, players
are given tickets (shown below) which have numbers on them. A round has multiple games
in it. A round is complete when all games are complete. As numbers are announced by a
dealer at random, players match the numbers with those on the ticket and cross them. If
they have crossed all numbers needed to win a game, they can claim to be the winner of the
game.

### Problem statement: Claim validator

#### **Input**: 

Numbers announced so far, a valid ticket and claim for a specific game

#### **Output**: 
Accepted/Rejected

#### Games

Each round has multiple games. Each game has a winning pattern.
* **Top line**: The ticket with all the numbers of the top row crossed fastest
* **Middle line**: The ticket with all the numbers of the middle row crossed fastest
* **Bottom line**: The ticket with the numbers of the bottom row crossed fastest
* **Full house**: The ticket with all the 15 numbers crossed first
* **Early five**: The fastest ticket to have 5 numbers crossed

#### Rules
1. System only has to return whether a claim is accepted or rejected
2. A player's claim to victory is only valid if it is made immediately following the
   announcement of the number that completes their winning sequence.


