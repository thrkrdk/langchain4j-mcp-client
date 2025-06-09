# MCP Demo
Check your local LLM settings and make sure that it is up and runnin.
## AnythingLLM Demo
Check the `anythingllm_mcp_servers.json` file and show users the contents of this file.
### - springboot-mcp-starter
- Check `springboot-mcp-starter` agent is ready in AnythingLLM settings.
- Check podman is running container
- First ask `who am I?` and see the LLM answer.
- Then ask the same question with the `@agent` command.
### postgresql-mcp-server
Postgresql DB Sever and Postgresql MCP Stdio Server run in podman. to avoid an error, run the docker image in this network.
Here is a sample Podman code
```sh
podman network create mcp-net
podman run -d   --name pg-world   --network mcp-net   --network-alias pg-world ghusta/postgres-world-db:2.12
podman run -i --rm  postgresql-mcp-server-stdio --network mcp-net -e DATABASE_URL=jdbc:postgresql://pg-world:5432/world-db -e DATABASE_USERNAME=world -e  "DATABASE_PASSWORD=world123 postgresql-mcp-server-stdio
``` 
- Create network in podman.  `podman network create mcp-net`
- Run postgresql db server in podman. `podman run -d   --name pg-world   --network mcp-net   --network-alias pg-world ghusta/postgres-world-db:2.12`
- Check db is running in podman
- Check `postgresql-mcp-server` agent is ready in AnythingLLM settings.
- Check podman is running container
- Show all tools in the postgresql_mcp_server 
- Question-1: Start with the @agent command and write this prompt: "What tables are available in the database?"
- Follow Up Question: `How many records are in the orders table?`
  
### - youtube-video-summarizer
- Before starting the presentation, run the command "npx @modelcontextprotocol/inspector node dist/index.js" in terminal.
- Check "youtube-video-summarizer" agent is ready in AnythingLLM settings.
- First ask `What are the key points from this video: https://www.youtube.com/watch?v=dQw4w9WgXcQ` and see the LLM answer.
- Then ask the same question with the "@agent" command.

## CodeAsistan (VsCode, Intellij)
### - Local Coding LMM (Intellij)
- Configure `DevoxxGenie` for local LLM.
- `springboot-mcp-starter`
  - Check podman is running container
  - First ask `who am I?` and see the LLM answer.
  - Then ask the same question with the `@agent` command.
- `postgresql-mcp-server` in sse mode
  - Run postgresql db server in podman. `podman run -d   --name pg-world   --network mcp-net   --network-alias pg-world ghusta/postgres-world-db:2.12`
  - Run postgresql-mcp-server in sse mode in podman. `podman run -i   --name postgresql-mcp-server-sse   -p 8080:8080   --network mcp-net   -e DATABASE_URL=jdbc:postgresql://pg-world:5432/world-db   -e DATABASE_USERNAME=world   -e DATABASE_PASSWORD=world123   postgresql-mcp-server-sse`
  - Go to settings on  "DevoxxGenie". Connect MCP server in SSE mode. Url is `http://localhost:8080/sse`
  - Fetch all tools from server
  - Question-1: with the `@agent` command these prompts `What tables are available in the database?`
  - Follow Up Question: `How many records are in the orders table?`
- `Github MCP` server
- `Jira MCP` server

### - (Optional) Github Copilot (VS code)
 - Show MCP settings
 - `Github MCP` server

### - (Optional) Windsurf Copilot (Vs code)
 - Show MCP settings
 - `Github MCP` server
 
