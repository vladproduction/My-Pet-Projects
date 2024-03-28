# Propagation (распространение, то как транзакция распространяется среди классов):
#### 1)REQUIRED
* дефолтный, если метод НЕ был вызван в рамках какой-то транзакции, то в этом методе начинается новая транзакция;
* если метод БЫЛ вызван в рамках какой-то транзакции, то он к этой транзакции присоединится;
* Ensures that the method executes within a transaction. If there's no active transaction, it throws an exception. 
This is often used for critical operations that require atomicity (all or nothing).
#### 2)REQUIRES_NEW
* всегда создает новую транзакцию, в не зависимости от того состоит в другой транзакции или нет;
* Starts a new transaction regardless of any existing transaction. 
This can be useful for isolating specific operations within a larger transaction flow.
#### 3)MANDATORY
* всегда должен выполнятся в рамках транзакции;
* Similar to REQUIRED, but throws an exception if there's no existing transaction. 
This ensures the method can only participate in an already ongoing transaction.
#### 4)NEVER
* противоположность mandatory, никогда не должен вызываться;
* Throws an exception if there's an existing transaction. Ensures the method always runs outside any transaction context.
#### 5)SUPPORTED
* если запущен в рамках транзакции, то вызывается, если нет то, новая транзакция на этой базе не начнется;
* The method participates in an existing transaction if one exists. Otherwise, it executes non-transactional. 
This is useful for methods that can work with or without a transaction.
#### 6)NOT_SUPPORTED
* если запущен в рамках транзакции, то он эту транзакцию приостанавливает и выполняет свой метод (но не в транзакции);
* Suspends any existing transaction and executes the method non-transactional. 
Any changes made won't be rolled back even if other parts of the transaction fail.
#### 7)NESTED
* Starts a nested transaction within an existing transaction. This allows for hierarchical transaction management.

### details of transaction scenario context (@BeanA => @BeanB):
| REQUIRED | details       | @BeanA                                 | @BeanB                                               |
|----------|---------------|----------------------------------------|------------------------------------------------------|
| ==>      | REQUIRED      | start new T or participate T if exist  | Requires a surrounding transaction to participate in |
| ==>      | REQUIRED_NEW  | start new T or participate T if exist  | starting a new Tran Y independently of @BeanA        |
| ==>      | MANDATORY     | start new T or participate T if exist  | participate in exist or throw exception              |
| ==>      | NEVER         | start new T or participate T if exist  | exception or execute non-transactional               |
| ==>      | SUPPORTED     | start new T or participate T if exist  | participates in exist or executes non-transactional  |
| ==>      | NOT_SUPPORTED | start new T or participate T if exist  | suspend any exist or executes non-transactional      |
| ==>      | NESTED        | start new T or participate T if exist  | create new nested Tran Y withing context of Tran X   |

| REQUIRED_NEW  | details       | @BeanA                                      | @BeanB                                                      |
|---------------|---------------|---------------------------------------------|-------------------------------------------------------------|
| ==>           | REQUIRED      | suspend any exist, start new T independent  | beside T participate or throw an exception                  |
| ==>           | REQUIRED_NEW  | suspend any exist, start new T independent  | starting a new Tran Y independently of @BeanA               |
| ==>           | MANDATORY     | suspend any exist, start new T independent  | beside T participate or throw an exception                  |
| ==>           | NEVER         | suspend any exist, start new T independent  | Throws an exception if a surrounding transaction exists     |
| ==>           | SUPPORTED     | suspend any exist, start new T independent  | beside T participate in exist or executes non-transactional |
| ==>           | NOT_SUPPORTED | suspend any exist, start new T independent  | executes outside any transaction context                    |
| ==>           | NESTED        | suspend any exist, start new T independent  | create new nested Tran Y withing context of Tran T          |

| MANDATORY  | details       | @BeanA                                                          | @BeanB                                                                 |
|------------|---------------|-----------------------------------------------------------------|------------------------------------------------------------------------|
| ==>        | REQUIRED      | requires a surrounding Tran T or Exception                      | Requires a surrounding or exception if no Tran T  exists               |
| ==>        | REQUIRED_NEW  | requires (participate if any) a surrounding Tran T or Exception | start new Tran Y independently (can lead data consistency)             |
| ==>        | MANDATORY     | requires (participate if any) a surrounding Tran T or Exception | Also requires a surrounding transaction or exception                   |
| ==>        | NEVER         | requires a surrounding Tran T or Exception                      | Throws an exception if a surrounding transaction exists                |
| ==>        | SUPPORTED     | requires a surrounding Tran T or Exception                      | participate in exist T (commit/rollback) or executes non-transactional |
| ==>        | NOT_SUPPORTED | requires a surrounding Tran T or Exception                      | suspend any transactions and executes @BeanB non-transactional         |
| ==>        | NESTED        | requires(participate if any) a surrounding Tran T or Exception  | create new nested Tran Y withing context of Tran T or Exception        |

