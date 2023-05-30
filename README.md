# Delivery

A aplicação foi desenvolvida utilizando o banco de dados MySQL, portanto, é necessário um banco de dados rodando
na porta padrão (3306). Será preciso alterar o usuário e senha do banco de dados.

O registro de um novo usuário, deve ser feito na URI, /api/register. Segue exemplo de utilização:

```json
{
    "nome": "Pedro Junior",
    "email": "pedroferreiracjr@gmail.com",
    "password": "@P3dr0.060392"
}
```

Após o registro do novo usuário, será preciso obter um token válido para usar nos outros recursos da API.

Para obter um novo token para um usuário previamente cadastrado, basta informar, **email** e **password**, segue exemplo:

```json
{
    "email": "pedroferreiracjr@gmail.com",
    "password": "@P3dr0.060392"
}
```

Para acessar qualquer dos endpoints protegidos, basta utilizar o cabeçalho **Authorization**, fornecendo o token
previamente gerado, como sendo um **Bearer <Token>**.

Recursos restantes:
	
	GET /clientes - Listagem de clientes;
	GET /clientes/1 - Busca de cliente por id;
	POST /clientes - Criação de clientes - **Deprecated**;
	PUT /clientes/1 - Atualização do recurso de clientes;
	GET /pedidos - Listagem de pedidos;
	GET /pedidos/1 - Busca de pedido por id;
	POST /pedidos - Criação de pedido - Basta informar o **clienteId**;

```json
{
    "clienteId": 3
}
```
	PUT /pedidos/1/CONFIRMAR - Atualização de determinado pedido;
	GET /entregas/1 - Listagem dos status para o pedido 1;
```json
[
    {
        "status": "CRIADO",
        "dataHora": "2023-05-30T16:40:43"
    },
    {
        "status": "CONFIRMADO",
        "dataHora": "2023-05-30T16:42:17"
    },
    {
        "status": "A_CAMINHO",
        "dataHora": "2023-05-30T16:42:48"
    },
    {
        "status": "ENTREGUE",
        "dataHora": "2023-05-30T16:43:02"
    }
]
```