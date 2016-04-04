# Snake
Version of the game Snake, based on a Youtube tutorial but heavily improved based on techniques from other tutorials

## Based on a tutorial
This game is based on the Youtube tutorial by BrandonioProductions titled 'Making Snake in Java'  
Tutorial, part 1: https://www.youtube.com/watch?v=FABTl1Q1byw.

## Improvements over tutorial version
* Split out functionality into more concrete classes to improve maintainability
  * Number of classes increased from 5 to 16
* Classes now represent distinct real-world objects e.g. Snake, HUD, Fruit and manage their own functionality
* Made use of encapsulation by splitting out distinct functionality into separate classes e.g. ScoreKeeper, KeyInput
* Split out colours and fonts into their own classes, using public constants - allows them to be changed easily in a single place
