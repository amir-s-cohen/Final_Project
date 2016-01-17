#!/bin/csh -f

set hostname="$1"
set migEnv="$2"
set area="$3"
set file="$4"
set type="$5"


setenv JAVA_HOME /exlibris/product/jdk
setenv PATH $JAVA_HOME/bin:${PATH}
setenv CLASSPATH ./

java -cp ../code:../code/lib/com-jcraft-jsch.jar:../code/ils-local-toolkit-1.0.0.0-ILK.jar:../code/ils-migration-toolkit-1.0.0.0-IMK.jar:../code/AlmaME.jar -Xms4096M -Xmx4096M com.exlibris.alma_me.test3.FactoryPatternCountData "$hostname" "$migEnv" "$area" "$file" "$type"

