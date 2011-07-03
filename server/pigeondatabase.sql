# MySQL-Front 5.0  (Build 1.0)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: www.dhiegoabrantes.com    Database: dhiegoab_pigeon
# ------------------------------------------------------
# Server version 5.0.91-community

#
# Table structure for table Credential
#

DROP TABLE IF EXISTS `Credential`;
CREATE TABLE `Credential` (
  `id` int(11) NOT NULL auto_increment,
  `social_id` varchar(255) default NULL,
  `socialNetwork_id` int(11) default NULL,
  `user_id` varchar(100) NOT NULL default '0',
  `allowPublish` bit(1) default b'1',
  PRIMARY KEY  (`id`),
  KEY `FK_CREDENTIAL_USER` (`user_id`),
  KEY `FK_CREDENTIAL_SOCIALNETWORK` (`socialNetwork_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
INSERT INTO `Credential` VALUES (2,'1010',1,'dhiegoabrantes@gmail.com',b'1');
INSERT INTO `Credential` VALUES (3,'10101010',1,'dhiegoabrantes@gmail.com',b'0');
/*!40000 ALTER TABLE `Credential` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table SocialNetwork
#

DROP TABLE IF EXISTS `SocialNetwork`;
CREATE TABLE `SocialNetwork` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `SocialNetwork` VALUES (1,'Dhiego');
/*!40000 ALTER TABLE `SocialNetwork` ENABLE KEYS */;
UNLOCK TABLES;

#
# Table structure for table User
#

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `email` varchar(50) NOT NULL default '',
  `name` varchar(255) NOT NULL default '',
  `score` bigint(10) NOT NULL default '0',
  PRIMARY KEY  (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `User` VALUES ('dhiegoabrantes@gmail.com','Dhiego Abrantes de Oliveira Martins',5);
INSERT INTO `User` VALUES ('jamilsonbatista@gmail.com','Jamilson Batista',12);
INSERT INTO `User` VALUES ('raonikulesza@gmail.com','Raoni Kulesza',10);
INSERT INTO `User` VALUES ('teste2@teste.com','Teste 2',80);
INSERT INTO `User` VALUES ('teste3@teste.com','Teste 3',2);
INSERT INTO `User` VALUES ('teste@teste.com','Teste 1',100);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

#
#  Foreign keys for table Credential
#

ALTER TABLE `Credential`
ADD CONSTRAINT `FK_CREDENTIAL_SOCIALNETWORK` FOREIGN KEY (`socialNetwork_id`) REFERENCES `SocialNetwork` (`id`),
  ADD CONSTRAINT `FK_CREDENTIAL_USER` FOREIGN KEY (`user_id`) REFERENCES `User` (`email`);


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
