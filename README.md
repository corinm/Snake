# Snake
Version of the game Snake, based on a Youtube tutorial but heavily improved

## Based on a tutorial
This game is based on the Youtube tutorial by BrandonioProductions titled 'Making Snake in Java' (first video: https://www.youtube.com/watch?v=FABTl1Q1byw).

## Improvements over tutorial version
* Split out functionality into more concrete classes to improve maintainability
  * Number of classes increased from 5 to 16
* Objects now manage their own functionality and represent distinct real-world objects e.g. Snake, HUD, Fruit
* Made use of encapsulation by splitting out distinct functionality e.g. ScoreKeeper, KeyInput
* Split out colours and fonts into their own classes, using public constants - allows them to be changed easily in a single place
