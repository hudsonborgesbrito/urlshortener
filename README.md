<h1>URL Shortener</h1>
This project receives any valid url starting with http and creates a short code with 4 random characters (a-zA-Z0-9).

In case the 4 characters code already exists in the database it will retry with a 6 digit code and subsequent with a 8 digit code.

The possibilities of conflict are: 
- 4 characters code:          14 776 336
- 6 characters code:      56 800 235 584 
- 8 characters code: 218 340 105 584 896

** These values are calculated taking in consideration 26 characters lowercase (a-z), 26 uppercase (A-Z) and 10 numbers (0-9) with a total of 62 characters. 

<pre>
<h2>Requirements</h2>
- Java 21
- Wildfly 31.0.0
<h2>Installation Instructions</h2>
- Download the maven project
- Run "mvn clean install" at root folder
- Deploy the war generated file under ./target/urlshortener.war to wildfly 31.0.0.
- Access the application <b>http://localhost:8080/urlshortener</b>.
</pre>