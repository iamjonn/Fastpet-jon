# Projeto FastPet JPA

Este projeto é um sistema de gerenciamento para uma clínica veterinária, desenvolvido para aplicar os conceitos de persistência de objetos utilizando JPA (Jakarta Persistence API) com Hibernate. A arquitetura segue um padrão de camadas para garantir a separação de responsabilidades e facilitar a manutenção.

## Tecnologias Utilizadas
* **Java**: Linguagem de programação principal.
* **JPA/Hibernate**: Framework de persistência de objetos para o mapeamento e interação com o banco de dados.
* **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional.
* **Swing**: Biblioteca para a criação da interface gráfica do utilizador (GUI).

## Estrutura do Projeto

O projeto foi refatorado para seguir uma arquitetura de camadas bem definida, conforme os seguintes pacotes:

* **`modelo`**: Contém as classes de negócio que representam os dados da aplicação (`Tutor`, `Pet`, `Servico`). A classe `Pet` foi estendida para incluir um atributo de foto.
* **`daojpa`**: A camada de acesso a dados. Contém classes DAO (Data Access Object) para cada entidade do modelo (`TutorDAO`, `PetDAO`, `ServicoDAO`), que herdam de uma classe genérica `DAO<T>`. Esta camada é responsável por todas as operações de persistência.
* **`requisito`**: A camada de lógica de negócio. A classe `Fachada` atua como a interface principal do sistema, expondo métodos de alto nível para as operações de CRUD e as consultas JPQL.
* **`appswing`**: Contém todas as classes da interface gráfica do utilizador (GUI). As telas interagem exclusivamente com a classe `Fachada`.
* **`aplicacao`**: Contém classes de teste em modo console para validar a funcionalidade da camada `Fachada` de forma rápida e direta.

A configuração de persistência é definida no arquivo `src/META-INF/persistence.xml`, que especifica as unidades de persistência para PostgreSQL e MySQL.

## Funcionalidades

As seguintes funcionalidades foram implementadas e podem ser acedidas tanto pela interface de console quanto pela GUI:

* **CRUD de Tutores**:
    * Cadastrar novos tutores com CPF, nome e telefone.
    * Listar todos os tutores cadastrados.
* **CRUD de Pets**:
    * Cadastrar novos pets com apelido, raça, tutor e foto.
    * Listar todos os pets cadastrados.
    * Excluir um pet e os seus serviços associados.
    * Alterar o apelido de um pet.
* **CRUD de Serviços**:
    * Adicionar novos serviços a um pet.
    * Listar todos os serviços.
* **Consultas JPQL**:
    * Consultar pets por raça.
    * Consultar pets que possuem mais de N serviços.
    * Consultar serviços por tipo e data.

---

## Resolução de Erros de Conexão com PostgreSQL

Se você encontrar uma exceção como `Connection to localhost:5432 refused`, isso significa que a aplicação não conseguiu conectar-se ao servidor de banco de dados. O problema não está no código Java, mas na configuração do ambiente.

Para resolver, siga estes passos para garantir que o seu banco de dados esteja a funcionar corretamente:

1.  **Verificar o Servidor PostgreSQL:** Certifique-se de que o serviço do PostgreSQL está a ser executado na sua máquina. Se o serviço não estiver ativo, a conexão será sempre recusada.
2.  **Verificar as Credenciais de Conexão:** Confirme se o nome de utilizador (`postgres`) e a palavra-passe (`ifpb`) no arquivo `src/META-INF/persistence.xml` estão corretos e correspondem às credenciais do seu banco de dados.
3.  **Verificar o Nome do Banco de Dados:** A aplicação está a tentar conectar-se ao banco de dados chamado `fastpet`. Certifique-se de que este banco de dados foi criado e existe no seu servidor PostgreSQL.
4.  **Verificar as Configurações de Firewall:** Um firewall pode estar a bloquear a comunicação entre a sua aplicação e a porta `5432`. Verifique as configurações de firewall para permitir a conexão.
