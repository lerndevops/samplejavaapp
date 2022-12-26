pipeline {
    agent any
    stages {
        stage('sonar quality check') {
	        steps {
                script{
                    withSonarQubeEnv(credentialsId: 'SonarqubeToken') {
                        //sh 'chmod +x gradlew' //+x mean we are giving the execute permissions to that file
                        bat ' ./gradlew.bat sonarqube' // It collects information from the project and all its subprojects, generating the properties for the analysis.
                    }
                }
            }
        }
    }
}
