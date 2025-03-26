#  Task-management-language

This project is a **domain-specific language (DSL)** for task management and scheduling, developed using **Xtext**.  
It was implemented as part of the coursework for the *Model-Driven Engineering* module at **King's College London**,  
*MSc Advanced Software Engineering*, 2025.

The DSL allows users to define tasks with estimated time, priority, repetition count, people requirements, and dependencies.  
It also supports group references, status assignment, and system-level time/people calculations.
Hope you find it interesting!😊

---

##  Features

- `group`: define groups with custom size
- `add task`: define tasks with:
  - **Name** (unique string)
  - **Requirement** (description)
  - **Time** (float)
  - **Priority** (positive integer)
  - **Repeat count** (positive integer)
  - **People** (can use expressions with `+`, `*`, group references)
  - **Dependencies** (optional)
- `status of`: assign task status (`waiting`, `in_progress`, `completed`)
- `calculate time for`, `calculate total time`, `calculate total people`

---

##  Validation Rules

Implemented using Xtext’s `@Check` validator and tested using XPect:

- Task name must be **unique**
- Group name must be **unique**
- `Priority` and `Repeat` must be **positive integers**
- `Requirement` must **start with a capital letter**
- **Circular dependencies** are not allowed
- People required (if literal) must be **positive**
- Quickfix for **lowercase requirement descriptions**

---

##  XPect Test Coverage

Test cases using `.xt` files verify:

-  Valid programs generate correct output
-  Invalid input (e.g. zero priority, duplicate names, cyclic dependencies) raises proper errors or warnings
-  All validation rules are covered and tested

---

##  How to Run

###  Runtime Eclipse

1. Launch runtime Eclipse
2. Create a new file with `.turtles` extension
3. Write a DSL program and save the file
`

###  XPect Testing

1. Open `test1.turtle.xt` or `test2.turtle.xt`
2. Run as: `JUnit Test`
3. XPect will validate your DSL and trigger generator logic
4. Generator will automatically create `filename.txt

---

##  Sample DSL Input

```turtles
group TeamA size 3
group TeamB size 2

// add task
add task "T1" 
	Requirement"AnalysisRequirement" 
    time 2.5 
    priority 1 
    repeat 2 times 
    people 3 + 1 * TeamA

add task "T2" 
	Requirement "Design" 
    time 1.0 
    priority 2 
    repeat 1 times 
    people 2 + TeamB 
    depends on "T1"

add task "T3" 
	Requirement "Testing" 
    time 5.0 
    priority 1 
    repeat 3 times 
    people 5 * TeamB 
    depends on "T1"
    
// define status
status of "T1" is completed
status of "T2" is in_progress
status of "T3" is waiting

// calculation
calculate time for "T3"
calculate total time
calculate total people



## For the above sample DSL input, the generated file would contain:
Program contains:
- 3 turn commands
- 1 variable declarations
- 3 task status checks
- Total time required (waiting/inprogress): 16.0 minutes
- Total people required: -1

Task Details:
- Task name: T1
  Requirement: AnalysisRequirement
  Required Time: 5.0 minutes
  Priority: 1
  Repeat Count: 2 times
  Dependencies: 
  People Required Expression: uk.ac.kcl.inf.languages.turtles.turtlesLanguage.impl.AdditionImpl@761d20de (operator: [+])  // not evaluated
  Status: completed

- Task name: T2
  Requirement: Design
  Required Time: 1.0 minutes
  Priority: 2
  Repeat Count: 1 times
  Dependencies: T1
  People Required Expression: uk.ac.kcl.inf.languages.turtles.turtlesLanguage.impl.AdditionImpl@13cf2632 (operator: [+])  // not evaluated
  Status: in_progress

- Task name: T3
  Requirement: Testing
  Required Time: 15.0 minutes
  Priority: 1
  Repeat Count: 3 times
  Dependencies: T1
  People Required Expression: uk.ac.kcl.inf.languages.turtles.turtlesLanguage.impl.MultiplicationImpl@b833dd7 (operator: [*])  // not evaluated
  Status: waiting
