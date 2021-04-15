default:
	cat ./Makefile
build:
	mvn -f ./superhero-service/pom.xml clean package
image:
	docker build -t superhero-service:latest .
run:
	docker run -p 8887:8080 superhero-service:latest
run-bash:
	docker run -i -t superhero-service:latest /bin/bash
up: build image run