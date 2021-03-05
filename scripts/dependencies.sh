DIR="/opt/gradle"
if [ -d "$DIR" ]; then
   echo "Nothing to do ... directory exists"
else
   echo "Warning: '$DIR' NOT found."
   sudo yum update -y
   wget https://services.gradle.org/distributions/gradle-6.8.2-bin.zip -P /tmp
   sudo unzip -d /opt/gradle /tmp/gradle-*.zip
   export GRADLE_HOME=/opt/gradle/gradle-6.8.2
   export PATH=${GRADLE_HOME}/bin:${PATH}
fi