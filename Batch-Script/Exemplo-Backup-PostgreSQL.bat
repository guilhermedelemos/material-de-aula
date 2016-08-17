@ECHO OFF
REM Material de referência: https://en.wikibooks.org/wiki/Windows_Batch_Scripting

REM PREENCHA AS VARIAVEIS A BAIXO DE ACORDO COM SUA NECESSIDADE
SET NOME_ARQUIVO_BACKUP=ARQUIVO

ECHO DATA:       %DATE% %TIME%
ECHO DIRETORIO:  %CD%
ECHO VERSAO CMD: %CMDEXTVERSION%
ECHO EXECUTANDO: %CMDCMDLINE%

SET DATA_HOJE=%date:~6,4%%date:~3,2%%date:~0,2%
SET HORA_HOJE=%time:~0,2%%time:~3,2%%time:~6,2%
SET NOME_ARQUIVO=%DATA_HOJE%_%HORA_HOJE%_%NOME_ARQUIVO_BACKUP%

ECHO %NOME_ARQUIVO%

REM criar arquivo .pgpass no diretório do usuário. O conteúdo do arquivo é o seguinte:
REM hostname:port:database:username:password
pg_dump --host=127.0.0.1 --username=postgres --dbname=banco --format=custom --file="%NOME_ARQUIVO%.backup"

PAUSE
