package br.com.github.danielso.ifood.pedido.kafka;

import br.com.github.danielso.ifood.pedido.dto.PedidoRealizadoDTO;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class PedidoDeserializer extends ObjectMapperDeserializer<PedidoRealizadoDTO> {

    public PedidoDeserializer() {
        super(PedidoRealizadoDTO.class);
    }

}