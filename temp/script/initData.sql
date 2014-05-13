--sys_code_type
insert into sys_code_type (CODE_TYPE_ID, CODE_TYPE, CODE_LABEL, DESCRIPTION)
values (1, 'BASIC_ROLE', '基本权限', '');

insert into sys_code_type (CODE_TYPE_ID, CODE_TYPE, CODE_LABEL, DESCRIPTION)
values (2, 'ADVANCED_ROLE', '高级权限', '');

insert into sys_code_type (CODE_TYPE_ID, CODE_TYPE, CODE_LABEL, DESCRIPTION)
values (3, 'SERVER_ROLE', '服务器权限', '');


--sys_code

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (18, 'ROLE_TYPE', null, '', '1', '服务器权限', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (19, 'ROLE_TYPE', null, '', '2', '基本权限', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (20, 'ROLE_TYPE', null, '', '3', '高级权限', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (1, 'BASIC_ROLE', 19, 'ROLE_TYPE', '1', '后台记录查询', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (2, 'BASIC_ROLE', 19, 'ROLE_TYPE', '2', '奖励发放', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (3, 'BASIC_ROLE', 19, 'ROLE_TYPE', '3', '内部账户管理', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (4, 'BASIC_ROLE', 19, 'ROLE_TYPE', '4', '封号处理', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (5, 'BASIC_ROLE', 19, 'ROLE_TYPE', '5', '账户查询', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (6, 'BASIC_ROLE', 19, 'ROLE_TYPE', '6', '登录留存数据', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (7, 'BASIC_ROLE', 19, 'ROLE_TYPE', '7', '充值数据', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (8, 'BASIC_ROLE', 19, 'ROLE_TYPE', '8', '排行数据', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (9, 'BASIC_ROLE', 19, 'ROLE_TYPE', '9', '资源产量数据', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (10, 'BASIC_ROLE', 19, 'ROLE_TYPE', '10', '模块参与数据', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (11, 'ADVANCED_ROLE', 20, 'ROLE_TYPE', '11', '职位管理', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (12, 'ADVANCED_ROLE', 20, 'ROLE_TYPE', '12', '账号建立', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (13, 'ADVANCED_ROLE', 20, 'ROLE_TYPE', '13', '账户修改删除', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (14, 'ADVANCED_ROLE', 20, 'ROLE_TYPE', '14', '账户权限配置', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (15, 'ADVANCED_ROLE', 20, 'ROLE_TYPE', '15', '调数据', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (16, 'ADVANCED_ROLE', 20, 'ROLE_TYPE', '16', '审核发放', '0', '');

insert into sys_code (CODE_ID, CODE_TYPE, PARENT_CODE_ID, PARENT_TYPE, CODE_VALUE, CODE_LABEL, DISABLE, DESCRIPTION)
values (17, 'ADVANCED_ROLE', 20, 'ROLE_TYPE', '17', '后台记录查询', '0', '');


--sys_menu
insert into sys_menu (MENU_ID, ROUTE_ID, PARENT_ID, MENU_NAME, MENU_URL, DISABLE, DESCRIPTION)
values ('1', 3, 0, 'User', null, '0', null);

insert into sys_menu (MENU_ID, ROUTE_ID, PARENT_ID, MENU_NAME, MENU_URL, DISABLE, DESCRIPTION)
values ('2', 3, 0, 'Member', null, '0', null);

insert into sys_menu (MENU_ID, ROUTE_ID, PARENT_ID, MENU_NAME, MENU_URL, DISABLE, DESCRIPTION)
values ('11', 5, 1, 'user1', 'user/subuser1/subuser11', '0', null);

insert into sys_menu (MENU_ID, ROUTE_ID, PARENT_ID, MENU_NAME, MENU_URL, DISABLE, DESCRIPTION)
values ('12', 3, 2, 'user2', 'user/subuser2/subuser22', '0', null);

insert into sys_menu (MENU_ID, ROUTE_ID, PARENT_ID, MENU_NAME, MENU_URL, DISABLE, DESCRIPTION)
values ('21', 5, 0, 'Member1', 'user/member/member', '0', null);

insert into sys_menu (MENU_ID, ROUTE_ID, PARENT_ID, MENU_NAME, MENU_URL, DISABLE, DESCRIPTION)
values ('22', 3, 1, 'Member2', 'user/subuser1', '0', null);

insert into sys_menu (MENU_ID, ROUTE_ID, PARENT_ID, MENU_NAME, MENU_URL, DISABLE, DESCRIPTION)
values ('0', 1, -1, 'root', null, '0', null);

-- temp user sys_user

insert into sys_user (USER_ID, USER_NAME, NICK_NAME, USER_PWD)
values (1, 'root', 'root', 'root');


--sys_role

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (1, '后台记录查询', '2', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (2, '奖励发放', '2', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (3, '内部账户管理', '2', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (4, '封号处理', '2', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (5, '账户查询', '2', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (6, '登录留存数据', '2', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (7, '充值数据', '2', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (8, '排行数据', '2', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (9, '资源产量数据', '2', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (10, '模块参与数据', '2', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (11, '职位管理', '3', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (12, '账号建立', '3', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (13, '账户修改删除', '3', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (14, '账户权限配置', '3', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (15, '调数据', '3', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (16, '审核发放', '3', '');

insert into sys_role (ROLE_ID, ROLE_NAME, ROLE_TYPE, DESCRIPTION)
values (17, '后台记录查询', '3', '');

--sys_user_re_role

insert into sys_user_re_role (USER_ROLE_ID, USER_ID, ROLE_ID)
values (1, 1, 1);

insert into sys_user_re_role (USER_ROLE_ID, USER_ID, ROLE_ID)
values (2, 1, 2);

insert into sys_user_re_role (USER_ROLE_ID, USER_ID, ROLE_ID)
values (3, 1, 3);

--sys_menu_re_role
insert into sys_menu_re_role (MENU_ROLE_ID, MENU_ID, ROLE_ID)
values (2, '0', 1);

insert into sys_menu_re_role (MENU_ROLE_ID, MENU_ID, ROLE_ID)
values (3, '1', 1);

insert into sys_menu_re_role (MENU_ROLE_ID, MENU_ID, ROLE_ID)
values (4, '11', 1);

insert into sys_menu_re_role (MENU_ROLE_ID, MENU_ID, ROLE_ID)
values (5, '12', 1);

insert into sys_menu_re_role (MENU_ROLE_ID, MENU_ID, ROLE_ID)
values (6, '2', 1);

insert into sys_menu_re_role (MENU_ROLE_ID, MENU_ID, ROLE_ID)
values (7, '21', 1);

insert into sys_menu_re_role (MENU_ROLE_ID, MENU_ID, ROLE_ID)
values (8, '22', 1);



insert into sys_route (ROUTE_ID, ROUTE_PATH, MODULE_PATH, MODULE_NAME, LOAD_CSS, DISABLE, DESCRIPTION)
values (0, '/', 'overview/', 'Overview', '1', '0', null);

insert into sys_route (ROUTE_ID, ROUTE_PATH, MODULE_PATH, MODULE_NAME, LOAD_CSS, DISABLE, DESCRIPTION)
values (1, '/user/subuser1/subuser1', 'user/subuser1/subuser1/', 'Subuser1', '0', '0', null);

insert into sys_route (ROUTE_ID, ROUTE_PATH, MODULE_PATH, MODULE_NAME, LOAD_CSS, DISABLE, DESCRIPTION)
values (2, '/user/subuser2/subuser22', 'user/subuser2/subuser22/', 'Subuser22', '0', '0', null);

insert into sys_route (ROUTE_ID, ROUTE_PATH, MODULE_PATH, MODULE_NAME, LOAD_CSS, DISABLE, DESCRIPTION)
values (3, '/user/member', 'user/member/', 'Member', '0', '0', null);




----- temp
