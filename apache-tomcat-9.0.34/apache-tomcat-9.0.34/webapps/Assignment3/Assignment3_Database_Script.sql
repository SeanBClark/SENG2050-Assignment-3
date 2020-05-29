-- Sean Clark | c3269995
-- Kieran Walsh |
-- Declan Keir |

-- mySQL script for Assignment 3: SENG2050

-- select * from mysql.user;

-- DROP DATABASE GroupManagementDatabase;
DROP USER IF EXISTS 'root'@'GroupManagementDatabase';

CREATE DATABASE IF NOT EXISTS GroupManagementDatabase;
USE GroupManagementDatabase;

FLUSH PRIVILEGES;
CREATE USER 'root'@'GroupManagementDatabase' IDENTIFIED BY 'root'; 
GRANT ALL PRIVILEGES ON *.* TO 'root'@'GroupManagementDatabase';

DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS course_cord;
DROP TABLE IF EXISTS course_enrolments;
DROP TABLE IF EXISTS user_group_info;
DROP TABLE IF EXISTS group_milestones;
DROP TABLE IF EXISTS group_appointment;
DROP TABLE IF EXISTS file_mngt;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS group_info;
DROP TABLE IF EXISTS course;

-- User Info table
-- user_password must be inserted or updated using sha1('password') hash function
CREATE TABLE user (

	user_id INT PRIMARY KEY NOT NULL auto_increment,
    user_email VARCHAR(50) NOT NULL,
    user_password VARCHAR(50) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    user_type VARCHAR(5) default 'Std',
    user_status bit(1) default 1,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp on update current_timestamp    

);

-- Admin Acccount
INSERT INTO user(user_email, user_password, user_name, user_type) VALUES ('admin@admin.com', sha1('admin'), 'admin', 'admin');
-- SELECT * FROM user where user_type = 'admin';

-- Student Accounts
INSERT INTO user(user_email, user_password, user_name) VALUES ('std1@std1.com', sha1('password'), 'Student 1');
INSERT INTO user(user_email, user_password, user_name) VALUES ('std2@std2.com', sha1('password'), 'Student 2');
INSERT INTO user(user_email, user_password, user_name) VALUES ('std3@std3.com', sha1('password'), 'Student 3');
INSERT INTO user(user_email, user_password, user_name) VALUES ('std4@std4.com', sha1('password'), 'Student 4');
-- SELECT * FROM user where user_type = 'std';

-- Lecturer Accounts
INSERT INTO user(user_email, user_password, user_name, user_type) VALUES ('lect1@lect1.com', sha1('lect1'), 'Lecturer 1', 'lect');
INSERT INTO user(user_email, user_password, user_name, user_type) VALUES ('lect2@lect2.com', sha1('lect2'), 'Lecturer 2', 'lect');
INSERT INTO user(user_email, user_password, user_name, user_type) VALUES ('lect3@lect3.com', sha1('lect3'), 'Lecturer 3', 'lect');
INSERT INTO user(user_email, user_password, user_name, user_type) VALUES ('lect4@lect4.com', sha1('lect4'), 'Lecturer 4', 'lect');
-- SELECT * FROM user where user_type = 'lect';


--  Group Infomation Table
CREATE TABLE group_info (

	group_id INT PRIMARY KEY NOT NULL auto_increment,
    group_name VARCHAR(50) NOT NULL, -- Also Project Name!!!!!!!!
    group_description VARCHAR(200), 
    group_status bit(1) default 1,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp    

);

INSERT INTO group_info(group_name, group_description) VALUES ('Group 1', 'Test group description short');
INSERT INTO group_info(group_name, group_description) VALUES ('Group 2', 'Test group description looooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong');
INSERT INTO group_info(group_name) VALUES ('Group 3');
INSERT INTO group_info(group_name) VALUES ('Group 4');
-- SELECT * FROM group_info;

-- User Group Info table
-- Enables user to be a part of multiple groups
CREATE TABLE user_group_info (

	user_group_id INT PRIMARY KEY NOT NULL auto_increment,
    user_id INT NOT NULL,
    group_id INT NOT NULL,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp,
    
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
	FOREIGN KEY (group_id) REFERENCES group_info(group_id) ON DELETE CASCADE
);


INSERT INTO user_group_info(user_id, group_id) VALUES (1,1);
INSERT INTO user_group_info(user_id, group_id) VALUES (2,1);
INSERT INTO user_group_info(user_id, group_id) VALUES (4,1);
INSERT INTO user_group_info(user_id, group_id) VALUES (1,2);
INSERT INTO user_group_info(user_id, group_id) VALUES (3,2);
-- SELECT * FROM user_group_info;

-- Stores Group Appointments
CREATE TABLE group_appointment (

	appointment_id INT PRIMARY KEY NOT NULL auto_increment,
    group_id INT NOT NULL,
    app_name VARCHAR(50) NOT NULL,
    app_description VARCHAR(1000),
    app_status bit(1) default 0,
	date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp,
    appointment_datetime datetime,
    
    FOREIGN KEY (group_id) REFERENCES group_info(group_id) ON DELETE CASCADE
);

