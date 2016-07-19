#Jenkins 2.0 Cloud Foundry Pipeline Demo

Demonstrates a Jenkins 2.0 Pipeline to build a Java project and deploy the project to a running instance of Pivotal Cloud Foundry.

## To Run this Demo

Simply clone this project:
```
git clone https://github.com/dave-malone/jenkins2-pipeline-demo
cd jenkins2-pipeline-demo
```

Assumes a running Docker Machine at IP 192.168.99.100, and that you have run through the initial Jenkins 2.0 setup.

To start the Docker Machine and to pull the jenkinsci/jenkins Docker image, first run these commands:
```
## Your container will mount the jenkins home volume using this directory
docker-machine create jenkins2-pipeline-demo --driver virtualbox --virtualbox-memory "11000" --virtualbox-disk-size "100000"
eval "$(docker-machine env jenkins2-pipeline-demo)"
docker pull dmalone/jenkins2-cf-pipeline-demo or docker build -t dmalone/jenkins2-cf-pipeline-demo - < Dockerfile
docker run -i -t -p 8080:8080 -p 50000:50000 --name=jenkins-pipeline-demo -v /var/jenkins_home dmalone/jenkins2-cf-pipeline-demo
```

The default admin password will appear in the console as logs from Jenkins. This password is required to run through the initial setup of Jenkins. You can also obtain the Jenkins Admin password by executing the following: `docker exec -it ``docker ps -l -q`` cat /var/jenkins_home/secrets/initialAdminPassword` where ``docker ps -l -q`` will retrieve the last container ID.

After this is complete, then you can create the Demo Pipeline Job using the CLI:

```
curl -O http://192.168.99.100:8080/jnlpJars/jenkins-cli.jar
export JENKINS_URL=http://192.168.99.100:8080/
java -jar jenkins-cli.jar create-job sample-spring-cloud-svc-ci < config.xml --username admin --password <REPLACE-WITH-PASSWORD-ABOVE>
```

Navigate to the IP of the Docker VM and port 8080 to access Jenkins. The pipeline created using the above script should be located at http://192.168.99.100:8080/job/sample-spring-cloud-svc-ci


## To Build this project

This project builds a custom Docker container with Jenkins plugins automatically installed, and the CF CLI automatically installed and configured so it can be used by Pipelines.

`build -t dmalone/jenkins2-cf-pipeline-demo .`


## References

* Docker Machine Installation Instructions: https://docs.docker.com/machine/install-machine/
* Jenkins Docker Image README:  https://github.com/jenkinsci/docker/blob/master/README.md
