use Coders;



INSERT INTO Users (mailID, userName, Datejoined, Streakdate) VALUES ('charu07@gmail.com', 'charu07', '2024-02-29', '2024-02-29');
INSERT INTO Login (mailID, passwd) VALUES ('charu07@gmail.com', '$2a$10$3U4KHiDOSaiqgHXf9RDbaeJzAcplsVpYdxas50NUda/HcIbpt8NlW');
INSERT INTO Session (sessionID, mailID, loggedTime) VALUES ('272a6da3-23fd-463c-a1ff-f5877844abd3', 'charu07@gmail.com', '2024-02-29 22:24:57.473');
INSERT INTO Users (mailID, userName, Datejoined, Streakdate) VALUES ('krishnagokul810@gmail.com', 'krishaee', '2024-02-29', '2024-02-29');
INSERT INTO Login (mailID, passwd) VALUES ('krishnagokul810@gmail.com', '$2a$10$gNt6SZzJKPmZtVXB1TMWQ.bonA233722UPPCIuuXCN4F2FE7NvLIK');
INSERT INTO Session (sessionID, mailID, loggedTime) VALUES ('53555758-c9de-4800-891e-7e28023f7d50', 'krishnagokul810@gmail.com', '2024-02-29 22:25:04.239');
INSERT INTO Users (mailID, userName, Datejoined, Streakdate) VALUES ('vasanth.albert@zohocorp.com', 'vsr', '2024-02-29', '2024-02-29');
INSERT INTO Login (mailID, passwd) VALUES ('vasanth.albert@zohocorp.com', '$2a$10$VJc29ymwppWLHK4KkjSVaOBcPot4JRj87jsPS2ZeBtVsfw3ykVTdq');
INSERT INTO Session (sessionID, mailID, loggedTime) VALUES ('56fbd67e-0945-4747-8c06-594f9971ecdc', 'vasanth.albert@zohocorp.com', '2024-02-29 22:25:28.455');


-- INSERT INTO Users (mailID, userName, Datejoined) VALUES 
-- ('sorimuthu@gmail.com', 'Sorimuthu', '2024-03-06'),
-- ('vijila@gmail.com', 'Vijila', '2024-03-06'),
-- ('abitha@gmail.com', 'Abitha', '2024-03-06'),
-- ('arun@gmail.com', 'Arun', '2024-03-06'),
-- ('bangalore@gmail.com', 'Bangalore', '2024-03-06'),
-- ('elango@gmail.com', 'Elango', '2024-03-06'),
-- ('gopika@gmail.com', 'Gopika', '2024-03-06'),
-- ('indhu@gmail.com', 'Indhu', '2024-03-06'),
-- ('indirajith@gmail.com', 'Indirajith', '2024-03-06'),
-- ('jeyrajesh@gmail.com', 'Jey Rajesh', '2024-03-06'),
-- ('amos@gmail.com', 'Amos', '2024-03-06'),
-- ('kumaravel@gmail.com', 'Kumaravel', '2024-03-06'),
-- ('mahalakshmi@gmail.com', 'Mahalakshmi', '2024-03-06'),
-- ('mathimaran@gmail.com', 'Mathimaran', '2024-03-06'),
-- ('naveenbabu@gmail.com', 'Naveen Babu', '2024-03-06'),
-- ('padma@gmail.com', 'Padma', '2024-03-06'),
-- ('piravin@gmail.com', 'Piravin', '2024-03-06'),
-- ('rabi@gmail.com', 'Rabi', '2024-03-06'),
-- ('ragavi@gmail.com', 'Ragavi', '2024-03-06'),
-- ('rahul@gmail.com', 'Rahul', '2024-03-06'),
-- ('santhiya@gmail.com', 'Santhiya', '2024-03-06'),
-- ('saranesh@gmail.com', 'Saranesh', '2024-03-06'),
-- ('sun@gmail.com', 'Sun', '2024-03-06'),
-- ('tharan@gmail.com', 'Tharan', '2024-03-06'),
-- ('sundari@gmail.com', 'Sundari', '2024-03-06'),
-- ('ulaga@gmail.com', 'Ulaga', '2024-03-06');


-- INSERT INTO Clan(clanID, clanName, Admin) VALUES('66423152', 'coders', 'krishnagokul810@gmail.com'), ('78320745', 'Zoho Schools', 'vasanth.albert@zohocorp.com'), ('40378295', 'Zsttk', 'charu07@gmail.com'), ('93820754', 'CringeSquad', 'tharan@gmail.com');

-- INSERT INTO ClanRelation(clanID, mailID, role) VALUES('66423152', 'krishnagokul810@gmail.com', 'ADMIN');
-- INSERT INTO ClanRelation (clanID, mailID, role) VALUES ('40378295', 'charu07@gmail.com', 'ADMIN'), ('78320745', 'vasanth.albert@zohocorp.com', 'ADMIN'),('93820754','tharan@gmail.com','ADMIN');

