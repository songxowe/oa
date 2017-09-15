/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017-08-13 21:37:38                          */
/*==============================================================*/


drop table if exists HR_DEPT;

drop table if exists HR_EMP;

drop table if exists PRI_DIARY;

drop table if exists PRI_DIARY_COMMENT;

drop table if exists PRI_MAIL;

drop table if exists PRI_MAIL_TO;

drop table if exists PRI_SMS;

drop table if exists PRI_SMS_TO;

drop table if exists PUB_MEETING_ROOM;

drop table if exists PUB_NOTIFY;

drop table if exists PUB_NOTIFY_REVICE;

drop table if exists PUB_PRODUCT;

drop table if exists PUB_VEHICLE;

drop table if exists PUB_VEHICLE_MAINTENANCE;


drop table if exists SYS_MENUS;

drop table if exists SYS_MENU_ROLE;

drop table if exists SYS_ROLES;

drop table if exists SYS_USERS;

drop table if exists SYS_USER_ROLE;

/*==============================================================*/
/* Table: HR_DEPT                                               */
/*==============================================================*/
create table HR_DEPT
(
   DEPT_ID              INT not null auto_increment,
   DEPT_NAME            VARCHAR(50) not null,
   DEPT_TEL             VARCHAR(50),
   DEPT_FUNC            VARCHAR(400),
   primary key (DEPT_ID)
);


/*==============================================================*/
/* Table: HR_EMP                                                */
/*==============================================================*/
create table HR_EMP
(
   EMP_ID               INT not null auto_increment,
   EMP_NAME             VARCHAR(20) not null,
   WORK_ID              VARCHAR(20) not null,
   DEPT_ID              INT,
   GENDER               INT,
   BIRTHDAY             DATE,
   NATION               VARCHAR(20),
   ID_CARD              VARCHAR(20),
   PHOTO                VARCHAR(200),
   IS_MARRY             VARCHAR(20),
   POLITICS             VARCHAR(20),
   DIPLOMA              VARCHAR(20),
   TITLE                VARCHAR(20),
   UNIVERSITY           VARCHAR(80),
   SPECIALITY           VARCHAR(80),
   JOIN_DATE            DATE,
   RANK                 VARCHAR(20),
   TELEPHONE            VARCHAR(20),
   CELLPHONE            VARCHAR(20),
   EMAIL                VARCHAR(100),
   ADDRESS              VARCHAR(200),
   REMARK               VARCHAR(400),
   primary key (EMP_ID)
);


/*==============================================================*/
/* Table: PRI_DIARY                                             */
/*==============================================================*/
create table PRI_DIARY
(
   DIARY_ID             INT not null auto_increment,
   USER_ID              INT,
   DIA_TIME             DATETIME,
   DIA_TYPE             VARCHAR(20),
   SUBJECT              VARCHAR(200),
   CONTENT              VARCHAR(500),
   ATTACHMENT           VARCHAR(200),
   primary key (DIARY_ID)
);


/*==============================================================*/
/* Table: PRI_DIARY_COMMENT                                     */
/*==============================================================*/
create table PRI_DIARY_COMMENT
(
   DC_ID                INT not null auto_increment,
   DIARY_ID             INT,
   USER_ID              INT,
   CONTENT              VARCHAR(500),
   SEND_TIME            DATETIME,
   COMMENT_FLAG         INT,
   primary key (DC_ID)
);


/*==============================================================*/
/* Table: PRI_MAIL                                              */
/*==============================================================*/
create table PRI_MAIL
(
   MAIL_ID              INT not null auto_increment,
   FROM_ID              VARCHAR(200),
   TO_ID                VARCHAR(200),
   COPY_TO              VARCHAR(200),
   SECREAT_TO           VARCHAR(200),
   SUBJECT              VARCHAR(200),
   CONTENT              VARCHAR(500),
   SEND_TIME            DATETIME,
   ATTACHMENT           VARCHAR(200),
   SEND_FLAG            VARCHAR(20),
   SMS_REMIND           VARCHAR(20),
   IMPORTANT            VARCHAR(20),
   MAIL_SIZE            VARCHAR(20),
   primary key (MAIL_ID)
);


