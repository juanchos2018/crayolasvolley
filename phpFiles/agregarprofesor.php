<?PHP
$hostname_localhost="localhost";
$database_localhost="id12787771_tienda";
$username_localhost="id12787771_admin";
$password_localhost="1234567890";

$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

    $nombre=$_POST['nombre_profesor'];
    $apellido=$_POST['apellido_profesor'];
    $correo=$_POST['correo_profesor'];
    $telefono=$_POST['telefono_profesor'];
    $direccion=$_POST['direccion_profesor'];
    $foto=$_POST['foto_profesor'];
 
        
        $sql="INSERT INTO profesores (nombre_profesor,apellido_profesor,correo_profesor,telefono_profesor,direccion_profesor,foto_profesor)
         VALUES ('$nombre','$apellido','$correo','$telefono','$direccion','$foto')";
        //	$stm=$conexion->prepare($sql);
            $resultado_insert=mysqli_query($conexion,$sql);
        //	$stm->bind_param('sss',$sede,$direccion,$telefono);
                
            if($resultado_insert){
                echo "registra";
            }else{
                echo "noRegistra";
            }
            
            mysqli_close($conexion);
    

?>
