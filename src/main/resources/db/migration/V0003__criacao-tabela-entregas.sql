CREATE TABLE IF NOT EXISTS entregas (
  id                BIGINT NOT NULL AUTO_INCREMENT,
  status            VARCHAR(50) NOT NULL,
  data_hora         DATETIME NOT NULL,
  pedido_id         BIGINT NOT NULL,
  
  CONSTRAINT pk_entregas_id PRIMARY KEY (id),
  CONSTRAINT fk_entregas_pedido_id FOREIGN KEY (pedido_id) REFERENCES pedidos (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