/*==============================================================*/
/* Table: PRI_MAIL_TO                                           */
/*==============================================================*/
create table PRI_MAIL_TO
(
   MAIL_TO_ID           INT not null auto_increment,
   MAIL_ID              INT,
   TO_ID                VARCHAR(200),
   READ_FLAG            INT,
   DELETE_FLAG          INT,
   primary key (MAIL_TO_ID)
);


/*==============================================================*/
/* Table: PRI_SMS                                               */
/*==============================================================*/
create table PRI_SMS
(
   SMS_ID               INT not null auto_increment,
   FROM_ID              INT,
   SMS_TYPE             VARCHAR(20),
   CONTENT              VARCHAR(400),
   SEND_TIME            DATETIME,
   REMIND_URL           VARCHAR(400),
   primary key (SMS_ID)
);


/*==============================================================*/
/* Table: PRI_SMS_TO                                            */
/*==============================================================*/
create table PRI_SMS_TO
(
   SMS_TO_ID            INT not null auto_increment,
   SMS_ID               INT,
   TO_ID                INT,
   READ_FLAG            INT,
   DELETE_FLAG          INT,
   primary key (SMS_TO_ID)
);


/*==============================================================*/
/* Table: PUB_MEETING_ROOM                                      */
/*==============================================================*/
create table PUB_MEETING_ROOM
(
   MR_ID                INT not null auto_increment,
   MR_NAME              VARCHAR(20),
   MR_CAPACITY          INT,
   MR_DEVICE            VARCHAR(200),
   MR_DESC              VARCHAR(200),
   MR_PLACE             VARCHAR(200),
   USEING_FALG          INT,
   primary key (MR_ID)
);


/*==============================================================*/
/* Table: PUB_NOTIFY                                            */
/*==============================================================*/
create table PUB_NOTIFY
(
   NOTIFY_ID            INT not null auto_increment,
   SUBJECT              VARCHAR(200) not null,
   CONTENT              VARCHAR(500),
   FROM_ID              INT,
   CREATE_TIME          DATETIME,
   TO_DEPT              VARCHAR(200),
   TO_RANK              VARCHAR(200),
   TO_ID                VARCHAR(200),
   START_DATE           DATE,
   END_DATE             DATE,
   ATTACHMENT           VARCHAR(200),
   IS_TOP               INT,
   NOTIFY_STATUS        VARCHAR(20),
   primary key (NOTIFY_ID)
);


/*==============================================================*/
/* Table: PUB_NOTIFY_REVICE                                     */
/*==============================================================*/
create table PUB_NOTIFY_REVICE
(
   REVICE_ID            INT not null auto_increment,
   NOTIFY_ID            INT,
   TO_ID                INT,
   READ_FLAG            INT,
   DELETE_FLAG          INT,
   primary key (REVICE_ID)
);


/*==============================================================*/
/* Table: PUB_PRODUCT                                           */
/*==============================================================*/
create table PUB_PRODUCT
(
   PRODUCT_ID           INT not null auto_increment,
   PRODUCT_NAME         VARCHAR(100) not null,
   CATEGORY_ID          VARCHAR(20) not null,
   PRODUCT_CODE         VARCHAR(20),
   UNIT                 VARCHAR(20),
   UNIT_PRICE           DOUBLE,
   SUPPLIER             VARCHAR(200),
   STORE_CURR           INT,
   PRODUCT_DESCN        VARCHAR(400),
   primary key (PRODUCT_ID)
);


/*==============================================================*/
/* Table: PUB_VEHICLE                                           */
/*==============================================================*/
create table PUB_VEHICLE
(
   V_ID                 INT not null auto_increment,
   V_NUM                VARCHAR(20),
   V_TYPE               VARCHAR(20),
   V_DRIVER             VARCHAR(20),
   V_PRICE              DOUBLE,
   BUY_DATE             DATE,
   V_REMARK             VARCHAR(200),
   V_STATUS             VARCHAR(20),
   USEING_FALG          INT,
   primary key (V_ID)
);


