
# Dine Ease

Restaurant Management System build to allow customers to book tables at a any branch of a restaurant. It enables owners to efficiently manage their restaurant operations, including menu updates, table bookings, and branch management.

## Set up data

Database setup is required prior to loading application, use the file `project-details/dine_ease_set_up.sql`
```
mysql> source path-to-set-up-file
```
You can also checkout the ER diagram for the database `project-details/dine_ease_er_diagram.png`
## Environment Variables

To run this project, you will need to update the following environment variables to your `application.properties` file


```bash
spring.datasource.url=jdbc:mysql://localhost:3306/dine_ease
spring.datasource.username=user_name
spring.datasource.password=user_password

jwt.token.secret.key=256-bit key 
#5 minutes
jwt.token.secret.validity=300
```

## Run Locally

Run following command from the root directory of the project to run the application.
```bash
  mvn spring-boot:run
```

Application will start running at port `8085`
## Documentation

#### Swagger
You can access Swagger API Documentation here:

```
http://localhost:8085/swagger-ui/index.html
```
#### Swagger
I have also attached postman collection export `project-details/dine_ease.postman_collection.json`  Follow the steps

```
Postman > import > drop the file
```

#### Implementation

    1. Login/Signup User 
        - Admin
        - Restaurant owner
        - Customer
        - JWT based Authentication + Authorization

    2. Restaurant Owner
        - Add branches
        - Add tables at each branch
        - Add Dishes with price
        - View booked tables by customer

    3. Customer 
        - Search restaurant by city
        - Search restaurant by name
        - Search restaurant by food
        - Book table at a restaurant date and start time
        - Cancel booking
        
    4. Admin
        - View Restaurant tables and bookings
        - View Restaurant foods
        - View Customer bookings
        - Add food categories
        - Add Cities
        - Add Dishes
        - Add table


#### Assumptions

    1. Each slot is for 1 hour at XX:00 to XX+1:00
    2. Can book for any day
    3. One owner per restaurant
    4. Same menu at each branch
    5. Restaurant has one branch in a city



#### Practices followed

    1. REST 
    2. Git commit
    3. SQL ng)
    4. Spring Data JPA 
    5. Spring Security 
    6. Authorization RBAC
    7. Authorization custom ABAC 
    8. Documentation
    9. Exception Handling
    10. custom Validation 
    


#### Learning:

    1. Normalization branches, menu, tables 
    2. With Spring Security permit /error page to avoid 403 for every error
    3. ControllerAdvice handles exception after filter, errors before filter to be handled by first filter and map to resolver, also handle RuntimeException
    4. JOIN FETCH to avoid N + 1
    5. Data JPA updates all fields by default fetch and update
    6. Role Based Access Control
    7. Adding @Transactional to update/delete methods only along with @Version for optimistic locking, as very less updates are expected. Not required for Create methods as DB handles duplicate creation based on unique constraints.
    8. Optimizing queries
        - JOIN FETCH
        - Fetch.LAZY
    9. Custom Annotation based validation
    10. Delete CASCADE 

            food -> dish -> menu
            restaurant -> branch   \
            city -> branch ---------> branch_tables -> bookings
            table_type ------------/
    11. Separate DTO for request with Validations
    12. Pick user from Authentication, do not pass Id
    13. Implement UserDetails with our entity for using Id
    14. Event Listeners to notify User on deletion of booking PostRemove 
    15. Attribute Based Access Control - PermissionEvaluator  DefaultEvaluator -> DenyAllPE Create Bean of DefaultMethodSecurityHandler and set PE to Custom PE ABAC on Restaurant, Branch, Branch table, Booking



#### Design changes

    1. Existing: branch tables (count), bookings ( Id)
    available = count - no. of bookings, concurrent bookings as booking is inserted, phantom read problem arises and can't be solved without Isolation.SERIALIZABLE which is in efficient



