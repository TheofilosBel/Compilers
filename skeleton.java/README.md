# Instructions

## To compile and create a packaged JAR file

In the current directory (skeleton.java) run the following command:

mvn clean package

## To run the program

Move inside the target directory and run the following command
(Replace input_filename.grace with a name of a file inside the /src/test directory):

java -cp target/compiler-1.0-SNAPSHOT.jar:./classes/compiler.Compiler ../src/test/input_filename.grace

## To clean

In the current directory (skeleton.java) run the following command:

mvn clean

## Design Decisions

We have corrected the problems of part 1.

Our implementation of the Symbol Table is based on a stack of treemaps
for faster searching. Every stack item represents a new scope. The treemaps
are used to save the information of every variable or function.
