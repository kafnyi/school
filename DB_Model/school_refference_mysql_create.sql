CREATE TABLE `student`
(
    `SCID`      bigint(11) NOT NULL,
    `Name`      varchar(54) NOT NULL,
    `BirthDate` DATE        NOT NULL,
    `Password`  varchar(16) NOT NULL,
    PRIMARY KEY (`SCID`)
);

CREATE TABLE `diary`
(
    `DiaryID` int    NOT NULL,
    `SCID`    bigint NOT NULL,
    `ClassID` int    NOT NULL,
    PRIMARY KEY (`DiaryID`)
);

CREATE TABLE `mark`
(
    `MarkID`    bigint  NOT NULL AUTO_INCREMENT,
    `DiaryID`   int     NOT NULL,
    `Date`      DATE    NOT NULL,
    `SubjectID` int     NOT NULL,
    `Mark`      tinyint NOT NULL,
    PRIMARY KEY (`MarkID`)
);

CREATE TABLE `subject`
(
    `SubjectID`   int         NOT NULL,
    `SubjectName` varchar(32) NOT NULL,
    `TID`         bigint      NOT NULL,
    PRIMARY KEY (`SubjectID`)
);

CREATE TABLE `teacher`
(
    `TID`       bigint      NOT NULL,
    `Name`      varchar(54) NOT NULL,
    `BirthDate` DATE        NOT NULL,
    `Password`  varchar(16) NOT NULL,
    PRIMARY KEY (`TID`)
);

CREATE TABLE `class`
(
    `ClassID` int      NOT NULL,
    `Grade`   smallint NOT NULL,
    `Sign`    char     NOT NULL,
    `Year` year NOT NULL,
    `TID`     bigint   NOT NULL,
    PRIMARY KEY (`ClassID`)
);

ALTER TABLE `diary`
    ADD CONSTRAINT `Diary_fk0` FOREIGN KEY (`SCID`) REFERENCES `student` (`SCID`);

ALTER TABLE `diary`
    ADD CONSTRAINT `Diary_fk1` FOREIGN KEY (`ClassID`) REFERENCES `class` (`ClassID`);

ALTER TABLE `mark`
    ADD CONSTRAINT `Mark_fk0` FOREIGN KEY (`DiaryID`) REFERENCES `diary` (`DiaryID`);

ALTER TABLE `mark`
    ADD CONSTRAINT `Mark_fk1` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`SubjectID`);

ALTER TABLE `subject`
    ADD CONSTRAINT `Subject_fk0` FOREIGN KEY (`TID`) REFERENCES `teacher` (`TID`);

ALTER TABLE `class`
    ADD CONSTRAINT `Class_fk0` FOREIGN KEY (`TID`) REFERENCES `teacher` (`TID`);







