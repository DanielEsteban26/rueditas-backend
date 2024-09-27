package pe.edu.cibertec.rueditasbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.rueditasbackend.dto.RueditasRequest;
import pe.edu.cibertec.rueditasbackend.dto.RueditasResponse;
import pe.edu.cibertec.rueditasbackend.service.ServiceRueditas;

@RestController
@RequestMapping("/rueditas")
public class RueditasController {

    @Autowired
    ServiceRueditas serviceRueditas;

    /**
     * Endpoint para buscar información de "rueditas" por placa.

     * rueditasRequest Objeto que contiene la placa a buscar.
     * return RueditasResponse con la información encontrada o un mensaje de error.
     */
    @PostMapping("/buscar")
    public RueditasResponse buscarRueditas(@RequestBody RueditasRequest rueditasRequest) {
        try {
            // Llama al servicio para buscar la información de "rueditas" por placa.
            String[] dates = serviceRueditas.buscarRueditas(rueditasRequest);

            // Si no se encuentra la placa, devuelve una respuesta indicando que no se encontró.
            if (dates == null) {
                return new RueditasResponse("01", "Placa no Encontrada", null, null, null, null, null);
            } else {
                // Si se encuentra la placa, devuelve una respuesta con la información encontrada.
                return new RueditasResponse("00", "Placa Encontrada", dates[0], dates[1], dates[2], dates[3], dates[4]);
            }
        } catch (Exception e) {
            // En caso de error, devuelve una respuesta con un mensaje de error.
            return new RueditasResponse("99", "Error 404", null, null, null, null, null);
        }
    }
}