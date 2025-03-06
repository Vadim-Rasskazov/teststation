#!/bin/bash
sudo apt install -y openjdk-21-jdk
sudo apt install maven
sudo rm -R /opt/maven
sudo rm -R /usr/share/maven
wget https://dlcdn.apache.org/maven/maven-4/4.0.0-alpha-13/binaries/apache-maven-4.0.0-alpha-13-bin.tar.gz
sudo tar xzf apache-maven-4.0.0-alpha-13-bin.tar.gz -C /opt
sudo tar xzf apache-maven-4.0.0-alpha-13-bin.tar.gz -C /usr
sudo mv /opt/apache-maven-4.0.0-alpha-13 /opt/maven
sudo mv /usr/apache-maven-4.0.0-alpha-13 /usr/share/maven
sudo apt install openjfx
sudo su <<EOF
grep -qxF 'export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64' /etc/profile || echo 'export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64' >> /etc/profile
EOF
mvn -version
