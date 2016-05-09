rm *.class
rm *.jar
javac *.java
jar -cvfm impressora.jar Manifest.txt *.class
jarsigner -keystore chave impressora.jar Exemplo
pause