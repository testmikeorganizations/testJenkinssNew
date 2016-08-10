FROM jenkinsci/jenkins:latest
MAINTAINER David Malone <dmalone@pivotal.io>


USER root
ENV CF_CLI $PWD/
ENV PATH $CF_CLI:$PATH

ADD https://cli.run.pivotal.io/stable?release=linux32-binary&version=6.21.0&source=github-rel \
  $CF_CLI/cf.tgz

RUN tar zxvf $CF_CLI/cf.tgz
RUN cf --version


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
  credentials-binding:1.23 \
  job-dsl \
  config-file-provider:2.11
