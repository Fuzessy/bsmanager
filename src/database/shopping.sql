create table item_category (
  id bigint not null auto_increment,
  alive bit not null,
  name varchar(64) not null,
  primary key (id)) engine=MyISAM;

create table item_target (
  id bigint not null auto_increment,
  name varchar(255),
  primary key (id)) engine=MyISAM;

create table shopping_list_item (
  id bigint not null auto_increment,
  delete_time datetime,
  item_status varchar(255) not null,
  name varchar(255),
  note varchar(255),
  priority varchar(255),
  purchase_time datetime,
  quantity varchar(255),
  record_time datetime,
  category bigint,
  delete_user bigint,
  purchase_user bigint,
  record_user bigint not null,
  target bigint,
  primary key (id)) engine=MyISAM;


alter table shopping_list_item add constraint shopping_list_item__category foreign key (category) references item_category (id);
alter table shopping_list_item add constraint shopping_list_item__delete_ueser foreign key (delete_user) references application_user (id);
alter table shopping_list_item add constraint shopping_list_item__purchase_user foreign key (purchase_user) references application_user (id);
alter table shopping_list_item add constraint shopping_list_item__record_user foreign key (record_user) references application_user (id);
alter table shopping_list_item add constraint shopping_list_item__target foreign key (target) references item_target (id);

