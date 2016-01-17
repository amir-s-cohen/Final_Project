<?php

$HostName="mig04.impl.alma.dc04.hosted.exlibrisgroup.com";
$fullEnvName="01cals_sjo_testload";
$ssNameItemsFromList="sjsu_ite*15.csv";


echo "csh -fx /home/amirc/public_html/tool/scripts/run_data_count.sh {$HostName} {$fullEnvName} ITEM \"{$ssNameItemsFromList}\" xml";


$providedItemsCount2 = shell_exec("csh -fx /home/amirc/public_html/tool/scripts/run_data_count.sh {$HostName} {$fullEnvName} ITEM \"{$ssNameItemsFromList}\" xml");


echo "----------->" . $providedItemsCount2;


?>
