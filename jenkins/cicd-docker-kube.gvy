pipeline {
    agent any
    stages {
        stage('compile') {
	   steps {
                echo 'compiling..'
		git url: 'https://github.com/lerndevops/samplejavaapp'
		sh script: '/opt/maven/bin/mvn compile'
           }
        }
        stage('codereview-pmd') {
	   steps {
                echo 'codereview..'
		sh script: '/opt/maven/bin/mvn -P metrics pmd:pmd'
           }
	   post {
               success {
		   recordIssues enabledForFailure: true, tool: pmdParser(pattern: '**/target/pmd.xml')
               }
           }		
        }
        stage('unit-test') {
	   steps {
                echo 'unittest..'
	        sh script: '/opt/maven/bin/mvn test'
                 }
	   post {
               success {
                   junit 'target/surefire-reports/*.xml'
               }
           }			
        }
        stage('codecoverage') {
	   steps {
                echo 'codecoverage..'
		sh script: '/opt/maven/bin/mvn cobertura:cobertura -Dcobertura.report.format=xml'
           }
	   post {
               success {
	           cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: 'target/site/cobertura/coverage.xml', conditionalCoverageTargets: '70, 0, 0', failUnhealthy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false                  
               }
           }		
        }
        stage('package') {
	   steps {
                echo 'package......'
		sh script: '/opt/maven/bin/mvn package'	
           }		
        }
        stage('build docker image') {
	   steps {
	        sh 'cd $WORKSPACE'
		sh 'docker build --file Dockerfile --tag lerndevops/samplejavaapp:$BUILD_NUMBER .'
            }	
        }
        stage('push docker image') {
	   steps {
		withCredentials([string(credentialsId: 'DOCKER_HUB_PWD', variable: 'DOCKER_HUB_PWD')]) {
                	sh "docker login -u lerndevops -p ${DOCKER_HUB_PWD}"
			}
		sh 'docker push lerndevops/samplejavaapp:$BUILD_NUMBER'
		}
        }
        stage('Deploy to K8s') {
  	   steps {
    		sh 'sed -i "s/bno/"$BUILD_NUMBER"/g" deploy/sampleapp-deploy-k8s.yml'
    		sh 'kubectl apply -f deploy/sampleapp-deploy-k8s.yml'
	   }
	   post { 
              always { 
                cleanWs() 
	      }
	   }
	}   
    }

