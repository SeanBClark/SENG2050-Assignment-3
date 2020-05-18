-- Sean Clark | c3269995
-- Kieran Walsh |
-- Declan Keir |

-- mySQL script for Assignment 3: SENG2050

CREATE DATABASE IF NOT EXISTS GroupManagementDatabase;
USE GroupManagementDatabase;

DROP TABLE user_group_info;
DROP TABLE group_milestones;
DROP TABLE group_appointment;
DROP TABLE file_mngt;
DROP TABLE user;
DROP TABLE group_info;

-- User Info table
-- user_password must be inserted or updated using sha1('password') hash function
CREATE TABLE user (

	user_id INT PRIMARY KEY NOT NULL auto_increment,
    user_email VARCHAR(50) NOT NULL,
    user_password VARCHAR(50) NOT NULL,
    user_status bit(1) default 1,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp on update current_timestamp    

);

--  Group Infomation Table
CREATE TABLE group_info (

	group_id INT PRIMARY KEY NOT NULL auto_increment,
    group_name VARCHAR(50) NOT NULL,    
    group_status bit(1) default 1,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp    

);

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
-- INSERT EXAMPLE
-- insert into user_group_info(user_id, group_id) values (
-- 	(select user_id from user where user_name = 'name'), 
--  (select group_id from group_info where group_name = 'name'));

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


-- info on files
CREATE TABLE file_mngt (

	file_id INT PRIMARY KEY NOT NULL auto_increment,
    group_id INT NOT NULL,
    file_name VARCHAR(200) NOT NULL,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp,
    
    FOREIGN KEY (group_id) REFERENCES group_info(group_id) ON DELETE CASCADE
)
-- Test functions

-- INSERT INTO user(user_email, user_password) VALUES ('a@a.com', sha1('test'));
-- SELECT * FROM user;

-- INSERT INTO group_info(group_name) VALUES ('test');
-- UPDATE GROUP_INFO SET group_name = 'update' WHERE group_id = 1;

-- SELECT * FROM group_info;