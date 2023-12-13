#!/bin/bash
sudo apt install -y openjdk-21-jdk
sudo apt install maven
sudo rm -R /usr/share/maven
wget https://dlcdn.apache.org/maven/maven-4/4.0.0-alpha-8/binaries/apache-maven-4.0.0-alpha-8-bin.tar.gz
sudo tar xzf apache-maven-4.0.0-alpha-8-bin.tar.gz -C /usr/share
sudo mv /usr/share/apache-maven-4.0.0-alpha-8 /usr/share/maven
sudo apt install openjfx
sudo su <<EOF
grep -qxF 'export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64' /etc/profile || echo 'export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64' >> /etc/profile
EOF
mvn -version
