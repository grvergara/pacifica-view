# --- !Ups

alter table users add column created_at timestamptz default now();

# --- !Downs

alter table users drop column created_at;

