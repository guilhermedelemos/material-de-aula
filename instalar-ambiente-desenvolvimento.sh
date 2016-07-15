#!/bin/sh

atualizar_so() {
    apt-get update
    apt-get upgrade
}

instalar_essenciais() {
    apt-get install -y build-essential
    apt-get install -y vim
    apt-get install -y unzip
    apt-get install -y git git-core
    apt-get install -y vsftpd
    apt-get install -y munin munin-node
    apt-get install -y kdiff3
}

instalar_mysql() {
    echo "ATENÇÃO: MYSQL NÃO TEM RECEBIDO ATUALIZÇÕES EVOLUTIVAS A ALGUNS ANOS."
    echo "CONSIDERE UTILIZAR O MARIADB OU O POSTGRESQL PARA NOVOS PROJETOS!"
    apt-get install -y mysql_server
    mysql_secure_installation
}

instalar_postgresql() {
    # REPOSITORIO OFICIAL DO POSTGRESQL
    # MAIS INFORMACOES EM: https://www.postgresql.org/download/linux/debian/
    echo "deb http://apt.postgresql.org/pub/repos/apt/ jessie-pgdg main" | tee /etc/apt/sources.list.d/pgdg.list
    wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | apt-key add -
    apt-get update

    # NA ORDEM: Servidor, modulos adicionais, bibliotecas e binarios cliente, desenvolvimento frontend, desenvolvimento backend, ferramenta administrativa 
    apt-get install -y postgresql-9.5 postgresql-contrib-9.5 postgresql-client-9.5 libpq-dev postgresql-server-dev-9.5 pgadmin3
}

instalar_php() {
    # instala apache2 e php5
    apt-get install -y apache2 php5 libapache2-mod-php5
    # instala extenção MySQL para php5
    apt-get install -y php5-mysql
    # instala extenção PostgreSQL para php5
    apt-get install -y php5-pgsql
    # instala extenção GD (biblioteca gráfica) para php5
    apt-get install -y php5-gd
    # instala extenção CURL
    apt-get install -y php5-curl
    # instala extenção IMAP
    apt-get install -y libc-client-dev php5-imap
    php5enmod imap
    # instala extenção INTL (internationalization)
    apt-get install -y php5-intl
    # extenção JSON e XML
    apt-get install -y php5-json php5-xmlrpc
}

instalar_java() {
    echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list
    echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
    apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
    apt-get update
    apt-get install -y oracle-java8-installer
}


instalar_ruby() {
    apt-get install -y curl gnupg libmagickcore-dev libmagickwand-dev
    apt-get install -y ruby ruby-dev rubygems libruby ruby-full libapache2-mod-passenger
    # Dependencia do Jekyll
    apt-get install -y nodejs
    gem install jekyll
}

escritorio() {
    echo "GNUCASH http://gnucash.org"
    apt-get install -y slib libgnomeui-common libgnomeui-dev guile-1.8 guile-1.8-dev checkinstall
    apt-get install -y swig libgnome2-dev libgnomeui-dev libgoffice-0.10-10 libgsf-1-dev libgtkhtml3.14-dev libofx-dev
    apt-get install -y libdbd-sqlite3 libdbd-pgsql libdbd-mysql
    apt-get install -y gnucash
}

iniciar_instalacao() {
    exibir_avisos_inicializacao
    atualizar_so
    instalar_essenciais
    instalar_ambiente_desenvolvimento_php
    instalar_ambiente_desenvolvimento_java
    instalar_ambiente_desenvolvimento_ruby
    exibir_avisos_encerramento
}

exibir_avisos_inicializacao() {
    echo "ATENCAO, ESTE SCRIPT E COMPATIVEL APENAS COM DISTRIBUICOES DEBIAN E DERIVADOS."
    echo "TESTADO COM DEBIAN 8.4.0 DE 02/04/2016"
    echo " "
}

exibir_avisos_encerramento() {
    echo "1. Configure o VSFTPD em:"
    echo "/etc/vsftpd.conf"
    echo "2. Configure IP estático em:"
    echo "/etc/resolv.conf"
    echo "/etc/network/interfaces"
    echo "3. Configure o UserDir Apache, referencias:"
    echo "http://www.techytalk.info/enable-userdir-apache-module-ubuntu-debian-based-linux-distributions/"
    echo "http://ubuntuserverguide.com/2012/10/how-to-enable-and-configure-apache2-userdir-module-in-ubuntu-server-12-04.html"
    echo "4. Configure o Munin e o Munin-Node"
    echo "https://www.linode.com/docs/uptime/monitoring/monitoring-servers-with-munin-on-debian-6-squeeze"
    echo "https://www.digitalocean.com/community/tutorials/how-to-install-the-munin-monitoring-tool-on-debian-8"
}

iniciar_instalacao
exibir_avisos
