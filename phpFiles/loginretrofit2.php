<?php
define('HOST','localhost');
define('USER','id12787771_admin');
define('PASS','1234567890');
define('DB','id12787771_tienda');
$con = mysqli_connect(HOST,USER,PASS,DB);
$sql = "select Cod_persona, Nombre,Apellidos,Usuario from persona where Usuario='$username' and
pass='$password'";
$res = mysqli_query($con,$sql);
$check = mysqli_fetch_array($res);


if(isset($check)){
    echo 'success';
    $jsonData = array();
while ($array = mysqli_fetch_array($res)) {
$row_array['Cod_persona'] = $array['Cod_persona'];
$row_array['Nombre'] = $array['Nombre'];
$row_array['Apellidos'] = $array['Apellidos'];
$row_array['Usuario'] = $array['Usuario'];
array_push($jsonData,$row_array);
}
echo json_encode($jsonData);
}else{
    echo 'failure';
    }


mysqli_close($con);
?>