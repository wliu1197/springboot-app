create table admin_user(
	id bigint auto_increment primary key,
	username varchar(255) not null,
	password varchar(255) not null,
	cancelled timestamp
);

create table todo(
	id bigint auto_increment primary key,
	description varchar(255) not null,
	target_date timestamp,
	done boolean,
	user_id bigint
);
ALTER TABLE todo ADD FOREIGN KEY(user_id) REFERENCES admin_user(id);