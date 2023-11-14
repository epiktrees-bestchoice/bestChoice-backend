echo "Start WAS in port 80."
sudo nohup java -Dserver.port=80 -jar /path/to/spring-0.0.1-SNAPSHOT.jar > /path/to/spring-0.0.1-SNAPSHOT.log 2>&1 &