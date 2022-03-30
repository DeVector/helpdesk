# Repositório Help Desck - Back-end

Esse repositório tem o intuito de aprendizado para realizar autenticação de usuario.

Este projeto foi desenvolvido com a linguagem de programação Java, com o framework
Spring-boot e com o BD Postgres.

# Especificações técnicas do projeto:

- A versão do Java utilizada foi a 11;
- A versão do Spring-boot foi a 2.3.12.RELEASE;
- O postgres foi utilizado inicialmente com o Docker, apos
ele foi disponibilizado na plataforma da Heroku;

# Sobre o projeto
Este projeto teve o intuito de adquirir conhecimento no abinto de desenvolver
uma autenticação, onde se tem usuarios que não podem ter acesso a todos os 
recursos disponiveis no sistema em si. O projeto foi feito no modelo Spring 
MVC, assim facilitando na transferencia de dados como também nas tratativas 
de erros, como também para que o codigo fosse organizado de uma melhor forma.
Nele também foi utilizado o versionamento do git para que fossem realizados
commit a cada criação de uma nova funcionalidade caso desse algum problema
podesse haver o caso de recuperar o que estava em funcionamento.

Foram criados tres tipos de perfis.

- Admin: Tendo acesso completo podendo fazer o crud em qualquer usuario;
- Técnico: Tendo acesso apenas CRUD de técnicos;
- Cliente: Que nã tem acesso a nenhum recurso do CRUD.

Sendo que cada pessoa cadastrada pode ter os tres perfis.

Foram criadas prioridades:

- Alta:
- Media:
- Baixa:

Foram criados status:

- Aberto;
- Andamento;
- Encerrado: Quando este status é ativo na base de dados é pegado do sistema a data atual e colocada no campo de data de fechamento.

