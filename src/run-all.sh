#!/bin/bash

javac -cp .:../bin/stdlib.jar:../bin/algs4.jar Tax.java Item.java ItemList.java Exemptions.java WordNet.java SAP.java BreadthFirstDirectedPaths.java
java -cp .:../bin/stdlib.jar:../bin/algs4.jar Tax $1 $2 $(ls ../input)