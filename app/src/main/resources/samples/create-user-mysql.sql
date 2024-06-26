CREATE DATABASE IF NOT EXISTS $DATABASE;
CREATE USER '$USERNAME'@'$HOSTNAME' IDENTIFIED BY '$PASSWORD';
GRANT ALL ON `$DATABASE`.* TO '$USERNAME'@'$HOSTNAME';
flush privileges;