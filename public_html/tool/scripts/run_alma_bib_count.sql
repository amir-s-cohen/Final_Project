#!/bin/bash -f

#line="aio03.impl.alma.dc04.hosted.exlibrisgroup.com"
host="<HOST>"
instid="<INSTITUTIONID>"
target="<TARGET>"

if [ $host == "aio02.impl.alma.dc03.hosted.exlibrisgroup.com" ]  ; then
        ORA_SID="urm"
else
        ORA_SID="alma"
fi

#Starting
/exlibris/app/oracle/product/11r2/bin/sqlplus -s "v2u1_urm00/v2u1_urm00@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=$host)(Port=1521))(CONNECT_DATA=(SID=$ORA_SID)))" <<RUN_SCRIPT

SET TRIMSPOOL ON
SET ECHO OFF
SET FEEDBACK OFF
SET PAUSE OFF
SET TERMOUT OFF
SET VERIFY OFF
SET SPACE 1
SET PAGESIZE 0
SET LINESIZE 255
SET HEADING OFF
spool $target

SELECT COUNT (*) FROM mms_record WHERE objecttype='BIB_MMS' AND institutionid=2914 AND ORIGINATING_SYSTEM_ID IS NOT NULL;

RUN_SCRIPT

#echo $line -------

echo -e "\n\n\n"




