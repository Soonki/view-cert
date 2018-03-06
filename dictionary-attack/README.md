## Instruction
#### Build
- mvnw -DskipTests clean package
#### Compile and Run
- mvnw -q clean compile exec:java -Dexec.mainClass=com.demo.Main
#### Unit tests
- mvnw clean test
#### Input files
- **src\main\resources\sample-users.txt**: Login ID and SHA-1 encrypted password files
  - user1 e0c9035898dd52fc65c41454cec9c4d2611bfb37
  - user2 9a900f538965a426994e1e90600920aff0b4e8d2
  - user3 aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d
  - user4 secret4
  - user5 secret5
  - user6 secret6
  - user7 secret7
  - user8 secret8
  - user9 secret9
  - user10 secret10
  - Note: user1's password is 'aa', user2's password is 'bb', user3's password is 'hello', user4 to user10 are not valid SHA-1 hashes represending passwords that are not discoverable).
- **src\main\resources\sample-english-dictionary.txt**: English dictionary  
  - Contains words (possible passwords) for dictionary attack.
#### Output file
- **output.txt**: Created newly or appended.
## Demo instruction
#### Demo 1 - Run dictionary attack with English dictionary over 10 users
- Run **mvnw -q clean compile exec:java -Dexec.mainClass=com.demo.Main**
#### Demo 2 - Run dictionary attack with Bruteforce dictionary over 10 users sequentially
- Comment out line 56 and activate line 55 in src\main\java\com\demo\thread\ThreadManager.java
- Run: **mvnw -q clean compile exec:java -Dexec.mainClass=com.demo.Main**
- This take about 7 seconds to attack 10 users with minimum 3 characters, maximum 6 characters, with alphabets abcdefghij only. The attack will run sequentially.
#### Demo 3 - Run dictionary attack with Bruteforce dictionary over 10 users in parallel
- On top of the configuration for demo 2, edit src\main\resources\homework-default.properties to set thread-pool.size=10
- Run **mvnw -q clean compile exec:java -Dexec.mainClass=com.demo.Main**
- This takes about 3 secondds to finish to do the same thing as demo 2. Multithreading helps.