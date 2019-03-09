#!/bin/bash
echo "Compiling"
javac *.java
echo "Running on Easy files"
java Knapsack easy20.txt > logeasy20 &
java Knapsack easy50.txt > logeasy50 &
java Knapsack easy200.txt > logeasy200 &
echo "Running on Hard files"
java Knapsack hard50.txt > loghard50 &
java Knapsack hard200.txt > loghard200 &
