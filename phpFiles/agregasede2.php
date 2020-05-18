<?PHP
$hostname_localhost="localhost";
$database_localhost="id12787771_tienda";
$username_localhost="id12787771_admin";
$password_localhost="1234567890";

$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

    $sede=$_POST['nombre_sede'];
    $direccion=$_POST['direccion_sede'];
    $telefono=$_POST['telefono_sede'];
	$sql="INSERT INTO sedes VALUES (?,?,?)";
	$stm=$conexion->prepare($sql);
	$stm->bind_param('issss',$sede,$direccion,$telefono);
		
	if($stm->execute()){
		echo "registra";
	}else{
		echo "noRegistra";
	}
	
	mysqli_close($conexion);
?>