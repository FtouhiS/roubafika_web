<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230410224049 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE commande (id INT AUTO_INCREMENT NOT NULL, ligne_commande_id INT DEFAULT NULL, adresse VARCHAR(255) NOT NULL, numero_tel VARCHAR(255) NOT NULL, somme DOUBLE PRECISION NOT NULL, status VARCHAR(255) NOT NULL, INDEX IDX_6EEAA67DE10FEE63 (ligne_commande_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE ligne_commande (id INT AUTO_INCREMENT NOT NULL, prix DOUBLE PRECISION NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE ligne_commande_produit (ligne_commande_id INT NOT NULL, produit_id INT NOT NULL, INDEX IDX_5BAB3E38E10FEE63 (ligne_commande_id), INDEX IDX_5BAB3E38F347EFB (produit_id), PRIMARY KEY(ligne_commande_id, produit_id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67DE10FEE63 FOREIGN KEY (ligne_commande_id) REFERENCES ligne_commande (id)');
        $this->addSql('ALTER TABLE ligne_commande_produit ADD CONSTRAINT FK_5BAB3E38E10FEE63 FOREIGN KEY (ligne_commande_id) REFERENCES ligne_commande (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE ligne_commande_produit ADD CONSTRAINT FK_5BAB3E38F347EFB FOREIGN KEY (produit_id) REFERENCES product (id) ON DELETE CASCADE');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67DE10FEE63');
        $this->addSql('ALTER TABLE ligne_commande_produit DROP FOREIGN KEY FK_5BAB3E38E10FEE63');
        $this->addSql('ALTER TABLE ligne_commande_produit DROP FOREIGN KEY FK_5BAB3E38F347EFB');
        $this->addSql('DROP TABLE commande');
        $this->addSql('DROP TABLE ligne_commande');
        $this->addSql('DROP TABLE ligne_commande_produit');
    }
}
