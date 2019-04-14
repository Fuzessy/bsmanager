create table application_user (
  id bigint not null auto_increment,
  avatar longblob,
  full_name varchar(128),
  nick_name varchar(128),
  password varchar(128),
  user_name varchar(64),
  primary key (id)) engine=MyISAM;