INSERT group_appointment(group_id, app_name, app_status, appointment_datetime)
	VALUES (1, 'Appointment One', 1, '2020-05-24 11:00:00');
INSERT group_appointment(group_id, app_name, app_status, appointment_datetime)
	VALUES (1, 'Appointment Two', 0, '2020-06-24 11:00:00');
INSERT group_appointment(group_id, app_name, app_status, appointment_datetime)
	VALUES (1, 'Appointment Three', 0, '2020-07-24 11:00:00');
INSERT group_appointment(group_id, app_name, app_status, appointment_datetime)
	VALUES (1, 'Appointment Four', 0, '2020-08-24 11:00:00');
INSERT group_appointment(group_id, app_name, app_status, appointment_datetime)
	VALUES (1, 'Appointment Five', 0, '2020-09-24 11:00:00');

-- Stores milesstones for groups
CREATE TABLE group_milestones(

	milestone_id INT PRIMARY KEY NOT NULL auto_increment,
    group_id INT NOT NULL,
    milestone_name VARCHAR(50) NOT NULL,
    milestone_description VARCHAR(1000),
    milestone_status bit(1) default 0,
	date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp,
    milestone_datetime datetime,

	FOREIGN KEY (group_id) REFERENCES group_info(group_id) ON DELETE CASCADE
);

INSERT INTO group_milestones(group_id, milestone_name, milestone_status, milestone_datetime)
	VALUES (1, 'Milestone 1', 1, '2020-05-24 11:00:00');
INSERT INTO group_milestones(group_id, milestone_name, milestone_status, milestone_datetime)
	VALUES (1, 'Milestone 2', 0, '2020-06-24 11:00:00');
INSERT INTO group_milestones(group_id, milestone_name, milestone_status, milestone_datetime)
	VALUES (1, 'Milestone 3', 0, '2020-07-24 11:00:00');
INSERT INTO group_milestones(group_id, milestone_name, milestone_status, milestone_datetime)
	VALUES (1, 'Milestone 4', 1, '2020-08-24 11:00:00');
    
-- SELECT * FROM group_milestones;