#### Solving Concurrent Booking:
	1. Context branch_tables(table_id, count)
				bookings(branch_tables_id, start, end)
	2. Should we change the schema? possibly
	3. Request(branch_table_id, start, end = start + 1 hr)
		To check Availability count(branch_table) - booked(branch_table, start_time)
		count - tables booked at given time
		
		Now we can apply pessimistic lock when findById(branch_table) it means no body will be able to make update to branch_table
		but we are not making any update anyway. Lets say they are not allowed to even read it, then
		Let's say there are 4 round tables, then when I'm booking 1 round table for 3pm to 4pm
		Then no body will be able to book the other table at same time or at any time.
		This is serious issue to concurrency and performance.
		If there is only one type of table at a branch then it will be serial booking in a way. 
		
	4. What can we do?
		1. Should we remove count? I think so
		2. We give each table a number table no. 1, 2, 3 and keep adding it? ok
		3. So it will be round table 1, round table 2, High table 1.....
		4. This will improve the other table lock issue
		
		1. Now we want to book a table at other time parallelly? Yes
	
	5. Changed model remove count : 
		1. available = booked(branch_table, start_time) ? boolean If not then we can book
		2. So two requests occur both read as available, then try to book, then how?
		3. What kind of lock should be applied
		   So here I will use version and all, and add unique constraint for (table_id & start) so that only one entry is
		   existing. So will use Optimistic locking
		4. But there will be one more field called status two choices (Either remove or mark status CANCELLED)
			going with remove so that, unique constraint works.
		



#### Concurrency:

[article 1](https://medium.com/@bubu.tripathy/managing-concurrent-database-updates-eaf2fe161c48)

[article 2](https://medium-parser-seven.vercel.app/?url=https://medium.com/@soyjuanmalopez/conquering-concurrency-in-spring-boot-strategies-and-solutions-152f41dd9005)


	Locks:
		When some PORTION of the database is LOCKED, any other users wishing to
		modify (or read) that data must WAIT
		
		Two locking strategies:
		1. Write lock & read lock
		2. Write lock & read based on versioning(by DB to ensure 
		from start to end of query same data)
		
		
		Pros & cons:
		1. Long wait times if there are many concurrent read and write requests
		2. Problematic if there are long-running queries while data is being modified
		
		
		Granularities:
		1. Table lock
		2. Page lock (same segment of memory 2 - 16KB) of a table
		3. Row lock
		
		Pros & cons:
		1. +Little bookkeeping - More wait time
		2,3. -More bookkeeping + Less wait time (if modifying different rows)
		
	Transactions:
		Start Transaction
		good -> commit
		bad -> rollback
		
		If server shut down before commit/ rollback
		first thing after coming on online is rollback 
		If shut down after commit(is in memory) but before flush(move to permanent storage)
		first thing after coming on online is reapply
		
		
		Handling by DB 2 ways:
		1. Transaction is active always, if it ends automatically start new
		2. You start manually
		
		Either way used you have to explicity end the transaction

	
	
	Implementation:
		
		1. Versioning / Optimistic lock
			@Version, retrieve - process - update, before updating check version is same
			 as retrieve. Detecting changes on entities by checking their version attribute
			 
			 Pros & Cons:
			 1. + Easy to implement
			 2. + Less Overhead on application performance 
			 3. + No locks, so no waiting
			 4. + No silent lost/ over ride
			 5. + No dead locks
			 6. - Many rollbacks
			 
			 Suitable:
			 1. Where less updates/ deletes
			 2. Where less conflict of updating same resource 
			 3. Where lock cannot be held
			 4. Where transaction are short running
			 
			 Optimistic Lock is available by default to version-ed entities, but can request explicitly as well
			 ObjectOptimisticLockingFailureException
			 
			 
			
		2. Transactional 
			It has Propagation, Isolation, readOnly, Timeout, rollBack
			imp: Propagation, isolation
			logging.level.org.springframework=trace to see the transactions/orm

			What I understood so far is, it maintains atomicity. But concurrency is not his business
			It will roll back if any error, so can use with optimistic Locking

			Roll back does not happen on checked Excep
			Only on unchecked

			Propagation:
				How transactions are created for methods called under a transaction
				How existing transactions are getting used


			Concurrency Problems + Concurrency solution - Isolation:
			https://jennyttt.medium.com/dirty-read-non-repeatable-read-and-phantom-read-bd75dd69d03a

			read uncommited:
				No locks

			read commited:


			Repeatable_read:
				The queried rows are locked, so other cannot update

			Serializable:
				Table is locked


		3. Pessimistc Locking
			SELECT * FROM product WHERE id = ? FOR UPDATE
			Once an entity is locked, other transactions trying to access or update the same entity will have to wait until the lock is released. This ensures that only one transaction can access the entity at a time, preventing concurrency issues.
			
			@Lock(LockModeType.PESSIMISTIC_WRITE)
    		MyEntity findById(Long id);

    		Pros & Cons:
    		1. + Strong way to prevent con issue
    		2. - Locks, Waiting so Performance, Deadlocks

		4. Application level locking
			1. synchronised(lock){
				...
				...
			}
			2. java.util.concurrent.atomic
			3. java.util.concurrent.locks.Lock