-- INSERT INTO ClanRelation (clanID, mailID, role) VALUES
--     -> (93820754, 'sorimuthu@gmail.com', 'Member'),
--     -> (93820754, 'abitha@gmail.com', 'Member'),
--     -> (93820754, 'arun@gmail.com', 'Member'),
--     -> (93820754, 'bangalore@gmail.com', 'Member'),
--     -> (93820754, 'elango@gmail.com', 'Member'),
--     -> (93820754, 'gopika@gmail.com', 'Member');

-- INSERT INTO ClanRelation (clanID, mailID, role) VALUES
-- (66423152, 'indhu@gmail.com', 'Member'),
-- (66423152, 'indirajith@gmail.com', 'Member'),
-- (66423152, 'jeyrajesh@gmail.com', 'Member'),
-- (66423152, 'amos@gmail.com', 'Member'),
-- (66423152, 'kumaravel@gmail.com', 'Member'),
-- (66423152, 'mahalakshmi@gmail.com', 'Member');

-- INSERT INTO ClanRelation (clanID, mailID, role) VALUES
-- (78320745, 'mathimaran@gmail.com', 'Member'),
-- (78320745, 'naveenbabu@gmail.com', 'Member'),
-- (78320745, 'padma@gmail.com', 'Member'),
-- (78320745, 'piravin@gmail.com', 'Member'),
-- (78320745, 'rabi@gmail.com', 'Member'),
-- (78320745, 'ragavi@gmail.com', 'Member');



-- INSERT INTO ClanRelation (clanID, mailID, role) VALUES
-- (40378295, 'rahul@gmail.com', 'Member'),
-- (40378295, 'santhiya@gmail.com', 'Member'),
-- (40378295, 'saranesh@gmail.com', 'Member'),
-- (40378295, 'sun@gmail.com', 'Member'),
-- (40378295, 'sundari@gmail.com', 'Member'),
-- (40378295, 'vijila@gmail.com', 'Member'),
-- (40378295, 'ulaga@gmail.com', 'Member');


INSERT into Languages(lang_name) values('Java'), ('Python'), ('Mysql');
INSERT into Tags(Tag_name) values('Arrays'), ('Algorithm'), ('Debugging'),('Fundamentals'),('String');
INSERT into Levels(level_name,score) values ('8Kyu',2),('7Kyu',3),('6Kyu',4);

INSERT into Questions(Q_ID, Q_name,description,example,functionString,testcaseJSON,levelID,Author,status) values ("56873465","Sum Arrays",'Write a function that takes an array of numbers and returns the sum of the numbers. The numbers can be negative or non-integer. If the array does not contain any numbers then you should return 0.', 'Examples Input: [1, 5.2, 4, 0, -1]\nOutput: 9.2\n\nInput: []\nOutput: 0\n\n Input: [-2.398]\nOutput: -2.398\n\n','sum_array','{
    "0": {
        "params": {"0": []},
        "output": 0
    },
    "1": {
        "params": {"0": [5]},
        "output": 5
    },
    "2": {
        "params": {"0": [1, 2, 3, 4, 5]},
        "output": 15
    },
    "3": {
        "params": {"0": [-1, -2, -3, -4, -5]},
        "output": -15
    },
    "4": {
        "params": {"0": [1, -2, 3, -4, 5]},
        "output": 3
    },
    "5": {
        "params": {"0": [1000000, 2000000, 3000000]},
        "output": 6000000
    },
    "6": {
        "params": {"0": [5, 5, 5, 5, 5]},
        "output": 25
    },
    "7": {
        "params": {"0": [0, 0, 0, 0, 0]},
        "output": 0
    },
    "8": {
        "params": {"0": [-5, 10]},
        "output": 5
    },
    "9": {
        "params": {"0": [-1000000]},
        "output": -1000000
    }
}',1,"charu07@gmail.com", 'APPROVED');
    
INSERT into Questions(Q_ID, Q_name,description,example,functionString,testcaseJSON,levelID,Author,status) values ("45986677","Sum of positive",'You get an array of numbers, return the sum of all of the positives ones.','Example [1,-4,7,12] => 1 + 7 + 12 = 20','positive_sum','{
    "0": {
        "params": {"0": []},
        "output": 0
    },
    "1": {
        "params": {"0": [5]},
        "output": 5
    },
    "2": {
        "params": {"0": [1, 2, 3, 4, 5]},
        "output": 15
    },
    "3": {
        "params": {"0": [-1, -2, -3, -4, -5]},
        "output": 0
    },
    "4": {
        "params": {"0": [1, -2, 3, -4, 5]},
        "output": 9
    },
    "5": {
        "params": {"0": [1000000, 2000000, 3000000]},
        "output": 6000000
    },
    "6": {
        "params": {"0": [5, 5, 5, 5, 5]},
        "output": 25
    },
    "7": {
        "params": {"0": [0, 0, 0, 0, 0]},
        "output": 0
    },
    "8": {
        "params": {"0": [-5, 10]},
        "output": 10
    },
    "9": {
        "params": {"0": [-1000000]},
        "output": 0
    }
}',2,"krishnagokul810@gmail.com", 'APPROVED');

