/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017-08-15 16:22:18                          */
/*==============================================================*/


drop table if exists FLOW_LEAVE;

drop table if exists FLOW_MEETING;

drop table if exists FLOW_VEHICLE;

drop table if exists LEAVE_BILL;

drop table if exists MEETING;

drop table if exists VEHICLE_USAGE;

/*==============================================================*/
/* Table: FLOW_LEAVE                                            */
/*==============================================================*/
create table FLOW_LEAVE
(
   ID                   INT not null auto_increment,
   CHECKER              VARCHAR(50),
   CHECK_DATE           DATE,
   CHECK_IDEA           VARCHAR(400),
   CHECK_STATUS         VARCHAR(10),
   LEAVE_ID		INT,
   primary key (ID)
);


/*==============================================================*/
/* Table: FLOW_MEETING                                          */
/*==============================================================*/
create table FLOW_MEETING
(
   ID                   INT not null auto_increment,
   CHECKER              VARCHAR(50),
   CHECK_DATE           DATE,
   CHECK_IDEA           VARCHAR(400),
   CHECK_STATUS         VARCHAR(10),
   MU_ID		INT,
   primary key (ID)
);



/*==============================================================*/
/* Table: FLOW_VEHICLE                                          */
/*==============================================================*/
create table FLOW_VEHICLE
(
   ID                   INT not null auto_increment,
   CHECKER              VARCHAR(50),
   CHECK_DATE           DATE,
   CHECK_IDEA           VARCHAR(400),
   CHECK_STATUS         VARCHAR(10),
   VU_ID		INT,
   primary key (ID)
);


/*==============================================================*/
/* Table: LEAVE_BILL                                               */
/*==============================================================*/
create table LEAVE_BILL
(
   LEAVE_ID             INT not null auto_increment,
   PROPOSER             VARCHAR(50),
   APPLY_DATE           DATE,
   DEPT_ID              INT,
   LEAVE_TYPE           VARCHAR(20),
   START_DATE           DATE,
   END_DATE             DATE,
   LEAVE_REASON         VARCHAR(200),
   CURRENT_STEP         VARCHAR(50),
   TASK_ID         VARCHAR(50),
   primary key (LEAVE_ID)
);


/*==============================================================*/
/* Table: MEETING                                               */
/*==============================================================*/
create table MEETING
(
   ID                   INT not null auto_increment,
   PROPOSER             VARCHAR(50),
   APPLY_DATE           DATETIME,
   M_ROOM               INT,
   M_TOPIC              VARCHAR(20),
   M_DESC               VARCHAR(200),
   M_ATTENDEE           VARCHAR(200),
   M_START              DATETIME,
   M_END                DATETIME,
   CURRENT_STEP         VARCHAR(50),
   TASK_ID		 VARCHAR(50),
   primary key (ID)
);


/*==============================================================*/
/* Table: VEHICLE_USAGE                                         */
/*==============================================================*/
create table VEHICLE_USAGE
(
   ID                   INT not null auto_increment,
   PROPOSER             VARCHAR(50),
   APPLY_DATE           DATE,
   DEPT_ID              INT,
   V_ID                 INT,
   VU_REASON            VARCHAR(200),
   VU_START             DATE,
   VU_END               DATE,
   CURRENT_STEP         VARCHAR(50),
   TASK_ID         VARCHAR(50),
   primary key (ID)
);


