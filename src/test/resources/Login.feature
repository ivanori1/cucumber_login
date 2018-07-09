Feature: Login

  Scenario Outline: 1.	Failed login – EULA is not accepted
    Given Go to webstation
    When Set <username> and <password>
    And Click Login
    Then Error Message Accept EULA
    And Close browser
    Examples:
      | username | password |
      |          |          |

  Scenario Outline: 2.	Failed login – EULA is accepted
    Given Go to webstation
    When Set <username> and <password>
    And Check Accept EULA
    And Click Login
    Then Error Message Credentials incorrect
    And Close browser

    Examples:
      | username | password |
      |          |          |

  Scenario Outline: 3.	Failed login – valid user name / invalid password
    Given Go to webstation
    When Set <username> and <password>
    And Check Accept EULA
    And Click Login
    Then Error Message Credentials incorrect
    And Close browser

    Examples:
      | username     | password |
      | ivan.coric91 | 123      |

  Scenario Outline: 4.	Failed login – invalid user name / valid password
    Given Go to webstation
    When Set <username> and <password>
    And Check Accept EULA
    And Click Login
    Then Error Message Credentials incorrect
    And Close browser

    Examples:
      | username | password    |
      | coric91  | ictrader123 |

  Scenario Outline: 5.	Failed login – invalid user
    Given Go to webstation
    When Set <username> and <password>
    And Check Accept EULA
    And Click Login
    Then Error Message This user account is disabled
    And Close browser

    Examples:
      | username    | password |
      | test.test18 | test12   |

  Scenario Outline: 6.	Failed login – invalid user name / valid password (German login dialog)
    Given Go to webstation
    And Change Language to German
    When Set <username> and <password>
    And Check Accept EULA
    And Click Login
    Then Error Message Credentials incorrect German
    And Close browser

    Examples:
      | username     | password |
      | ivan.coric91 | 123      |

  Scenario Outline: 7.	Failed login – valid user name / invalid password (German login dialog)
    Given Go to webstation
    And Change Language to German
    When Set <username> and <password>
    And Check Accept EULA
    And Click Login
    Then Error Message Credentials incorrect German
    And Close browser

    Examples:
      | username | password    |
      | ivan.    | ictrader123 |

  Scenario Outline: Successful login
    Given Go to webstation
    When Set <username> and <password>
    And Check Accept EULA
    And Click Login
    Then User is successfully login
    And Close browser

    Examples:
      | username     | password    |
      | ivan.coric91 | ictrader123 |
      | dpaafx.web92 | TTtest123   |
      | teletrad.95  | 7zvTFDRY    |

  Scenario Outline:	Expired session
    Given Go to webstation
    And Go to webstation expired session
    When Set <username> and <password>
    And Check Accept EULA
    And Click Login
    Then Relog button appear
    And Close browser

    Examples:
      | username     | password    |
      | ivan.coric91 | ictrader123 |