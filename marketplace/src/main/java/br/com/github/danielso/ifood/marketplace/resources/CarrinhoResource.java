package br.com.github.danielso.ifood.marketplace.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import br.com.github.danielso.ifood.marketplace.commons.Constants;
import br.com.github.danielso.ifood.marketplace.dto.PedidoRealizadoDTO;
import br.com.github.danielso.ifood.marketplace.dto.PratoDTO;
import br.com.github.danielso.ifood.marketplace.dto.PratoPedidoDTO;
import br.com.github.danielso.ifood.marketplace.dto.RestauranteDTO;
import br.com.github.danielso.ifood.marketplace.entities.Prato;
import br.com.github.danielso.ifood.marketplace.entities.PratoCarrinho;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

@Path(Constants.API_VERSION + Constants.REST_CARRINHO)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoResource {

	private static final String CLIENTE = "a";

    @Inject
    PgPool client;

    @Inject
    @Channel("pedidos")
    Emitter<PedidoRealizadoDTO> emitterPedido;

    @GET
    public Uni<List<PratoCarrinho>> buscarcarrinho() {
        return PratoCarrinho.findCarrinho(client, CLIENTE);
    }

    @POST
    @Path("/prato/{idPrato}")
    public Uni<Long> adicionarPrato(@PathParam("idPrato") Long idPrato) {
        //poderia retornar o pedido atual
        PratoCarrinho pc = new PratoCarrinho();
        pc.cliente(CLIENTE).prato(idPrato);
        return PratoCarrinho.save(client, CLIENTE, idPrato);

    }

    @POST
    @Path("/realizar-pedido")
    public Uni<Boolean> finalizar() {
        PedidoRealizadoDTO pedido = new PedidoRealizadoDTO();
        String cliente = CLIENTE;
        pedido.cliente = cliente;
        List<PratoCarrinho> pratoCarrinho = PratoCarrinho.findCarrinho(client, cliente).await().indefinitely();
        //Utilizar mapstruts
        List<PratoPedidoDTO> pratos = pratoCarrinho.stream().map(pc -> from(pc)).collect(Collectors.toList());
        pedido.pratos = pratos;

        RestauranteDTO restaurante = new RestauranteDTO();
        restaurante.nome = "nome restaurante";
        pedido.restaurante = restaurante;
        
        emitterPedido.send(pedido).whenComplete((success, failure) -> {
            if (failure != null) {
                System.out.println("D'oh! " + failure.getMessage());
            } else {
                System.out.println("Message processed successfully");
            }
        });;
        return PratoCarrinho.delete(client, cliente);
    }

    private PratoPedidoDTO from(PratoCarrinho pratoCarrinho) {
        PratoDTO dto = Prato.findById(client, pratoCarrinho.getPrato()).await().indefinitely();
        return new PratoPedidoDTO(dto.getNome(), dto.getDescricao(), dto.getPreco());
    }
    
}
