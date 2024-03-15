# TRANSACTIONS-APP

# Money transferring Spring Boot App (how transaction management work) 

### Base components:
* controller
* service
* dao
* model
* h2

### Intro:
* TRANSACTION - number of actions that fail as group, or complete entirely as a group;
in case of fail (rollback), if all actions is success (commit); single unit of work; 
* ACID:
  * #### Atomicity - each transaction is considered as one unit (all done or does not happen at all);
  * #### Consistency - guarantees changes consistent with database constraints (if the data gets illegal state, the whole transaction fails);
  * #### Isolation - any data being used during one transaction cannot be used by another until the first transaction is complete;
  * #### Durability - ensures that changes made to the database (transactions) that are successfully committed will survive permanently, even in the case of system failures;
  
## Two types of transactions:
* Global (multiple resources manage the transaction);
* Local (one resource manage the transaction);

### SPRING FRAMEWORK FOR TRANSACTION MANAGEMENT:
* Declarative: 
  * Manage transactions via configuration.
  * Separate transaction logic from business logic.
  * Easy to maintain.
  * Preferred when a lot of transaction logic.
* Programmatic:
  * Explicitly coded transaction management.
  * Manage transactions via code.
  * Useful for minimal transaction logic.
  * Flexible but difficult to maintain.
  * Couples transaction and business logic. 

#### programmatic transaction use two implementations:
* Platform transaction manager (transactions across Hibernate, JDBC, JPA, JMS, etc.); example:
  (@Autowired
  private PlatformTransactionManager transactionManager;)
* Transaction template (Similar to Spring templates like JdbcTemplate and other available templates)


