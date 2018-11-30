git pull
mvn clean package
nohup java $* -jar target/ipchecker-1.0-SNAPSHOT.jar > /dev/null &
