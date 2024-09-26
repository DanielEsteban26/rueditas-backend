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

@Service
public class RueditasServiceImp implements ServiceRueditas {

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public String[] buscarRueditas(RueditasRequest rueditasRequest) throws Exception {

        String[] rueditas = null;
        Resource resource = resourceLoader.getResource("classpath:rueditas.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dates = line.split(";");
                if (rueditasRequest.placa().equals(dates[1])) {
                    rueditas = new String[5];
                    rueditas[0] = dates[2];
                    rueditas[1] = dates[3];
                    rueditas[2] = dates[4];
                    rueditas[3] = dates[5];
                    rueditas[4] = dates[6];
                    break;
                }
            }

        } catch (IOException e) {
            throw new Exception("Error al leer el archivo de rueditas");
        }

        return rueditas;

    }
}
