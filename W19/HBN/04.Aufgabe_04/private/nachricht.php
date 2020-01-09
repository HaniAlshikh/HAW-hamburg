<!DOCTYPE html>
<html>
<head>
  <title>Nachricht</title>
  <link rel="stylesheet" type="text/css" href="../style.css">
</head>

<body>
<?php
	$dateiname = "nachrichten.csv";
	
	// Formulardaten ermitteln
	$name = $_POST['Name'];
	$absender = $_POST['Absender'];
	$nachricht = $_POST['Nachricht'];	
	$empfangdatum = strftime("%d.%m.%Y  %H:%M");
	
	// Neue Bestellung als String zusammenbauen (Trennzeichen: ";")
	$nachricht = $name . ";" .
               $absender . ";" .
		           $nachricht . ";" .
		           $empfangdatum . "\n";
					
	// Neuen Eintrag an den Dateiinhalt anhängen
	$file = fopen($dateiname, "a");
	fwrite($file, $nachricht);
	fclose($file);

	echo "<p>Ihre Nachricht wurde gesendet.</p>";
?>
</body>
</html>
