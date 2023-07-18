#!/bin/bash
sudo apt install -y openjdk-19-jdk
sudo apt install maven
wget https://dlcdn.apache.org/maven/maven-4/4.0.0-alpha-5/binaries/apache-maven-4.0.0-alpha-5-bin.tar.gz
sudo tar xzf apache-maven-4.0.0-alpha-5-bin.tar.gz -C /opt/
#delete previous version
#sudo rm -R /opt/apache-maven-3.8.6
sudo rm /opt/maven
sudo ln -s /opt/apache-maven-4.0.0-alpha-5 /opt/maven
sudo cat > /etc/profile.d/maven.sh << EOF
export JAVA_HOME=/usr/lib/jvm/java-19-openjdk-amd64
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}
EOF
sudo chmod +x /etc/profile.d/maven.sh
source /etc/profile.d/maven.sh
mvn -version
