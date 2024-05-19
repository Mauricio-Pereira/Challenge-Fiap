package org.fiap.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fiap.entities.Cliente;
import org.fiap.entities.Produto;
import org.fiap.repositories.ClienteRepository;
import org.fiap.repositories.ProdutoRepository;
import org.fiap.services.ProdutoService;
import org.fiap.utils.Log4jLogger;
import org.fiap.utils.Logger;

import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("salesforcedirections")
public class ProdutoResource {
    private Log4jLogger<Produto> logger = new Log4jLogger<>(Produto.class);
    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    public ProdutoResource() {
        this.produtoRepository = new ProdutoRepository();
        this.produtoService = new ProdutoService();
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @Path("produto")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> getAllProdutos() {
        logger.logReadAll(null);
        return produtoRepository.ReadAll();
    }

    @POST
    @Path("produto/{clienteId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduto(@PathParam("clienteId") int clienteId, Produto produto) {
        try {
            produtoService.Create(produto, clienteId);
            logger.logCreate(produtoRepository.ReadById(produto.getId()));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("produto/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduto(@PathParam("id") int id, Produto produto) {
       try {
           produtoService.UpdateById(produto, id);
           logger.logUpdateById(produtoRepository.ReadById(id));
           return Response.status(Response.Status.OK).build();
       } catch (Exception e) {
           return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
       }
    }

    @DELETE
    @Path("produto/{id}")
    public Response deleteProduto(@PathParam("id") int id) {
        if (produtoRepository.ReadById(id) != null){
            logger.logDeleteById(produtoRepository.ReadById(id));
            produtoRepository.DeleteById(id);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("produto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduto(@PathParam("id") int id) {
        Produto produto = produtoRepository.ReadById(id);
        if (produto != null) {
            logger.logReadById(produto);
            return Response.status(Response.Status.OK).entity(produto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
