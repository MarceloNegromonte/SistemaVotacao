# SistemaVotacao

# Pré-Requisitos:
- Java 11+
- Docker

# Instalação:
Clone o repositório: 
- https://github.com/MarceloNegromonte/SistemaVotacao.git

Se caso estiver rodando no seu computador o mysql, vá em serviços e pare a aplicação.

Depois na pasta clonada abra o terminal e rode o comando docker:
- docker compose up

Depois volte a rodar o mysql.

OBS: na IDE de sua escolha, rode o projeto.

# Acesso das principais funções: 
Login e senha mysql:
- Login: root
- Senha: root

Usuário em memória no banco:
- usuario: admin@email.com
- senha: 12345

# Link Swagger:
- http://localhost:8080/swagger-ui/index.html

# Desafio Solutis Scholl

# Implemente uma solução utilizando JAVA 11 e [SpringBoot](https://spring.io/) conforme descrição abaixo.</br>
**ATENÇÂO**, faça a **LEITURA COMPLETA** deste documento.

## Tarefa Principal
Em uma cooperativa, cada associado possui um voto e as decisões são tomadas através de assembleias, por votação. A partir disso, você precisa criar o backend para gerenciar essas sessões de votação. A solução deve atender os seguintes requisitos através de uma API REST:

- (RF0) Autenticação e autorização.
  - Todo usuário, seja ele administrador ou cooperado (pessoa que votam) deve estar devidamente autenticado para operar o sistema. (Utilize bearer token JWT).
  - Usuários administradores podem realizar todas as operações do sistema. 
  - Usuários cooperados podem apenas votar. 
  - Deve ser possível adicionar, alterar ou excluir usuários.
  - Os dados requisitados para cadastro de usuário são: CPF, nome tipo (administrador ou cooperado) e e-mail.

- (RF1) Cadastrar pauta.
  - A pauta pode ser apenas cadastrada.
  - Toda pauta deve conter um nome da pauta e uma descrição.

- (RF2) Abrir uma sessão de votação para uma pauta.
  - Cada pauta deve comportar apenas uma sessão de votação. 
  - A sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por padrão.

- (RF3) Receber votos dos cooperados em pautas abertas. 
  - Os votos são apenas 'Sim'/'Não'.
  - Cada cooperado é identificado por um id único e pode votar apenas uma vez por pauta. 
  - Registre a data/hora do voto.

- (RF4) Contabilizar os votos e dar o resultado da votação na pauta.
  - Exibir vencedor por maioria simples.
  - Exibir quantidade de votos para cada um dos grupos 'Sim'/'Não'.
  - Exibir percentual para cada um dos grupos 'Sim'/'Não'.

Implemente testes unitários com ao menos 50% de cobertura de código.

Utilize [Swagger](https://swagger.io/) para documentar sua API.

Disponibilize uma coleção do [Postman](https://www.postman.com/) com os endpoints da API.

# Tarefas Bônus feitas:
-  Tarefa Bônus 1 - Integração com sistemas externos
Integrar com um sistema externo que verifica o CPF antes de cadastrar um usuário. Caso o sistema esteja indisponível, você deve fazer mais duas tentativas (retry) no intervalo de 2 e 4 segundos respectivamente (você deve registrar as tentativas no log). Se ainda assim o serviço externo esteja indisponível, permita o cadastro do usuário, mas registre no log que o serviço não pode ser consultado. OBS: O serviço só verifica se foram passados 11 dígitos ou não, veja exemplos de requisição e retornos abaixo.

- Tarefa Bônus 2 - Contabilização automática
A contabilização de votos de pautas encerradas (RF4), deve ser feita de forma automática pelo sistema. A rotina de contabilização deve ser executada a cada minuto. Os dados devem ser persistidos no banco de dados.

-  Tarefa Bônus 7 - Cache
Os endpoint de listagem e consulta de usuários devem utilizar sistema de cache, a duração do cache deve ser de 10 segundos. Exemplo: seu consultar os dados de um usuário do id 1 duas ou mais vezes no intervalo inferior a 10 segundos a aplicação deve retornar os dados da cache e não do banco de dados.
