package org.fiap.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fiap.entities.Cliente;
import org.fiap.entities.Produto;
import org.fiap.repositories.ClienteRepository;
import org.fiap.services.ClienteService;
import org.fiap.utils.Log4jLogger;

import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("salesforcedirections")
public class ClienteResource {
    private Log4jLogger<Cliente> logger = new Log4jLogger<>(Cliente.class);
    private ClienteRepository clienteRepository;
    private ClienteService clienteService;


    public ClienteResource() {
        this.clienteRepository = new ClienteRepository();
        this.clienteService = new ClienteService();
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @Path("cliente")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getAllClientes() {
        logger.logReadAll(null);
        return clienteRepository.ReadAll();
    }

    @POST
    @Path("cliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCliente(Cliente cliente) {
        try {
            clienteService.Create(cliente);
            logger.logCreate(clienteRepository.ReadById(cliente.getId()));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("cliente/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCliente(@PathParam("id") int id, Cliente cliente) {
       try {
           clienteService.UpdateById(cliente, id);
           logger.logUpdateById(clienteRepository.ReadById(id));
           return Response.status(Response.Status.OK).build();
       } catch (Exception e) {
           return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
       }
    }

    @DELETE
    @Path("cliente/{id}")
    public Response deleteCliente(@PathParam("id") int id) {
        if (clienteRepository.ReadById(id) != null) {
            logger.logDeleteById(clienteRepository.ReadById(id));
            clienteRepository.DeleteById(id);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("cliente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliente(@PathParam("id") int id) {
        Cliente cliente = clienteRepository.ReadById(id);
        if (cliente != null) {
            logger.logReadById(cliente);
            return Response.status(Response.Status.OK).entity(cliente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
