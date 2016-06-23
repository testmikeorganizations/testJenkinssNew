#!groovy

echo "Starting workflow"

stage 'build'
node {
    echo "Cloning Project"
    git 'https://github.com/cloudfoundry-samples/spring-music.git'

    echo "Building the Project with Gradle Wrapper"
    sh './gradlew build -x test'
}

stage 'test'
node {
    sh './gradlew clean test assemble'
    // step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/TEST-*.xml'])
}

stage 'deploy'
node {
  sh "ls -la"
  echo "CF CLI Version: "
  sh "cf --version"
  sh "CF_HOME=./ cf login -a https://api.run.pez.pivotal.io -u dmalone+jenkins@pivotal.io -p jenkins -o pivot-dmalone -s development"
  sh "CF_HOME=./ cf push spring-music -p build/libs/spring-music.war -b java_buildpack_offline"
}

node {
  input 'Deploy to Test?'
  sh "CF_HOME=./ cf login -a https://api.run.pez.pivotal.io -u dmalone+jenkins@pivotal.io -p jenkins -o pivot-dmalone -s test"
  sh "CF_HOME=./ cf push spring-music -p build/libs/spring-music.war -b java_buildpack_offline"
}

node {
  input 'Deploy to Prod?'
  sh "CF_HOME=./ cf login -a https://api.run.pez.pivotal.io -u dmalone+jenkins@pivotal.io -p jenkins -o pivot-dmalone -s prod"
  sh "CF_HOME=./ cf push spring-music -p build/libs/spring-music.war -b java_buildpack_offline"
}