/*==============================================================*/
/* Table: PUB_VEHICLE_MAINTENANCE                               */
/*==============================================================*/
create table PUB_VEHICLE_MAINTENANCE
(
   VM_ID                INT not null auto_increment,
   V_ID                 INT,
   VM_REQUEST_DATE      DATE,
   VM_TYPE              VARCHAR(20),
   VM_REASON            VARCHAR(200),
   VM_PERSON            VARCHAR(20),
   VM_FEE               DOUBLE,
   VM_REMARK            VARCHAR(200),
   primary key (VM_ID)
);


/*==============================================================*/
/* Table: SYS_MENUS                                             */
/*==============================================================*/
create table SYS_MENUS
(
   ID                   INT not null auto_increment,
   PARENT_ID            INT,
   SEQ                  INT,
   NAME                 VARCHAR(50),
   DESCN                VARCHAR(400),
   LINK_URL             VARCHAR(200),
   STATUS               VARCHAR(20),
   primary key (ID)
);

/*==============================================================*/
/* Table: SYS_MENU_ROLE                                         */
/*==============================================================*/
create table SYS_MENU_ROLE
(
   MENU_ID              INT,
   ROLE_ID              INT
);


/*==============================================================*/
/* Table: SYS_ROLES                                             */
/*==============================================================*/
create table SYS_ROLES
(
   ID                   INT not null auto_increment,
   NAME                 VARCHAR(50),
   CODE                 VARCHAR(50),
   DESCN                VARCHAR(400),
   primary key (ID)
);

/*==============================================================*/
/* Table: SYS_USERS                                             */
/*==============================================================*/
create table SYS_USERS
(
   ID                   INT not null auto_increment,
   USERNAME             VARCHAR(50) not null,
   USER_TRUE_NAME       varchar(50),
   PASSWORD             VARCHAR(50) not null,
   STATUS               INT,
   PHOTO_PATH           VARCHAR(200),
   MANAGER_ID           INT,
   primary key (ID)
);


/*==============================================================*/
/* Table: SYS_USER_ROLE                                         */
/*==============================================================*/
create table SYS_USER_ROLE
(
   USER_ID              INT,
   ROLE_ID              INT
);



drop table if exists pub_vote;

drop table if exists pub_vote_item;

drop table if exists pub_vote_sub;

/*==============================================================*/
/* Table: pub_vote                                              */
/*==============================================================*/
create table pub_vote
(
   vote_id              int not null auto_increment,
   subject              varchar(200),
   descn                varchar(400),
   from_id              int,
   create_time          datetime,
   end_time             datetime,
   to_dept              varchar(200),
   to_rank              varchar(200),
   to_id                varchar(200),
   can_view             varchar(20),
   vote_status          varchar(20),
   primary key (vote_id)
);

/*==============================================================*/
/* Table: pub_vote_item                                         */
/*==============================================================*/
create table pub_vote_item
(
   item_id              int not null auto_increment,
   sub_id               int,
   user_id              int,
   primary key (item_id)
);

/*==============================================================*/
/* Table: pub_vote_sub                                          */
/*==============================================================*/
create table pub_vote_sub
(
   sub_id               int not null auto_increment,
   vote_id              int,
   title                varchar(50),
   descn                varchar(200),
   vote_count           int,
   primary key (sub_id)
);

drop table if exists daliy;


create table daliy(
id int not null auto_increment,
username varchar(50),
priopity varchar(200),
login_date date,
classmate varchar(200),
method varchar(200),
msg varchar(200),
primary key (id)
);




alter table HR_EMP add constraint FK_Reference_5 foreign key (DEPT_ID)
      references HR_DEPT (DEPT_ID) on delete restrict on update restrict;

alter table PRI_DIARY_COMMENT add constraint FK_Reference_3 foreign key (DIARY_ID)
      references PRI_DIARY (DIARY_ID) on delete restrict on update restrict;

alter table PRI_MAIL_TO add constraint FK_Reference_1 foreign key (MAIL_ID)
      references PRI_MAIL (MAIL_ID) on delete restrict on update restrict;

alter table PRI_SMS_TO add constraint FK_Reference_2 foreign key (SMS_ID)
      references PRI_SMS (SMS_ID) on delete restrict on update restrict;

alter table PUB_NOTIFY_REVICE add constraint FK_Reference_8 foreign key (NOTIFY_ID)
      references PUB_NOTIFY (NOTIFY_ID) on delete restrict on update restrict;

