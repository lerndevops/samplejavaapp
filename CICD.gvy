pipeline {
    agent any
    stages {
        stage('compile') {
	         steps {
                // step1 
                echo 'compiling..'
		            git url: 'https://github.com/lerndevops/samplejavaapp'
		            sh script: '/opt/apache-maven-3.8.4/bin/mvn compile'
           }
        }
        stage('codereview-pmd') {
	         steps {
                // step2
                echo 'codereview..'
		            sh script: '/opt/apache-maven-3.8.4/bin/mvn -P metrics pmd:pmd'
           }
	         post {
               success {
		             recordIssues enabledForFailure: true, tool: pmdParser(pattern: '**/target/pmd.xml')
               }
           }		
        }
        stage('unit-test') {
	          steps {
                // step3
                echo 'unittest..'
	               sh script: '/opt/apache-maven-3.8.4/bin/mvn test'
            }
	          post {
               success {
                   junit 'target/surefire-reports/*.xml'
               }
            }			
        }
        stage('codecoverage') {

           tools {
              jdk 'java1.8'
           }
	         steps {
                // step4
                echo 'codecoverage..'
		            sh script: '/opt/apache-maven-3.8.4/bin/mvn cobertura:cobertura -Dcobertura.report.format=xml'
           }
	         post {
               success {
	               cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: 'target/site/cobertura/coverage.xml', conditionalCoverageTargets: '70, 0, 0', failUnhealthy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false                  
               }
           }		
        }
        stage('package/build-war') {
	         steps {
                // step5
                echo 'package......'
		            sh script: '/opt/apache-maven-3.8.4/bin/mvn package'	
           }		
        }
        stage('build & push docker image') {
	         steps {
              withDockerRegistry(credentialsId: 'DOCKER_HUB_LOGIN', url: 'https://index.docker.io/v1/') {
                    sh script: 'cd  $WORKSPACE'
                    sh script: 'docker build --file Dockerfile --tag docker.io/lerndevops/samplejavaapp:$BUILD_NUMBER .'
                    sh script: 'docker push docker.io/lerndevops/samplejavaapp:$BUILD_NUMBER'
              }	
           }		
        }
        stage('deploy-QA') {
	         steps {
                    sh script: 'cd $WORKSPACE/deploy'
                    sh script: 'sudo ansible-playbook --inventory /tmp/myinv deploy-kube.yml --extra-vars "env=qa build=$BUILD_NUMBER"'
           }		
        }
    }
}
