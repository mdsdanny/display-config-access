cd /home/ubuntu/display-config-access
./gradlew clean build
JARS="/home/ubuntu/jars/"
if [ -d "$DIR" ]; then
   echo "Nothing to do ... directory exists"
   rm /home/ubuntu/jars/display-config-access-1.0.0.jar
else
   mkdir /home/ubuntu/jars/
fi
cp /home/ubuntu/display-config-access/build/libs/display-config-access-1.0.0.jar /home/ubuntu/jars
cd /home/ubuntu/jars
java -jar display-config-access-1.0.0.jar &
