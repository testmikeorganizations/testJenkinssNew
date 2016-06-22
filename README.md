#Jenkins 2.0 Pipeline Demo


```
docker pull jenkinsci/jenkins
docker run -i -t -p 8080:8080 -p 5000:5000 -v ~/jenkins2.0_container_home:/var/jenkins_home jenkinsci/jenkins
```

Navigate to the IP of the Docker VM and port 8080 to access Jenkins: http://192.168.99.100:8080/

README: https://github.com/jenkinsci/docker/blob/master/README.md
