
# Arquillian Drone Sample Project

## Jboss Forge Steps
The following commands are needed to setup this example project

	project-new --named arquillian-drone-example --version 2.5.0 --top-level-package de.bitc --stack JAVA_EE_7
	jpa-setup --jpa-container WILDFLY --persistence-unit-name sample --jpa-provider Hibernate --db-type H2 --data-source-name  java:jboss/datasources/ExampleDS
	servlet-setup --servlet-version 3.1
	ejb-setup
	
The next commands create a simple table

	jpa-new-entity --named Customer --target-package de.bitc.model
	jpa-new-field --named firstName
	jpa-new-field --named lastName
	jpa-new-field --named email
	jpa-new-field --named login

Add a dao for test driven development

	jpa-generate-daos-from-entities --package-name de.bitc.dao --ejb-version  3.2 --generator JPA_ENTITY --persistence-unit sample --targets de.bitc.model.dao

JSF 2.2 Setup

	faces-setup --faces-version 2.2
	primefaces-setup --primefaces-version PRIMEFACES_5_1

Generate a simple web site

	scaffold-setup --provider Faces
	scaffold-generate
	
Arquillian
 	
 	arquillian-setup --test-framework junit --arquillian-version 1.1.15.2 --container-adapter wildfly-remote
	arquillian-cube-setup --type docker --docker-machine-name 10.20.1.4