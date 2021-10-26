package br.com.github.danielso.ifood.pedido.incoming;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import org.jboss.logging.Logger;

import org.bson.types.Decimal128;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import br.com.github.danielso.ifood.pedido.dto.PedidoRealizadoDTO;
import br.com.github.danielso.ifood.pedido.dto.PratoPedidoDTO;
import br.com.github.danielso.ifood.pedido.entities.Pedido;
import br.com.github.danielso.ifood.pedido.entities.Prato;
import br.com.github.danielso.ifood.pedido.entities.Restaurante;
import br.com.github.danielso.ifood.pedido.service.ESService;

@ApplicationScoped
public class PedidoRealizadoIncoming {

	private static final Logger LOG = Logger.getLogger(PedidoRealizadoIncoming.class);

	@Inject
    ESService elastic;
	
	@Incoming("pedidos")
	public void lerPedidos(PedidoRealizadoDTO dto) {
		LOG.info("--------------- @Incoming ------------------");
		String json = JsonbBuilder.create().toJson(dto);

        LOG.info(json);

		Pedido p = new Pedido();
		p.setCliente(dto.cliente);
		p.setPratos(new ArrayList<>());
		dto.pratos.forEach(prato -> p.getPratos().add(from(prato)));
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(dto.restaurante.nome);
		p.setRestaurante(restaurante);

        elastic.index("pedidos", json);
        
		p.persist();
	}

	private Prato from(PratoPedidoDTO prato) {
		Prato p = new Prato();
		p.setDescricao(prato.descricao);
		p.setNome(prato.nome);
		p.setPreco(new Decimal128(prato.preco));
		return p;
	}
}
