# DevHelper - Bot Discord Java JDA

[![GitHub License](https://img.shields.io/github/license/MyCoffeToCode/DevHelper)](https://github.com/MyCoffeToCode/DevHelper/blob/master/LICENSE.md)
[![GitHub Stars](https://img.shields.io/github/stars/MyCoffeToCode/DevHelper?style=social)](https://github.com/MyCoffeToCode/DevHelper)
[![GitHub Forks](https://img.shields.io/github/forks/MyCoffeToCode/DevHelper?style=social)](https://github.com/MyCoffeToCode/DevHelper)
[![Java Version](https://img.shields.io/badge/Java-17-green)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![JDA Version](https://img.shields.io/badge/JDA-v5.0-blue)](https://github.com/DV8FromTheWorld/JDA)

Este projeto utiliza a JDA (Java Discord API) para interagir com o Discord e realizar ações como responder comandos de slash, monitorar eventos como banimentos, desbanimentos, exclusão de mensagens e muito mais.

## Funcionalidades do Bot

### Comandos
- `ping:` Mostra a latência do bot.
- `codigo:` Mostra o link do repositório do bot no GitHub.
- `help:` Mostra os comandos disponíveis com uma interface de seleção.

### Eventos Monitorados
- Mensagens recebidas
- Mensagens excluídas
- Banimentos e desbanimentos

## Como Funciona o Bot

### `SlashCommandListener`
O `SlashCommandListener` é responsável por escutar eventos de slash commands e delegar a execução dos comandos para o `CommandManager`.

### `CommandManager`
O `CommandManager` gerencia todos os comandos registrados. Ele processa os eventos de interação e executa o comando correspondente.

### Logs
O `LogsListener` monitora eventos de mensagens e mantém um cache de mensagens para auditoria.

### Registro de Usuários
O `RegisterListener` gerencia a atribuição de cargos aos membros do servidor com base em interações de menus de seleção.

### Estrutura do Projeto
`src/main/java/DevHelper/:` Diretório principal contendo as classes do bot. <br>
---`ICommand.java:` Interface que define a estrutura básica para comandos. <br>
---`Commands/:` Diretório contendo as implementações dos comandos. <br>
---- `CommandPing.java` <br>
---- `CodeCommand.java` <br>
---- `CommandHelp.java` <br>
---`Listeners/`: Diretório contendo os listeners para diferentes eventos. <br>
---- `SlashCommandListener.java` <br>
---- `LogsListener.java` <br>
---- `HelpInteractionListener.java` <br>
---- `RegisterListener.java` <br>
`MainBot.java:` Classe principal do bot que configura e inicializa o bot.

## Configuração e Execução

Clone o repositório:

```bash
git clone https://github.com/MyCoffeToCode/DevHelper.git
```

Entre na pasta raiz do projeto

```bash
cd DevHelper
```

Crie um arquivo `.env` na raiz do projeto e adicione o token do seu bot do Discord:

```bash
cd . > .env
```

Abra o arquivo .env e coloque o conteúdo abaixo, substituindo os valores para o seu token e a seu caminho do SQLite

```env
TOKEN=seu-token-do-bot
URL_DB=url-do-arquivo-do-sqlite
```

Entre na pasta dos arquivos docker

```bash
cd docker
```

execute o projeto:

```bash
docker-compose up -d
```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests para melhorias e novas funcionalidades.

## Colaboradores

| Colaborador                               | GitHub                                                                   |
|-------------------------------------------|--------------------------------------------------------------------------|
| [Arthur Sousa](https://github.com/SousaaArthur) | ![Arthur Sousa](https://avatars.githubusercontent.com/u/127199264?v=4)   |
| [Filipe Dhunior](https://github.com/filoroch) | ![Filipe Dhunior](https://avatars.githubusercontent.com/u/162770790?v=4) |
| [Kayo](https://github.com/OyakXD)      | ![Kayo](https://avatars.githubusercontent.com/u/131064997?v=4)        |
| [André Xisto](https://github.com/andre-xizto) | ![André Xisto](https://avatars.githubusercontent.com/u/35929740?v=4)     |
| [Jeff](https://github.com/fergo8)          | ![Jeff](https://avatars.githubusercontent.com/u/30440307?v=4)            |

## Discord do Projeto

Entre no nosso servidor do Discord para discussões e desenvolvimento: [Discord](https://discord.gg/)

## Licença

Este projeto está licenciado sob a [licença MIT](https://github.com/MyCoffeToCode/DevHelper/blob/master/LICENSE.md).

Desenvolvido por **StarCode**

# Ideias futuras (features)

- Criar uma função que permita adicionar **pomodoro**
- Criar uma integração com o [roadmap.sh](https://roadmap.sh)
- Criar exercícios