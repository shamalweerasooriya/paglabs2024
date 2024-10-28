#!/bin/bash
# Wait for RabbitMQ to start
sleep 10

# Create user and set permissions
rabbitmqctl add_user shamalw secret
rabbitmqctl set_user_tags shamalw administrator
rabbitmqctl delete_user guest
rabbitmqctl add_vhost paglabs24
rabbitmqctl set_permissions -p paglabs24 shamalw ".*" ".*" ".*"
