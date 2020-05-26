-- ________________________________________________________________________________________________

--  Test Queries for user table

UPDATE user SET user_status = 1 where user_id = 1 and user_password = sha1('admin');

SELECT EXISTS(select * from user where user_email = 'admin@admin.com' and user_password = sha1('admin'));
SELECT user_id, user_email, user_status FROM user WHERE user_email = 'admin@admin.com';

-- ________________________________________________________________________________________________

--  Test Queries for user_group_info table

-- INSERT EXAMPLE
insert into user_group_info(user_id, group_id) values (
	(select user_id from user where user_name = 'name'), 
 (select group_id from group_info where group_name = 'name'));

-- Add new user to group
INSERT INTO user_group_info(user_id, group_id) VALUES ((SELECT user_id FROM user WHERE user_email = 'd@d.com'), 1);

SELECT group_info.group_id, group_info.group_name, group_info.group_description, user.user_name, user.user_id 
	FROM user_group_info 
    JOIN user ON user.user_id = user_group_info.user_id 
    JOIN group_info ON group_info.group_id = user_group_info.group_id 
    WHERE user_group_info.user_id = 1;
    
SELECT user.user_name, user.user_id 
	FROM user
    JOIN user_group_info on user_group_info.user_id = user.user_id
    WHERE user_group_info.group_id = 1;

    -- ________________________________________________________________________________________________

--  Test Queries for group_appointment table

    
SELECT app_name, appointment_datetime FROM group_appointment WHERE group_id = 1 and app_status = 0 ORDER BY appointment_datetime ASC LIMIT 4;

SELECT milestone_datetime, milestone_name FROM group_milestones WHERE group_id = 1 ORDER BY milestone_datetime ASC LIMIT 4;

    -- ________________________________________________________________________________________________

--  Test Queries for group_milestones table

SET @zeroCount = SELECT count(milestone_status) FROM group_milestones where group_id = 1 and group_status = 0;
SET @totalCount = SELECT count(milestone_status) FROM group_milestones where group_id = 1 and group_status = 0

SELECT (count(milestone_status) * 100 / (SELECT count(milestone_status) FROM group_milestones where group_id = 1)) as percentageComplete from group_milestones where group_id = 1 and milestone_status = 0;

SELECT count(milestone_status) FROM group_milestones where group_id = 1 and milestone_status = 0;
SELECT count(milestone_status) FROM group_milestones where group_id = 1;

SELECT (count(select count(milestone_status) from group_milestones where group_status = 0)* 100 / (select count(milestone_status) from group_milestones)) as percentageComplete  FROM group_milestones WHERE group_id = 1;

SELECT milestone_datetime, milestone_name FROM group_milestones WHERE group_id = 1 ORDER BY milestone_datetime ASC LIMIT 4;

-- Test functions

-- INSERT INTO user(user_email, user_password) VALUES ('a@a.com', sha1('test'));
-- SELECT * FROM user;

-- INSERT INTO group_info(group_name) VALUES ('test');
-- UPDATE GROUP_INFO SET group_name = 'update' WHERE group_id = 1;

-- SELECT * FROM group_info;
