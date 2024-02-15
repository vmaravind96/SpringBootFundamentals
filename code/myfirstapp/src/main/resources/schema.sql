DROP TABLE STUDENT_SCHEDULEDCLASS IF EXISTS;

DROP TABLE SCHEDULEDCLASS IF EXISTS;

DROP TABLE COURSE IF EXISTS;
DROP TABLE COURSE2 IF EXISTS;

DROP TABLE STUDENT IF EXISTS;
DROP TABLE STUDENTVERSIONED IF EXISTS;

CREATE USER IF NOT EXISTS LARKU SALT 'f2d97d5e5c194fe4' HASH 'bf9ac7082b79123183a1a58f3f23b3822cbedc5c1161394f43bd4d0d03237c59' ADMIN;
CREATE MEMORY TABLE COURSE(
	id integer primary key auto_increment NOT NULL,
    CODE VARCHAR(255) NOT NULL,
    CREDITS FLOAT NOT NULL,
    TITLE VARCHAR(255)
);

CREATE MEMORY TABLE ScheduledClass(
	id integer primary key auto_increment NOT NULL,
    ENDDATE DATE,
    STARTDATE DATE,
    COURSE_ID INTEGER
);

CREATE MEMORY TABLE STUDENT(
    ID integer primary key auto_increment not null,
    NAME VARCHAR(255),
    PHONENUMBER VARCHAR(255),
    DOB DATE,
    STATUS VARCHAR(255)
);

CREATE MEMORY TABLE STUDENT_SCHEDULEDCLASS(
    STUDENTS_ID INTEGER NOT NULL,
    CLASSES_ID INTEGER NOT NULL
);

--index etc.
ALTER TABLE STUDENT_SCHEDULEDCLASS ADD CONSTRAINT PUBLIC.Student_To_Class_Unique UNIQUE(STUDENTS_ID, CLASSES_ID);
ALTER TABLE SCHEDULEDCLASS ADD CONSTRAINT PUBLIC.ScheduledClass FOREIGN KEY(COURSE_ID) REFERENCES COURSE(ID) NOCHECK;
ALTER TABLE STUDENT_SCHEDULEDCLASS ADD CONSTRAINT PUBLIC.Student_ID FOREIGN KEY(STUDENTS_ID) REFERENCES STUDENT(ID) NOCHECK;
ALTER TABLE STUDENT_SCHEDULEDCLASS ADD CONSTRAINT PUBLIC.Class_ID FOREIGN KEY(CLASSES_ID) REFERENCES SCHEDULEDCLASS(ID) NOCHECK;