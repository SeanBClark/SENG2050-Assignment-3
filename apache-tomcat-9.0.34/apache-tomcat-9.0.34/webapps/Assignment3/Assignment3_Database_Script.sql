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

DROP TABLE IF EXISTS project_assign;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS course_cord;
DROP TABLE IF EXISTS course_enrollments;
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
    user_type VARCHAR(5) default 'std' NOT NULL,
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
-- SELECT EXISTS( SELECT user_id FROM user WHERE user_email = 'std1@std1.com')

-- Lecturer Accounts
INSERT INTO user(user_email, user_password, user_name, user_type) VALUES ('lect1@lect1.com', sha1('lect1'), 'Lecturer 1', 'lect');
INSERT INTO user(user_email, user_password, user_name, user_type) VALUES ('lect2@lect2.com', sha1('lect2'), 'Lecturer 2', 'lect');
INSERT INTO user(user_email, user_password, user_name, user_type) VALUES ('lect3@lect3.com', sha1('lect3'), 'Lecturer 3', 'lect');
INSERT INTO user(user_email, user_password, user_name) VALUES ('lect4@lect4.com', sha1('lect4'), 'Lecturer 4');
-- SELECT * FROM user where user_type = 'lect';

-- UPDATE user SET user_type = 'lect' WHERE user_id = 9 AND user_email = 'lect4@lect4.com';
-- SELECT * FROM user where user_type = 'lect' AND user_email = 'lect4@lect4.com';
-- SELECT user_id FROM user where user_email = 'lect4@lect4.com';

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
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp,
    
    FOREIGN KEY (group_id) REFERENCES group_info(group_id) ON DELETE CASCADE
);

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
CREATE TABLE course_enrollments (

    id INT PRIMARY KEY NOT NULL auto_increment,
    course_id INT NOT NULL,
    std_id INT NOT NULL,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp,

    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    FOREIGN KEY (std_id) REFERENCES user(user_id) ON DELETE CASCADE

);

-- Insert Student 1 into courses
INSERT INTO course_enrollments(course_id, std_id) VALUES (1,2);
INSERT INTO course_enrollments(course_id, std_id) VALUES (2,2);
INSERT INTO course_enrollments(course_id, std_id) VALUES (3,2);
INSERT INTO course_enrollments(course_id, std_id) VALUES (4,2);

-- Insert Student 2 into courses
INSERT INTO course_enrollments(course_id, std_id) VALUES (1,3);
INSERT INTO course_enrollments(course_id, std_id) VALUES (2,3);
INSERT INTO course_enrollments(course_id, std_id) VALUES (3,3);

-- Insert Student 3 into courses
INSERT INTO course_enrollments(course_id, std_id) VALUES (2,4);
INSERT INTO course_enrollments(course_id, std_id) VALUES (3,4);
INSERT INTO course_enrollments(course_id, std_id) VALUES (4,4);

-- Insert Student 4 into courses
INSERT INTO course_enrollments(course_id, std_id) VALUES (1,5);
INSERT INTO course_enrollments(course_id, std_id) VALUES (2,5);

-- SELECT * FROM course_enrollments

-- INSERT INTO course_enrollments(course_id, std_id) VALUES ( ( SELECT id FROM course WHERE course_code = 'COMP1120' ), ( SELECT user_id FROM user WHERE user_email = 'std4@std4.com'  ) );
-- SELECT std_id, course_id FROM course_enrollments WHERE std_id = ( SELECT user_id FROM user WHERE user_email = 'std4@std4.com'  );

-- Table to assign a lect to a course
CREATE TABLE course_cord (

    id INT PRIMARY KEY NOT NULL auto_increment,
    course_id INT NOT NULL,
    lect_id INT NOT NULL,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp,

    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    FOREIGN KEY (lect_id) REFERENCES user(user_id) ON DELETE CASCADE

);

-- Assign lecture 1 to web eng
INSERT INTO course_cord(course_id, lect_id) VALUES (1, 6);
INSERT INTO course_cord(course_id, lect_id) VALUES (2, 6);

-- Assign lecture 2 to course 2
INSERT INTO course_cord(course_id, lect_id) VALUES (2, 7);

-- Assign lecture 3 to course 3
INSERT INTO course_cord(course_id, lect_id) VALUES (3, 8);

-- Assign lecture 4 to course 4
INSERT INTO course_cord(course_id, lect_id) VALUES (4, 9);

-- SELECT course_cord.course_id, course.name, course.course_code FROM course_cord 
-- 	JOIN course ON course.id = course_cord.course_id
-- 	WHERE course_cord.lect_id = 6;

CREATE TABLE project (

    id INT PRIMARY KEY NOT NULL auto_increment,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    due_date TIMESTAMP NOT NULL,
    course_id INT NOT NULL,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp,

    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE

);

-- Creates 2 uncompleted Projects for web eng and adv db for group 1 and group 2
INSERT INTO project(name, description, course_id, due_date) VALUES ('Assignment 2', 'Assignment 2 Desc', 1, '2020-07-24 15:00:00');
INSERT INTO project(name, description, course_id, due_date) VALUES ('Assignment 3', 'Assignment 3 Desc', 2, '2020-08-24 10:00:00');
INSERT INTO project(name, description, course_id, due_date) VALUES ('Assignment 4', 'Assignment 4 Desc', 3, '2020-09-24 23:59:59');
INSERT INTO project(name, description, course_id, due_date) VALUES ('Assignment 5', 'Assignment 5 Desc', 4, '2020-10-24 06:00:00');
-- SELECT * FROM project; 

CREATE TABLE project_assign (

    id INT PRIMARY KEY NOT NULL auto_increment,
    project_id INT NOT NULL,
    group_id INT NOT NULL,
    grade DECIMAL(4,2),
    mark VARCHAR(1),
    marked bit(1) default 0,
    feedback VARCHAR(1000),
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp,

    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES group_info(group_id) ON DELETE CASCADE

);

-- Creates 2 completed and one uncompleted project for group 1 and 2
INSERT INTO project_assign(project_id, group_id, feedback) VALUES (1, 1, 'Good work, keep it up');
INSERT INTO project_assign(project_id, group_id, feedback) VALUES (2, 1, 'This is terrible, drop out');
INSERT INTO project_assign(project_id, group_id, grade, marked, feedback) VALUES (3, 1, 88.88, 1, 'Great work!');
INSERT INTO project_assign(project_id, group_id, grade, marked, feedback) VALUES (4, 1, 12.33, 1, 'Worst I have ever seen!');

INSERT INTO project_assign(project_id, group_id, feedback) VALUES (4, 2, 'Good work, keep it up');
INSERT INTO project_assign(project_id, group_id, feedback) VALUES (3, 2, 'This is terrible, drop out');
INSERT INTO project_assign(project_id, group_id, grade, marked, feedback) VALUES (2, 2, 88.88, 1, 'Great work!');
INSERT INTO project_assign(project_id, group_id, grade, marked, feedback) VALUES (1, 2, 12.33, 1, 'Worst I have ever seen!');

SELECT project_assign.project_id, project.name, group_info.group_id, group_info.group_name, project_assign.marked
	FROM project_assign
    JOIN project ON project_assign.project_id = project.id
    JOIN group_info ON group_info.group_id = project_assign.group_id
    WHERE group_info.group_status = 1
	AND project.course_id = 1;

-- SELECT project.id, project.name, group_info.group_id, group_info.group_name 
--     FROM project 
--     JOIN group_info ON group_info.group_id = project.group_id 
--     WHERE group_info.group_status = 1
--     AND project.marked = 0
--     AND project.course_id = 1;

