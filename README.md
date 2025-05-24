# Path to/ Carrer

Aplicação desenvolvida em React, focada na navegação por trilhas de aprendizagem personalizadas.
Destina-se a estudantes e mentores interessados em compartilhar ou acompanhar percursos educacionais organizados de maneira estruturada.

A plataforma Path to Career permite aos usuários criar e acessar estruturas chamadas "Paths", que são constituídas por módulos e, respectivamente, por aulas. Cada aula é composta por um vídeo e uma descrição. Além disso, cada módulo pode conter suas próprias aulas e um fórum, onde usuários e criadores podem interagir entre si.

![](https://github.com/AlisonMartinss/Imagens/blob/main/FluxoGrama.png?raw=true)

### Acesse o site para ver isso em pratica: [ https://path-carrer-front.vercel.app/](https://path-carrer-front.vercel.app/)

#### Demonstrando o primeiro acesso a plataforma: 
[Assista ao vídeo](https://www.youtube.com/watch?v=V-uKTesoJds&t=22s)

#### Utilizando alguns dos recursos  da platafroma:
[Assista ao vídeo](https://www.youtube.com/watch?v=y9Uz3luEqzQ)

##🔧 Tecnologias
- Java 17
- SpringBoot v-3.4
- SpringSecurity v-3.4
- Swagger
- MongoDB

## 🏗️ Arquitetura
- Arquitetura usada no sistema: REST.
- Autenticação: JWT + OAuth + CORS e etc, implementações usando Spring Security.

## 🗂️ Estrutura
Uma vez que você estiver no diretorio "API-PathCarrer/src/main/java/PathCarrer" vamos ver as delegações de responsabilidades seguindo um os principios SOLID

#### /Configurations
 - /ConfigurationsAll
  - /AuthenticationTratamentCuston: Tratamento de exceptions lançados pela authenticação Authenticação.
  - /FilterSecurityConfigAndCORS: Configurações do filtro de segurança e CORS.
  - /SecurityFulterToToken: Filtro de confg token.
  - /SwwagerConfig: Configurações do Swagger.
