# Sobre :blue_book:
Desenvolver uma solução para o agronegócio que coleta dados via sensores de temperatura e umidade. Esses sensores ficarão instalados em um drone com uma altíssima autonomia
de energia, pois conta com pequenos, porém muito eficientes painéis fotovoltaicos.
A cada 10 segundos são enviados para o message broker os dados de temperatura e umidade capturado naquele instante. Além da primeira função também é possível acionar o rastreamento do drone. Esta funcionalidade resulta na localização do equipamento em um mapa. A estruturação do código é em MVC cujos packages são separados por contexto de negócio, onde a codificação foi feita seguindo as exigências: codificação de visualização do desenvolvedor em inglês, texto de exibição para o cliente em português e seguir os principios S(Single Responsibility Principle) e O (Open Closed Principle) do paradigma O.O. Por familiaridade a linguagem adotada foi o Java 11 com maven conectando-se ao Postgresql, um banco relacional open source. Para facilitar no gerenciamento dos scripts em banco foi escolhido o Flyway e para documentação da API o Swagger. Para praticar a ideia de low code foi usado o Lombok junto ao SpringBoot. Na comunicação com o cliente via e-mail foi escolhida a G-MAIL API, onde possui uma integração e segurança de autenticação já validada pelo mercado.

# Diagrama :gear:
![Database Schema](wiki/diagram/GestãoAgroTech.svg)

# Tech Stack :clipboard:
Front-end:
- Angular 8

Back-end:
- SpringBoot
- Apache-Kafka
- Java 11
- Postgres
- Lombok
- Jackson-databind
- Gmail-api
- Docker
- JUnit

# Executando o projeto :computer:
- Passo1: Escolha escolha a pasta para download do projeto e execute o clone;
```
git clone https://github.com/rickdevbr-io/AgroTech
```
- Passo2: Dentro da pasta Agrotech\front\front-agro, instale o node module caso não exista a pasta;
```
npm install
```
- Passo3: Dentro da pasta front-agro execute o comando de start;
```
npm run start
```
- Passo4: abra o browser e chame a aplicação:
```
http://localhost:4200
```
- Passo5: Com o intelij ou STS configure os e-mails remetente e destino;
no arquivo application.properties altere o valor da spring.mail.username e spring.mail.password (autenticação do email remetente)
na classe ConsumerService altere MAIL para o e-mail de destion

- Passo6: execute o boot do proveto
```
mvn run
```
- Passo7: conect-se com o h2 (apenas clique no conect) e rode o scritp data.sql
```
http://localhost:8080/api/h2
```
