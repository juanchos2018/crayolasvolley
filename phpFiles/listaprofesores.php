<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id12787771_tienda";
$username_localhost ="id12787771_admin";
$password_localhost ="1234567890";
$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select nombre_profesor,apellido_profesor,correo_profesor,telefono_profesor,direccion_profesor,foto_profesor from profesores";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$json['profesores'][]=$registro;
			
		}
		mysqli_close($conexion);
		echo json_encode($json);
?>

