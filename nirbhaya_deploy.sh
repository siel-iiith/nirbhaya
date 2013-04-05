#!/bin/sh

BUILD_FOLDER=`date +%d-%m`
mkdir $BUILD_FOLDER
cd $BUILD_FOLDER

TOMCAT_WEBAPPS=/usr/local/apache-tomcat-7.0.22/webapps
git clone https://github.com/siel-iiith/nirbhaya.git
cd nirbhaya

$TOMCAT_WEBAPPS/../bin/shutdown.sh

#compiling and deploying individual modules
cd Search
./testScript.sh
mvn clean
mvn compile
mvn package war
rm $TOMCAT_WEBAPPS/VerticalSearch.war
rm -r $TOMCAT_WEBAPPS/VerticalSearch
cp target/VerticalSearch-0.0.1-SNAPSHOT.war $TOMCAT_WEBAPPS/VerticalSearch.war
cd ..

cd SolutionsModule
./test.sh
mvn compile
mvn package war
rm $TOMCAT_WEBAPPS/SolutionsModule.war
rm -r $TOMCAT_WEBAPPS/SolutionsModule
cp target/SolutionsModule-1.0-SNAPSHOT.war $TOMCAT_WEBAPPS/SolutionsModule.war
cd ..

cd Trending
./testScript.sh
mvn compile
mvn package war
rm $TOMCAT_WEBAPPS/Trending.war
rm -r $TOMCAT_WEBAPPS/Trending
cp target/Trending-1.0-SNAPSHOT.war $TOMCAT_WEBAPPS/Trending.war
cd ..


cd HeatMap
./test.sh
mvn compile
mvn package war
rm $TOMCAT_WEBAPPS/HeatMap.war
rm -r $TOMCAT_WEBAPPS/HeatMap
cp target/HeatMap-1.0-SNAPSHOT.war $TOMCAT_WEBAPPS/HeatMap.war
cd ..

mkdir $TOMCAT_WEBAPPS/Nirbhaya
cp -Rf UI $TOMCAT_WEBAPPS/Nirbhaya

$TOMCAT_WEBAPPS/../bin/startup.sh
