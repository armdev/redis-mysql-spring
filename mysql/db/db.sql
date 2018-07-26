DROP TABLE IF EXISTS `article`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `article` (
  `id` bigint(11) NOT NULL auto_increment,  
  `header` varchar(255) default NULL,
  `content` text default NULL,
  `post_date` datetime default NULL,  
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1160 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;
