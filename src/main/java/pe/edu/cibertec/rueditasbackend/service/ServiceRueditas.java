package pe.edu.cibertec.rueditasbackend.service;

import pe.edu.cibertec.rueditasbackend.dto.RueditasRequest;

public interface ServiceRueditas {
    String[] buscarRueditas(RueditasRequest rueditasRequest) throws Exception;


}
