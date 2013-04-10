wget http://weka.wikispaces.com/file/view/snowball-20051019.jar/82917267/snowball-20051019.jar
smpath=`pwd`
mvn install:install-file -Dfile=$smpath/WebContent/WEB-INF/lib/mongo-2.10.1.jar -DgroupId=com.mongodb -DartifactId=mongodb-java-driver -Dversion=2.10.1 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=$smpath/WebContent/WEB-INF/lib/snowball-20051019.jar -DgroupId=org.tartarus.snowball.ext -DartifactId=english-stemmer -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=true