INSERT INTO Questions (Q_ID, Q_name, description, example, functionString, testcaseJSON, levelID, Author, status) 
VALUES ("42887152", "reverse the string", 'You get a string, return its reverse.', 'Example "codewars" => "srawedoc"', 'reverse',
'{
    "0": {
        "params": { "0": "hello" },
        "output":  "olleh"
    },
    "1": {
        "params": { "0": "world" },
        "output":  "dlrow"
    },
    "2": {
        "params": { "0": "" },
        "output":  ""
    },
    "3": {
        "params": { "0": "a" },
        "output":  "a"
    },
    "4": {
        "params": { "0": "!@#$%^" },
        "output":  "^%$#@!"
    },
    "5": {
        "params": { "0": " hello " },
        "output":  " olleh "
    },
    "6": {
        "params": { "0": "HeLlO WoRlD" },
        "output":  "DlRoW OlLeH"
    },
    "7": {
        "params": { "0": "12345" },
        "output":  "54321"
    },
    "8": {
        "params": { "0": "hello001 " },
        "output":  " 100olleh"
    },
    "9": {
        "params": { "0": "@!23" },
        "output":  "32!@"
    },
    "10": {
        "params": { "0": "hello  world" },
        "output":  "dlrow  olleh"
    }
}', 1, "charu07@gmail.com", 'APPROVED');

INSERT into Questions(Q_ID, Q_name,description,example,functionString,testcaseJSON,levelID,Author,status) values ("54281620","check if it is even",'You get a number, return a boolean whether the number is even','Example 453 => false','isEven','
    "0": {
        "params": {"0": Integer.MAX_VALUE},
        "output": false
    },
    "1": {
        "params": {"0": Integer.MIN_VALUE},
        "output": false
    },
    "2": {
        "params": {"0": -1},
        "output": false
    },
    "3": {
        "params": {"0": 1},
        "output": false
    }
    "4": {
        "params": {"0": 9},
        "output": false
    },
    "5": {
        "params": {"0": -11},
        "output": false
    },
    "6": {
        "params": {"0": 32},
        "output": true
    },
    "7": {
        "params": {"0": -256},
        "output": true
    },
    "8": {
        "params": {"0": 999},
        "output": false
    },
    "9": {
        "params": {"0": -1000},
        "output": true
    }
											
',2,"charu07@gmail.com", 'APPROVED');

INSERT into Questions(Q_ID, Q_name,description,example,functionString,testcaseJSON,levelID,Author,status) values ("27624322","findLargestOfArray",'You get an array of numbers, return largest of all.','Example [1,-4,7,12] => 12','findLargest','"0": {
        "params": {"0": [5, 7, 1, 8, 4]},
        "output": 8
    },
    "1": {
        "params": {"0": [-3, -6, -1, -4]},
        "output": -1
    },
    "2": {
        "params": {"0": [0, 0, 0]},
        "output": 0
    },
    "3": {
        "params": {"0": [1, 2, 3, 4, 5, 6, 7, 8, 9]},
        "output": 9
    },
    "4": {
        "params": {"0": [8, 2, 7, 5, 4]},
        "output": 8
    },
    "5": {
        "params": {"0": [1]},
        "output": 1
    },
    "6": {
        "params": {"0": [2, -7, 3, -4]},
        "output": 3
    },
    "7": {
        "params": {"0": [100, 50, 75, 60]},
        "output": 100
    },
    "8": {
        "params": {"0": [123, 456, 789]},
        "output": 789
    }',2,"charu07@gmail.com", 'APPROVED');
INSERT into TagsRelation values('56873465', 1);
INSERT into TagsRelation values('45986677', 1);
INSERT into TagsRelation values('42887152', 5);
INSERT into TagsRelation values('54281620', 4);
INSERT into TagsRelation values('27624322', 4);
INSERT into TagsRelation values('27624322', 1);


insert into LanguageRelation values(1, '56873465', 'public static double sum_array(double[] numbers)', '');
insert into LanguageRelation values(2, '56873465', 'def sum_array(a)', '');
insert into LanguageRelation values(1, '45986677', 'public static int positive_sum(int[] arr)', '');
insert into LanguageRelation values(2, '45986677', 'def positive_sum(arr)', '');
insert into LanguageRelation values(1, '42887152', 'public static int reverse(String word)', '');
insert into LanguageRelation values(2, '42887152', 'def reverse(word)', '');

insert into Solutions values('12345678', 'vasanth.albert@zohocorp.com', '56873465', 'double total = 0;
    if(numbers.length == 0 || numbers == null)return total;
    for(double number : numbers){
      total+=number;
    }
    return total;', 1,'2023-02-07', 'COMPLETED', 'PRACTICE');
insert into Solutions values('87654321', 'vasanth.albert@zohocorp.com', '45986677', 'testing', 2,'2023-08-07', 'ATTEMPTED', 'PRACTICE');
insert into Solutions values('23456789', 'vasanth.albert@zohocorp.com', '56873465', 'testing', 1,'2023-08-07', 'COMPLETED', 'PRACTICE');
insert into Solutions values('34567890', 'vasanth.albert@zohocorp.com', '42887152', 'testing', 1,'2023-08-07', 'COMPLETED', 'PRACTICE');
