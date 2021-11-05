Feature: Login

  Background:
    Given Colin is on the login page

  Rule: Customers needs to provide valid credentials to access the site

    Example: Colin logs in with valid credentials
      When Colin logs in with valid credentials
      Then he should be taken to the application home page

    @singlebrowser
    Scenario Outline: Login on with invalid credentials
      When Colin attempts to login with the following credentials:
        | username   | password   |
        | <username> | <password> |
      Then he should be presented with the error message <message>
      Examples:
        | username        | password       | message                                                     |
        | standard_user   | wrong_password | Username and password do not match any user in this service |
        | unknown_user    | secret_sauce   | Username and password do not match any user in this service |
        | unknown_user    | wrong_password | Username and password do not match any user in this service |
        | locked_out_user | secret_sauce   | Sorry, this user has been locked out.                       |

