drop table if exists Category ;

create table
    Category (
        idx bigint AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        parent INT,
        rank INT,
        modDate DATETIME,
        regDate DATETIME NOT NULL
    );