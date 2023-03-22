/*
SQLyog Community Edition- MySQL GUI v8.03 
MySQL - 5.6.12-log : Database - 22_epicene
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`22_epicene` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `22_epicene`;

/*Table structure for table `appointments` */

DROP TABLE IF EXISTS `appointments`;

CREATE TABLE `appointments` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `la_id` int(11) DEFAULT NULL,
  `a_date` date DEFAULT NULL,
  `a_time` time DEFAULT NULL,
  `a_status` varchar(20) DEFAULT NULL,
  `a_place` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `appointments` */

/*Table structure for table `awarness` */

DROP TABLE IF EXISTS `awarness`;

CREATE TABLE `awarness` (
  `awerness_id` int(11) NOT NULL AUTO_INCREMENT,
  `location_id` int(11) DEFAULT NULL,
  `program_name` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `venue` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`awerness_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `awarness` */

insert  into `awarness`(`awerness_id`,`location_id`,`program_name`,`description`,`date`,`time`,`venue`) values (1,0,'admin','sssdsd','2022-11-02','11:25:00','Keecheri'),(2,2,'xx','xxxx','2022-11-26','21:01:00','xxx');

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `booking_date` date DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `booking` */

insert  into `booking`(`book_id`,`from_id`,`schedule_id`,`booking_date`,`status`) values (1,14,1,'2022-11-03','pending'),(2,14,3,'2022-11-03','booked'),(3,14,3,'2022-11-03','booked'),(4,14,3,'2022-11-03','rejected'),(5,14,1,'2022-11-04','pending');

/*Table structure for table `chat` */

DROP TABLE IF EXISTS `chat`;

CREATE TABLE `chat` (
  `chat_id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) DEFAULT NULL,
  `to_id` int(11) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`chat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `chat` */

insert  into `chat`(`chat_id`,`from_id`,`to_id`,`message`,`date`) values (1,14,10,'jcvjv','2022-11-03'),(2,14,10,'jcvjv','2022-11-03'),(3,14,12,'vjv','2022-11-03'),(4,14,12,'','2022-11-03'),(5,12,14,'jj','2022-11-03 13:46:36'),(6,14,12,'jyjyjyjyj','2022-11-04');

/*Table structure for table `counselling` */

DROP TABLE IF EXISTS `counselling`;

CREATE TABLE `counselling` (
  `medicalofficer_id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `counsellingprogram` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  `time` varchar(54) DEFAULT NULL,
  `contactno` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`medicalofficer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `counselling` */

insert  into `counselling`(`medicalofficer_id`,`cid`,`counsellingprogram`,`description`,`place`,`date`,`time`,`contactno`) values (1,NULL,'Orange','kannur','kannur','2022-10-31','11:28',9876543210),(3,8,'ldfkdl','lklk','lkkl','2022-11-03','09:38',9887654329);

/*Table structure for table `counselling_pgm` */

DROP TABLE IF EXISTS `counselling_pgm`;

CREATE TABLE `counselling_pgm` (
  `cp_id` int(11) NOT NULL AUTO_INCREMENT,
  `cp_pgm` varchar(30) DEFAULT NULL,
  `cp_description` varchar(30) DEFAULT NULL,
  `cp_place` varchar(30) DEFAULT NULL,
  `cp_date` varchar(30) DEFAULT NULL,
  `cp_time` varchar(30) DEFAULT NULL,
  `cp_contactno` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `counselling_pgm` */

/*Table structure for table `education` */

DROP TABLE IF EXISTS `education`;

CREATE TABLE `education` (
  `e_id` int(10) NOT NULL AUTO_INCREMENT,
  `e_name` varchar(30) DEFAULT NULL,
  `e_place` varchar(30) DEFAULT NULL,
  `e_phno` bigint(30) DEFAULT NULL,
  `e_documents` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `education` */

/*Table structure for table `educationsupport` */

DROP TABLE IF EXISTS `educationsupport`;

CREATE TABLE `educationsupport` (
  `educationinstitute_id` int(11) NOT NULL AUTO_INCREMENT,
  `educationinstitutename` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `post` varchar(100) DEFAULT NULL,
  `pin` bigint(20) DEFAULT NULL,
  `contactno` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `document` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`educationinstitute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `educationsupport` */

insert  into `educationsupport`(`educationinstitute_id`,`educationinstitutename`,`place`,`post`,`pin`,`contactno`,`email`,`document`) values (2,'regioanl','regioanl','kannur',670000,9876543211,'regionak@gmail.com','WIN_20200710_16_04_34_Pro.jpg'),(3,'maxlore','kannur','kannur',670000,9876543211,'riss@gmail.com','<FileStorage: \'WIN_20200710_16_04_34_Pro.jpg\' (\'image/jpeg\')>');

