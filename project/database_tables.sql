CREATE TABLE `funcionarios` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `data_nascimento` DATE,
  `contacto` INT,
  `titulo` VARCHAR(255)
);

CREATE TABLE `edificios` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `horario_abertura` DATE,
  `horario_fecho` DATE,
  `morada` VARCHAR(255),
  `valor_base` FLOAT,
  `id_funcionario` INT
);

CREATE TABLE `faccoes` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `id_edificios` INT,
  `valor_base` FLOAT,
  `permilagem` FLOAT,
  `nome_proprietario` VARCHAR(255),
  `identificador` VARCHAR(255),
  `piso` VARCHAR(255),
  `contacto` INT
);

CREATE TABLE `despesas` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `id_edificios` INT,
  `valor` FLOAT,
  `description` VARCHAR(255),
  `type` TINYINT(1)
);

CREATE TABLE `valencias` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `id_edificios` INT,
  `nome` VARCHAR(255),
  `custo_anual` FLOAT,
  `tipo` VARCHAR(255)
);

CREATE TABLE `log_management_edificio` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `data_inicio` DATE,
  `data_fim` DATE,
  `id_edificio` INT,
  `id_funcionario` INT
);

ALTER TABLE `edificios` ADD FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id`);

ALTER TABLE `faccoes` ADD FOREIGN KEY (`id_edificios`) REFERENCES `edificios` (`id`);

ALTER TABLE `despesas` ADD FOREIGN KEY (`id_edificios`) REFERENCES `edificios` (`id`);

ALTER TABLE `valencias` ADD FOREIGN KEY (`id_edificios`) REFERENCES `edificios` (`id`);

ALTER TABLE `log_management_edificio` ADD FOREIGN KEY (`id_edificio`) REFERENCES `edificios` (`id`);

ALTER TABLE `log_management_edificio` ADD FOREIGN KEY (`id_funcionario`) REFERENCES `funcionarios` (`id`);
