smpath=`pwd`
mvn install:install-file -Dfile=$smpath/WebContent/WEB-INF/lib/mongo-2.10.1.jar -DgroupId=com.mongodb -DartifactId=mongodb-java-driver -Dversion=2.10.1 -Dpackaging=jar -DgeneratePom=true
