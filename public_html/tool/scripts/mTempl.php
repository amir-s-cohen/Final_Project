<?php

function normalizeFieldName($inputString)
{
                $stringToReturn = preg_replace('/ |"/', '', $inputString);
                return $stringToReturn;
}


$username='michaelp';
$password='2001Mike';
$url="http://intranet/AlmaTS/_vti_bin/owssvr.dll?XMLDATA=1&List={3E012000-5A51-459F-807A-65F9A91E3504}&View={5D1DFA67-DBCF-42C9-BCE2-F744CF9D2AEB}&RowLimit=0&RootFolder=/AlmaTS/Lists/Full%20Operations";


$ch = curl_init();
curl_setopt($ch, CURLOPT_URL,$url);
curl_setopt($ch, CURLOPT_TIMEOUT, 30); //timeout after 30 seconds
curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_ANY);
curl_setopt($ch, CURLOPT_USERPWD, "$username:$password");
$status_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);   //get status code
$data=curl_exec ($ch);
curl_close($ch);

//file_put_contents('calendar.xml', $data) ;

//echo $data;

$xml = simplexml_load_string(str_replace("\r\n", "", $data));


$columnCodeName = array();
$columnRealName = array();
$columnIndex = 0;

$namespaces = $xml->getNamespaces(true);

$S_child = $xml->children($namespaces['s']);

foreach ($S_child->Schema->ElementType->AttributeType as $currentAttributeXML)
{
                $columnIndex = (int)$currentAttributeXML->attributes('rs',true)->number;
                $columnRealName[$columnIndex] = (String)$currentAttributeXML->attributes('rs',true)->name;
                $columnCodeName[$columnIndex] = (String)$currentAttributeXML->attributes()->name;
}

$columnName = normalizeFieldName("('" . implode("','", $columnRealName) . "')");

$insertQuery = "insert into calendarMain $columnName values ";


$finalValuesString = "";
$RZ_child = $xml->children($namespaces['rs'])->data;


// backup previous DB
copy("./calendar", "./calendar_work/calendar" . date('mdY'));
chmod("./calendar_work/calendar" . date('mdY'), 0777);

// open DB
//$db = new SQLite3('./calendar');
$db = new PDO('sqlite:./calendar');

// truncate existed DB data

/* ; */

$query = $db->query('DROP TABLE calendarMain');
$query = $db->query('create table calendarMain '. $columnName );


foreach($RZ_child->children($namespaces['z'])->row as $currentDataRow)
{
                $valuesToInsert = "" ;
                for ($counter = 1; $counter <= $columnIndex; $counter++)
                {
                                $tmpStr = (String)$currentDataRow->attributes()->$columnCodeName[$counter] ;
                                $tmpStr = preg_replace('/^(.)+;#/', '', $tmpStr);
                                $tmpStr = preg_replace('/\'/', '\'\'', $tmpStr);
                                $valuesToInsert .= "','" . $tmpStr ;
                }
                $valuesToInsert = "(" . substr($valuesToInsert, 2) . "')" . PHP_EOL ;

                $updateQuery = $insertQuery . $valuesToInsert ;

                $query = $db->query($updateQuery);

                //echo $updateQuery ;
}

//remove also EOL
$updateQuery .= substr($finalValuesString, 0,-3) ;

$queryToNormalizeData="update calendarMain set
`Est#Bibs` =  CAST(`Est#Bibs` as decimal),
`FTEs` =  CAST(`FTEs` as decimal),
`NamedUsers` =  CAST(`NamedUsers` as decimal),
`ExtrapNamedUsers` =  CAST(`ExtrapNamedUsers` as decimal),
`ProjCompleted` = CAST(`ProjCompleted` as boolean),
`sizeforcalculator` = CASE WHEN sizeforcalculator = '0' THEN 'FALSE' WHEN sizeforcalculator = '1' THEN 'TRUE' ELSE sizeforcalculator END,
`DeprovisionedIMPL` = CASE WHEN DeprovisionedIMPL = '0' THEN 'FALSE' WHEN DeprovisionedIMPL = '1' THEN 'TRUE' ELSE DeprovisionedIMPL END,
`ProdProvisioned` = CASE WHEN ProdProvisioned = '0' THEN 'FALSE' WHEN ProdProvisioned = '1' THEN 'TRUE' ELSE ProdProvisioned END,
`PremiumSandbox` = CASE WHEN PremiumSandbox = '0' THEN 'FALSE' WHEN PremiumSandbox = '1' THEN 'TRUE' ELSE PremiumSandbox END,
`PremiumMigrationPackage` = CASE WHEN PremiumMigrationPackage = '0' THEN 'FALSE' WHEN PremiumMigrationPackage = '1' THEN 'TRUE' ELSE PremiumMigrationPackage END,
`LivewithAlma` = CASE WHEN LivewithAlma = '0' THEN 'FALSE' WHEN LivewithAlma = '1' THEN 'TRUE' ELSE LivewithAlma END,
`AutoEx` = CASE WHEN AutoEx = '0' THEN 'FALSE' WHEN AutoEx = '1' THEN 'TRUE' ELSE AutoEx END,
`NETWORK` = CASE WHEN NETWORK = '0' THEN 'FALSE' WHEN NETWORK = '1' THEN 'TRUE' ELSE NETWORK END,
`HasNZ` = CASE WHEN `HasNZ` = '0' THEN 'FALSE' WHEN `HasNZ` = '1' THEN 'TRUE' ELSE `HasNZ` END,
`ScheduleConfirmed` = CASE WHEN ScheduleConfirmed = '0' THEN 'FALSE' WHEN ScheduleConfirmed = '1' THEN 'TRUE' ELSE ScheduleConfirmed END ,
`DateProvisioned` = strftime('%d/%m/%Y', DateProvisioned),
`StartTime` = strftime('%d/%m/%Y', `StartTime`),
`Copy_to_Prod` = strftime('%d/%m/%Y', `Copy_to_Prod`),
`Go-LiveDate` = strftime('%d/%m/%Y', `Go-LiveDate`),
`fulfillmentCutover` = strftime('%d/%m/%Y', `fulfillmentCutover`),
`EndDate` = strftime('%d/%m/%Y', `EndDate`),
`impldeprovisiondate`= strftime('%d/%m/%Y', `impldeprovisiondate`)";



// insert new data


// normalize fields
$query = $db->query($queryToNormalizeData);

// VACUUM;
$query = $db->query("VACUUM;");


?>


