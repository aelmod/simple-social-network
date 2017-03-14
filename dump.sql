CREATE TABLE users (
  id        INT          NOT NULL AUTO_INCREMENT,
  name      VARCHAR(255) NOT NULL,
  nickname  VARCHAR(255) NOT NULL,
  password  VARCHAR(255) NOT NULL,
  birthday  TIMESTAMP,
  email     VARCHAR(255),
  phone     VARCHAR(13),
  countryId INTEGER,
  cityId    INTEGER,
  address   VARCHAR(255),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY (`nickname`),
  CONSTRAINT `FK_countryId` FOREIGN KEY (`countryId`) REFERENCES countries (`id`),
  CONSTRAINT `FK_cityId` FOREIGN KEY (`cityId`) REFERENCES cities (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE roles (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  type        VARCHAR(255),
  desctiption VARCHAR(255)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE countries (
  id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE cities (
  id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE pictures (
  id   INT NOT NULL AUTO_INCREMENT,
  #   userId  INT,
  #   albumId INT,
  path VARCHAR(255),
  #   metadata VARCHAR(255),
  PRIMARY KEY (`id`) USING BTREE
  #   CONSTRAINT `FK_userId` FOREIGN KEY (`userId`) REFERENCES user (`id`),
  #   CONSTRAINT `FK_albumId` FOREIGN KEY (`albumId`) REFERENCES album (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE albums (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255),
  description VARCHAR(255),
  isPrivate   TINYINT
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE picture_album_map (
  pictureId INT,
  albumId   INT,
  CONSTRAINT `FK_pictureId` FOREIGN KEY (`pictureId`) REFERENCES pictures (`id`),
  CONSTRAINT `FK_albumId` FOREIGN KEY (`albumId`) REFERENCES albums (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO roles (type, desctiption) VALUES ('adm', 'odmen');
INSERT INTO countries (name) VALUE ('degan');
INSERT INTO cities (name) VALUE ('eblan');
INSERT INTO users
(name, nickname, password, birthday, email, phone, countryId, cityId, address, roleId)
VALUES ('igor', 'yamudak', 1234, '2017-03-03 18:56:55', 'ibitemenya@vjopu.nah', 0, 1, 1, 'musorka pod mostm', 1);

