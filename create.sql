







create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null auto_increment, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null auto_increment, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null auto_increment, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null auto_increment, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null auto_increment, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
create table account (id bigint not null, name varchar(255) not null, order_number integer not null, primary key (id)) engine=MyISAM
create table application_user (id bigint not null auto_increment, avatar longblob, full_name varchar(128), nick_name varchar(128), password varchar(128), user_name varchar(64), primary key (id)) engine=MyISAM
create table finance_item (id bigint not null auto_increment, amount decimal(19,2) not null, balance decimal(19,2) not null, note varchar(128), order_number integer not null, record_timestamp datetime not null, transaction_date date not null, category bigint not null, record_user bigint not null, source_account bigint not null, target_account bigint, primary key (id)) engine=MyISAM
create table financial_item_category (id bigint not null, alive bit not null, name varchar(255) not null, primary key (id)) engine=MyISAM
create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table item_category (id bigint not null auto_increment, alive bit not null, name varchar(64) not null, primary key (id)) engine=MyISAM
create table item_target (id bigint not null, name varchar(255), primary key (id)) engine=MyISAM
create table shopping_list_item (id bigint not null auto_increment, delete_time datetime, item_status varchar(255) not null, name varchar(255), note varchar(255), priority varchar(255), purchase_time datetime, quantity varchar(255), record_time datetime, category bigint, delete_user bigint, purchase_user bigint, record_user bigint not null, target bigint, primary key (id)) engine=MyISAM
alter table finance_item add constraint FK4og9ldd3ldha5e6yen9wskrw2 foreign key (category) references financial_item_category (id)
alter table finance_item add constraint FKcxjydxog532prs5ms5xo1ujcx foreign key (record_user) references application_user (id)
alter table finance_item add constraint FKf0kud4gi0dp2b7wdvvh80xe38 foreign key (source_account) references account (id)
alter table finance_item add constraint FKb72s9x6fs0auctc5de2psk9im foreign key (target_account) references account (id)
alter table shopping_list_item add constraint FKi7lofu2bsu1k5gm493eyqqrv6 foreign key (category) references item_category (id)
alter table shopping_list_item add constraint FKjut07sd6fp1lunr9y90q6fev9 foreign key (delete_user) references application_user (id)
alter table shopping_list_item add constraint FK2jy3d4gmyk1wxt2ikenajoafx foreign key (purchase_user) references application_user (id)
alter table shopping_list_item add constraint FK7wis76b8h2kcff7jrwq40qlwt foreign key (record_user) references application_user (id)
alter table shopping_list_item add constraint FKpadb2hdel51n0vx13rx4ht2j3 foreign key (target) references item_target (id)
