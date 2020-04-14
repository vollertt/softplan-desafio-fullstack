
Back-end (caracteristica e dependencias). Projeto Spring Boot Web Maven.
  1) Spring Boot para selecionar tipo de projeto, tecnologias e depend�ncias utilizadas e criar o projeto inicial do sistema.
  2) Spring DevTools
  3) Spring JPA
  4) Banco de dados PostgresSQL
  5) Spring Swagger 

Fron-End
  1) Framework Vue.js (dependencias das bibliotecas e do servidor Node.js instalado) 
  2) axios como um cliente HTTP para consumir os servi�os REST da api


A aplica��o do desafio utiliza dois projetos diferentes

1) O back-end (pasta "apirest", criada com IDE Eclipse e projeto Maven), que implementou servi�os REST utilizando JPA e Spring
   aos modelos de entidade criados conforme a modelagem do sistema e regras.

2) O front-end desenvolvido da aplica��o, est� localizado na pasta src/main/front-end. Utilizou o framework Vue.js


======================================================


Desenvolvimento em ambiente windows com IDE Eclipse


Foi utilizado as depend�ncias conforme arquivo pom.xml (da aplica��o back-end "apirest") associados ao Maven


Back-end projeto java Spring Boot Maven "apirest"

- Com o Devtools utilizado, j� possui o tomcat e roda por default na porta 8080
- requisi��es da api get, put e post testadas com o Postman, assim como com a implementa��o utilizando
  o Swagger (http://localhost:8080/swagger-ui.html ) que contem detalhe das requisi��es



Prepara��o do ambiente para o front-end

- Instala��o do Eclipse
- Instala��o do Git
- Instala��o do servidor Node ( https://nodejs.org/en/download/ )
- Instala��o do Vue ( https://cli.vuejs.org/guide/installation.html )
- cria um projeto Vue.js (  https://cli.vuejs.org/guide/creating-a-project.html#vue-create  )
- instala axios JS como dependencia do projeto ( na pasta do projeto criado de front-end  roda comando : 
                                                 npm install axios --save )
- copiar os arquivos da pasta apirest/src/main/front-end/softplan-desafio-front/front-desafio ao projeto criado Vue.js
- iniciar o servidor ( na pasta do projeto criado associada ao front-end,   rodar comando :   npm run server )
- conforme a porta instalada (default � 8080. Caso j� estiver em uso instalar� na 8081 como default).
  A aplica��o possui um index.html configurado como p�gina inicial, logo o sistema poder� ser acessado 
  diretamente pelo endere�o http://localhost:8080
