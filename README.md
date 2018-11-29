# Equipo-03

### Generate executable jar
Ensure that `persistence.xml` is referencing RDS instance db and not `localhost:3306/`. RDS endpoint must be commented in the file.

To deploy a fat jar (jar with dependences) simply run in the project directory:
``` 
mvn clean compile assembly:single -Dmaven.test.skip=true -e
```

This will generate .jar called `something-jar-with-dependences.jar` in `/project/target/` location.
We can ensure it's correct functioning with: `java -jar TrabajoPractico-0.0.1-SNAPSHOT-jar-with-dependencies.jar`.
This should generate the spark app in `localhost:9000/`.

### Dump values to mysql RDS instance

#### Connect to mySql RDS instance
We use [AWS RDS](https://aws.amazon.com/es/rds/) for our mysql 5.7 instance in the cloud.
To connect to the instance through command line:
```
mysql -h equipo3.c8laxzmm3pst.us-east-2.rds.amazonaws.com -u root -p
```
It may seem insecure to have the 3306 mysql port public, but in this case, we shouldn't dedicate time for security vulnerabilities.

#### Import whole .sql database dump
First, generate a single file dump, save it as standard `.sql`.
Ensure you have access to the RDS instance by completing the previous step.
In the terminal run the following:
```
mysql -h equipo3.c8laxzmm3pst.us-east-2.rds.amazonaws.com -u root -p < dump.sql
``` 
It'll run for a while and terminal `next-line` will indicate that is terminated.

Ensure that the schema is created and check tables with `SHOW TABLES;`.

### Deploy and host the app
We use [AWS ElasticBeanstalk](https://aws.amazon.com/es/elasticbeanstalk/) to host the Spark server. As the spark server port is configured to be `9000` the url must append it at the end.
Elastic is already configured to have 9000 public port. So after deploying the previous generated .jar file, the app will be accesible through http://equipo3-app.us-east-2.elasticbeanstalk.com:9000/.