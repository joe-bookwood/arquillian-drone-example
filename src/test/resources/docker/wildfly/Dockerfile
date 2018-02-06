FROM jboss/wildfly

EXPOSE 8080 9990

RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent

CMD /opt/jboss/wildfly/bin/standalone.sh -c standalone.xml -b 0.0.0.0 -bmanagement=0.0.0.0
