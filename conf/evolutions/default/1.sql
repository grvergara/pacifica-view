# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table charity (
  id                            serial not null,
  name                          varchar(255),
  short_name                    varchar(255),
  url                           varchar(255),
  description                   varchar(255),
  image                         varchar(255),
  image_alt                     varchar(255),
  vote_count                    integer,
  constraint pk_charity primary key (id)
);

create table machine (
  id                            bigserial not null,
  license_plate                 varchar(255) not null,
  description                   varchar(255),
  status                        varchar(255),
  location                      varchar(255),
  image                         varchar(255),
  date_created                  timestamptz,
  date_updated                  timestamptz,
  machine_model_id              bigint,
  constraint pk_machine primary key (id)
);

create table machine_model (
  id                            bigserial not null,
  brand                         varchar(255),
  model                         varchar(255),
  year                          varchar(255),
  date_created                  timestamptz,
  date_updated                  timestamptz,
  constraint pk_machine_model primary key (id)
);

create table nmea_data (
  id                            bigserial not null,
  machine_id                    varchar(255),
  sentence_type                 varchar(255),
  raw_sentence                  varchar(255),
  latitude                      float,
  longitude                     float,
  status                        varchar(255),
  speed                         float,
  timestamp                     timestamptz,
  constraint pk_nmea_data primary key (id)
);

create table pdf (
  id                            bigserial not null,
  title                         varchar(255),
  description                   varchar(255),
  file_path                     varchar(255),
  uploaded_by                   varchar(255),
  upload_date                   timestamptz,
  constraint pk_pdf primary key (id)
);

create table users (
  id                            bigserial not null,
  username                      varchar(255) not null,
  password_hash                 varchar(255) not null,
  constraint uq_users_username unique (username),
  constraint pk_users primary key (id)
);

create index ix_machine_machine_model_id on machine (machine_model_id);
alter table machine add constraint fk_machine_machine_model_id foreign key (machine_model_id) references machine_model (id) on delete restrict on update restrict;


# --- !Downs

alter table if exists machine drop constraint if exists fk_machine_machine_model_id;
drop index if exists ix_machine_machine_model_id;

drop table if exists charity cascade;

drop table if exists machine cascade;

drop table if exists machine_model cascade;

drop table if exists nmea_data cascade;

drop table if exists pdf cascade;

drop table if exists users cascade;

