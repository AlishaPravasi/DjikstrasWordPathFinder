# DjikstrasWordPathFinder
This program, given a starting word and ending word, will compute the shortest path in a weighted graph from starting word to ending word.

The word path is subject to the following constraints: 
- The words along the path must be contained in the English dictionary.
- Every word differs from one that follows by one letter and they are all of the same length. 
- If a path doesn't exist then the algorithm should return "no path exists".

An example of a path that is game > gate > gite > kite.

To find the shortest path, I employ Djikstras algorithm for weighted graphs as well as a traditional Hashtable.
