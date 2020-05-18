$consulta="SELECT a.id_salon,a.nombre_salon,a.correo_salon,a.clave_salon,a.id_sede,se.nombre_sede from correosalones  as a
        INNER JOIN sedes as se
        on a.id_sede=se.id_sede";