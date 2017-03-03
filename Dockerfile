FROM jenkinsci/jenkins:latest
MAINTAINER Mark Alston <malston@pivotal.io>

USER root

RUN curl -L "https://cli.run.pivotal.io/stable?release=linux64-binary" -o cf.tgz \
    && tar -xvf cf.tgz \
    && chmod +x cf \
    && mv cf /usr/bin \
    && cf --version

RUN curl -L "https://github.com/contraband/autopilot/releases/download/0.0.2/autopilot-linux" -o autopilot \
    && chmod +x autopilot \
    && cf install-plugin ./autopilot -f \
    && cf plugins | grep autopilot

RUN curl -L "https://github.com/odlp/antifreeze/releases/download/v0.3.0/antifreeze-linux" -o antifreeze \
    && chmod +x antifreeze \
    && cf install-plugin ./antifreeze -f \
    && cf plugins | grep antifreeze

USER jenkins
RUN install-plugins.sh \
  workflow-aggregator \
  ssh-slaves \
  ws-cleanup \
  timestamper \
  subversion \
  gradle \
  maven \
  github-organization-folder \
  email-ext \
  credentials-binding \
  job-dsl \
  delivery-pipeline-plugin \
  config-file-provider
