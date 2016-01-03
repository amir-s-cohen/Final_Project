#!/bin/bash -f

export ORACLE_ALERT_LOG='/exlibris/app/oracle/diag/rdbms/urm/urm/trace/alert_urm.log'
export ORACLE_BASE='/exlibris/app/oracle'
export ORACLE_HOME='/exlibris/app/oracle/product/11r2'
export ORACLE_OWNER='oracle'
export ORACLE_PATH='.:/exlibris/app/oracle/product/11r2/bin:/opt/bin:/bin:/usr/bin:'
export ORACLE_TERM='vt220'
export ORACLE_VERSION='11'

PATH=/exlibris/app/oracle/product/11r2/bin:/exlibris/app/oracle/product/11r2/bin:/exlibris/app/oracle/product/11r2/bin:/usr/kerberos/bin:/usr/local/bin:/bin:/usr/bin:/exlibris/product/jdk/bin:/exlibris/app/oracle/product/11r2/bin:/bin:/usr/bin:/usr/local/bin:/exlibris/app/oracle/product/102/bin:/home/amirc/bin:/exlibris/product/jdk/bin:/exlibris/app/oracle/product/11r2/bin:/bin:/usr/bin:/usr/local/bin:/exlibris/app/oracle/product/102/bin:/exlibris/product/jdk/bin:/exlibris/app/oracle/product/11r2/bin:/bin:/usr/bin:/usr/local/bin:/exlibris/app/oracle/product/102/bin


migServer=$1
migEnv=$2


/exlibris/app/oracle/product/11r2/bin/sqlplus "exl_$2/exl_$2@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=$1)(Port=1521))(CONNECT_DATA=(SID=urm)))" << EOF

whenever sqlerror exit sql.sqlcode;
set echo off
set heading off

spool bibindex.count
@bibindex.sql

spool globalinventoryindex.count
@globalinventoryindex.sql

exit;

EOF

#whenever sqlerror exit sql.sqlcode;
#set echo off
#set heading off

#spool bibindex.count
#@bibindex.sql

#spool globalinventoryindex.count
#@globalinventoryindex.sql

