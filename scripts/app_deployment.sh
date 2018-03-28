echo "Deployment script by Priya"
cd /var/TMWithSpring
rm -Rf target
mvn clean install package
cp  -Rv /var/TMWithSpring/target/TaskManagementWithSpring.war /var/lib/tomcat8/webapps/
sleep 10


