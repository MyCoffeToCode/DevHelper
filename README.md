# DevHelper // an Discord Java JDA Bot
Este projeto utiliza a **JDA (Java Discord API)** para interagir com o Discord e realizar ações como responder comandos de slash, monitorar eventos como banimentos, desbanimentos, exclusão de mensagens e reações em mensagens.

## Funcionalidades do Bot

### Comandos

1. `/ping`
Responde com a latência do bot.
Exibe uma mensagem com a resposta "Pong!" e a latência em milissegundos.
Exemplo de resposta: "Pong! Latência: 120ms"

2. `/help`
Exibe informações sobre os comandos disponíveis.
Inclui uma lista de comandos com descrições.
Exemplo de resposta: "Comando: /ping - Responde com 'Pong!'"

3. `/bump`
Simula a execução do comando /bump com uma resposta simples.

### Eventos Monitorados

O bot também escuta e executa ações em resposta a eventos no servidor do Discord.

1. **Banimento de Usuário**
Registra quando um usuário é banido do servidor.
Exibe uma mensagem no canal de logs com o nome, avatar e razão do banimento (se especificada).
Exemplo de resposta:
"O usuário @username foi banido! Motivo: Spamming"
2. **Desbanimento de Usuário**
Registra quando um usuário é desbanido do servidor.
Exibe uma mensagem no canal de logs com o nome e avatar do usuário desbanido.
Exemplo de resposta:
"O usuário @username foi desbanido!"
3. **Exclusão de Mensagens**
Registra a exclusão de mensagens.
Exibe o conteúdo da mensagem excluída (se disponível), o canal onde a mensagem foi deletada e o usuário responsável pela exclusão.
Exemplo de resposta:
"Mensagem excluída: 'Texto da mensagem' ID: 12345. Deletado por: @admin."
4. **Reação a Mensagens**
Registra quando um usuário reage a uma mensagem.
Exibe o emoji utilizado, o usuário que reagiu e o link para a mensagem original.
Exemplo de resposta:
"Reação de @username com ❤️ na mensagem [link]."

## Como Funciona o Bot

### `SlashCommandListener`

O SlashCommandListener é responsável por escutar os comandos executados pelos usuários no servidor. Ele verifica o nome do comando e chama o método correspondente.
Quando o comando /ping é executado, o método CommandPing.ping() é chamado para calcular e retornar a latência do bot.
Quando o comando /help é executado, o método CommandHelp.help() é chamado para enviar uma mensagem de ajuda.
Quando o comando /bump é executado, o bot simplesmente responde com o texto "/bump".

### Logs

O bot possui um sistema de logs para registrar eventos importantes no servidor:
Banimento: Quando um banimento é detectado, o bot envia uma mensagem com o nome do usuário, sua foto de perfil e o motivo do banimento (se especificado).
Desbanimento: Similar ao banimento, o bot envia uma mensagem quando um usuário é desbanido, incluindo o nome e a foto do usuário.
Exclusão de Mensagens: O bot armazena o conteúdo da mensagem excluída em uma cache e, quando detecta a exclusão, ele registra essa informação, incluindo quem deletou a mensagem.
Reações: O bot também monitora as reações às mensagens. Ele captura o emoji usado, o usuário que reagiu e inclui um link para a mensagem original.

### Cache de Mensagens

O bot utiliza um mapa cache para armazenar temporariamente o conteúdo das mensagens recebidas. Quando uma mensagem é excluída, o bot pode recuperar o conteúdo da mensagem cacheada e exibir as informações relevantes no canal de logs.
Uso de EmbedBuilder
Em várias partes do código, o EmbedBuilder da JDA é usado para criar mensagens embutidas (embeds). Estas mensagens são estilizadas e são enviadas para os canais de texto, facilitando a leitura e o entendimento das mensagens enviadas pelo bot.

## Ideias futuras (features)
- Criar uma função que permita adicionar **pomodoro**
- Criar uma integração com o [roadmap.sh](roadmap.sh)
- Criar exercicios
