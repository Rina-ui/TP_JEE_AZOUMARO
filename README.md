# TP Java EE – Gestion Bancaire « Ega »

La société bancaire **Ega** souhaite mettre en place une application permettant la **gestion de ses clients, de leurs comptes bancaires et des transactions associées**.  
Ce système reproduit le **fonctionnement réel d’une banque**, dans lequel la création et la gestion des comptes sont assurées par le **personnel bancaire**, tandis que les **clients** peuvent uniquement effectuer des opérations sur leurs comptes.

---

Gestion des utilisateurs et rôles

L’application distingue deux types d’utilisateurs :

1. Agent bancaire
- Crée et gère les clients
- Ouvre et supervise les comptes bancaires
- Consulte toutes les transactions et comptes

2. Client
- Consulte ses comptes et transactions
- Effectue :
  - Dépôt sur son compte
  - Retrait (si le solde le permet)
  - Virement vers un autre compte
- Télécharge ou imprime son relevé bancaire

---

Gestion des comptes

La banque propose **deux types de comptes** :
- **Compte épargne**
- **Compte courant**

Chaque compte est caractérisé par :
- **Numéro de compte unique** généré automatiquement avec **iban4j**
- **Type de compte**
- **Date de création**
- **Solde du compte** (initialisé à zéro)
- **Propriétaire** (client)

> L’IBAN garantit que le numéro de compte respecte les **standards bancaires internationaux** et peut être validé automatiquement.

---

Gestion des transactions

Les clients peuvent effectuer plusieurs transactions sur leurs comptes :
- **Dépôt (versement)**  
- **Retrait**, sous réserve du solde disponible  
- **Virement** d’un compte vers un autre compte  

Toutes les transactions sont **historisées**, permettant au client et à l’agent bancaire de suivre les opérations.


Choix techniques

Le projet est développé avec les technologies suivantes :

- **Backend** : Spring Boot  
- **API** : GraphQL pour la gestion des requêtes et mutations  
- **Base de données** : PostgreSQL  
- **Hébergement cloud** : Neon (PostgreSQL cloud)  
- **Numéro de compte** : iban4j pour la génération et la validation des IBAN  

Autres outils :
- Lombok pour réduire le code boilerplate
- Validation Java pour garantir l’intégrité des données
- Spring Security pour la gestion des rôles Client / Agent



