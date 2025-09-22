# 🛒 E-commerce Microservices

Um sistema de e-commerce construído com **arquitetura de microsserviços**, comunicação assíncrona via **Apache Kafka**, integração entre serviços e infraestrutura containerizada com **Docker**.
O projeto foi desenvolvido como parte do exercicio do curso de **Arquitetura de Microservices com Spring Boot e Apache Kafka**, para demonstrar **boas práticas de arquitetura distribuída**, escalabilidade e desacoplamento.

---

## 🏗 Arquitetura

O sistema segue o padrão **event-driven** com microsserviços independentes, cada um responsável por um domínio específico.  
A comunicação entre serviços ocorre via **Apache Kafka** (eventos) e **APIs REST** (consultas diretas).

**Visão geral:**
```
[orders-service] → Feign → [products-service] → Feign → [customers-service]
↓
Webhooks ↔ [gateway de pagamento simulado] → Kafka
↓
[invoicing-service] → MinIO (relatórios) → Kafka → [logistic-service] → emissão do código de rastreio
```

📝 **Explicação do fluxo:**

1. `orders-service` consulta `products-service` e `customers-service` via **Feign Clients** para validação do pedido.
2. Webhook envia dados para o **gateway de pagamento simulado**, que processa o pagamento e publica eventos no **Kafka**.
3. `invoicing-service` consome eventos de pagamento e gera notas fiscais, armazenando relatórios no **MinIO**.
4. Eventos de fatura também são enviados via Kafka para `logistic-service`, que processa a logística e emite o **código de rastreio**.


## 🛠 Tecnologias


- **Java 21** + **Spring Boot**
- **Apache Kafka** (mensageria e comunicação assíncrona)
- **Docker & Docker Compose**
- **Spring Data JPA** (PostgreSQL / MongoDB)
- **Feign Client** (integração entre serviços)
- **Webhooks** (notificações externas)
- **MinIO** (armazenamento de objetos compatível com S3)
- **Jasper Reports** (relatórios PDF/Excel)
- **JWT / OAuth2** (autenticação e autorização)


---


## 📦 Serviços


| Serviço | Descrição |
|-----------------------|-----------|
| **customers-service** | Cadastro e gestão de clientes |
| **products-service** | Catálogo e informações de produtos |
| **orders-service** | Criação e gerenciamento de pedidos |
| **logistic-service** | Processamento de logística e entregas |
| **invoicing-service** | Faturamento e geração de notas fiscais |
| **infrastructure-service** | Serviços de suporte e integrações |


---

## 🔄 Fluxo de Comunicação

1. **Cliente cria pedido** → `orders-service`
2. Webhook de pagamento simulado notifica `orders-service`
3. `orders-service` publica evento **orders-paid** no Kafka
4. **invoicing-service** consome evento e gera nota fiscal (Jasper Reports)
5. Relatório é armazenado no **MinIO**
6. **logistic-service** consome evento e inicia processo de entrega gerando o rastreio

---

## 🎓 Aprendizado do Curso
- **Webhooks**: integração e comunicação entre sistemas externos via HTTP
- **Cloud Buckets com MinIO**: armazenamento, recuperação e gerenciamento de arquivos compatível com Amazon S3
- **Jasper Reports**: criação de relatórios dinâmicos e profissionais integrados aos microservices


**Aprendizado:**
- Dominar a arquitetura de microservices e comunicação entre sistemas distribuídos
- Implementar fluxos de eventos com Kafka e tópicos particionados
- Gerenciar bancos de dados independentes e arquivos em Cloud Buckets
---

## 🚀 Como Executar


```bash
# 1. Clonar repositório
git clone https://github.com/CaiquePirs/ecommerce-microservices.git
cd ecommerce-microservices


# 2. Subir infraestrutura (Kafka, MinIO, DBs)
docker-compose up -d


# 3. Rodar cada serviço
cd customers-service && mvn spring-boot:run
cd ../products-service && mvn spring-boot:run
# ... repetir para os demais serviços
```

---

## 📡 Eventos Kafka

| Evento | Produtor | Consumidor(es) | Descrição |
|------------------|----------------|------------------------|--------------------|
| OrderCreated | orders-service | logistic-service, invoicing-service | Pedido criado |
| OrderShipped | logistic-service| orders-service | Pedido enviado |
| InvoiceGenerated | invoicing-service | orders-service | Nota fiscal gerada |

---
