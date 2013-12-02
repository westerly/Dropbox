-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Lun 02 Décembre 2013 à 16:23
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.3.13

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `dropbox`
--

-- --------------------------------------------------------

--
-- Structure de la table `files`
--

DROP TABLE IF EXISTS `files`;
CREATE TABLE IF NOT EXISTS `files` (
  `name` varchar(100) NOT NULL,
  `id_space` int(11) NOT NULL,
  PRIMARY KEY (`name`),
  KEY `id_space` (`id_space`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `file_tag`
--

DROP TABLE IF EXISTS `file_tag`;
CREATE TABLE IF NOT EXISTS `file_tag` (
  `filename` varchar(100) NOT NULL DEFAULT '',
  `tagname` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`filename`,`tagname`),
  KEY `tagname` (`tagname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `rights_read`
--

DROP TABLE IF EXISTS `rights_read`;
CREATE TABLE IF NOT EXISTS `rights_read` (
  `id_user` int(11) NOT NULL DEFAULT '0',
  `id_space` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_user`,`id_space`),
  KEY `id_space` (`id_space`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `spaces`
--

DROP TABLE IF EXISTS `spaces`;
CREATE TABLE IF NOT EXISTS `spaces` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `size` float NOT NULL,
  `owner` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `owner` (`owner`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `login`, `pwd`) VALUES(12, 'login', 'd56b699830e77ba53855679cb1d252da');
INSERT INTO `users` (`id`, `login`, `pwd`) VALUES(11, 'test', '098f6bcd4621d373cade4e832627b4f6');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `files`
--
ALTER TABLE `files`
  ADD CONSTRAINT `files_ibfk_1` FOREIGN KEY (`id_space`) REFERENCES `spaces` (`id`);

--
-- Contraintes pour la table `file_tag`
--
ALTER TABLE `file_tag`
  ADD CONSTRAINT `file_tag_ibfk_1` FOREIGN KEY (`filename`) REFERENCES `files` (`name`),
  ADD CONSTRAINT `file_tag_ibfk_2` FOREIGN KEY (`tagname`) REFERENCES `tags` (`name`);

--
-- Contraintes pour la table `rights_read`
--
ALTER TABLE `rights_read`
  ADD CONSTRAINT `rights_read_ibfk_3` FOREIGN KEY (`id_space`) REFERENCES `spaces` (`id`),
  ADD CONSTRAINT `rights_read_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `spaces`
--
ALTER TABLE `spaces`
  ADD CONSTRAINT `spaces_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `users` (`id`);
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
