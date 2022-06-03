pipeline {
    agent any
    stages {
        stage('checkout repo') {
			steps {
				git url: 'https://github.com/lerndevops/samplejavaapp.git'
            }
        }
        stage('build artifact') {
			steps {
				sh '/opt/maven/bin/mvn clean package'
            }
        }
        stage('build docker image') {
			steps {
				sh 'docker build -t lerndevops/samplejavaapp .'
            }	
        }
        stage('push docker image') {
			steps {
				withCredentials([string(credentialsId: 'DOCKER_HUB_PWD', variable: 'DOCKER_HUB_PWD')]) {
                sh "docker login -u lerndevops -p ${DOCKER_HUB_PWD}"
				}
				sh 'docker push lerndevops/samplejavaapp'
			}
        }
        stage('Deploy to K8s') {
			steps {
				sh 'kubectl apply -f deploy/sampleapp-deploy-k8s.yml'
			}
        }
    }
}
