
# Arquillian Drone Sample Project

This project should be a sample how to create an integration test with
Arquillian. The integration test should start a wildfly docker container and
runs a selenium webdriver and a JPA test on it. I created the example project
with jboss forge. I described the steps below.

The project does not work now, and I posted the following text as an issue.

The first test is a JPA dao test `de.bitc.dao.CustomerDaoIT`. I want that the 
test build and deploy a customized wildfly 10 docker container and run the test
in it. I followed the
[Building containers](http://arquillian.org/arquillian-cube/#_building_containers)
example in the Arquillian Cube documentation. 

I start the test with:

	mvn -Pintegration-test -Ddocker.server=10.33.33.4 verify


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
 	
 	arquillian-setup --test-framework junit --arquillian-version 1.2.0.1 --container-adapter wildfly-remote
	arquillian-cube-setup --type docker --docker-machine-name 10.33.33.4
	
