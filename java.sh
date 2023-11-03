#!/bin/bash
sudo apt install -y openjdk-19-jdk
sudo apt install maven
wget https://dlcdn.apache.org/maven/maven-4/4.0.0-alpha-8/binaries/apache-maven-4.0.0-alpha-8-bin.tar.gz
sudo tar xzf apache-maven-4.0.0-alpha-8-bin.tar.gz -C /opt/
#delete previous version
#sudo rm -R /opt/apache-maven-3.8.7
sudo rm /opt/maven
sudo ln -s /opt/apache-maven-4.0.0-alpha-8 /opt/maven
export JAVA_HOME=/usr/lib/jvm/java-19-openjdk-amd64
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}
sudo apt install openjfx
mvn -version
