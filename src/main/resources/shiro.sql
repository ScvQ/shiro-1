-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2014-09-24 09:16:02
-- 服务器版本： 5.5.39
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `shiro`
--

-- --------------------------------------------------------

--
-- 表的结构 `alliance`
--

CREATE TABLE IF NOT EXISTS `alliance` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `alliance`
--

INSERT INTO `alliance` (`id`, `name`) VALUES
(1, 'alian'),
(2, 'asan');

-- --------------------------------------------------------

--
-- 表的结构 `alliance_member`
--

CREATE TABLE IF NOT EXISTS `alliance_member` (
`id` int(11) NOT NULL,
  `alliance_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `privileges` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `alliance_member`
--

INSERT INTO `alliance_member` (`id`, `alliance_id`, `user_id`, `group_id`, `privileges`) VALUES
(1, 1, 1, 1, '1'),
(2, 2, 2, 1, '2');

-- --------------------------------------------------------

--
-- 表的结构 `permissions`
--

CREATE TABLE IF NOT EXISTS `permissions` (
`id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET latin1 NOT NULL,
  `permission` varchar(255) CHARACTER SET latin1 NOT NULL,
  `specialID` int(11) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- 转存表中的数据 `permissions`
--

INSERT INTO `permissions` (`id`, `name`, `permission`, `specialID`, `type`) VALUES
(1, 'delete all\r\n', 'alliance:announcement:delete:8', 0, 0),
(2, 'delete one', 'alliance:announcement:delete:111', 0, 0),
(3, 'chat to someone', 'alliance:guest:chat:111', 0, 0),
(4, 'chat to guest', 'alliance:guest:chat', 0, 0),
(5, 'view goverman', 'alliance:goverman:view', 0, 0),
(6, 'view guest', 'alliance:guest:view', 0, 0),
(7, 'chat to gover', 'alliance:gover:chat', 0, 0),
(8, 'delete some goverman', 'alliance:goverman:delete:111', 0, 0),
(9, 'delete guest', 'alliance:guest:delete', 0, 0),
(10, 'view discussionsGroup', 'alliance:discussionsGroup:view', 0, 0),
(11, 'delete discussionsGroup', 'alliance:discussionGroup:delete', 0, 0),
(12, 'delete goverman', 'alliance:goverman:delete', 0, 0);

-- --------------------------------------------------------

--
-- 表的结构 `permissions_group`
--

CREATE TABLE IF NOT EXISTS `permissions_group` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `permissionsGroup` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `permissions_group`
--

INSERT INTO `permissions_group` (`id`, `name`, `permissionsGroup`) VALUES
(1, 'guest', '5,7,10'),
(2, 'goverman', '1,4,5,6,7,9,10,11');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `locked` int(11) NOT NULL,
  `salt` varchar(255) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `locked`, `salt`) VALUES
(1, 'zp', 'fe8f1d68a2b4c48ad279d2530b658ca6', 0, '7047ddf2238bd167410b24710389c105'),
(2, 'admin', '25c2ee685381e11fcc56cd98f998792c', 0, 'eefeeb29fb6d47f73ae8a49215feda14');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alliance`
--
ALTER TABLE `alliance`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `alliance_member`
--
ALTER TABLE `alliance_member`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `permissions_group`
--
ALTER TABLE `permissions_group`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alliance`
--
ALTER TABLE `alliance`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `alliance_member`
--
ALTER TABLE `alliance_member`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `permissions`
--
ALTER TABLE `permissions`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `permissions_group`
--
ALTER TABLE `permissions_group`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
