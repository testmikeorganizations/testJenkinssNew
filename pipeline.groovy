#!groovy

echo "Starting workflow"

stage 'build'
node {
    echo "Cloning Project"
    git 'https://github.com/bijukunjummen/sample-spring-cloud-svc-ci'

    echo "Building the Project with Gradle Wrapper"
    sh './gradlew build -x test'
}

stage 'test'
node {
    sh './gradlew clean test assemble'
    // step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/TEST-*.xml'])
}

// stage 'bump-version'
// node {
//   sh './gradlew -q bumpPatch release printVersion'
// }

stage 'deploy'
node {
  sh "ls -la"
  sh "./gradlew cf-push -Pcf.ccHost=api.run.pez.pivotal.io -Pcf.ccUser=bkunjummen+jenkins@pivotal.io -Pcf.ccPassword=jenkins -Pcf.org=pivot-bkunjummen -Pcf.space=development -Pcf.domain=cfapps.pez.pivotal.io -Pcf.hostName=sample-spring-cloud-svc-ci-dev"
}

node {
  input 'Deploy to Test?'
  sh "./gradlew cf-push -Pcf.ccHost=api.run.pez.pivotal.io -Pcf.ccUser=bkunjummen+jenkins@pivotal.io -Pcf.ccPassword=jenkins -Pcf.org=pivot-bkunjummen -Pcf.space=test -Pcf.domain=cfapps.pez.pivotal.io -Pcf.hostName=sample-spring-cloud-svc-ci-test"
}

node {
  input 'Deploy to Prod?'
  sh "./gradlew cf-push -Pcf.ccHost=api.run.pez.pivotal.io -Pcf.ccUser=bkunjummen+jenkins@pivotal.io -Pcf.ccPassword=jenkins -Pcf.org=pivot-bkunjummen -Pcf.space=prod -Pcf.domain=cfapps.pez.pivotal.io -Pcf.hostName=sample-spring-cloud-svc-ci-prod"
}