-- info on files
CREATE TABLE file_mngt (

	file_id INT PRIMARY KEY NOT NULL auto_increment,
    group_id INT NOT NULL,
    file_name VARCHAR(200) NOT NULL,
    file_url VARCHAR(200) NOT NULL,
    file_desc VARCHAR(500),
    file_version INT NOT NULL, 
    file_status bit(1) default 0,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp,
    
    FOREIGN KEY (group_id) REFERENCES group_info(group_id) ON DELETE CASCADE
);

 -- Add files
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (1, 'Dropbox', 'https://www.dropbox.com/?landing=dbv2', 'Images used in report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (1, 'Google Drive', 'https://www.google.com/drive/', 'Notes for report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (1, 'Report', 'https://www.cdc.gov/niosh/surveyreports/pdfs/349-12a.pdf', 'Draft report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version, file_status) VALUES (1, 'Report', 'https://www.gvsu.edu/cms4/asset/CC3BFEEB-C364-E1A1-A5390F221AC0FD2D/engineering_full_technical_report_gg_final.pdf', 'Final report.', 2, 1);

INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (2, 'Dropbox', 'https://www.dropbox.com/?landing=dbv2', 'Images used in report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (2, 'Google Drive', 'https://www.google.com/drive/', 'Notes for report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (2, 'Report', 'https://www.cdc.gov/niosh/surveyreports/pdfs/349-12a.pdf', 'Draft report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version, file_status) VALUES (2, 'Report', 'https://www.gvsu.edu/cms4/asset/CC3BFEEB-C364-E1A1-A5390F221AC0FD2D/engineering_full_technical_report_gg_final.pdf', 'Final report.', 2, 1);

INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (3, 'Dropbox', 'https://www.dropbox.com/?landing=dbv2', 'Images used in report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (3, 'Google Drive', 'https://www.google.com/drive/', 'Notes for report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (3, 'Report', 'https://www.cdc.gov/niosh/surveyreports/pdfs/349-12a.pdf', 'Draft report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version, file_status) VALUES (3, 'Report', 'https://www.gvsu.edu/cms4/asset/CC3BFEEB-C364-E1A1-A5390F221AC0FD2D/engineering_full_technical_report_gg_final.pdf', 'Final report.', 2, 1);

INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (4, 'Dropbox', 'https://www.dropbox.com/?landing=dbv2', 'Images used in report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (4, 'Google Drive', 'https://www.google.com/drive/', 'Notes for report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version) VALUES (4, 'Report', 'https://www.cdc.gov/niosh/surveyreports/pdfs/349-12a.pdf', 'Draft report.', 1);
INSERT INTO file_mngt (group_id, file_name, file_url, file_desc, file_version, file_status) VALUES (4, 'Report', 'https://www.gvsu.edu/cms4/asset/CC3BFEEB-C364-E1A1-A5390F221AC0FD2D/engineering_full_technical_report_gg_final.pdf', 'Final report.', 2, 1);

-- SELECT * FROM file_mngt; 



-- Table for course details
CREATE TABLE course (

    id INT PRIMARY KEY NOT NULL auto_increment,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(500),
    course_code VARCHAR(8) NOT NULL,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp

);

-- Creates courses
INSERT INTO course(name, description,course_code) VALUES ('Web Engineering', 'Web End Desc', 'SENG2050');
INSERT INTO course(name, description,course_code) VALUES ('Advanced Databases', 'Advaced Databases Desc', 'COMP3350');
INSERT INTO course(name, description,course_code) VALUES ('Data Structures', 'Data Structures Desc', 'COMP1120');
INSERT INTO course(name, description,course_code) VALUES ('Algorithms', 'Algorithms Desc', 'COMP2230');
-- SELECT * FROM course;

-- Table for students to enrol within a course
-- Currently will just be set by admin
CREATE TABLE course_enrolments (

    id INT PRIMARY KEY NOT NULL auto_increment,
    course_id INT NOT NULL,
    std_id INT NOT NULL,

    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    FOREIGN KEY (std_id) REFERENCES user(user_id) ON DELETE CASCADE

);

-- Insert Student 1 into courses
INSERT INTO course_enrolments(course_id, std_id) VALUES (1,2);
INSERT INTO course_enrolments(course_id, std_id) VALUES (2,2);
INSERT INTO course_enrolments(course_id, std_id) VALUES (3,2);
INSERT INTO course_enrolments(course_id, std_id) VALUES (4,2);

-- Insert Student 2 into courses
INSERT INTO course_enrolments(course_id, std_id) VALUES (1,3);
INSERT INTO course_enrolments(course_id, std_id) VALUES (2,3);
INSERT INTO course_enrolments(course_id, std_id) VALUES (3,3);

-- Insert Student 3 into courses
INSERT INTO course_enrolments(course_id, std_id) VALUES (2,4);
INSERT INTO course_enrolments(course_id, std_id) VALUES (3,4);
INSERT INTO course_enrolments(course_id, std_id) VALUES (4,4);

-- Insert Student 4 into courses
INSERT INTO course_enrolments(course_id, std_id) VALUES (1,5);
INSERT INTO course_enrolments(course_id, std_id) VALUES (2,5);

-- SELECT * FROM course_enrolments


-- Table to assign a lect to a course
CREATE TABLE course_cord (

    id INT PRIMARY KEY NOT NULL auto_increment,
    course_id INT NOT NULL,
    lect_id INT NOT NULL,

    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    FOREIGN KEY (lect_id) REFERENCES user(user_id) ON DELETE CASCADE

);

-- Assign lecture 1 to web eng
INSERT INTO course_cord(course_id, lect_id) VALUES (1, 6);

-- Assign lecture 2 to web eng
INSERT INTO course_cord(course_id, lect_id) VALUES (1, 7);

-- Assign lecture 3 to web eng
INSERT INTO course_cord(course_id, lect_id) VALUES (1, 8);

-- Assign lecture 4 to web eng
INSERT INTO course_cord(course_id, lect_id) VALUES (1, 9);

-- SELECT * FROM course_cord

CREATE TABLE project (

    id INT PRIMARY KEY NOT NULL auto_increment,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    grade DECIMAL(4,2),
    mark VARCHAR(1),
    marked bit(1) default 0,
    feedback VARCHAR(1000),
    course_id INT NOT NULL,
    group_id INT NOT NULL,

    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES group_info(group_id) ON DELETE CASCADE


);

-- Creates 2 uncompleted Projects for web eng and adv db for group 1 and group 2
INSERT INTO project(name, description, course_id, group_id) VALUES ('Assignment 2', 'Assignment 2 Desc', 1, 1);
INSERT INTO project(name, description, course_id, group_id) VALUES ('Assignment 2', 'Assignment 2 Desc', 2, 1);
INSERT INTO project(name, description, course_id, group_id) VALUES ('Assignment 2', 'Assignment 2 Desc', 1, 2);
INSERT INTO project(name, description, course_id, group_id) VALUES ('Assignment 2', 'Assignment 2 Desc', 2, 2);

-- Create 2 completed Projects for group 1 and 2
INSERT INTO project(name, description, course_id, group_id, marked, grade, mark, feedback) VALUES ('Assignment 1', 'Assignment 1 Desc', 1, 1, 1, 77.55, 'C', 'Great job you did not fail!');
INSERT INTO project(name, description, course_id, group_id, marked, grade, mark, feedback) VALUES ('Assignment 1', 'Assignment 1 Desc', 2, 1, 1, 12.55, 'F', 'Terrilbe job you failure');
INSERT INTO project(name, description, course_id, group_id, marked, grade, mark, feedback) VALUES ('Assignment 1', 'Assignment 1 Desc', 1, 2, 1, 77.55, 'C', 'Great job you did not fail!');
INSERT INTO project(name, description, course_id, group_id, marked, grade, mark, feedback) VALUES ('Assignment 1', 'Assignment 1 Desc', 2, 2, 1, 37.55, 'F', 'Terrilbe job you failure');
-- SELECT * FROM project