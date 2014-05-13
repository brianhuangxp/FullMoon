create table sys_code_type
(
  code_type_id           number(8) not null,
  code_type varchar2(32),
  code_label  varchar2(32),
  disable char(1) default '0',
  description varchar2(255)
);

alter table sys_code_type
  add primary key (code_type_id);


create table sys_code
(
  code_id  number(8) not null,
  code_type varchar2(32),
  parent_code_id  number(8),
  parent_type varchar2(32),
  code_value  varchar2(32),
  code_label  varchar2(32),
  disable char(1) default '0',
  description varchar2(255)
);

alter table sys_code
  add primary key (code_id);

create table sys_role
(
  role_id   number(8) not null,
  role_name varchar2(32),
  role_type  char(1),
  disable char(1) default '0',
  description varchar2(255)
);

alter table sys_role
  add primary key (role_id);

create table sys_route
(
  route_id    number(8) not null,
  route_path  varchar2(255),
  module_path  varchar2(255),
  module_name varchar2(200),
  load_css      CHAR(1) default '0',
  disable char(1) default '0',
  description varchar2(255)
);

alter table sys_route
  add primary key (route_id);


create table sys_menu
(
  menu_id    varchar2(8) not null,
  route_id number(8),
  parent_ID  number(8),
  menu_name  varchar2(200),
  menu_url   varchar2(255),
  disable      CHAR(1) default '0',
  description varchar2(255)
);

alter table sys_menu
  add primary key (menu_id);


create table sys_menu_re_role
(
  menu_role_id  number(8) not null,
  menu_id    varchar2(8) not null,
  role_id    number(8) not null
);

alter table sys_menu_re_role
  add primary key (menu_role_id);


create table sys_user
(
  user_id  number(8) not null,
  user_name    VARCHAR2(32) not null,
  nick_name    VARCHAR2(200) not null,
  user_pwd    varchar2(32) not null,
  disable      CHAR(1) default '0'
);

alter table sys_user
  add primary key (user_id);


  create table sys_user_re_role
(
  user_role_id  number(8) not null,
  user_id    number(8) not null,
  role_id    number(8) not null
);

alter table sys_user_re_role
  add primary key (user_role_id);



  ------------------
  -- temp


  create table Member(
   id number(8) not null,
   name varchar(200),
   email varchar(200),
   phone_number varchar(200),
   record_time timestamp
  );

alter table Member
  add primary key (id);
