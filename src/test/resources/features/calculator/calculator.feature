Feature: Calculator

  Rule: Should be able to perform the four basic arithmetic operations

    Scenario Outline: Performing basic arithmetic
      Given Carrie has opened the calculator
      When she performs a "<Operation>" operation on "<First Number>" and "<Second Number>"
      Then the result should be "<Result>"

      Examples: Adding numbers
        | First Number | Second Number | Operation | Result |
        | 1            | 2             | Add       | 3      |
        | -1           | 2             | Add       | 1      |
        | 1.5          | 2             | Add       | 3.5    |

      Examples: Subtracting numbers
        | First Number | Second Number | Operation | Result |
        | 2            | 1             | Subtract  | 1      |
        | -1           | -1            | Subtract  | 0      |
        | 3.5          | 2.25          | Subtract  | 1.25   |

      Examples: Concatenating numbers
        | First Number | Second Number | Operation   | Result  |
        | 2            | 1             | Concatenate | 21      |
        | -1           | -1            | Concatenate | -1-1    |
        | 3.5          | 2.25          | Concatenate | 3.52.25 |

    @current
    Scenario Outline: Rounding down
      Given Carrie has opened the calculator
      When she activates "Integers Only"
      And she performs a "<Operation>" operation on "<First Number>" and "<Second Number>"
      Then the result should be "<Result>"
      Examples:
        | First Number | Second Number | Operation | Result |
        | 1            | 2             | Add       | 3      |
        | 1            | 2.5           | Add       | 3      |
        | 5.5          | 2             | Subtract  | 3      |


    @current
    Scenario: Should report divide by zero errors
      Given Carrie has opened the calculator
      And she tries to perform a "Divide" operation on "1" and "0"
      Then a "Divide by zero error!" error message should be reported




