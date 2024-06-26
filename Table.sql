create user 'krish'@'localhost' identified by '';
grant all privileges on *.* to 'krish'@'localhost' with grant option;


drop database if exists Coders;
create database Coders;

USE Coders;


CREATE table Users
(
	mailID varchar(30) PRIMARY KEY,
	userName varchar(50),
	score int default 0,
	streak int default 0,
	Datejoined date,
	Streakdate date,
	Ace_badge int default 0,
	Conquer_badge int default 0,
	Crown_badge int default 0
);

CREATE table Login
(
	mailID varchar(30) unique,
	passwd varchar(255) not null,
	Role ENUM('USER', 'ADMIN') default 'USER',
	FOREIGN KEY(mailID) REFERENCES Users(mailID)
);

CREATE table Session
(
	sessionID varchar(40) PRIMARY KEY,
	mailID varchar(30),
   	loggedTime TIMESTAMP,
	FOREIGN KEY(mailID) REFERENCES Users(mailID) 
);



CREATE table Clan
(
	clanID varchar(8) PRIMARY KEY,
	clanName varchar(100),
	Admin varchar(30),
	FOREIGN KEY(Admin) REFERENCES Users(mailID)
);



CREATE table ClanRelation
(
	clanID varchar(8),
	mailID varchar(50) NOT NULL,
	role enum('CO_ADMIN', 'MEMBER', 'ADMIN') default 'MEMBER',
	PRIMARY KEY (mailID, clanID),
	FOREIGN KEY(clanID) REFERENCES Clan(clanID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(mailID) REFERENCES Users(mailID) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE table ClanRequest
(
	clanID varchar(8),
	mailID varchar(30),
	PRIMARY KEY (clanID, mailID),
	FOREIGN KEY(clanID) REFERENCES Clan(clanID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(mailID) REFERENCES Users(mailID) ON DELETE CASCADE ON UPDATE CASCADE
);


create table Levels(
    levelID tinyint auto_increment primary key,
    level_name varchar(10),
    score tinyint default 0
);

CREATE table Questions
(
    Q_ID varchar(8) primary key,
    Q_name varchar(100),
    description text,
    example text,
	functionString varchar(200),
	testcaseJSON text,
    levelID tinyint,
    Author varchar(30),
    status enum('APPROVED', 'NOTAPPROVED') default 'NOTAPPROVED',
	questionType enum('PRACTICE','TOURNAMENT') default 'PRACTICE',
    FOREIGN KEY(Author) REFERENCES Users(mailID),
    FOREIGN KEY(levelID) REFERENCES Levels(levelID)
);

CREATE table Languages
(
	l_ID int AUTO_INCREMENT PRIMARY KEY,
	lang_name varchar(100)
);

CREATE table LanguageRelation
(
	l_ID int,
	Q_ID varchar(8),
	funcName text,
	testCases varchar(255),
	FOREIGN KEY(l_ID) REFERENCES Languages(l_ID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(Q_ID) REFERENCES Questions(Q_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE table Solutions
(
	Sol_ID varchar(8) PRIMARY KEY,
	mailID varchar(30),
	Q_ID varchar(8),
	Sol text,
	lang_ID int,
	solDate date,
	Execution_time double,
	status enum('ATTEMPTED', 'COMPLETED'),
	solvedType enum('PRACTICE','TOURNAMENT'),
	FOREIGN KEY(lang_ID) REFERENCES Languages(l_ID),
	FOREIGN KEY(mailID) REFERENCES Users(mailID),
	FOREIGN KEY(Q_ID) REFERENCES Questions(Q_ID)
);

CREATE table Tags
(
	Tag_ID int AUTO_INCREMENT PRIMARY KEY,
	Tag_name varchar(100)
);

CREATE table TagsRelation
(
	Q_ID varchar(8),
	Tag_ID int,
	FOREIGN KEY(Q_ID) REFERENCES Questions(Q_ID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(Tag_ID) REFERENCES Tags(Tag_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Tournament
(
	
    mailID VARCHAR(30),
    Join_time TIMESTAMP,
    Submit_time TIMESTAMP,
    Date DATE,
    Sol_ID varchar(8),
	Score double,
	primary key(mailID,Date),
    FOREIGN KEY(mailID) REFERENCES Users(mailID),
	FOREIGN KEY(Sol_ID) REFERENCES Solutions(Sol_ID)
);


CREATE TABLE TournamentQuestionRelation(
	Date DATE primary key,
	Q_ID varchar(8),
	l_ID int,
	Conquer_winner VARCHAR(30),
	Ace_winner VARCHAR(30),
	Crown_winner VARCHAR(30),
	FOREIGN KEY(Conquer_winner) REFERENCES Users(mailID),
	FOREIGN KEY(l_ID) REFERENCES Languages(l_ID) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(Ace_winner) REFERENCES Users(mailID),
	FOREIGN KEY(Crown_winner) REFERENCES Users(mailID),
	FOREIGN KEY(Q_ID) REFERENCES Questions(Q_ID)
);



	