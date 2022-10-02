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
		sh script: '/opt/maven/bin/mvn verify'
           }
	   post {
               success {
	           jacoco changeBuildStatus: true, maximumBranchCoverage: '10', maximumClassCoverage: '10', maximumComplexityCoverage: '10', maximumInstructionCoverage: '10', maximumLineCoverage: '10', maximumMethodCoverage: '10'                  
               }
           }		
        }
        stage('package') {
	   steps {
                echo 'package......'
		sh script: '/opt/maven/bin/mvn package'	
           }		
        }
    }
}
