<?php
// Exibir erros do PHP
error_reporting(E_ALL);
ini_set("display_errors", 1);

// Carregar classe de criptografia
require_once 'AES.php';

// Preparacao dos dados
$palavra = 'Teste';
$senha128 = '2b7e151628aed2a6'; // 16 char = 16 bytes = 128 bit (16*8)
$senha192 = '2b7e151628aed2a6abf71588'; // 24 char = 24 bytes = 192 bit (24*8)
$senha256 = '2b7e151628aed2a6abf7158809cf4f3c'; // 32 char = 32 bytes = 256 bit (32*8)

// Preparacao da classe de criptografia
$cript = new AES();
$cript->setMode(AES::MODE_ECB);

// Execucao
echo '<h3>Teste de criptografia AES</h3>';
echo '<h5>Palavra: ' . $palavra . '</h5>';

echo '<h4>128 bit</h4>';
$cript->setKey($senha128);
$palavraCriptografada    = $cript->encrypt($palavra);
$palavraDescriptografada = $cript->decrypt($palavraCriptografada);
echo '<h5>Senha: ' . $senha128 . '</h5>';
echo '<h5>Palavra criptografada: ' . $palavraCriptografada . '</h5>';
echo '<h5>Palavra descriptografada: ' . $palavraDescriptografada . '</h5>';


echo '<h4>192 bit</h4>';
$cript->setKey($senha192);
$palavraCriptografada    = $cript->encrypt($palavra);
$palavraDescriptografada = $cript->decrypt($palavraCriptografada);
echo '<h5>Senha: ' . $senha192 . '</h5>';
echo '<h5>Palavra criptografada: ' . $palavraCriptografada . '</h5>';
echo '<h5>Palavra descriptografada: ' . $palavraDescriptografada . '</h5>';


echo '<h4>256 bit</h4>';
$cript->setKey($senha256);
$palavraCriptografada    = $cript->encrypt($palavra);
$palavraDescriptografada = $cript->decrypt($palavraCriptografada);
echo '<h5>Senha: ' . $senha256 . '</h5>';
echo '<h5>Palavra criptografada: ' . $palavraCriptografada . '</h5>';
echo '<h5>Palavra descriptografada: ' . $palavraDescriptografada . '</h5>';

