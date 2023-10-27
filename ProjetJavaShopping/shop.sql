-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 16 avr. 2023 à 20:44
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `shop`
--

-- --------------------------------------------------------

--
-- Structure de la table `login_admin`
--

DROP TABLE IF EXISTS `login_admin`;
CREATE TABLE IF NOT EXISTS `login_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `passwords` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `login_admin`
--

INSERT INTO `login_admin` (`id`, `nom`, `prenom`, `passwords`, `email`) VALUES
(1, 'admin', 'ad', 'pomme', 'admin@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `login_customer`
--

DROP TABLE IF EXISTS `login_customer`;
CREATE TABLE IF NOT EXISTS `login_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `passwords` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `login_customer`
--

INSERT INTO `login_customer` (`id`, `nom`, `prenom`, `passwords`, `email`) VALUES
(1, 'bon', 'jour', 'mdp', 'bonjour@gmail.com'),
(3, 'nou', 'nouveau', 'mdpp', 'nouveau@gmail.com'),
(5, 'test', 'te', 'aze', 'te@gmail.com'),
(6, 'pp', 'azee', 'd', 'ed@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

DROP TABLE IF EXISTS `produits`;
CREATE TABLE IF NOT EXISTS `produits` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prix` double NOT NULL,
  `quantite` int(255) NOT NULL,
  `description` text NOT NULL,
  `promotion` double NOT NULL,
  `id_categorie` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_categorie` (`id_categorie`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`id`, `nom`, `prix`, `quantite`, `description`, `promotion`, `id_categorie`) VALUES
(1, 'livre', 12.3, 40, 'description', 0, 1),
(2, 'Livre2', 15.6, 70, 'description livre 2 nouveau up description livre 2 nouveau up description livre 2 nouveau up', 0, 1),
(6, 'livre3', 32.6, 77, 'dessssss', 0, 2),
(7, 'livresss', 12.8, 43, 'ppppppp', 0, 2),
(8, 'produit', 12.3, 55, 'desss', 0, 3),
(9, 'geo', 34.22, 38, 'teee', 0, 1),
(10, 'histoire', 32.1, 87, 'desss', 12, 2),
(11, 'azee', 32.6, 68, 'azeeeee', 0, 3),
(12, 'ff', 33, 32, 'gff', 0, 1),
(13, 'ttess', 32, 44, 'fffd', 10, 2),
(14, 'vetement', 12.3, 40, 'dees', 0, 6),
(15, 'Livre', 12.8, 21, 'azea', 0, 4),
(16, 'Vinyle', 32.3, 77, 'qssd', 0, 7),
(17, 'Film', 32.3, 40, 'qcc', 0, 5);

-- --------------------------------------------------------

--
-- Structure de la table `produits_categorie`
--

DROP TABLE IF EXISTS `produits_categorie`;
CREATE TABLE IF NOT EXISTS `produits_categorie` (
  `id_categorie` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id_categorie`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produits_categorie`
--

INSERT INTO `produits_categorie` (`id_categorie`, `nom`) VALUES
(1, 'geographie'),
(2, 'histoire'),
(3, 'livre'),
(4, 'Livre'),
(5, 'Film'),
(6, 'Vetement'),
(7, 'Vinyle');

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE IF NOT EXISTS `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_produit` int(11) NOT NULL,
  `quantiteAchete` int(255) NOT NULL,
  `prix_total` int(11) NOT NULL,
  `id_customer` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_customer` (`id_customer`),
  KEY `id_produit` (`id_produit`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ticket`
--

INSERT INTO `ticket` (`id`, `id_produit`, `quantiteAchete`, `prix_total`, `id_customer`) VALUES
(1, 1, 10, 55, 1),
(2, 2, 21, 65, 5),
(4, 9, 32, 55, 3),
(5, 6, 32, 33, 6),
(6, 11, 12, 12, 6),
(7, 11, 12, 12, 6),
(8, 11, 12, 12, 6),
(9, 11, 12, 12, 6),
(10, 11, 12, 12, 6),
(11, 11, 12, 12, 6),
(12, 11, 12, 12, 6),
(13, 11, 12, 12, 6),
(14, 11, 12, 12, 6),
(15, 11, 12, 12, 6),
(16, 11, 12, 12, 6),
(17, 11, 12, 12, 6),
(18, 11, 12, 12, 6),
(19, 11, 12, 12, 6),
(20, 11, 12, 12, 6),
(21, 11, 12, 12, 6),
(22, 11, 12, 12, 6),
(23, 11, 12, 12, 6),
(24, 11, 12, 12, 6),
(25, 11, 12, 12, 6),
(26, 11, 12, 12, 6),
(27, 11, 12, 12, 6),
(28, 11, 12, 12, 6),
(29, 11, 12, 12, 6),
(30, 11, 12, 12, 6);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `produits`
--
ALTER TABLE `produits`
  ADD CONSTRAINT `fk_produits_produits_categorie` FOREIGN KEY (`id_categorie`) REFERENCES `produits_categorie` (`id_categorie`);

--
-- Contraintes pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `fk_tickets_login_customer` FOREIGN KEY (`id_customer`) REFERENCES `login_customer` (`id`),
  ADD CONSTRAINT `fk_tickets_produits` FOREIGN KEY (`id_produit`) REFERENCES `produits` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
