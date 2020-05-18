<?php

$hostname_localhost ="localhost";
$database_localhost ="crayolas";
$username_localhost ="root";
$password_localhost ="";


if(isset($_GET["id_sede"])){
    $id_sede=$_GET["id_sede"];

$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);


$sql="SELECT  se.id_sede,co.id_salon, co.nombre_salon,co.correo_salon  from correosalones as co INNER JOIN   
sedes as se 
 on se.id_sede= co.id_sede  
 where se.id_sede ='{$id_sede}'";

        $resultado =mysqli_query($conexion,$sql);
        
        while($registro=mysqli_fetch_array($resultado)){
			$datos['sedes'][]=$registro;
			
		}
//	$ver=mysqli_fetch_row($resultado);
//		$datos=array(
//			'salom'=>$ver[1],
//			'correo'=>$ver[2],			
			
//		);

      //  return $datos;
       // $json['sedes'][]=$datos;
       // var_dump($datos);
        echo json_encode($datos);
    }
    else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['sedes'][]=$resultar;
		echo json_encode($json);
	}
        
?>