| NEVER  | details       | @BeanA                                             | @BeanB                                                                 |
|--------|---------------|----------------------------------------------------|------------------------------------------------------------------------|
| ==>    | REQUIRED      | never participates and executes non-transactional  | exception or require surround if @BeanA is not NEVER                   |
| ==>    | REQUIRED_NEW  | never participates and executes non-transactional  | start new Tran Y independently even if a surrounding exists            |
| ==>    | MANDATORY     | never participates and executes non-transactional  | exception or require surround if @BeanA is not NEVER                   |
| ==>    | NEVER         | never participates and executes non-transactional  | executes non-transactional                                             |
| ==>    | SUPPORTED     | never participates and executes non-transactional  | participate in exist T (commit/rollback) or executes non-transactional |
| ==>    | NOT_SUPPORTED | never participates and executes non-transactional  | suspend any transactions and executes non-transactional                |
| ==>    | NESTED        | never participates and executes non-transactional  | create new nested Tran Y withing context of non @BeanA or Exception    |

| SUPPORTED  | details       | @BeanA                                                      | @BeanB                                                                  |
|------------|---------------|-------------------------------------------------------------|-------------------------------------------------------------------------|
| ==>        | REQUIRED      | if another exist participate or executes non-transactional  | participate in exist T or exception                                     |
| ==>        | REQUIRED_NEW  | if another exist participate or executes non-transactional  | start new Tran Y independently even if a surrounding exists             |
| ==>        | MANDATORY     | if another exist participate or executes non-transactional  | exception or require surround transaction to participate                |
| ==>        | NEVER         | if another exist participate or executes non-transactional  | executes non-transactional                                              |
| ==>        | SUPPORTED     | if another exist participate or executes non-transactional  | participate in exist T (commit/rollback) or executes non-transactional  |
| ==>        | NOT_SUPPORTED | if another exist participate or executes non-transactional  | suspend any transactions and executes non-transactional                 |
| ==>        | NESTED        | if another exist participate or executes non-transactional  | create new nested Tran Y withing context of surrounding or Exception    |

| NOT_SUPPORTED | details       | @BeanA                                                     | @BeanB                                                                             |
|---------------|---------------|------------------------------------------------------------|------------------------------------------------------------------------------------|
| ==>           | REQUIRED      | never participates in a transaction it executes outside    | Requires a surrounding or exception                                                |
| ==>           | REQUIRED_NEW  | never participates in a transaction it executes outside    | start new Tran Y independently even if a surrounding exists                        |
| ==>           | MANDATORY     | never participates in a transaction it executes outside    | exception or require surround transaction to participate                           |
| ==>           | NEVER         | never participates in a transaction it executes outside    | executes outside of any transactional context                                      |
| ==>           | SUPPORTED     | never participates in a transaction it executes outside    | participate in exist or executes non-transactional                                 |
| ==>           | NOT_SUPPORTED | never participates in a transaction it executes outside    | suspend any transactions and executes non-transactional                            |
| ==>           | NESTED        | never participates in a transaction it executes outside    | fundamental mismatch, @BeanA's isolation and @BeanB's dependency on a surrounding  |

| NESTED  | details       | @BeanA                                                 | @BeanB                                                                                                 |
|---------|---------------|--------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| ==>     | REQUIRED      | Requires a surrounding transaction to create its own   | Requires a surrounding to participate it or exception                                                  |
| ==>     | REQUIRED_NEW  | Requires a surrounding transaction to create its own   | start new Tran Y independently even if a surrounding exists                                            |
| ==>     | MANDATORY     | Requires a surrounding transaction to create its own   | exception or require surround beside T transaction to participate (another level)                      |
| ==>     | NEVER         | Requires a surrounding transaction to create its own   | executes outside of any transactional context                                                          |
| ==>     | SUPPORTED     | Requires a surrounding transaction to create its own   | participate in that exist or executes non-transactional                                                |
| ==>     | NOT_SUPPORTED | Requires a surrounding transaction to create its own   | suspend any transactions and executes non-transactional                                                |
| ==>     | NESTED        | Requires a surrounding transaction to create its own   | if exist in context T creates own new (deeper nesting level) or exception due to missing requirements  |


