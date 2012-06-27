# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table atividade (
  id                        bigint not null,
  titulo                    varchar(255),
  descricao                 varchar(255),
  constraint pk_atividade primary key (id))
;

create sequence atividade_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists atividade;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists atividade_seq;

