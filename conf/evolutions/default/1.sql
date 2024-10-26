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

create table pdf (
  id                            bigserial not null,
  title                         varchar(255),
  description                   varchar(255),
  file_path                     varchar(255),
  uploaded_by                   varchar(255),
  upload_date                   timestamptz,
  constraint pk_pdf primary key (id)
);


# --- !Downs

drop table if exists charity cascade;

drop table if exists pdf cascade;

