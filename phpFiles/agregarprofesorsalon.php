<?PHP
$hostname_localhost="localhost";
$database_localhost="id12787771_tienda";
$username_localhost="id12787771_admin";
$password_localhost="1234567890";

$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

    $idprofesor=$_POST['id_profesor'];
    $idsalon=$_POST['id_salon'];
    $fecha=$_POST['fecha_creacion'];    

    $consulta="SELECT * FROM  profesorsalon where id_salon='$idsalon'";
    $result=mysqli_query($conexion,$consulta);
    if(mysqli_num_rows($result)>0){

        echo "existe";
    }
  
   else{
        
    $sql="INSERT INTO profesorsalon (id_profesor,id_salon,fecha_creacion) VALUES ('$idprofesor','$idsalon','$fecha')";
        //	$stm=$conexion->prepare($sql);
            $resultado_insert=mysqli_query($conexion,$sql);
        //	$stm->bind_param('sss',$sede,$direccion,$telefono);
                
            if($resultado_insert){
                echo "registra";
            }else{
                echo "noRegistra";
            }
            
            mysqli_close($conexion);
    }

?>
