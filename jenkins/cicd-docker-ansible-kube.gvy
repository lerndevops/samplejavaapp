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
    stage('package/build-war') {
	    steps {
		    echo 'package......'
		    sh script: '/opt/maven/bin/mvn package'	
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
    stage('Deploy-QA') {
	    steps {
		    sh 'ansible-playbook --inventory /tmp/myinv deploy/deploy-kube.yml --extra-vars "env=qa build=$BUILD_NUMBER"'
	    }
    }
}
}
