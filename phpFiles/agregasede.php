<?PHP
$hostname_localhost="localhost";
$database_localhost="id12787771_tienda";
$username_localhost="id12787771_admin";
$password_localhost="1234567890";

$json=array();

	if(isset($_POST["nombre_sede"]) && isset($_POST["direccion_sede"]) && isset($_POST["telefono_sede"])){
		$sede=$_POST['nombre_sede'];
		$direccion=$_POST['direccion_sede'];
		$telefono=$_POST['telefono_sede'];
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO sedes(nombre_sede,direccion_sede,telefono_sede) VALUES ('{$sede}','{$direccion}','{$telefono}')";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM sedes WHERE nombre_sede = '{$sede}'";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['sedes'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["nombre_sede"]='No Registra';
			$resulta["direccion_sede"]='No Registra';
			$resulta["telefono_sede"]='No Registra';
			$json['sedes'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["nombre_sede"]='Ws No retorna';
			$resulta["direccion_sede"]='WS No retorna';
			$resulta["telefono_sede"]='WS No retorna';
			$json['sedes'][]=$resulta;
			echo json_encode($json);
		}

?>
