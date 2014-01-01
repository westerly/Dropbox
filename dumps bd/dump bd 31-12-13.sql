-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 01 Janvier 2014 à 13:39
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.12

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `dropbox`
--
CREATE DATABASE IF NOT EXISTS `dropbox` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `dropbox`;

-- --------------------------------------------------------

--
-- Structure de la table `files`
--

DROP TABLE IF EXISTS `files`;
CREATE TABLE IF NOT EXISTS `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `id_folder_parent` int(11) DEFAULT NULL,
  `name_space_parent` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_folder_parent` (`id_folder_parent`),
  KEY `name_space_parent` (`name_space_parent`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `files`
--

INSERT INTO `files` (`id`, `name`, `id_folder_parent`, `name_space_parent`) VALUES
(1, 'testFile', NULL, 'thespacenametest'),
(2, 'file', 2, NULL),
(3, 'FILE', NULL, 'thespacenametest');

-- --------------------------------------------------------

--
-- Structure de la table `file_tag`
--

DROP TABLE IF EXISTS `file_tag`;
CREATE TABLE IF NOT EXISTS `file_tag` (
  `IdFile` int(11) NOT NULL,
  `tagname` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`IdFile`,`tagname`),
  KEY `tagname` (`tagname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `folders`
--

DROP TABLE IF EXISTS `folders`;
CREATE TABLE IF NOT EXISTS `folders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `name_space_parent` varchar(30) DEFAULT NULL,
  `id_folder_parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name_space_parent` (`name_space_parent`),
  KEY `id_folder_parent` (`id_folder_parent`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

--
-- Contenu de la table `folders`
--

INSERT INTO `folders` (`id`, `name`, `name_space_parent`, `id_folder_parent`) VALUES
(1, 'folderTest', 'thespacenametest', NULL),
(2, 'folder', NULL, 1),
(11, 'test', 'thespacenametest', NULL),
(12, 'okld', NULL, 11),
(13, 'myfolder', NULL, 2),
(14, 'folder', NULL, 2),
(15, 'gngngdzd', 'thespacenametest', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `rights_read`
--

DROP TABLE IF EXISTS `rights_read`;
CREATE TABLE IF NOT EXISTS `rights_read` (
  `id_user` int(11) NOT NULL DEFAULT '0',
  `name_space` varchar(30) NOT NULL,
  PRIMARY KEY (`id_user`,`name_space`),
  KEY `name_space` (`name_space`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `spaces`
--

DROP TABLE IF EXISTS `spaces`;
CREATE TABLE IF NOT EXISTS `spaces` (
  `name` varchar(30) NOT NULL DEFAULT '',
  `owner` int(11) NOT NULL,
  PRIMARY KEY (`name`),
  KEY `owner` (`owner`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `spaces`
--

INSERT INTO `spaces` (`name`, `owner`) VALUES
('thespacenametest', 11);

-- --------------------------------------------------------

--
-- Structure de la table `tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE IF NOT EXISTS `tags` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`,`pwd`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `login`, `pwd`) VALUES
(15, 'login', '73f744fe95eaca16b7ca24558ee61983'),
(16, 'newlogin', '330cf47b6adba82f100bb1f833f8ef7b'),
(11, 'test', '098f6bcd4621d373cade4e832627b4f6'),
(14, 'testezfef', 'f472e934715eae41e439ef3818ef7a2b');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `files`
--
ALTER TABLE `files`
  ADD CONSTRAINT `files_ibfk_1` FOREIGN KEY (`id_folder_parent`) REFERENCES `folders` (`id`) ON DELETE CASCADE;

ALTER TABLE `files`
  ADD CONSTRAINT `files_ibfk_2` FOREIGN KEY (`name_space_parent`) REFERENCES `spaces` (`name`) ON DELETE CASCADE;
  

--
-- Contraintes pour la table `file_tag`
--
ALTER TABLE `file_tag`
  ADD CONSTRAINT `file_tag_ibfk_1` FOREIGN KEY (`IdFile`) REFERENCES `files` (`id`) ON DELETE CASCADE;

ALTER TABLE `file_tag`  
  ADD CONSTRAINT `file_tag_ibfk_2` FOREIGN KEY (`tagname`) REFERENCES `tags` (`name`) ON DELETE CASCADE;

--
-- Contraintes pour la table `folders`
--
ALTER TABLE `folders`
  ADD CONSTRAINT `files_fofk_1` FOREIGN KEY (`name_space_parent`) REFERENCES `spaces` (`name`) ON DELETE CASCADE;

ALTER TABLE `folders`
 ADD CONSTRAINT `files_fofk_2` FOREIGN KEY (`id_folder_parent`) REFERENCES `folders` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `rights_read`
--
ALTER TABLE `rights_read`
  ADD CONSTRAINT `rights_read_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE;

ALTER TABLE `rights_read`
  ADD CONSTRAINT `rights_read_ibfk_3` FOREIGN KEY (`name_space`) REFERENCES `spaces` (`name`) ON DELETE CASCADE;

--
-- Contraintes pour la table `spaces`
--
ALTER TABLE `spaces`
  ADD CONSTRAINT `spaces_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `users` (`id`) ON DELETE CASCADE;
  
  
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
