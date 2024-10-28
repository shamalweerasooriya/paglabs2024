FROM rabbitmq:3.11-management

EXPOSE 5672 15672

ENV RABBITMQ_DEFAULT_USER=shamalw
ENV RABBITMQ_DEFAULT_PASS=secret

COPY init_rabbitmq.sh /init_rabbitmq.sh
RUN chmod +x /init_rabbitmq.sh

CMD ["sh", "-c", "/init_rabbitmq.sh && rabbitmq-server"]
