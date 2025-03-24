Task-management-language
This project is a domain-specific language (DSL) for task management and scheduling, developed using Xtext. It was implemented as part of the coursework for the Model-Driven Engineering module at King's College London, MSc Advanced Software Engineering, 2025.
The DSL allows users to define tasks with estimated time, priority, repetition count, people requirements, and dependencies. It also supports group references, status assignment, and system-level time/people calculations.

Features
● group: define groups with custom size
● add task: define tasks with
  1 Requirement (description)
  2 Time (float)
  3 Priority (positive integer)
  4 Repeat count (positive integer)
  5 People (can use expressions with +, *, group reference)
  6 Dependencies (optional)
● status of: assign task status (waiting, in_progress, completed)
● calculate time for, calculate total time, calculate total people

Validation Rules
Implemented using Xtext’s @Check validator and tested using XPect:
● Task name must be unique
● Group name must be unique
● priority and repeat must be positive
● Requirement must start with a capital letter
● No circular dependencies allowed
● People required (if literal) must be positive
● Quickfix for lowercase Requirement description

XPect Test Coverage
Test cases using .xt files verify:
● Valid programs generate correct output
● Invalid input (e.g. zero priority, duplicate names, cyclic dependencies) raises proper errors or warnings
● All validator rules are covered


How to Run
Option 1: Runtime Eclipse
1. Launch runtime Eclipse
2. Create a new file with .turtles extension
3. Write a DSL program and save the file
Option 2: XPect Testing
1. Open XPectTests.xt
2. Run as: JUnit Test
3. XPect will validate your DSL and trigger the generator
4. Generator will output a .txt file automatically

Sample DSL Input (sample.turtles)
group TeamA size 3
group TeamB size 2

add task "T1" Requirement "Analysis"
time 2.5 priority 1 repeat 2 times people 3 + 1 * TeamA

add task "T2" Requirement "Design"
time 1.0 priority 2 repeat 1 times people 2 + TeamB depends on "T1"

add task "T3" Requirement "Testing"
time 5.0 priority 1 repeat 3 times people TeamB depends on "T1"

status of "T1" is completed
status of "T2" is in_progress
status of "T3" is waiting

calculate time for "T3"
calculate total time
calculate total people
● people expressions are parsed but not evaluated in this version
● Output includes expression text with // not evaluated
● Future work can implement full expression evaluation with group resolution



YUNYAO LYU
MSc Advanced Software Engineering
King's College London
It was a great learning experience building a DSL from beginning to the end . Hope you find it interesting! 😊
