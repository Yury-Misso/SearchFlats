FROM rabbitmq:3.12.12-management-alpine

#ENV RABBITMQ_DEFAULT_USER=misso
#ENV RABBITMQ_DEFAULT_PASS=misso

#COPY init-rabbitmq.sh /init-rabbitmq.sh

RUN #chmod +x /init-rabbitmq.sh

EXPOSE 5672 15672

CMD ["rabbitmq-server"]