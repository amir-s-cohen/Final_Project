#!/bin/csh -f

set deliverdFile="$1"
set migratedArea="$2"
set getName="$3"

setenv JAVA_HOME /exlibris/product/jdk
setenv PATH $JAVA_HOME/bin:${PATH}
setenv CLASSPATH ./

java -cp ../code:../code/ils-local-toolkit-1.0.0.0-ILK.jar:../code/ils-migration-toolkit-1.0.0.0-IMK.jar:../code/AlmaME.jar -Xms4096M -Xmx4096M com.exlibris.alma_me.test3.compareDeliverdListWithProvidedData "$deliverdFile" "$migratedArea" "$getName"

