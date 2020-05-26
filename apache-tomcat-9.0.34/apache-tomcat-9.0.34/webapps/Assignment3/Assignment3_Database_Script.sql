-- Sean Clark | c3269995
-- Kieran Walsh |
-- Declan Keir |

-- mySQL script for Assignment 3: SENG2050

select * from mysql.user;

-- DROP DATABASE GroupManagementDatabase;
DROP USER IF EXISTS 'admin'@'GroupManagementDatabase';

CREATE DATABASE IF NOT EXISTS GroupManagementDatabase;
USE GroupManagementDatabase;

FLUSH PRIVILEGES;
CREATE USER 'admin'@'GroupManagementDatabase' IDENTIFIED BY 'admin'; 
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'GroupManagementDatabase';

DROP TABLE IF EXISTS user_group_info;
DROP TABLE IF EXISTS group_milestones;
DROP TABLE IF EXISTS group_appointment;
DROP TABLE IF EXISTS file_mngt;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS group_info;

-- User Info table
-- user_password must be inserted or updated using sha1('password') hash function
CREATE TABLE user (

	user_id INT PRIMARY KEY NOT NULL auto_increment,
    user_email VARCHAR(50) NOT NULL,
    user_password VARCHAR(50) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    user_type VARCHAR(3) default 'Std',
    user_status bit(1) default 1,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp on update current_timestamp    

);

INSERT INTO user(user_email, user_password, user_name) VALUES ('admin@admin.com', sha1('admin'), 'admin');
INSERT INTO user(user_email, user_password, user_name) VALUES ('a@a.com', sha1('password'), 'Person 1');
INSERT INTO user(user_email, user_password, user_name) VALUES ('b@b.com', sha1('password'), 'Person 2');
INSERT INTO user(user_email, user_password, user_name) VALUES ('c@c.com', sha1('password'), 'Person 3');
INSERT INTO user(user_email, user_password, user_name) VALUES ('d@d.com', sha1('password'), 'Person 4');
SELECT * from user;
-- UPDATE user SET user_status = 1 where user_id = 1 and user_password = sha1('admin');

-- SELECT EXISTS(select * from user where user_email = 'admin@admin.com' and user_password = sha1('admin'));
-- SELECT user_id, user_email, user_status FROM user WHERE user_email = 'admin@admin.com';

--  Group Infomation Table
CREATE TABLE group_info (

	group_id INT PRIMARY KEY NOT NULL auto_increment,
    group_name VARCHAR(50) NOT NULL,
    group_description VARCHAR(200), 
    group_status bit(1) default 1,
    date_created TIMESTAMP default current_timestamp,
    date_updated TIMESTAMP default current_timestamp ON UPDATE current_timestamp    

);

INSERT INTO group_info(group_name, group_description) VALUES ('Group 1', 'Test group description short');
INSERT INTO group_info(group_name, group_description) VALUES ('Group 3', 'Test group description looooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong');
INSERT INTO group_info(group_name) VALUES ('Group 4');
INSERT INTO group_info(group_name) VALUES ('Group 5');
SELECT * FROM group_info;

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

-- Add new user to group
-- INSERT INTO user_group_info(user_id, group_id) VALUES ((SELECT user_id FROM user WHERE user_email = 'd@d.com'), 1);

INSERT INTO user_group_info(user_id, group_id) VALUES (1,1);
INSERT INTO user_group_info(user_id, group_id) VALUES (2,1);
INSERT INTO user_group_info(user_id, group_id) VALUES (4,1);
INSERT INTO user_group_info(user_id, group_id) VALUES (1,2);
INSERT INTO user_group_info(user_id, group_id) VALUES (3,2);
SELECT * FROM user_group_info;

SELECT group_info.group_id, group_info.group_name, group_info.group_description, user.user_name, user.user_id 
	FROM user_group_info 
    JOIN user ON user.user_id = user_group_info.user_id 
    JOIN group_info ON group_info.group_id = user_group_info.group_id 
    WHERE user_group_info.user_id = 1;
    
SELECT user.user_name, user.user_id 
	FROM user
    JOIN user_group_info on user_group_info.user_id = user.user_id
    WHERE user_group_info.group_id = 1;

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
    
-- SELECT app_name, appointment_datetime FROM group_appointment WHERE group_id = 1 and app_status = 0 ORDER BY appointment_datetime ASC LIMIT 4;

-- SELECT milestone_datetime, milestone_name FROM group_milestones WHERE group_id = 1 ORDER BY milestone_datetime ASC LIMIT 4;

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
    
SELECT * FROM group_milestones;

-- SET @zeroCount = SELECT count(milestone_status) FROM group_milestones where group_id = 1 and group_status = 0;
-- SET @totalCount = SELECT count(milestone_status) FROM group_milestones where group_id = 1 and group_status = 0

-- SELECT (count(milestone_status) * 100 / (SELECT count(milestone_status) FROM group_milestones where group_id = 1)) as percentageComplete from group_milestones where group_id = 1 and milestone_status = 0;

-- SELECT count(milestone_status) FROM group_milestones where group_id = 1 and milestone_status = 0;
-- SELECT count(milestone_status) FROM group_milestones where group_id = 1;

-- SELECT (count(select count(milestone_status) from group_milestones where group_status = 0)* 100 / (select count(milestone_status) from group_milestones)) as percentageComplete  FROM group_milestones WHERE group_id = 1;

-- SELECT milestone_datetime, milestone_name FROM group_milestones WHERE group_id = 1 ORDER BY milestone_datetime ASC LIMIT 4;

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
)
-- Test functions

-- INSERT INTO user(user_email, user_password) VALUES ('a@a.com', sha1('test'));
-- SELECT * FROM user;

-- INSERT INTO group_info(group_name) VALUES ('test');
-- UPDATE GROUP_INFO SET group_name = 'update' WHERE group_id = 1;

-- SELECT * FROM group_info;




-- Test file
-- INSERT INTO file_mngt (group_id, file_name, file_url, file_desc) VALUES (1, 'test file', 'https://www.google.com/', 'google');
-- INSERT INTO file_mngt (group_id, file_name, file_url, file_desc) VALUES (1, 'abcd', 'https://123', 'jksdflhsdfjkjjkljkdfjkgnjf');

-- SELECT * FROM file_mngt WHERE group_id = 1; 