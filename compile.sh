#!/bin/bash

cd src
javac -cp .:../bin/stdlib.jar:../bin/algs4.jar Tax.java Item.java ItemList.java Exemptions.java WordNet.java SAP.java BreadthFirstDirectedPaths.java
cd ..