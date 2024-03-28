package org.fiap.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fiap.entities.Endereco;
import org.fiap.entities.Endereco;
import org.fiap.repositories.EnderecoRepository;
import org.fiap.repositories.EnderecoRepository;
import org.fiap.utils.Log4jLogger;

import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("salesforcedirections")
public class EnderecoResource {
    private Log4jLogger<Endereco> logger = new Log4jLogger<>(Endereco.class);
    private EnderecoRepository enderecoRepository;

    public EnderecoResource() {
        this.enderecoRepository = new EnderecoRepository();
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @Path("endereco")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Endereco> getAllEnderecos() {
        logger.logReadAll(null);
        return enderecoRepository.ReadAll();

    }

    @POST
    @Path("endereco")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEndereco(Endereco endereco) {
        enderecoRepository.Create(endereco);
        logger.logCreate(enderecoRepository.ReadById(endereco.getId()));
        return Response.status(Response.Status.CREATED).entity(endereco).build();
    }

    @PUT
    @Path("endereco/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEndereco(@PathParam("id") int id, Endereco endereco) {
        if (enderecoRepository.ReadById(id) != null) {
            enderecoRepository.UpdateById(endereco, id);
            logger.logUpdateById(enderecoRepository.ReadById(id));
            return Response.status(Response.Status.OK).entity(endereco).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("endereco/{id}")
    public Response deleteEndereco(@PathParam("id") int id) {
        if (enderecoRepository.ReadById(id) != null) {
            logger.logDeleteById(enderecoRepository.ReadById(id));
            enderecoRepository.DeleteById(id);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("endereco/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEndereco(@PathParam("id") int id) {
        Endereco endereco = enderecoRepository.ReadById(id);
        if (endereco != null) {
            logger.logReadById(endereco);
            return Response.status(Response.Status.OK).entity(endereco).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
