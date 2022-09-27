pipeline {
agent any
stages {
    stage('compile') {
	    steps { 
		    echo 'compiling..'
		    git url: 'https://github.com/irfanhaneefcl/samplejavaapp'
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
		    sh 'cd $WORKSPACE'
		    sh 'docker build --file Dockerfile --tag irfanhaneefcl/samplejavaapp:$BUILD_NUMBER .'
		    withCredentials([string(credentialsId: 'DOCKER_HUB_LOGIN', variable: 'DOCKER_HUB_LOGIN')]) {
			    sh "docker login -u irfanhaneefcl -p ${DOCKER_HUB_LOGIN}"
		    }
		    sh 'docker push irfanhaneefcl/samplejavaapp:$BUILD_NUMBER'
	    }
    }
    stage('Deploy-QA') {
	    steps {
		    sh 'ansible-playbook --inventory /etc/ansible/myinv deploy/deploy-kube.yml --extra-vars "env=qa build=$BUILD_NUMBER"'
	    }
    }
}
}
