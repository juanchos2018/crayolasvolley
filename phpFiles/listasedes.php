<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id12787771_tienda";
$username_localhost ="id12787771_admin";
$password_localhost ="1234567890";
$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select id_sede,nombre_sede,direccion_sede,telefono_sede from sedes";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$json['sedes'][]=$registro;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
?>

