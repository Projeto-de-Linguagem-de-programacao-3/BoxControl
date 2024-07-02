-- MySQL Workbench Synchronization
-- Generated: 2024-06-29 12:54
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Jhon

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `mercado` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `mercado`.`Cliente` (
  `idCliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `CPF` VARCHAR(14) NOT NULL,
  `RG` VARCHAR(13) NOT NULL,
  `DataNascimento` DATE NOT NULL,
  `LimiteCredito` DECIMAL NOT NULL,
  `Estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mercado`.`Produto` (
  `idProduto` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `tipo` VARCHAR(100) NOT NULL,
  `precoCompra` DECIMAL NOT NULL,
  `precoVenda` DECIMAL NOT NULL,
  `fabricante` VARCHAR(100) NOT NULL,
  `validade` DATE NOT NULL,
  `quantidadeEstoque` INT(11) NOT NULL,
  PRIMARY KEY (`idProduto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mercado`.`PedidoProduto` (
  `idPedidoProduto` INT(11) NOT NULL AUTO_INCREMENT,
  `Produto_idProduto` INT(11) NOT NULL,
  `precoCompra` DECIMAL NOT NULL,
  `fabricante` VARCHAR(100) NOT NULL,
  `validade` DATE NOT NULL,
  `quantidade` INT(11) NOT NULL,
  PRIMARY KEY (`idPedidoProduto`),
  INDEX `fk_PedidoProduto_Produto_idx` (`Produto_idProduto` ASC) VISIBLE,
  CONSTRAINT `fk_PedidoProduto_Produto`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES `mercado`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mercado`.`Venda` (
  `idVenda` INT(11) NOT NULL AUTO_INCREMENT,
  `Cliente_idCliente` INT(11) NOT NULL,
  `formaDePagamento` VARCHAR(100) NOT NULL,
  `ValorTotal` DECIMAL NOT NULL,
  PRIMARY KEY (`idVenda`),
  INDEX `fk_Venda_Cliente1_idx` (`Cliente_idCliente` ASC) VISIBLE,
  CONSTRAINT `fk_Venda_Cliente1`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `mercado`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mercado`.`ItemVenda` (
  `Produto_idProduto` INT(11) NOT NULL,
  `Venda_idVenda` INT(11) NOT NULL,
  `Quantidade` INT(11) NOT NULL,
  PRIMARY KEY (`Produto_idProduto`, `Venda_idVenda`),
  INDEX `fk_Produto_has_Venda_Venda1_idx` (`Venda_idVenda` ASC) VISIBLE,
  INDEX `fk_Produto_has_Venda_Produto1_idx` (`Produto_idProduto` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_has_Venda_Produto1`
    FOREIGN KEY (`Produto_idProduto`)
    REFERENCES `mercado`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_has_Venda_Venda1`
    FOREIGN KEY (`Venda_idVenda`)
    REFERENCES `mercado`.`Venda` (`idVenda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `mercado`.`Balanco` (
  `idBalanco` INT(11) NOT NULL,
  `valorEstoque` DECIMAL NOT NULL,
  `valorVendas` DECIMAL NOT NULL,
  `lucro` DECIMAL NOT NULL,
  `numeroProdutos` INT(11) NOT NULL,
  `numeroClientes` INT(11) NOT NULL,
  `numeroVendas` INT(11) NOT NULL,
  `periodoBalanco` VARCHAR(100) NOT NULL,
  `faturamentoBruto` DECIMAL NOT NULL,
  `faturamentoLiquido` DECIMAL NOT NULL,
  PRIMARY KEY (`idBalanco`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
