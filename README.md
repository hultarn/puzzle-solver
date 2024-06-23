# Puzzle Solver Framework 2020

## Introduction 
Recursive puzzle solver for number and grid-based problems, such as *Sudoku* and *Tectonic*. The algorithm uses backpropagation to find a valid solution by comparing the new board to a set of predefined rules.

## Components
The framwork consists of the following components.

### Solver
Contains the recursive solving algorithm and two abstract methods.

#### getMaxNrm(...)
Returns the possible maximum value. For *Sudoku*, this is always 9, but for *Tectonic*, this depends on the adjacent squares of the same color.

#### possible(...)
Contains all checks to determine if the new grid is valid. If the new grid is valid, the algorithm will continue. If not, the grid will be reset and the next move will be tried.

### UI
The UI consists of a basic grid and a Done button that can be clicked when the values have been copied over from the puzzle the user is trying to solve.

This class also consists of two abstract methods that can be used to customize the view.

### addCustomComponents(...)
Allows for the addition of custom components. For Tectonic, this could be the Color button.

### addCustomListener(...)
Includes the action listeners for the custom components.

### PuzzleObject
The object must be number-based.

## Current Solvers

### Sudoku

![sudoku](https://github.com/hultarn/puzzle-solver/assets/50651800/f7192aa3-5bd2-4785-ad14-2bd05c411fbc)

### Tectonic
![tectonic](https://github.com/hultarn/puzzle-solver/assets/50651800/bde6c1ee-10f3-49f2-91d7-512d9837c9ca)

## FAQ

- **Why is the UI so ugly?**
  - Yes.