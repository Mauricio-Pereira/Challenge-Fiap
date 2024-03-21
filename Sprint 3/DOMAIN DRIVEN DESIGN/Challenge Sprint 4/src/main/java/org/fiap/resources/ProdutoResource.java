package org.fiap.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.fiap.entities.Produto;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("salesforcedirections")
public class ProdutoResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("produto")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto getProduto() {
        return new Produto(1, "Produto 1", 10.0, 100);
    }

    @POST
    @Path("produto")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProduto(Produto produto) {
        System.out.println(produto);
    }

}
