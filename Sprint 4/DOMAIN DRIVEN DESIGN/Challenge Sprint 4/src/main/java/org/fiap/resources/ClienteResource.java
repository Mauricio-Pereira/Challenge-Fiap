package org.fiap.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fiap.entities.Cliente;
import org.fiap.entities.Endereco;
import org.fiap.entities.LoginRequest;
import org.fiap.entities.Produto;
import org.fiap.repositories.ClienteRepository;
import org.fiap.repositories.EnderecoRepository;
import org.fiap.services.ClienteService;
import org.fiap.utils.Log4jLogger;

import java.util.List;
import java.util.Map;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("salesforcedirections")
public class ClienteResource {
    private Log4jLogger<Cliente> logger = new Log4jLogger<>(Cliente.class);
    private ClienteRepository clienteRepository;
    private ClienteService clienteService;
    private EnderecoRepository enderecoRepository = new EnderecoRepository();


    public ClienteResource() {
        this.clienteRepository = new ClienteRepository();
        this.clienteService = new ClienteService();
        this.enderecoRepository = new EnderecoRepository();
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

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        try {
            Cliente cliente = clienteService.authenticate(loginRequest.getEmail(), loginRequest.getSenha());
            logger.logReadById(cliente);
            return Response.status(Response.Status.OK).entity(cliente).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("cliente/id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClienteIdByEmail(@QueryParam("email") String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente != null) {
            return Response.status(Response.Status.OK).entity(cliente.getId()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
        }
    }

    @POST
    @Path("cliente-endereco")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClienteComEndereco(Map<String, Object> payload) {
        try {
            Cliente cliente = new Cliente();
            Endereco endereco = new Endereco();

            // Cliente
            cliente.setNome((String) payload.get("nome"));
            cliente.setSobrenome((String) payload.get("sobrenome"));
            cliente.setDataNascimento((String) payload.get("dataNascimento"));
            cliente.setTelefone((String) payload.get("telefone"));
            cliente.setEmail((String) payload.get("email"));
            cliente.setUsuario((String) payload.get("usuario"));
            cliente.setSenha((String) payload.get("senha"));

            clienteRepository.Create(cliente);

            // Endereço
            endereco.setCep((String) payload.get("cep"));
            endereco.setLogradouro((String) payload.get("rua"));
            endereco.setNumero((String) payload.get("numero"));
            endereco.setComplemento((String) payload.get("complemento"));
            endereco.setBairro((String) payload.get("bairro"));
            endereco.setCidade((String) payload.get("cidade"));
            endereco.setEstado((String) payload.get("estado"));
            endereco.setPais((String) payload.get("pais"));
            endereco.setCliente(cliente);

            enderecoRepository.Create(endereco);

            logger.logCreate(cliente);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
