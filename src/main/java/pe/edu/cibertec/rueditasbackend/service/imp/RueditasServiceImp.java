package pe.edu.cibertec.rueditasbackend.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.rueditasbackend.dto.RueditasRequest;
import pe.edu.cibertec.rueditasbackend.service.ServiceRueditas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service // Anotación que indica que esta clase es un servicio de Spring.
public class RueditasServiceImp implements ServiceRueditas {

    @Autowired
    ResourceLoader resourceLoader; // Inyección de dependencia de ResourceLoader para cargar recursos.

    @Override
    public String[] buscarRueditas(RueditasRequest rueditasRequest) throws Exception {

        String[] rueditas = null; // Inicializa el array rueditas como null.
        Resource resource = resourceLoader.getResource("classpath:rueditas.txt"); // Carga el recurso rueditas.txt desde el classpath.

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) { // Abre un BufferedReader para leer el archivo rueditas.txt.
            String line; // Declara una variable line para almacenar cada línea leída del archivo.
            while ((line = br.readLine()) != null) { // Lee cada línea del archivo hasta que no haya más líneas (null).
                String[] dates = line.split(";"); // Divide la línea leída en un array de String usando ; como delimitador.
                if (rueditasRequest.placa().equals(dates[1])) { // Compara la placa del RueditasRequest con el segundo elemento del array dates.
                    rueditas = new String[5]; // Inicializa el array rueditas con un tamaño de 5.
                    rueditas[0] = dates[2];
                    rueditas[1] = dates[3];
                    rueditas[2] = dates[4];
                    rueditas[3] = dates[5];
                    rueditas[4] = dates[6]; // Asigna los valores correspondientes del array dates al array rueditas.
                    break; // Sale del bucle while una vez que se encuentra la placa.
                }
            }

        } catch (IOException e) {
            throw new Exception("Error al leer el archivo de rueditas"); // Captura cualquier IOException y lanza una nueva excepción con un mensaje de error.
        }

        return rueditas; // Devuelve el array rueditas.

    }
}