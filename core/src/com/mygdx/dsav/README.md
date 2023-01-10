## BenHelper.java
This file contains my helper methods, specifically those that I didn't feel fit into another file or deserved their own.

## ColorHandler.java
This file handles the different colour themes and converts between the unit interval (0-1) and hex notation.

## DSAVisualiser.java
This is the start of the program.
It handles the control flow and hands off the the relevant classes.  
LibGDX is cross-platform. I only make use of the desktop functionality, but it's still good practice to have this landing point in core.

## DijkNodeData.java
This class (more of a struct) stores data that needs to be accessed by `Screens/DijkstrasAlgScreen.java` and `Generators/GDijkstrasAlg.java`.  
The file feels a bit out of place here, but it is not a Screen and it is not a Generator, so it is in the common directory.

## FactOption.java
This is the abstract class that all of the screens inherit from.
