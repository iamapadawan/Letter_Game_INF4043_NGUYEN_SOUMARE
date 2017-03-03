##-----------------------------------------------------------------------------------------
#How this project was built
#
#To run the code from the .jar file
#"$ java -cp target/LetterGame-0.0.1-SNAPSHOT.jar fr.esiea.unique.binome.nguyensoumare.letterGameEngine.LetterGameEngine"
##------------------------------------------------------------------------------------------
Main characteristics 
- The project has been build 
- First of all we have built the factory of every business object then we decided to build
every service that made our project

We used maven to manage our project 
maven-plugin:generate with following options
- web as type of project
- com.inetpsa.pi201 as group id
- manageEntity-Web as Id

Remark : We use java 8 and UTF8 as encoding

we've separated unit tests and every class implementation into different source folders : 
To do that, we must complete the structure of project with unit test in our branch

Converted as WAR project (http://seedstack.org/getting-started/project-templates/web/)
-- adding a src/main/webapp folder to hold the document root
-- adding tag to the pom.xml:
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency> 