<?PHP
$hostname_localhost ="localhost";
$database_localhost ="id12787771_tienda";
$username_localhost ="id12787771_admin";
$password_localhost ="1234567890";

$json=array();

	if(isset($_GET["id_profesor"])){
		$id_profesor=$_GET["id_profesor"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

        $consulta="SELECT  c.id_salon,c.nombre_salon, c.correo_salon,s.nombre_sede  from correosalones as c INNER JOIN   
        profesorsalon as p 
         on p.id_salon= c.id_salon  
         INNER JOIN sedes as s
         on s.id_sede=c.id_sede
		 where p.id_profesor ='{$id_profesor}'";
	  
	  $resultado=mysqli_query($conexion,$consulta);
	  if(mysqli_num_rows($resultado)>0){
		 while($registro=mysqli_fetch_array($resultado)){
					 $json['salones'][]=$registro;
					 
		 }			
			 echo json_encode($json);
		 }
		 else{
				 $resulta["nombre_salon"]='No hay registros';
				 $resulta["correo_salon"]='No hay registros';
					 $json['salones'][]=$resulta;
					 echo json_encode($json);
				 
			 }	 

		 mysqli_close($conexion);
	 }
	 else{
		 $resultar["success"]=0;
		 $resultar["message"]='Ws no Retorna';
		 $json['salones'][]=$resultar;
		 echo json_encode($json);
	 }

/*
     $resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
		
			$result["id_salon"]=$registro['id_salon'];
            $result["nombre_salon"]=$registro['nombre_salon'];
            $result["correo_salon"]=$registro['correo_salon'];
            $result["nombre_sede"]=$registro['nombre_sede'];
		
			$json['sedes'][]=$result;
		}else{
			
			$resultar["id_salon"]=0;
            $resultar["nombre_salon"]='no registra';
            $resultar["correo_salon"]='no registra';
            $resultar["nombre_sede"]='no registra';
		
			$json['sedes'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	*/
	
?>