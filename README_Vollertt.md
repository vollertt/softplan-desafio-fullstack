# Sistema de cadastro de usuários e processos: "desafio-softplan-fullstack"

A aplicação do desafio utiliza dois projetos diferentes

 1. O "back-end" (pasta "apirest", criada com IDE Eclipse e projeto Maven), que implementou serviços REST utilizando JPA e Spring   aos modelos de entidade criados conforme a modelagem do sistema e regras.
 2. O "front-end" desenvolvido da aplicação, está localizado na pasta "src/main/front-end". Utilizou o framework "Vue.js"

***Camada "back-end" (caracteristica e dependencias). Projeto Spring Boot Web Maven.***

	  1) Spring Boot para selecionar tipo de projeto, tecnologias e dependências utilizadas e criar o projeto inicial do sistema.
	  2) Spring DevTools
	  3) Spring JPA / REST
	  4) Banco de dados PostgresSQL
	  5) Spring Swagger 

***Camada Fron-End***

	  1) Framework Vue.js (dependencias das bibliotecas e do servidor Node.js instalado) 
	  2) axios como um cliente HTTP para consumir os serviços REST da api


**Ambiente de Desenvolvimento do sistema/aplicação:**
 
 1) Sistema Operacional Windows com IDE Eclipse (4.15.0), e Java sdk (1.8.0_241) e Bancode dados PostgreSQL (10.12) instalados.
2) instalado o servidor de versionamento git, e configurado conforme documento enviado do desafio 
3) Foi utilizado o Spring Boot para configurar e selecionar as dependências para com o sistema. Projeto do tipo
Maven com Devtools, Java Web , JPA / REST,  e Banco Dados PostgreSQL. Nome do pacote "com.desafio-softplan" e da aplicação "apirest".
4) Não foi necessário instalar servidor de aplicação, pois foi utilizado o default do "Devtools" (Tomcat e rodando porta default 8080) 
5) Foi utilizado o software  "Postman" para testar requisições REST (get, post, put), assim como através do "Swagger" após  implementado. (http://localhost:8080/swagger-ui.html ) 

**Banco de Dados do sistema**

Na raiz doprojeto "/softplan-desafio-fullstack" estão 3 arquivos sql

	1) crie a base de dados PostgreSQL apirest
	2) após instalar o back-end e rodar a aplicação com JPA, a aplicação automaticamente criará a as tabelas
	3) para popular as tabelas com dados iniciais de teste os dois sql
	   - sqlTesteRestUsuarioPopula.sql
	   - sqlTesteRestProcessoPopula.sql
	4) caso deseje criar manualmente o BD com os dados do desenvolvimento e teste até a data atual, o script sql é:
	   - sql_bdCreate_dados_dev_test_20100416

 A aplicação foi desenvolvida com um login simples, somente com "email" dos usuários na página inicial. Logo os emails no script de importação, contém os distintos usuários conforme seus perfis e permissões às funcionalidades desenvolvidas.
 
A seguir são descritas algumas regras de negócio e casos de uso associadas ao sistema desenvolvido:

a) exemplo: admin@teste.com (usuário "ADMIN", pode cadastrar novo usuário, alterar dados de usuário cadastrado,  visualizar usuários cadastrados, e visualizar processos (pendentes ou finalizados)    

b) exemplo: teste2@teste.com (usuário com perfil "DISTRIBUIDOR"), pode visualizar usuários cadastrados sem poder criar novo ou alterar.   Pode visualizar a listas dos processos pendentes ou finalizados, não somente dos quais cadastrou, mas de todos os usuários   "Distribuidores" que cadastraram, e cujo parecer ainda pode estar em status: finalizado  ou em aberto (pendente).   Pode cadastrar processo e atribui o processo a um usuário que dará o parecer (usuário com perfil: "FINALIZADOR")

  c) exemplo: teste7@teste.com (usuário com perfil: "FINALIZADOR"), pode visualizar usuários cadastrados sem poder criar novo ou alterar. Pode visualizar a listas dos processos pendentes ou finalizados, e somente atribuídos ao seu usuário. Pode alterar cadastro do processo conforme seu parecer. O parecer só é finalizado quando o mesmo, além de fornecer o parecer selecionar a opção finalizado. Após finalizado o processo não poderá mais ser alterado e  estará finalizado. 


**Preparação do ambiente para o front-end com framework "Vue.js" (módulos, bibliotecas e dependências utilizados)**

 

 - Instalação do servidor Node ( https://nodejs.org/en/download/ )
 - Instalação do Vue.js ( https://cli.vuejs.org/guide/installation.html )
 - Criado um projeto front-end com o Vue.js (  https://cli.vuejs.org/guide/creating-a-project.html#vue-create  )
 - Instalação do módulo axios JS como dependencia do projeto 
 -        na pasta do projeto criado roda comando : 
              npm install axios --save )
 - instalação do vue-moment para operações com objeto Date 
 -          npm install vue-moment 
 - instalação do "Materializer Css" como módulo do Vue.js
            npm install materialize-css@next --save
            npm install material-design-icons --save
 - copiar os arquivos da pasta "apirest/src/main/front-end/softplan-desafio-front/front-desafio" ao projeto criado utilizando o framework "Vue.js"
 - iniciar o servidor . Na pasta do projeto criado associada rodar comando 
 -       npm run serve 

conforme a porta instalada (default é 8080. Caso já estiver em uso instalará na 8081 como default).
  A aplicação possui um index.html configurado como página inicial, logo o sistema poderá ser acessado 
  diretamente pelo endereço http://localhost:8080