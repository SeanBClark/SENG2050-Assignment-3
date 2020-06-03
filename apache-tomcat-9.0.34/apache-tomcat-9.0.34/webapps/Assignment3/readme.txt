javac -cp ".;mysql-connector-java-8.0.20.jar;C:\Users\Work Account\Documents\GitHub\SENG2050-Assignment-3\apache-tomcat-9.0.34\apache-tomcat-9.0.34\lib\servlet-api.jar" *.java

javac -cp ".;mysql-connector-java-8.0.20.jar" *.java

DOCUMENTATION

    -   Log in 

            -   Database adds dummy accounts when script is ran 

                -   Account Name: lect1@lect1.com 
                -   Password: lect1

                    -   This account is an example of a lecturer/course coordinator account
                    -   This account has the ability to view courses they are a lecturer for, as well as all groups registered under each class
                    -   This account has the ability to view the progress of each group, give feedback on progress and milestones as well as mark submitted assignments/projects
                    -   This account has the ability to create assignments for each course they are a lecturer/course coordinator for

                -   Account Name: std1@std1.com
                -   Password: password

                    -   This account is an example of a students account
                    -   This account has the ability to join groups, create groups or invite people to groups
                    -   This account has the ability to submit files, create milestones, create meetings and update the status of all 3

                -   Account Name: admin@admin.com
                -   Password: admin

                    -   This account is the admin account for the entire application
                    -   This account has the ability to create courses, assign lecturers to courses or enrol students in a course.
                    -   Given more time this account would be tasked with general management of the application. 
                        As well as an interface to add new clients and schools to the app. As it stands this app is only made for a specific school