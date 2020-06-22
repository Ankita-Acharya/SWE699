# About this respository

MovieWeb is a product helping users to choose their favorite movies, rate them all and see the reports based on the rated movies. They can have an account created at first to exploit all these services.

The features which users can use are as follows:
•	Can create account in the movie web
•	Log in and browse through home page
•	Update user information like phone number, type of account, etc.
•	Add, edit and delete movies
•	Rate, comment and favorite movies they have created 
•	See reports and stats

Microservices and Component Architecture
 We recognized main components as follows:
•	User management – creating and managing user account and information
•	Movie management – adding, editing and deleting movies 
•	Movie rating – rate, comment and favorite movies
•	Reports – managing reports and stats

User management microservice has Front end as thymeleaf and database as MongoDB. It has adopted MVC pattern. 

Also used Pipeline here: 

The pipeline is integrated into bitbucket which triggers this pipeline to run automatically after every push to the repository. The bitbucket-pipelines.yml file has all the instructions required to run the pipeline. We designed three steps to carry out this job. First to just maven clean install the project. The artifact acquired in this step is a zip file of the project. This is uploaded into the S3 bucket on AWS account, which is the second step. The third step is to put these from S3 bucket to EC2 instance running on AWS account. The code deploy helps to manage the deployments.  

Testing:

Unit testing is performed on /getAll and /create methods to check the APIs using MockMVC 

These tests are run during the executing of pipeling first step.
For Acceptance test, cucumber tests are performed on the given features:
Feature: all users can be fetched
  Scenario: client makes a call to /getAll
    When client calls /getAll
    Then response is given

  Scenario: get user details
    When api call to /getInfo
    Then return user detail


