### GRITTO API
#### Documentação de autenticação

##### Usuários
- Listagem de usuários

Exemplo

    GET /usuarios
    Header: Authorization=Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

- Cadastro

Exemplo

	POST /usuarios
	Body
	{
		"email": "jonasdefarias@hotmail.com",
		"password": "hackme",
		"nome": "jonas",
		"rg": "##########",
		"cpf": "###.###.###-##",
		"telefone": "#######",
		"sexo": "M"
	}

##### Login
	POST /login
	Body
	{
		"username": "jonasdefarias@hotmail.com",
		"password": "hackme"
	}
Normal response [![CircleCI](https://img.shields.io/static/v1?label=OK&message=200&color=green)](#) with JWT Token