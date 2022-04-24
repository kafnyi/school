CREATE TABLE `Student`
(
    `SID`       bigint(11) NOT NULL,
    `Name`      varchar(54) NOT NULL,
    `BirthDate` DATE        NOT NULL,
    `Password`  varchar(16) NOT NULL,
    PRIMARY KEY (`SID`)
);

CREATE TABLE `Diary`
(
    `DiaryID` int    NOT NULL,
    `SCID`    bigint NOT NULL,
    `ClassID` int    NOT NULL,
    PRIMARY KEY (`DiaryID`)
);

CREATE TABLE `Mark`
(
    `MarkID`    bigint  NOT NULL AUTO_INCREMENT,
    `DiaryID`   int     NOT NULL,
    `Date`      DATE    NOT NULL,
    `SubjectID` int     NOT NULL,
    `Mark`      tinyint NOT NULL,
    PRIMARY KEY (`MarkID`)
);

CREATE TABLE `Subject`
(
    `SubjectID`   int         NOT NULL,
    `SubjectName` varchar(32) NOT NULL,
    `TID`         bigint      NOT NULL,
    PRIMARY KEY (`SubjectID`)
);

CREATE TABLE `Teacher`
(
    `TID`       bigint      NOT NULL,
    `Name`      varchar(54) NOT NULL,
    `BirthDate` DATE        NOT NULL,
    `Password`  varchar(16) NOT NULL,
    PRIMARY KEY (`TID`)
);

CREATE TABLE `Class`
(
    `ClassID` int      NOT NULL,
    `Grade`   smallint NOT NULL,
    `Sign`    char     NOT NULL,
    `Year` year NOT NULL,
    `TID`     bigint   NOT NULL,
    PRIMARY KEY (`ClassID`)
);

ALTER TABLE `Diary`
    ADD CONSTRAINT `Diary_fk0` FOREIGN KEY (`SCID`) REFERENCES `Student` (`SID`);

ALTER TABLE `Diary`
    ADD CONSTRAINT `Diary_fk1` FOREIGN KEY (`ClassID`) REFERENCES `Class` (`ClassID`);

ALTER TABLE `Mark`
    ADD CONSTRAINT `Mark_fk0` FOREIGN KEY (`DiaryID`) REFERENCES `Diary` (`DiaryID`);

ALTER TABLE `Mark`
    ADD CONSTRAINT `Mark_fk1` FOREIGN KEY (`SubjectID`) REFERENCES `Subject` (`SubjectID`);

ALTER TABLE `Subject`
    ADD CONSTRAINT `Subject_fk0` FOREIGN KEY (`TID`) REFERENCES `Teacher` (`TID`);

ALTER TABLE `Class`
    ADD CONSTRAINT `Class_fk0` FOREIGN KEY (`TID`) REFERENCES `Teacher` (`TID`);







