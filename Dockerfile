FROM bellsoft/liberica-openjdk-alpine:17.0.11

#install curl jq
RUN apk add curl jq

#workspace
WORKDIR /home/selenium-docker

# Add the required files for running the tests to the workspace
ADD target/docker-resources     ./
ADD runner.sh                   runner.sh

#Run the tests through runner.sh
ENTRYPOINT sh runner.sh
             