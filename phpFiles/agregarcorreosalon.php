<?PHP
$hostname_localhost="localhost";
$database_localhost="id12787771_tienda";
$username_localhost="id12787771_admin";
$password_localhost="1234567890";

$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

    $nombre=$_POST['nombre_salon'];
    $correo=$_POST['correo_salon'];
    $clave=$_POST['clave_salon'];
    $idsede=$_POST['id_sede'];

    $consulta="SELECT * FROM  correosalones where correo_salon='$correo'";
    $result=mysqli_query($conexion,$consulta);
    if(mysqli_num_rows($result)>0){

        echo "existe";
    }
  
   else{
        
    $sql="INSERT INTO correosalones (nombre_salon,correo_salon,clave_salon,id_sede) VALUES ('$nombre','$correo','$clave','$idsede')";
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
