<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id12787771_tienda";
$username_localhost ="id12787771_admin";
$password_localhost ="1234567890";

$json=array();

	if(isset($_GET["id_sede"])){
		$id_sede=$_GET["id_sede"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

	
        $consulta="SELECT  se.id_sede,co.id_salon, co.nombre_salon,co.correo_salon  from correosalones as co INNER JOIN   
        sedes as se 
         on se.id_sede= co.id_sede  
           where se.id_sede ='{$id_sede}'";

     $resultado=mysqli_query($conexion,$consulta);
			 if(mysqli_num_rows($resultado)>0){
        while($registro=mysqli_fetch_array($resultado)){
        			$json['sedes'][]=$registro;
        			
		 }
		 
	echo json_encode($json);
            }
            else{
                $resulta["nombre_salon"]='No hay registros';
		    	$resulta["correo_salon"]='No hay registros';
                	$json['sedes'][]=$resulta;
                	echo json_encode($json);
                
            }
		 
	
		mysqli_close($conexion);
	}
		
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['sedes'][]=$resultar;
		echo json_encode($json);
	}
?>