cwd=`pwd`
mvn install:install-file -Dfile=$cwd/lib/stanford-corenlp-1.3.4-models.jar -DgroupId=org.nirbhaya.trending.corenlpmod -DartifactId=1.0 -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
