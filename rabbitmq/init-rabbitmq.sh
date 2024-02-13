#!/bin/bash
set -e

# Ожидание полной инициализации RabbitMQ
rabbitmqctl wait /var/lib/rabbitmq/mnesia/rabbit@$HOSTNAME.pid

# Создание пользователя и настройка прав
rabbitmqctl add_user misso misso
rabbitmqctl set_permissions -p / misso ".*" ".*" ".*"
rabbitmqctl set_user_tags misso management

# Запуск сервера RabbitMQ
exec rabbitmq-server
