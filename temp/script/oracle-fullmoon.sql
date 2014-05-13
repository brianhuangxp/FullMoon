prompt PL/SQL Developer import file
prompt Created on 2014Äê1ÔÂ26ÈÕ by brain.huang
set feedback off
set define off
prompt Creating MEMBER...
create table MEMBER
(
  id           NUMBER(8) not null,
  name         VARCHAR2(200),
  email        VARCHAR2(200),
  phone_number VARCHAR2(200),
  record_time  TIMESTAMP(6)
);
alter table MEMBER
  add primary key (ID);

prompt Creating SYS_CODE...
create table SYS_CODE
(
  code_id        NUMBER(8) not null,
  code_type      VARCHAR2(32),
  parent_code_id NUMBER(8),
  parent_type    VARCHAR2(32),
  code_value     VARCHAR2(32),
  code_label     VARCHAR2(32),
  disable        CHAR(1) default '0',
  description    VARCHAR2(255)
);
alter table SYS_CODE
  add primary key (CODE_ID);

prompt Creating SYS_CODE_TYPE...
create table SYS_CODE_TYPE
(
  code_type_id NUMBER(8) not null,
  code_type    VARCHAR2(32),
  code_label   VARCHAR2(32),
  disable      CHAR(1) default '0',
  description  VARCHAR2(255)
);
alter table SYS_CODE_TYPE
  add primary key (CODE_TYPE_ID);

prompt Creating SYS_MENU...
create table SYS_MENU
(
  menu_id     VARCHAR2(8) not null,
  route_id    NUMBER(8),
  parent_id   NUMBER(8),
  menu_name   VARCHAR2(200),
  menu_url    VARCHAR2(255),
  disable     CHAR(1) default '0',
  description VARCHAR2(255)
);
alter table SYS_MENU
  add primary key (MENU_ID);

prompt Creating SYS_MENU_RE_ROLE...
create table SYS_MENU_RE_ROLE
(
  menu_role_id NUMBER(8) not null,
  menu_id      VARCHAR2(8) not null,
  role_id      NUMBER(8) not null
);
alter table SYS_MENU_RE_ROLE
  add primary key (MENU_ROLE_ID);

prompt Creating SYS_ROLE...
create table SYS_ROLE
(
  role_id     NUMBER(8) not null,
  role_name   VARCHAR2(32),
  role_type   CHAR(1),
  disable     CHAR(1) default '0',
  description VARCHAR2(255)
);
alter table SYS_ROLE
  add primary key (ROLE_ID);

prompt Creating SYS_ROUTE...
create table SYS_ROUTE
(
  route_id    NUMBER(8) not null,
  route_path  VARCHAR2(255),
  module_path VARCHAR2(255),
  module_name VARCHAR2(200),
  load_css    CHAR(1) default '0',
  disable     CHAR(1) default '0',
  description VARCHAR2(255)
);
alter table SYS_ROUTE
  add primary key (ROUTE_ID);

prompt Creating SYS_USER...
create table SYS_USER
(
  user_id   NUMBER(8) not null,
  user_name VARCHAR2(32) not null,
  nick_name VARCHAR2(200) not null,
  user_pwd  VARCHAR2(32) not null,
  disable   CHAR(1) default '0'
);
alter table SYS_USER
  add primary key (USER_ID);

prompt Creating SYS_USER_RE_ROLE...
create table SYS_USER_RE_ROLE
(
  user_role_id NUMBER(8) not null,
  user_id      NUMBER(8) not null,
  role_id      NUMBER(8) not null
);
alter table SYS_USER_RE_ROLE
  add primary key (USER_ROLE_ID);

