create table MEMBER
(
  id           int(8) not null,
  name         VARCHAR(200),
  email        VARCHAR(200),
  phone_number VARCHAR(200),
  record_time  TIMESTAMP
);
alter table MEMBER
  add primary key (ID);

create table SYS_CODE
(
  code_id        int(8) not null,
  code_type      VARCHAR(32),
  parent_code_id int(8),
  parent_type    VARCHAR(32),
  code_value     VARCHAR(32),
  code_label     VARCHAR(32),
  disable        CHAR(1) default '0',
  description    VARCHAR(255)
);
alter table SYS_CODE
  add primary key (CODE_ID);


create table SYS_CODE_TYPE
(
  code_type_id int(8) not null,
  code_type    VARCHAR(32),
  code_label   VARCHAR(32),
  disable      CHAR(1) default '0',
  description  VARCHAR(255)
);
alter table SYS_CODE_TYPE
  add primary key (CODE_TYPE_ID);


create table SYS_MENU
(
  menu_id     VARCHAR(8) not null,
  route_id    int(8),
  parent_id   VARCHAR(8),
  menu_name   VARCHAR(200),
  menu_url    VARCHAR(255),
  disable     CHAR(1) default '0',
  description VARCHAR(255)
);
alter table SYS_MENU
  add primary key (MENU_ID);

create table SYS_MENU_RE_ROLE
(
  menu_role_id int(8) not null,
  menu_id      VARCHAR(8) not null,
  role_id      int(8) not null
);
alter table SYS_MENU_RE_ROLE
  add primary key (MENU_ROLE_ID);


create table SYS_ROLE
(
  role_id     int(8) not null,
  role_name   VARCHAR(32),
  role_type   CHAR(1),
  disable     CHAR(1) default '0',
  description VARCHAR(255)
);
alter table SYS_ROLE
  add primary key (ROLE_ID);

create table SYS_ROUTE
(
  route_id    int(8) not null,
  route_path  VARCHAR(255),
  module_path VARCHAR(255),
  module_name VARCHAR(200),
  load_css    CHAR(1) default '0',
  disable     CHAR(1) default '0',
  description VARCHAR(255)
);
alter table SYS_ROUTE
  add primary key (ROUTE_ID);


create table SYS_USER
(
  user_id   int(8) not null,
  user_name VARCHAR(32) not null,
  nick_name VARCHAR(200) not null,
  user_pwd  VARCHAR(32) not null,
  disable   CHAR(1) default '0'
);
alter table SYS_USER
  add primary key (USER_ID);

create table SYS_USER_RE_ROLE
(
  user_role_id int(8) not null,
  user_id      int(8) not null,
  role_id      int(8) not null
);
alter table SYS_USER_RE_ROLE
  add primary key (USER_ROLE_ID);
  
  
  
  
  
  
  -- init data
  
insert into MEMBER (id, name, email, phone_number, record_time)
values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212', '2014-01-12 15:03:28');
insert into MEMBER (id, name, email, phone_number, record_time)
values (1, 'John Smith', 'john.smith1@mailinator.com', '2125551212','2014-01-13 15:03:28');
insert into MEMBER (id, name, email, phone_number, record_time)
values (2, 'John Smith', 'john.smith2@mailinator.com', '2125551212','2014-01-14 15:03:28');
insert into MEMBER (id, name, email, phone_number, record_time)
values (3, 'John Smith', 'john.smith3@mailinator.com', '2125551212','2014-01-15 15:03:28');
insert into MEMBER (id, name, email, phone_number, record_time)
values (4, 'John Smith', 'john.smith4@mailinator.com', '2125551212','2014-01-16 15:03:28');
insert into MEMBER (id, name, email, phone_number, record_time)
values (5, 'John Smith', 'john.smith5@mailinator.com', '2125551212','2014-01-17 15:03:28');
insert into MEMBER (id, name, email, phone_number, record_time)
values (6, 'John Smith', 'john.smith6@mailinator.com', '2125551212','2014-01-18 15:03:28');
insert into MEMBER (id, name, email, phone_number, record_time)
values (7, 'John Smith', 'john.smith7@mailinator.com', '2125551212','2014-01-19 15:03:28');
insert into MEMBER (id, name, email, phone_number, record_time)
values (8, 'John Smith', 'john.smith8@mailinator.com', '2125551212','2014-01-20 15:03:28');
insert into MEMBER (id, name, email, phone_number, record_time)
values (9, 'John Smith', 'john.smith9@mailinator.com', '2125551212','2014-01-21 15:03:28');
insert into MEMBER (id, name, email, phone_number, record_time)
values (10, 'John Smith', 'john.smith10@mailinator.com', '2125551212','2014-01-22 15:03:28');


insert into SYS_CODE (code_id, code_type, parent_code_id, parent_type, code_value, code_label, disable, description)
values (1, 'BASIC_ROLE', 19, 'ROLE_TYPE', '1', 'search', '0', null);


insert into SYS_CODE_TYPE (code_type_id, code_type, code_label, disable, description)
values (1, 'BASIC_ROLE', 'Basic Role', '0', null);
insert into SYS_CODE_TYPE (code_type_id, code_type, code_label, disable, description)
values (2, 'ADVANCED_ROLE', 'Advanced Role', '0', null);
insert into SYS_CODE_TYPE (code_type_id, code_type, code_label, disable, description)
values (3, 'SERVER_ROLE', 'Server Role', '0', null);


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


insert into SYS_ROLE (role_id, role_name, role_type, disable, description)
values (1, 'role1', '2', '0', null);
insert into SYS_ROLE (role_id, role_name, role_type, disable, description)
values (2, 'role2', '2', '0', null);
insert into SYS_ROLE (role_id, role_name, role_type, disable, description)
values (3, 'role3', '2', '0', null);


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


insert into SYS_USER (user_id, user_name, nick_name, user_pwd, disable)
values (1, 'root', 'root', 'root', '0');


insert into SYS_USER_RE_ROLE (user_role_id, user_id, role_id)
values (1, 1, 1);
insert into SYS_USER_RE_ROLE (user_role_id, user_id, role_id)
values (2, 1, 2);
insert into SYS_USER_RE_ROLE (user_role_id, user_id, role_id)
values (3, 1, 3);