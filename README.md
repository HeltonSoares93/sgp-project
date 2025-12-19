# SGP API - Sistema de Gestão de Projetos

API RESTful desenvolvida com Java e Spring Boot para o gerenciamento de projetos, tarefas e usuários. O sistema implementa relacionamentos entre entidades, validação de dados, tratamento de exceções global e padrão DTO para respostas da API.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Validation** (Bean Validation)
- **Lombok**
- **Maven**
- **Banco de Dados:** MySQL

## Arquitetura e Padrões de Projeto

O projeto segue uma arquitetura em camadas bem definida para garantir a separação de responsabilidades e a facilidade de manutenção:

- **Controller:** Camada responsável apenas por receber as requisições HTTP e retornar as respostas (ResponseEntity).
- **Service:** Camada que contém toda a regra de negócio (cálculos, validações lógicas, chamadas ao repositório).
- **Repository:** Interface de comunicação com o banco de dados via Spring Data JPA.
- **DTO (Data Transfer Object):** Utilizado na entidade `Usuario` para formatar a saída de dados, protegendo informações sensíveis (máscara de CPF) e entregando dados calculados (Idade) sem persistência desnecessária.
- **Global Exception Handler:** Tratamento centralizado de erros utilizando `@ControllerAdvice`, garantindo que erros de validação (`MethodArgumentNotValidException`) e regras de negócio (`UsuarioNaoEncontradoException`) retornem JSONs padronizados e status HTTP corretos.

## Funcionalidades Principais

### Usuários
- CRUD completo (Criar, Ler, Atualizar, Deletar).
- Busca de usuário por CPF.
- Validação de campos obrigatórios (CPF e Email únicos).
- **Lógica de DTO:** Ao buscar um usuário, o sistema retorna a idade calculada automaticamente com base na data de nascimento e aplica uma máscara no CPF.

### Projetos
- Gerenciamento de projetos vinculados a um usuário responsável.
- Definição de status (ATIVO, CONCLUIDO, CANCELADO).
- Listagem de projetos por status ou data de conclusão.

### Tarefas
- Criação de tarefas vinculadas a um projeto e a um usuário.
- Classificação por prioridade (BAIXA, MEDIA, ALTA) e status (PENDENTE, FAZENDO, FEITO).
- Filtros de busca por prioridade, status e agrupamento por projeto.

## Endpoints da API

### Usuários
- `GET /usuarios` - Lista todos os usuários.
- `GET /usuarios/{id}` - Busca usuário por ID (Retorna DTO).
- `GET /usuarios/busca?cpf={cpf}` - Busca usuário por CPF.
- `POST /usuarios` - Cadastra um novo usuário.
- `PUT /usuarios/{id}` - Atualiza um usuário existente.
- `DELETE /usuarios/{id}` - Remove um usuário.

### Projetos
- `GET /projetos` - Lista todos os projetos.
- `GET /projetos/{id}` - Detalhes de um projeto específico.
- `POST /projetos` - Cria um novo projeto.
- `PUT /projetos/{id}` - Atualiza um projeto.
- `DELETE /projetos/{id}` - Exclui um projeto.

### Tarefas
- `GET /tarefas` - Lista todas as tarefas.
- `POST /tarefas` - Cria uma nova tarefa.
- `GET /tarefas/busca-status?status={STATUS}` - Filtra tarefas por status.
- `GET /tarefas/busca-prioridade?prioridade={PRIORIDADE}` - Filtra tarefas por prioridade.
- `GET /tarefas/buscar-projeto?idProjeto={id}` - Lista tarefas de um projeto específico.

## Como Executar

Pré-requisitos: Java 17 e Maven instalados.

1. Clone o repositório:
   ```bash
   git clone [https://github.com/seu-usuario/sgp-api.git](https://github.com/seu-usuario/sgp-api.git)