#!/bin/bash
sudo apt-get update && sudo apt-get install java-common
curl https://d3pxv6yz143wms.cloudfront.net/11.0.5.10.1/java-11-amazon-corretto-jdk_11.0.5.10-1_amd64.deb > java11.deb
sudo dpkg --install java11.deb
rm java11.deb
wget https://services.gradle.org/distributions/gradle-5.4.1-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-5.4.1-bin.zip
rm -rf gradle-5.4.1-bin.zip
