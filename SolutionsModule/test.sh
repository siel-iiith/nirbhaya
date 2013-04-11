smpath=`pwd`
wget -P $smpath/WebContent/WEB-INF/lib/ http://weka.wikispaces.com/file/view/snowball-20051019.jar/82917267/snowball-20051019.jar
mvn install:install-file -Dfile=$smpath/WebContent/WEB-INF/lib/snowball-20051019.jar -DgroupId=org.tartarus.snowball -DartifactId=english-stemmer -Dversion=1.0.0 -Dpackaging=jar -DgeneratePom=true
