#Jenkins 2.0 Pipeline Demo


Assumes a running Docker Machine at IP 192.168.99.100, and that you have run through the initial Jenkins 2.0 setup.

To start the Docker Machine and to pull the jenkinsci/jenkins Docker image, first run these commands:
```
docker-machine start default
eval "$(docker-machine env default)"
docker pull jenkinsci/jenkins
docker run -i -t -p 8080:8080 -p 50000:50000 -v ~/jenkins2.0_container_home:/var/jenkins_home jenkinsci/jenkins
```

The default admin password will appear in the console as logs from Jenkins. This password is required to run through the initial setup of Jenkins. After this is complete, then you can create the Demo Pipeline Job using the CLI:


```
curl -O http://192.168.99.100:8080/jnlpJars/jenkins-cli.jar
export JENKINS_URL=http://192.168.99.100:8080/
java -jar jenkins-cli.jar create-job DemoPipeline < config.xml
```

Navigate to the IP of the Docker VM and port 8080 to access Jenkins. The pipeline created using the above script should be located at http://192.168.99.100:8080/DemoPipeline


## References

README: https://github.com/jenkinsci/docker/blob/master/README.md
