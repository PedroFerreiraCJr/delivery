CREATE TABLE IF NOT EXISTS pedidos (
  id                BIGINT NOT NULL AUTO_INCREMENT,
  total             DECIMAL(15,2) NOT NULL,
  frete             DECIMAL(15,2) NOT NULL,
  cliente_id        BIGINT NOT NULL,

  CONSTRAINT pk_pedidos_id PRIMARY KEY (id),
  CONSTRAINT fk_pedidos_cliente_id FOREIGN KEY (cliente_id) REFERENCES clientes (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
