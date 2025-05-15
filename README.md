# OrderTrack 🛒🚚

O **OrderTrack** é um sistema de pedidos online distribuído que simula o processamento assíncrono de pedidos, integração via mensageria com RabbitMQ, e rastreamento em tempo real do status dos pedidos. O sistema é dividido em múltiplos serviços independentes (microserviços), cada um conteinerizado com Docker.

---

## 📦 Módulos

O projeto é dividido em três módulos principais:

| Módulo     | Descrição                                                                 |
|------------|---------------------------------------------------------------------------|
| `Order`    | Responsável pela criação e gerenciamento dos pedidos.                     |
| `Payment`  | Processa os pagamentos de forma assíncrona via RabbitMQ.                  |
| `Track`    | Permite o rastreamento em tempo real do status dos pedidos.               |

---

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.5
- Spring Web, JPA, Validation, Security
- RabbitMQ (Mensageria)
- Flyway (Migrations)
- MySQL
- Docker & Docker Compose
- Swagger/OpenAPI
- JWT (Autenticação)
- Lombok
- Mockito e JUnit (Testes)

---

## 🚀 Como executar o projeto

### Pré-requisitos

- Docker e Docker Compose instalados
- Java 21 instalado
- Maven instalado

### Passo a passo

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/OrderTrack.git
cd OrderTrack
````

2. **Suba a infraestrutura com Docker Compose:**

```bash
docker-compose up -d
```

3. **Execute os módulos (caso queira rodar fora do Docker):**

Navegue até o diretório de cada módulo (`Order`, `Payment`, `Track`) e execute:

```bash
./mvnw spring-boot:run
```

4. **Acesse os serviços:**

* Swagger UI: `http://localhost:{porta}/swagger-ui.html`
* RabbitMQ Management: `http://localhost:15672` (usuário: `guest`, senha: `guest`)

---

## 🧪 Testes

Para executar os testes automatizados:

```bash
./mvnw test
```

---

## 🔄 Comunicação entre módulos

* Os serviços se comunicam via **RabbitMQ**, onde:

  * `Order` envia pedidos.
  * `Payment` escuta a fila e processa os pagamentos.
  * `Track` atualiza o status com base nos eventos.

---

## 🔐 Segurança

* Implementação de autenticação com JWT
* Integração com Spring Security
* Testes de segurança com `spring-security-test`

---

## 📚 Documentação da API

As APIs de cada serviço estão documentadas usando **Springdoc OpenAPI** e disponíveis em:

```
http://localhost:{porta}/swagger-ui.html
```

---

## 🧳 Estrutura do projeto

```
OrderTrack/
├── pom.xml               # POM principal (multi-módulo)
├── Order/                # Módulo de pedidos
├── Payment/              # Módulo de pagamentos
└── Track/                # Módulo de rastreamento
```

---

## 🧑‍💻 Autor

* Desenvolvido por ***Leonardo Chiarelli***


## 📝 Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
