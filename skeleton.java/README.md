# Instructions

## To compile and create a packaged JAR file

In the current directory (skeleton.java) run the following command:

mvn clean package

## To run the program

Move inside the target directory and run the following command
(Replace input_filename.grace with a name of a file inside the /src/test directory):

java -cp target/compiler-1.0-SNAPSHOT.jar:./classes/ compiler.Compiler ../src/test/input_filename.grace

## To clean

In the current directory (skeleton.java) run the following command:

mvn clean

## Output format

The output of parsing the concrete syntax tree is printed on the terminal
in the following general format:

type: specifies the type of an expression/command. Potential types include:
    function definitions, function/variable declarations, if/while statements,
    arithmetic expressions, assignments...
name: specifies the name of a function or a variable
parameters: specifies the parameter list in a function header or a function call

We present some examples of our printing style. However, the following list is not exhaustive.

### Arithmetic expressions

For arithmetic expressions we adopt the following printing format:

type: type of expression (add, sub, mult, div, mod)
    expression1:
    expression2:

Expression1 and expression2 can be complex arithmetic expressions too.
Operations with higher precedence (mult, div, mod) are printed deeper in this hierarchy.

### If/While statements

For if/while statements we adopt the following printing format:

type: type of statement (if-only, if-else, while)
    type: condition
    Operand: comparison operand
        part1:
        part2:

Condition is the condition to be evaluated to a boolean value.
The comparison operand can be a logical operand (and, or, not).
In case of the not operand there is only one part on the right hand side of the operand.
It can also be an expression comparison operand (<, >, <=, >=, #) in which case
both the left and right hand sides of the operand are expressions.

### Assignments

For assignment statements we adopt the following printing format:

type: assignment
type of lhs of assignment
type of rhs of assignment