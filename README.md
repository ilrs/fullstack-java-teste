# NinjaProject Contabilizei

Passo a passo para execução:
- Configurar o arquivo META-INF/persistence.xml a url de acesso ao banco de dados (HsqlDb);
- Configurar um servidor para rodar a aplicação, Jetty ou Tomcat;
- Adicionar ao Java Build Path os .jar localizados na pasta WebContent/WEB-INF/lib;
- Iniciar o servidor com a aplicação;

Caminhos para acessar a aplicação no navegador:
$path/ninja/clients
$path/ninja/products
$path/ninja/orders

Todos os 3 possuem o CRUD implementado

Arquitetura
Foi implementada uma aplicação pura Java EE utilizando Servlets e banco de dados utilizando JPA;
O acesso ao banco de dados é totalmente realizado dentro do package ninja.repositories;
Os modelos de dados ficam localizados no package ninja.models;
As classes para controle das requisições foram implementadas dentro do package ninja.servlet;
As páginas estão localizadas na pasta WebContent/jsp;