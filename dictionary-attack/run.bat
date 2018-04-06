@del output.txt > NUL 2>&1
call mvnw -q clean compile exec:java -Dexec.mainClass=com.demo.App -Dexec.args=
echo ---- output.txt ----
type output.txt
