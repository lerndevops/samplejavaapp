pipeline {
    agent any
    stages {
        stage('sonar quality check') {
	        steps {
                script{
                    withSonarQubeEnv(credentialsId: 'SonarqubeToken') {
                       // bat 'chmod +x gradlew' //+x mean we are giving the execute permissions to that file
                        bat './gradlew sonarqube' // It collects information from the project and all its subprojects, generating the properties for the analysis.
                    }
                }
            }
        }
    }
}
