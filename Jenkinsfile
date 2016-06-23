#!groovy

echo "Starting workflow"

stage 'build'
node {
    echo "Cloning Project"
    git 'https://github.com/dave-malone/spring-boot-personal-financier.git'

    echo "Building the Project with Gradle Wrapper"
    sh './gradlew build -x test'
}

stage 'test'
node {
    sh './gradlew clean unitTest'
    step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/TEST-*.xml'])

    echo "Archiving Jar file"
    archive 'build/libs/*.jar'
}

stage 'deploy'
node {
  echo "Deploying to CF"
  sh './download-cf-cli.sh'
  sh "./cf login -a https://api.run.pez.pivotal.io -u dmalone+jenkins@pivotal.io -p jenkins -o pivot-dmalone -s development"
  sh "./cf push -n personal-financier -p build/libs/*.jar"
}
