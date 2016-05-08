<?php


	$username='amirc';
	$password='Dafna1122';
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
	$columnIndex = 0 ;

	$namespaces = $xml->getNamespaces(true);

	$S_child = $xml->children($namespaces['s']);

	foreach ($S_child->Schema->ElementType->AttributeType as $currentAttributeXML)
	{
		$columnIndex = (int)$currentAttributeXML->attributes('rs',true)->number;
		$columnRealName[$columnIndex] = (String)$currentAttributeXML->attributes('rs',true)->name;
		$columnCodeName[$columnIndex] = (String)$currentAttributeXML->attributes()->name;
		print($columnRealName[$columnIndex]);
	}
?>
