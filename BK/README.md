# Setting up Instructions

## Prerequisites
* Java 1.7
* Maven (mvn command on your path)
* Mysql server installed (and mysql command on your path)

##Steps

1. Navigate to src/main/database/scripts and run in the given order the scripts.
    you can import them using command line or [source (mysql command)](http://stackoverflow.com/questions/5152921/import-sql-file-from-mysql-console)
2. Navigate to src/main/config/
3. In there you will find at least 3 folders (dev, qa and prod). Each of one represents the environment configuration.

**Select your system:**

** PC:**

4. Select the correct environment folder and copy the files inside to a destination folder, for example: `c:\appl\mygarage`
5. Using a Console/terminal navigate to the project root (where the pom.xml is located)
6. Run: "`mvn spring-boot:run -Dspring.config.location=c:\appl\mygarage\ -Dspring.profiles.active=dev`" Use the folder choose in Step 4 and the correct environment (dev, qa or prod)

** MAC:**

4. Set environment variables into your profile (.bash_profile): 

```
export spring_profiles_active=dev
export spring_config_location=/Users/**/Projects/V-GAR/BK/src/main/config/dev/
```
5. Run: mvn spring-boot:run



To check if it works, try to browser this url: [http://127.0.0.1:8080](http://127.0.0.1:8080)

