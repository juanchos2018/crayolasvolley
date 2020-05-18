<?php
define('HOST','localhost');
define('USER','id12787771_admin');
define('PASS','1234567890');
define('DB','id12787771_tienda');

$json=array();
$con = mysqli_connect(HOST,USER,PASS,DB);
$username = $_GET['Usuario'];
$password = $_GET['pass'];
$sql = "select Cod_persona, Nombre,Apellidos,Usuario from persona where Usuario='$username' and
pass='$password'";
$res = mysqli_query($con,$sql);
$check = mysqli_fetch_array($res);

if(isset($check)){
echo 'success';
if($registro=mysqli_fetch_array($res)){
		$result["Cod_persona, "]=$registro['Cod_persona, '];
		$result["Nombre, "]=$registro['Nombre, '];
		$result["Apellidos"]=$registro['Apellidos'];
		$result["Usuario"]=$registro['Usuario'];
			
			$json['usuario'][]=$result;
		}else{
		$resultar["Cod_persona"]=0;
		$resultar["Nombre"]='no registra';
		$resultar["Apellidos"]='no registra';
		
		$json['usuario'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
}else{
echo 'failure';
}
mysqli_close($con);
?>