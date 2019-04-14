create table account (
  id bigint not null auto_increment,
  name varchar(255) not null,
  order_number integer not null,
  primary key (id)) engine=MyISAM;

create table finance_item (
  id bigint not null auto_increment,
  amount decimal(19,2) not null,
  balance decimal(19,2) not null,
  note varchar(128),
  order_number integer not null,
  record_timestamp datetime not null,
  transaction_date date not null,
  category bigint not null,
  record_user bigint not null,
  source_account bigint not null,
  target_account bigint,
  primary key (id)) engine=MyISAM;

create table financial_item_category (
  id bigint not null auto_increment,
  alive bit not null,
  name varchar(255) not null,
  primary key (id)) engine=MyISAM;


alter table finance_item add constraint fk_finance_item__finance_item_category foreign key (category) references financial_item_category (id);
alter table finance_item add constraint fk_finance_item__application_user foreign key (record_user) references application_user (id);
alter table finance_item add constraint fk_finance_item__source_account foreign key (source_account) references account (id);
alter table finance_item add constraint fk_finance_item__target_account foreign key (target_account) references account (id);
