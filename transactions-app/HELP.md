# TRANSACTIONS-APP

# Money transferring Spring Boot App (how transaction management work) 

### Base components:
* controller (restApi, propagation)
* service (accountService, implementations)
* dao (accountDao --> JpaRepo)
* model (account)
* transfer (transferModel; for transaction)
* transactional_services (splitting transfer for deposit and withdraw as services)
* h2 (embedded db to vision transactions action)
* application.properties (settings for application and h2)

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

## ----------------------------actions----------------------------
#### 1) declarative: public class AccountServiceDecImpl
- @Transactional annotation for class defines as one unit for transfer method;
- here we split logic by deposit and withdraw as private methods for transaction;

#### 2) programmatic: public class AccountServicePlatformTMImpl
- We're injecting PlatformTransactionManager for TransactionStatus;
- TransactionStatus tracks the state of the transaction and allows methods like commit and rollback;
- If both withdraw and deposit succeed, this line commits the transaction, making the changes to both accounts permanent;
- Depending on the complexity of the transaction logic, need to consider advanced transaction management features
  like isolation levels and propagation behavior;
- A new instance of TransactionDefinition, which specifies the properties of the transaction 
  (isolation level, propagation behavior, etc.); DefaultTransactionDefinition is used, which provides the default settings;

#### 3) programmatic: public class AccountServiceTransTemplateImpl
- We're injecting TransactionTemplate for specific settings in our transaction;
- transactionTemplate.execute to execute the transfer logic, withdraw and deposit actions in a try-catch block;
- @Transactional annotation we set for method transfer; allow to set for all class(AccountServiceTransTemplateImpl) as well;

#### 4) programmatic: public class AccountServiceImplPropagation
- approach is based on splitting our transfer logic on two different services (DepositService and WithdrawService)
- implemented classes has it`s oun personal propagation behaviours;
- We`re injecting this services into AccountServiceImplPropagation and set for the class @Transactional
- so it`s possible now to adjust each part of transaction independently in consider propagation behaviour;

