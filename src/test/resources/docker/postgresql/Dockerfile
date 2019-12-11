FROM postgres:11-alpine as builder

ENV POSTGRES_PASSWORD secret 

COPY init-user-db.sh /docker-entrypoint-initdb.d/
CMD ["postgres"]

FROM builder as prod
ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["postgres"]
