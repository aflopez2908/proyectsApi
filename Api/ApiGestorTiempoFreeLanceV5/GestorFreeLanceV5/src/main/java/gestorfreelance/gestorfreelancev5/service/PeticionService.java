package gestorfreelance.gestorfreelancev5.service;


import gestorfreelance.gestorfreelancev5.DTO.PeticionDTO;
import gestorfreelance.gestorfreelancev5.DTO.ProyectoDTO;
import gestorfreelance.gestorfreelancev5.exception.InvalidDataException;
import gestorfreelance.gestorfreelancev5.exception.ProyectoTerminadoException;
import gestorfreelance.gestorfreelancev5.exception.ResourceNotFoundException;
import gestorfreelance.gestorfreelancev5.model.*;
import gestorfreelance.gestorfreelancev5.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PeticionService {

    @Autowired
    PeticionRepository peticionRepository;

    @Autowired
    ClientesRepository clientesRepository;

    @Autowired
    private PeticionEstadoRepository peticionEstadoRepository;
    @Autowired
    private EstadoPeticionRepository estadoPeticionRepository;


    @Autowired
    private TipoPeticionRepository tipoPeticionRepository;


    public List<Peticion> getAllPeticiones() {
        return peticionRepository.findAll();
    }

    public Peticion obtenerPeticionPorId(Long peticion_id) {
        return peticionRepository.findById(peticion_id)
                .orElseThrow(() -> new ResourceNotFoundException("Peticion no encontrada con id: " + peticion_id));
    }


    public List<PeticionEstado> cambioEstado(int id,int estado) {
        Peticion peticion= peticionRepository.findByIdPeticion((long) id);
        //falta poder registrar el estado terminado
        //falta no permitir pasar a estado creado

        if (peticion == null) {
            throw new ResourceNotFoundException("peticion no encontrada con id: " + id);
        }

        if (estadoPeticionRepository.findById((long) estado) == null) {
            throw new IllegalArgumentException("El estado proporcionado no es válido. Por favor, elija un estado correcto.");
        }

        // estado terminado de la peticion
        boolean consulta =peticionEstadoRepository.existsByPeticionIdAndPeticionEstadoIdEqualsTwo(peticion.getIdPeticion());
        if(consulta == true) {
            throw new ProyectoTerminadoException("La peticion esta en  fue terminado y no puede cambiar de estado");
        }

        EstadoPeticion estadoPeticion = estadoPeticionRepository.findById(Long.valueOf(estado))
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado con el ID: " + estado));

        // actualizar la vigencia en la tabla vigencia
        peticionEstadoRepository.actualizarVigenciaPeticion((long) id);
        //registro del estado con la funcion registrar estado
        registrarEstado(peticion,estadoPeticion , estadoPeticion.getNombreEstado(),1);

        // Obtener el historial de estados para la petición dada
        List<PeticionEstado> historialPeticiones = peticionEstadoRepository.findHistorialByPeticionId((long) id);

        return historialPeticiones;

    }

    public Peticion crearPeticion(PeticionDTO peticion) {
        validateFieldsNotNull(peticion);
        // validar si el cliente existe
        int clienteId = peticion.getIdCliente().intValue();

        Cliente cliente = clientesRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente con ID " + clienteId + " no encontrado"));

        //encontrar el tipo de peticion
        EstadoPeticion estadoPeticion = estadoPeticionRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("EstadoPeticion con ID 1 no encontrado"));

        TipoPeticion tipoPeticion = tipoPeticionRepository.findById(peticion.getIdTipoPeticion().intValue())
                .orElseThrow(() -> new EntityNotFoundException("TipoPeticion con ID "+ peticion.getIdTipoPeticion() + " no encontrado"));

        Peticion peticionNormal = new Peticion();
        peticionNormal.setCliente(cliente);
        peticionNormal.setTipoPeticion(tipoPeticion);
        peticionNormal.setComentarioPeticion(peticion.getComentarioPeticion());

        peticionRepository.save(peticionNormal);
        System.out.println("peticion"+peticion);
        registrarEstado(peticionNormal, estadoPeticion,"creacion de peticion",1);

        return peticionNormal;
    }

    public void validateFieldsNotNull(PeticionDTO peticion) {
        for (Field field : peticion.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object value = field.get(peticion);
                if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
                    throw new InvalidDataException("El campo '" + field.getName() + "' no puede estar vacío o nulo.");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al acceder a los campos de ProyectoDTO");
            }
        }
    }

    private void registrarEstado(Peticion peticion, EstadoPeticion estado, String comentario, int vigencia) {
        PeticionEstado peticionEstado = new PeticionEstado();
        peticionEstado.setPeticion(peticion);
        peticionEstado.setEstadoPeticion(estado);
        peticionEstado.setFechaCambio(LocalDate.now());
        peticionEstado.setComentario(comentario);
        peticionEstado.setVigencia(vigencia);
        peticionEstadoRepository.save(peticionEstado);
    }


}
