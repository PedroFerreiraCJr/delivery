package com.dotofcodex.delivery.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dotofcodex.delivery.exception.PedidoInvalidoException;
import com.dotofcodex.delivery.exception.PedidoNaoEncontradoException;
import com.dotofcodex.delivery.model.Cliente;
import com.dotofcodex.delivery.model.Entrega;
import com.dotofcodex.delivery.model.Pedido;
import com.dotofcodex.delivery.model.Status;
import com.dotofcodex.delivery.repository.PedidoRepository;

@Service
public class CadastroPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private CadastroClienteService clienteService;

	public List<Pedido> buscarTodos() {
		return pedidoRepository.findAll();
	}

	public Pedido cadastrar(Pedido pedido) {
		Cliente cliente = clienteService.buscarOuFalhar(pedido.getCliente().getId());

		pedido.setCliente(cliente);
		pedido.setFrete(new BigDecimal("10.00"));
		pedido.setTotal(new BigDecimal("110.00"));

		Entrega statusInicial = Entrega.builder().status(Status.CRIADO).dataHora(LocalDateTime.now()).pedido(pedido)
				.build();

		pedido.setStatus(Arrays.asList(statusInicial));

		return pedidoRepository.save(pedido);
	}

	public Pedido atualizarStatus(Long id, String status) {
		Pedido pedido = buscarOuFalhar(id);

		List<Entrega> lista = pedido.getStatus();
		lista.sort((a, b) -> {
			if (a.getDataHora().isBefore(b.getDataHora())) {
				return -1;
			}
			if (a.getDataHora().isEqual(b.getDataHora())) {
				return 0;
			}
			return 1;
		});

		Entrega ultimo = lista.get(lista.size() - 1);
		Status atual = ultimo.getStatus();
		Status proximo = ultimo.getStatus().proximo();
		if (atual != Status.CANCELADO && Status.valueOf(status) == Status.CANCELADO) {
			Entrega e = Entrega.builder()
				.pedido(pedido)
				.status(Status.CANCELADO)
				.dataHora(LocalDateTime.now())
				.build();

			lista.add(e);

			return pedidoRepository.save(pedido);
		}
		
		if (proximo != null) {
			Entrega e = Entrega.builder()
				.pedido(pedido)
				.status(proximo)
				.dataHora(LocalDateTime.now())
				.build();

			lista.add(e);

			return pedidoRepository.save(pedido);
		}

		if (atual == Status.ENTREGUE) {
			throw new PedidoInvalidoException(String.format("O pedido '%d' já está entregue.", pedido.getId()));
		}

		if (atual == Status.CANCELADO) {
			throw new PedidoInvalidoException(String.format("O pedido '%d' foi cancelado.", pedido.getId()));
		}

		throw new PedidoInvalidoException(String.format("O pedido '%d' já está entregue.", pedido.getId()));
	}

	public Pedido buscarOuFalhar(Long pedidoId) {
		return pedidoRepository.findById(pedidoId).orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}
}
