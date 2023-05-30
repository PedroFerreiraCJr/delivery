CREATE TABLE IF NOT EXISTS clientes (
  id                BIGINT NOT NULL AUTO_INCREMENT,
  nome              VARCHAR(50) NOT NULL,
  email             VARCHAR(50) NOT NULL,
  password          VARCHAR(255) NOT NULL,
  
  CONSTRAINT pk_clientes_id PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
