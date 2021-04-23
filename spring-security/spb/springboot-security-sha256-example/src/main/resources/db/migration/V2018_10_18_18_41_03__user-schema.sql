create table user_detail (
  id                varchar(64)  not null primary key,
  username          varchar(100) not null unique,
  encript_password  varchar(255) not null,
  hash_id           varchar(100),
  enabled           boolean      not null default false,
  blocked           boolean      not null default false,
  expired           boolean      not null default false,
  last_login        datetime,
  created_by        varchar(64)  not null,
  created_date      datetime     not null default now(),
  last_updated_by   varchar(64),
  last_udpated_date datetime
) engine = InnoDB;

create table roles (
  id          varchar(64)  not null primary key,
  name        varchar(100) not null,
  description text
) engine = InnoDB;

create table user_roles (
  id      varchar(64) not null primary key,
  user_id varchar(64) not null,
  role_id varchar(64) not null
) engine = InnoDB;

alter table user_roles
  add constraint fk_user_role_user_id foreign key (user_id)
references user_detail (id)
  on update cascade
  on delete cascade;

alter table user_roles
  add constraint fk_user_role_role_id foreign key (role_id)
references roles (id)
  on update cascade
  on delete cascade;