/*Table structure for table `emergency_help` */

DROP TABLE IF EXISTS `emergency_help`;

CREATE TABLE `emergency_help` (
  `emid` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(54) DEFAULT NULL,
  `transgender_id` int(11) NOT NULL,
  `lattitude` double DEFAULT NULL,
  `longittude` double DEFAULT NULL,
  PRIMARY KEY (`emid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `emergency_help` */

insert  into `emergency_help`(`emid`,`date`,`transgender_id`,`lattitude`,`longittude`) values (1,'2022-11-03',14,11.868478,75.3631733),(2,'2022-11-21',14,11.8684349,75.3631749),(3,'2022-11-21',14,11.25,75.78),(4,'2022-11-21',14,11.25,75.78),(5,'2022-11-21',14,11.25,75.78),(6,'2022-11-21',14,11.25,75.78),(7,'2022-11-21',14,11.25,75.78),(8,'2022-11-21',14,11.25,75.78),(9,'2022-11-21',14,11.8684431,75.3631693),(10,'2022-11-21',14,11.8684431,75.3631693);

/*Table structure for table `event` */

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `e_id` int(11) NOT NULL AUTO_INCREMENT,
  `e_name` varchar(30) DEFAULT NULL,
  `e_description` varchar(50) DEFAULT NULL,
  `e_venue` varchar(30) DEFAULT NULL,
  `e_date` varchar(20) DEFAULT NULL,
  `e_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `event` */

/*Table structure for table `events` */

DROP TABLE IF EXISTS `events`;

CREATE TABLE `events` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `location_id` int(11) DEFAULT NULL,
  `eventname` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `venue` varchar(100) DEFAULT NULL,
  `date` varchar(54) DEFAULT NULL,
  `time` varchar(43) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `events` */

insert  into `events`(`event_id`,`location_id`,`eventname`,`description`,`venue`,`date`,`time`) values (1,2,'admin','dsa','sadsd','2022-11-02','16:29');

/*Table structure for table `health_tips` */

DROP TABLE IF EXISTS `health_tips`;

CREATE TABLE `health_tips` (
  `h_id` int(11) NOT NULL AUTO_INCREMENT,
  `h_tip` varchar(20) DEFAULT NULL,
  `h_description` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`h_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `health_tips` */

/*Table structure for table `healthtip` */

DROP TABLE IF EXISTS `healthtip`;

CREATE TABLE `healthtip` (
  `hid` int(11) NOT NULL AUTO_INCREMENT,
  `lid` int(11) DEFAULT NULL,
  `jobtype` varchar(100) DEFAULT NULL,
  `discription` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `healthtip` */

insert  into `healthtip`(`hid`,`lid`,`jobtype`,`discription`) values (1,6,'mentalhealth','fdfdf');

/*Table structure for table `human_rights` */

DROP TABLE IF EXISTS `human_rights`;

CREATE TABLE `human_rights` (
  `hr_id` int(11) NOT NULL AUTO_INCREMENT,
  `hr_human_rights` varchar(30) DEFAULT NULL,
  `hr_description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`hr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `human_rights` */

/*Table structure for table `humanrights` */

DROP TABLE IF EXISTS `humanrights`;

CREATE TABLE `humanrights` (
  `hr_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_id` int(11) DEFAULT NULL,
  `hr_human_rights` varchar(100) DEFAULT NULL,
  `hr_description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`hr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `humanrights` */

insert  into `humanrights`(`hr_id`,`l_id`,`hr_human_rights`,`hr_description`) values (1,10,'economic','dd'),(3,12,'economic','lklk');

/*Table structure for table `jobs` */

DROP TABLE IF EXISTS `jobs`;

CREATE TABLE `jobs` (
  `j_id` int(11) NOT NULL AUTO_INCREMENT,
  `j_type` varchar(30) DEFAULT NULL,
  `j_name` varchar(30) DEFAULT NULL,
  `j_place` varchar(30) DEFAULT NULL,
  `j_phno` varchar(30) DEFAULT NULL,
  `j_email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`j_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `jobs` */

/*Table structure for table `jobsupport` */

DROP TABLE IF EXISTS `jobsupport`;

CREATE TABLE `jobsupport` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `jobtype` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `post` varchar(100) DEFAULT NULL,
  `contactno` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `jobsupport` */

insert  into `jobsupport`(`job_id`,`jobtype`,`place`,`post`,`contactno`) values (1,'Full time','f','kannur',9876543210),(2,'Part time','p','kannur',9876543210);

/*Table structure for table `legal_assistant` */

DROP TABLE IF EXISTS `legal_assistant`;

CREATE TABLE `legal_assistant` (
  `la_id` int(11) NOT NULL AUTO_INCREMENT,
  `la_name` varchar(40) DEFAULT NULL,
  `la_gender` varchar(40) DEFAULT NULL,
  `la_hname` varchar(40) DEFAULT NULL,
  `la_place` varchar(40) DEFAULT NULL,
  `la_post` varchar(50) DEFAULT NULL,
  `la_pin` bigint(20) DEFAULT NULL,
  `la_contactno` varchar(30) DEFAULT NULL,
  `la_email` varchar(40) DEFAULT NULL,
  `document` varchar(2000) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`la_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `legal_assistant` */

/*Table structure for table `legalassistant` */

DROP TABLE IF EXISTS `legalassistant`;

CREATE TABLE `legalassistant` (
  `LEGALASSISTANT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN_ID` int(11) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `GENDER` varchar(100) DEFAULT NULL,
  `PLACE` varchar(100) DEFAULT NULL,
  `POST` varchar(100) DEFAULT NULL,
  `PIN` bigint(20) DEFAULT NULL,
  `CONTACTNO` bigint(20) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `DOCUMENT` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`LEGALASSISTANT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `legalassistant` */

insert  into `legalassistant`(`LEGALASSISTANT_ID`,`LOGIN_ID`,`NAME`,`GENDER`,`PLACE`,`POST`,`PIN`,`CONTACTNO`,`EMAIL`,`DOCUMENT`) values (1,10,'Orange nayak','male','kannur','Kannur',670000,9876543678,'hm@gmail.com','WIN_20200710_16_04_34_Pro.jpg'),(2,12,'ramu','male','kannur','Kannur',670000,9876543678,'hm@gmail.com','WIN_20200710_16_04_34_Pro.jpg');

/*Table structure for table `localcoordinator` */

DROP TABLE IF EXISTS `localcoordinator`;

CREATE TABLE `localcoordinator` (
  `lc_id` int(10) NOT NULL AUTO_INCREMENT,
  `lc_name` varchar(30) DEFAULT NULL,
  `lc_gender` varchar(30) DEFAULT NULL,
  `lc_hname` varchar(30) DEFAULT NULL,
  `lc_place` varchar(30) DEFAULT NULL,
  `lc_post` varchar(30) DEFAULT NULL,
  `lc_pin` int(30) DEFAULT NULL,
  `lc_phno` varchar(30) DEFAULT NULL,
  `lc_email` varchar(30) DEFAULT NULL,
  `lc_area` varchar(30) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`lc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `localcoordinator` */

/*Table structure for table `localcordinator` */

DROP TABLE IF EXISTS `localcordinator`;

CREATE TABLE `localcordinator` (
  `localcordinator_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `LID` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `place` varchar(100) NOT NULL,
  `post` varchar(100) NOT NULL,
  `pin` bigint(20) NOT NULL,
  `phone` bigint(20) NOT NULL,
  `areaassigned` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `qualification` varchar(100) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `housename` varchar(100) NOT NULL,
  PRIMARY KEY (`localcordinator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `localcordinator` */

insert  into `localcordinator`(`localcordinator_id`,`LID`,`name`,`place`,`post`,`pin`,`phone`,`areaassigned`,`email`,`qualification`,`gender`,`housename`) values (1,2,'local coordinator','kannur','kannur',670000,9876543211,'kannur','hm@gmail.com','sslc','Male','riis'),(2,4,'local coordinator ','kannur','kannur',670000,9876543211,'kannur','hm@gmail.com','sslc','Male','riis'),(3,5,'local coordinator ','kannur','kannur',670000,9876543211,'kannur','hm@gmail.com','sslc','Male','riis'),(4,17,'jjj','fff','ddd',678909,9876543210,'tttttt','hm@gmail.com','sslc','Male','kkk'),(5,18,'byyyy','xxxxxx','wwwww',678909,9876543678,'tttttt','hm@gmail.com','sslc','Male','xbbbb');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `LID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `TYPE` varchar(100) NOT NULL,
  PRIMARY KEY (`LID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`LID`,`USERNAME`,`PASSWORD`,`TYPE`) values (1,'admin','Admin@123','admin'),(2,'lc','Admin@123','localcordinator'),(4,'lc1','lc','rejected'),(5,'lc12','lc','localcordinator'),(6,'mo','Admin@123','medicalofficer'),(8,'mo1','m','medicalofficer'),(9,'mo2','m','medicalofficer'),(10,'la','l','legalassistant'),(12,'la1','l','legalassistant'),(13,'la2','l','legalassistant'),(14,'y','y','user'),(15,'u','u','rejected'),(17,'ummmm','Admin@123','pending'),(18,'b','Admin@123','pending');

/*Table structure for table `medical` */

DROP TABLE IF EXISTS `medical`;

CREATE TABLE `medical` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `m_name` varchar(30) DEFAULT NULL,
  `m_gender` varchar(30) DEFAULT NULL,
  `m_specialization` varchar(30) DEFAULT NULL,
  `m_hname` varchar(30) DEFAULT NULL,
  `m_place` varchar(30) DEFAULT NULL,
  `m_post` varchar(11) DEFAULT NULL,
  `m_pin` int(30) DEFAULT NULL,
  `m_contactno` varchar(40) DEFAULT NULL,
  `m_email` varchar(30) DEFAULT NULL,
  `m_hospitalname` varchar(30) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `medical` */

/*Table structure for table `medicalofficer` */

DROP TABLE IF EXISTS `medicalofficer`;

CREATE TABLE `medicalofficer` (
  `medicalofficer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `post` varchar(100) DEFAULT NULL,
  `pin` bigint(20) DEFAULT NULL,
  `contact no` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL,
  `specialization` varchar(100) DEFAULT NULL,
  `hospital` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`medicalofficer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `medicalofficer` */

insert  into `medicalofficer`(`medicalofficer_id`,`name`,`gender`,`place`,`post`,`pin`,`contact no`,`email`,`login_id`,`specialization`,`hospital`) values (1,'Medical officer a','female','kannur','kannur',670000,9876543678,'mo@gmail.com',6,'dermetology','akg'),(2,'Medical officer b','male','kannur','kannur',670000,9876543678,'mo@gmail.com',8,'gyno','akg');

/*Table structure for table `meeting` */

DROP TABLE IF EXISTS `meeting`;

CREATE TABLE `meeting` (
  `mt_id` int(11) NOT NULL AUTO_INCREMENT,
  `mt_meeting_time` varchar(10) DEFAULT NULL,
  `mt_place` varchar(30) DEFAULT NULL,
  `mt_description` varchar(50) DEFAULT NULL,
  `mt_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`mt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `meeting` */

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `meeting_id` int(11) NOT NULL AUTO_INCREMENT,
  `legal_id` int(11) DEFAULT NULL,
  `meeting_datetime` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`meeting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `notification` */

insert  into `notification`(`meeting_id`,`legal_id`,`meeting_datetime`,`place`,`description`) values (1,12,'11:45','kannur','aaaa'),(5,10,'12:22','kkj','jjj');

/*Table structure for table `registration` */

DROP TABLE IF EXISTS `registration`;

CREATE TABLE `registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `LID` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `post` varchar(100) DEFAULT NULL,
  `pin` int(11) DEFAULT NULL,
  `phn_no` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `registration` */

/*Table structure for table `report` */

DROP TABLE IF EXISTS `report`;

CREATE TABLE `report` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `leid` int(11) DEFAULT NULL,
  `date` varchar(54) DEFAULT NULL,
  `month` varchar(54) DEFAULT NULL,
  `report` varchar(54) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `report` */

/*Table structure for table `schedule` */

DROP TABLE IF EXISTS `schedule`;

CREATE TABLE `schedule` (
  `schedule_id` int(11) NOT NULL AUTO_INCREMENT,
  `legal_id` int(11) DEFAULT NULL,
  `date` varchar(54) DEFAULT NULL,
  `fromtime` varchar(54) DEFAULT NULL,
  `totime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `schedule` */

insert  into `schedule`(`schedule_id`,`legal_id`,`date`,`fromtime`,`totime`) values (1,10,'2022-11-02','12:45','12:45'),(3,12,'2022-11-11','10:46','13:45');

/*Table structure for table `transgender` */

DROP TABLE IF EXISTS `transgender`;

CREATE TABLE `transgender` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `lid` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `post` varchar(100) DEFAULT NULL,
  `pin` bigint(20) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `contact` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `transgender` */

insert  into `transgender`(`user_id`,`lid`,`name`,`place`,`post`,`pin`,`district`,`email`,`contact`) values (1,14,'gh','g','g',639852,'j','h@g.c',9632580963),(2,15,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;



transgenderappointments