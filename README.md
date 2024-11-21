# DevHelper - Bot Discord Java JDA
Este projeto utiliza a JDA (Java Discord API) para interagir com o Discord e realizar ações como responder comandos de slash, monitorar eventos como banimentos, desbanimentos, exclusão de mensagens e muito mais.

## Funcionalidades do Bot
### Comandos
- `ping:` Mostra a latência do bot.
- `codigo:` Mostra o link do repositório do bot no GitHub.
- `help:` Mostra os comandos disponíveis com uma interface de seleção.
- Eventos Monitorados
- Mensagens recebidas
- Mensagens excluídas
- Banimentos e desbanimentos
## Como Funciona o Bot
### `SlashCommandListener`
O SlashCommandListener é responsável por escutar eventos de slash commands e delegar a execução dos comandos para o CommandManager.

### `CommandManager`
O CommandManager gerencia todos os comandos registrados. Ele processa os eventos de interação e executa o comando correspondente.

### Logs
O LogsListener monitora eventos de mensagens e mantém um cache de mensagens para auditoria.

### Registro de Usuários
O RegisterListener gerencia a atribuição de cargos aos membros do servidor com base em interações de menus de seleção.

### Estrutura do Projeto
`src/main/java/DevHelper/:` Diretório principal contendo as classes do bot. <br>
 ---`ICommand.java:` Interface que define a estrutura básica para comandos. <br>
 ---`Commands/:` Diretório contendo as implementações dos comandos. <br>
 ---`CommandPing.java` <br>
 --- `CodeCommand.java` <br>
 --- `CommandHelp.java` <br>
 --- `Listeners/`: Diretório contendo os listeners para diferentes eventos. <br>
 ---- `SlashCommandListener.java`
 ---- `LogsListener.java`
 ---- `HelpInteractionListener.java`
 ---- `RegisterListener.java`
`MainBot.java:` Classe principal do bot que configura e inicializa o bot.

## Configuração e Execução
Clone o repositório:

git clone https://github.com/MyCoffeToCode/DevHelper.git
cd DevHelper
Crie um arquivo .env na raiz do projeto e adicione o token do seu bot do Discord:

TOKEN=seu-token-do-bot
Compile e execute o projeto:

mvn clean package
java -jar target/DevHelper-1.0-SNAPSHOT.jar <br>

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests para melhorias e novas funcionalidades.

## Colaboradores
Arthur Sousa <br> 
Filipe Dhunior <br>
Oyak XD <br>
## Discord do Projeto
Entre no nosso servidor do Discord para discussões e desenvolvimento: Discord

## Licença
Este projeto está licenciado sob a MIT License.

Desenvolvido por **StarCode**

# Ideias futuras (features)
- Criar uma função que permita adicionar **pomodoro**
- Criar uma integração com o [roadmap.sh](roadmap.sh)
- Criar exercicios
