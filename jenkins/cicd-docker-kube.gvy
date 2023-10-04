pipeline {
    agent any
    stages {
      stage('compile') {
	        steps {
		            git url: 'https://github.com/lerndevops/samplejavaapp'
		            sh script: '/opt/maven/bin/mvn compile'
          }
      }
      stage('codereview-pmd') {
	      steps {
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
	        sh script: '/opt/maven/bin/mvn verify'
        }
	      post {
          success {
            jacoco buildOverBuild: true, deltaBranchCoverage: '20', deltaClassCoverage: '20', deltaComplexityCoverage: '20', deltaInstructionCoverage: '20', deltaLineCoverage: '20', deltaMethodCoverage: '20'
          }
        }			
      }
      stage('package') {
	      steps {
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
	    }   
    }
}
