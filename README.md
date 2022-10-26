# spring-data-jpa
 
- Encryption with Jasypt
- <dependency>
  	<groupId>com.github.ulisesbocchio</groupId>
  	<artifactId>jasypt-spring-boot-starter</artifactId>
  	<version>3.0.3</version>
  </dependency>
  
  <plugin>
  <groupId>com.github.ulisesbocchio</groupId>
  <artifactId>jasypt-maven-plugin</artifactId>
  <version>3.0.3</version>
  </plugin>

For encryption:

mvn jasypt:encrypt-value -Djasypt.encryptor.password=svj -Djasypt.plugin.value=root

To decrypt:

mvn jasypt:decrypt-value -D jasypt.encryptor.password=svj -D jasypt.plugin.value=rrGSbhKBLTF
soYo3RdhrFxmh/oH7cjp2ECO7tYatFO5jUjbNJwjPiYAyeFynlFus

To be added as VM options in config:
-Djasypt.encryptor.password=svj

To update app.yml file with encrypted value without copying from CLI, surround the key with DEC() and run the following
mvn jasypt:encrypt -Djasypt.encryptor.password=svj -Djasypt.plugin.path="file:src/main/resources/application.yml"

With EncryptorConfig, we can generate encrypted keys, and that can be saved in the app.yml. This isnt recommended. An alternate would be to use vaults or cloud security