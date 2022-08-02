install Mysql work bench
create database name userdatabase
and create tables   

CREATE TABLE userdatabase.user (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `role` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL UNIQUE,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE userdatabase.MOVIES (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movies` varchar(45) NOT NULL UNIQUE,
  `status` varchar(45),
  PRIMARY KEY (`id`)
);

for frontend
npm install
npm start