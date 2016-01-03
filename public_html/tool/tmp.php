<?php

$nameBibFromList = shell_exec("csh -fx /home/amirc/public_html/tool/scripts/run_compareDelieverdProvided_getNames.sh /home/amirc/public_html/tool/uploads/* 'bibs' 'true'");

echo $nameBibFromList ;


?>
