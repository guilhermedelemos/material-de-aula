<?php
// ATENCAO, este script e apenas um esboco.
// EXIBIR ERROS
error_reporting(E_ALL);
ini_set("display_errors", 1);

$con = new PDO('pgsql:host=127.0.0.1;port=5432;dbname=exemplo_arquivo', 'postgres', 'postgres');

$caminho='/caminho/para/arquivo.txt';


//GRAVAR NO BANCO
try {
    $arquivo_bin = fopen($caminho, "rb");
    $conteudo    = fread($arquivo_bin, filesize($caminho));
    $dados       = bin2hex($conteudo);
    fclose($arquivo_bin);
} catch (RuntimeException $ex) {
    throw $ex;
}

$rs = $con->prepare("insert into arquivos (nome,dados) values (:nome,decode(:dados,'hex'))");
$rs->bindValue(':nome',  $caminho);
$rs->bindValue(':dados', $dados);
$con->executeStatement($rs);

//RECUPERAR DO BANCO
$rs = $con->prepare("SELECT id, nome, encode(dados, 'hex') AS dados FROM arquivos WHERE id = :id");
$rs->bindValue(':id', 1);
$rs = $con->executeStatement($rs);

foreach ($rs->fetchAll() as $linha) {
    $id    = $linha['id'];
    $nome  = $linha['nome'];
    $dados = $linha['dados'];

    $fp = fopen($nome . $id, "w+");
    fwrite($fp, hex2bin($dados));
    fclose($fp);
}