prompt Disabling triggers for MEMBER...
alter table MEMBER disable all triggers;
prompt Disabling triggers for SYS_CODE...
alter table SYS_CODE disable all triggers;
prompt Disabling triggers for SYS_CODE_TYPE...
alter table SYS_CODE_TYPE disable all triggers;
prompt Disabling triggers for SYS_MENU...
alter table SYS_MENU disable all triggers;
prompt Disabling triggers for SYS_MENU_RE_ROLE...
alter table SYS_MENU_RE_ROLE disable all triggers;
prompt Disabling triggers for SYS_ROLE...
alter table SYS_ROLE disable all triggers;
prompt Disabling triggers for SYS_ROUTE...
alter table SYS_ROUTE disable all triggers;
prompt Disabling triggers for SYS_USER...
alter table SYS_USER disable all triggers;
prompt Disabling triggers for SYS_USER_RE_ROLE...
alter table SYS_USER_RE_ROLE disable all triggers;
prompt Loading MEMBER...
insert into MEMBER (id, name, email, phone_number, record_time)
values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212', to_timestamp('12-01-2013 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into MEMBER (id, name, email, phone_number, record_time)
values (1, 'John Smith', 'john.smith1@mailinator.com', '2125551212', to_timestamp('13-01-2014 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into MEMBER (id, name, email, phone_number, record_time)
values (2, 'John Smith', 'john.smith2@mailinator.com', '2125551212', to_timestamp('14-01-2014 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into MEMBER (id, name, email, phone_number, record_time)
values (3, 'John Smith', 'john.smith3@mailinator.com', '2125551212', to_timestamp('15-01-2014 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into MEMBER (id, name, email, phone_number, record_time)
values (4, 'John Smith', 'john.smith4@mailinator.com', '2125551212', to_timestamp('16-01-2014 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into MEMBER (id, name, email, phone_number, record_time)
values (5, 'John Smith', 'john.smith5@mailinator.com', '2125551212', to_timestamp('17-01-2014 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into MEMBER (id, name, email, phone_number, record_time)
values (6, 'John Smith', 'john.smith6@mailinator.com', '2125551212', to_timestamp('18-01-2014 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into MEMBER (id, name, email, phone_number, record_time)
values (7, 'John Smith', 'john.smith7@mailinator.com', '2125551212', to_timestamp('19-01-2014 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into MEMBER (id, name, email, phone_number, record_time)
values (8, 'John Smith', 'john.smith8@mailinator.com', '2125551212', to_timestamp('20-01-2014 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into MEMBER (id, name, email, phone_number, record_time)
values (9, 'John Smith', 'john.smith9@mailinator.com', '2125551212', to_timestamp('21-01-2014 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into MEMBER (id, name, email, phone_number, record_time)
values (10, 'John Smith', 'john.smith10@mailinator.com', '2125551212', to_timestamp('22-01-2014 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 11 records loaded
prompt Loading SYS_CODE...
insert into SYS_CODE (code_id, code_type, parent_code_id, parent_type, code_value, code_label, disable, description)
values (1, 'BASIC_ROLE', 19, 'ROLE_TYPE', '1', 'search', '0', null);
commit;
prompt 20 records loaded
prompt Loading SYS_CODE_TYPE...
insert into SYS_CODE_TYPE (code_type_id, code_type, code_label, disable, description)
values (1, 'BASIC_ROLE', 'Basic Role', '0', null);
insert into SYS_CODE_TYPE (code_type_id, code_type, code_label, disable, description)
values (2, 'ADVANCED_ROLE', 'Advanced Role', '0', null);
insert into SYS_CODE_TYPE (code_type_id, code_type, code_label, disable, description)
values (3, 'SERVER_ROLE', 'Server Role', '0', null);
commit;
prompt 3 records loaded
prompt Loading SYS_MENU...
insert into SYS_MENU (menu_id, route_id, parent_id, menu_name, menu_url, disable, description)
values ('12', null, 1, 'user2', null, '0', null);
insert into SYS_MENU (menu_id, route_id, parent_id, menu_name, menu_url, disable, description)
values ('1', null, 0, 'User', null, '0', null);
insert into SYS_MENU (menu_id, route_id, parent_id, menu_name, menu_url, disable, description)
values ('2', null, 0, 'Member', null, '0', null);
insert into SYS_MENU (menu_id, route_id, parent_id, menu_name, menu_url, disable, description)
values ('11', 1, 1, 'user1', 'user/subuser1', '0', null);
insert into SYS_MENU (menu_id, route_id, parent_id, menu_name, menu_url, disable, description)
values ('121', 2, 12, 'subuser2', 'user/subuser2', '0', null);
insert into SYS_MENU (menu_id, route_id, parent_id, menu_name, menu_url, disable, description)
values ('21', 3, 2, 'Member1', 'user/member', '0', null);
insert into SYS_MENU (menu_id, route_id, parent_id, menu_name, menu_url, disable, description)
values ('22', 4, 2, 'Member2', 'user/member2', '0', null);
insert into SYS_MENU (menu_id, route_id, parent_id, menu_name, menu_url, disable, description)
values ('0', 0, -1, 'root', null, '0', null);
commit;
prompt 8 records loaded
prompt Loading SYS_MENU_RE_ROLE...
insert into SYS_MENU_RE_ROLE (menu_role_id, menu_id, role_id)
values (2, '0', 1);
insert into SYS_MENU_RE_ROLE (menu_role_id, menu_id, role_id)
values (3, '1', 1);
insert into SYS_MENU_RE_ROLE (menu_role_id, menu_id, role_id)
values (4, '11', 1);
insert into SYS_MENU_RE_ROLE (menu_role_id, menu_id, role_id)
values (5, '12', 1);
insert into SYS_MENU_RE_ROLE (menu_role_id, menu_id, role_id)
values (6, '2', 1);
insert into SYS_MENU_RE_ROLE (menu_role_id, menu_id, role_id)
values (7, '21', 1);
insert into SYS_MENU_RE_ROLE (menu_role_id, menu_id, role_id)
values (8, '22', 1);
commit;
prompt 7 records loaded
prompt Loading SYS_ROLE...
insert into SYS_ROLE (role_id, role_name, role_type, disable, description)
values (1, 'role1', '2', '0', null);
insert into SYS_ROLE (role_id, role_name, role_type, disable, description)
values (2, 'role2', '2', '0', null);
insert into SYS_ROLE (role_id, role_name, role_type, disable, description)
values (3, 'role3', '2', '0', null);
commit;
prompt 3 records loaded
prompt Loading SYS_ROUTE...
insert into SYS_ROUTE (route_id, route_path, module_path, module_name, load_css, disable, description)
values (0, '/', 'overview/', 'Overview', '1', '0', null);
insert into SYS_ROUTE (route_id, route_path, module_path, module_name, load_css, disable, description)
values (1, '/user/subuser1', 'user/subuser1/', 'Subuser1', '0', '0', null);
insert into SYS_ROUTE (route_id, route_path, module_path, module_name, load_css, disable, description)
values (2, '/user/subuser2', 'user/subuser2/subuser22/', 'Subuser22', '0', '0', null);
insert into SYS_ROUTE (route_id, route_path, module_path, module_name, load_css, disable, description)
values (3, '/user/member', 'user/member/', 'Member', '0', '0', null);
insert into SYS_ROUTE (route_id, route_path, module_path, module_name, load_css, disable, description)
values (4, '/user/member2', 'user/member/', 'Member2', '0', '0', null);
commit;
prompt 5 records loaded
prompt Loading SYS_USER...
insert into SYS_USER (user_id, user_name, nick_name, user_pwd, disable)
values (1, 'root', 'root', 'root', '0');
commit;
prompt 1 records loaded
prompt Loading SYS_USER_RE_ROLE...
insert into SYS_USER_RE_ROLE (user_role_id, user_id, role_id)
values (1, 1, 1);
insert into SYS_USER_RE_ROLE (user_role_id, user_id, role_id)
values (2, 1, 2);
insert into SYS_USER_RE_ROLE (user_role_id, user_id, role_id)
values (3, 1, 3);
commit;
prompt 3 records loaded
prompt Enabling triggers for MEMBER...
alter table MEMBER enable all triggers;
prompt Enabling triggers for SYS_CODE...
alter table SYS_CODE enable all triggers;
prompt Enabling triggers for SYS_CODE_TYPE...
alter table SYS_CODE_TYPE enable all triggers;
prompt Enabling triggers for SYS_MENU...
alter table SYS_MENU enable all triggers;
prompt Enabling triggers for SYS_MENU_RE_ROLE...
alter table SYS_MENU_RE_ROLE enable all triggers;
prompt Enabling triggers for SYS_ROLE...
alter table SYS_ROLE enable all triggers;
prompt Enabling triggers for SYS_ROUTE...
alter table SYS_ROUTE enable all triggers;
prompt Enabling triggers for SYS_USER...
alter table SYS_USER enable all triggers;
prompt Enabling triggers for SYS_USER_RE_ROLE...
alter table SYS_USER_RE_ROLE enable all triggers;
set feedback on
set define on
prompt Done.
