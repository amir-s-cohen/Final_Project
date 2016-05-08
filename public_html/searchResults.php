<?php
echo <<<HTML
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="jquery-latest.min.js"></script>
<script src="jquery-ui.js"></script>
<link rel="stylesheet" href="jquery-ui.css">

<style type="text/css">
.cust_table  {border-collapse:collapse;border-spacing:0;float: left;margin: 2em;}
.cust_table td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
.cust_table th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;width: 15em;}
.cust_table th.subj {background: #f2f2f2;width: 10em;}
.cust_table th.InstCode {color: #2b73b7;font-weight: bolder;}

</style>
</head>
HTML;
$db = new PDO('sqlite:calendar');

$instListFull = $db->query("select InstitutionCode||' '||InstitutionName as mergedInst from calendarMain where InstitutionCode !='' group by InstitutionCode")->fetchAll(PDO::FETCH_COLUMN, 0);


$instCodesListString = implode('","',$instListFull);
$instCodesListString = '"' . $instCodesListString . '"' ;

//echo $_GET['instName'] ;

if ($_GET)
{

       if (!strpos($_GET['instName']," "))
       {
              $instCode = $_GET['instName'];
       }
       else
       {
              $instCode = substr($_GET['instName'], 0, strpos($_GET['instName'], " ")) ;
       }




      // if (strlen($instCode) < 3) { echo "not found"; die; }

// echo "<h3>" . $instCode . "</h3>";
	echo "<h3>Sanity Checks candidate Projects based on search</h3>";

$instData = $db->query("select
`TL/CO`,
`Status`,
`Region`,
`CustomerID`,
`InstitutionID`,
`CustomerCode`,
`InstitutionCode`,
`InstitutionName`,
`StartTime`,
`DelivertoPS/IMPL`,
`Copy_to_Prod`,
`FulfillmentCutover`,
`Go-LiveDate`,
`EndDate`,
`MigServer`,
`UsingImpl`,
`PSPM`,
`MigStaff`,
`ILS`,
`Est#Bibs`,
`AutoEx`,
`LinkResolver`,
`SubDomain/URLPrefix`,
`APIportalinitialpwd`,
`USTATCode`,
`USTATinitialpwd`,
`DateProvisioned`,
`ProdProvisioned`,
`TZ`,
`DeprovisionedIMPL`,
`IMPLdeprovisiondate`,
`LivewithAlma`
from calendarMain where institutioncode like '%$instCode%' or institutionname like '%$instCode%'")->fetchAll(PDO::FETCH_ASSOC);


foreach($instData as $instDataLine)
{





	echo "<table class='cust_table'>";

		echo "<tr><th class='subj'>TL/CO</th><th>{$instDataLine['TL/CO']}</th>";
		echo "<tr><th class='subj'>InstName</th><th>{$instDataLine['InstitutionName']}</th>";
		echo "<tr><th class='subj'>InstCode</th><th class='InstCode'>{$instDataLine['InstitutionCode']}</th>";
		echo "<tr><th class='subj'>CustId</th><th>{$instDataLine['CustomerID']}</th>";
		echo "<tr><th class='subj'>InstId</th><th>{$instDataLine['InstitutionID']}</th>";
		echo "<tr><th class='subj'>MigServer</th><th>{$instDataLine['MigServer']}</th>";
		echo "<tr><th class='subj'>UsingImpl</th><th>{$instDataLine['UsingImpl']}</th>";


/*
       echo "<tr><th class='subj'>TL/CO</th><th>{$instDataLine['TL/CO']}</th><th class='subj'>PS/PM</th><th>{$instDataLine['PSPM']}</th></tr>" ;
       echo "<tr><th class='subj'>Region</th><th>{$instDataLine['Region']}</th><th class='subj'>MigStaff</th><th>{$instDataLine['MigStaff']}</th></tr>" ;
       echo "<tr><th class='subj'>CustId</th><th>{$instDataLine['CustomerID']}</th><th class='subj'>ILS</th><th>{$instDataLine['ILS']}</th></tr>" ;
       echo "<tr><th class='subj'>InstId</th><th>{$instDataLine['InstitutionID']}</th><th class='subj'>BIBs#</th><th>{$instDataLine['Est#Bibs']}</th></tr>" ;
       echo "<tr><th class='subj'>CustCode</th><th>{$instDataLine['CustomerCode']}</th><th class='subj'>AutoEx</th><th>{$instDataLine['AutoEx']}</th></tr>" ;
       echo "<tr><th class='subj'>InstCode</th><th class='InstCode'>{$instDataLine['InstitutionCode']}</th><th class='subj'>LR</th><th>{$instDataLine['LinkResolver']}</th></tr>" ;
       echo "<tr><th class='subj'>InstName</th><th>{$instDataLine['InstitutionName']}</th><th class='subj'>SubDomain</th><th>{$instDataLine['SubDomain/URLPrefix']}</th></tr>" ;
       echo "<tr><th class='subj'>Start</th><th>{$instDataLine['StartTime']}</th><th class='subj'>APIpwd</th><th>{$instDataLine['APIportalinitialpwd']}</th></tr>" ;
       echo "<tr><th class='subj'>Delivery2PS</th><th>{$instDataLine['DelivertoPS/IMPL']}</th><th class='subj'>USTATCode</th><th>{$instDataLine['USTATCode']}</th></tr>" ;
       echo "<tr><th class='subj'>Copy2Prod</th><th>{$instDataLine['Copy_to_Prod']}</th><th class='subj'>USTATpwd</th><th>{$instDataLine['USTATinitialpwd']}</th></tr>" ;
       echo "<tr><th class='subj'>FF-Cutover</th><th>{$instDataLine['FulfillmentCutover']}</th><th class='subj'>Provisioned</th><th>{$instDataLine['DateProvisioned']}</th></tr>" ;
       echo "<tr><th class='subj'>GoLiveDate</th><th>{$instDataLine['Go-LiveDate']}</th><th class='subj'>ProdProvisioned</th><th>{$instDataLine['ProdProvisioned']}</th></tr>" ;
       echo "<tr><th class='subj'>EndDate</th><th>{$instDataLine['EndDate']}</th><th class='subj'>TZ</th><th>{$instDataLine['TZ']}</th></tr>" ;
       echo "<tr><th class='subj'>MigServer</th><th>{$instDataLine['MigServer']}</th><th class='subj'>DeprovisionedIMPL</th><th>{$instDataLine['DeprovisionedIMPL']}</th></tr>" ;
       echo "<tr><th class='subj'>UsingImpl</th><th>{$instDataLine['UsingImpl']}</th><th class='subj'>IMPLDeprovisionDate</th><th>{$instDataLine['IMPLdeprovisiondate']}</th></tr>" ;
       echo "<tr><th class='subj'>.</th><th>.</th><th class='subj'>LivewithAlma</th><th>{$instDataLine['LiveWithAlma']}</th></tr>" ;
*/
       echo "</table>";

}
}
echo "</html>";
?>


