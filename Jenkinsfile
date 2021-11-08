pipeline {
    agent any
 environment {
		DOCKERHUB_CREDENTIALS=credentials('DockerHub')
	}
    stages{
        stage('clone and clean repo'){
            steps {
            	bat "cd .."
            	bat "rmdir /S /q TimeSheet-Devops"
                bat "git clone -b Entreprise https://github.com/4Dev-Up/TimeSheet-Devops"
                bat "mvn clean install -U -f TimeSheet-Devops"
            }
        }
        stage('Test'){
            steps{ bat "mvn test -f TimeSheet-Devops"}
        }
        stage('Deploy'){
            steps {
                bat "mvn package -f TimeSheet-Devops"
                bat "mvn deploy -f TimeSheet-Devops"
                bat "mvn sonar:sonar -f TimeSheet-Devops"
                //build image on docker
            }
        }
        //send email
        stage('Email'){
            steps{
                //extended email
                emailext body: 'New Commit! Deployed to Sonar Check it.', subject: 'Hey mahdi !', to: 'mehdi.hrairi@esprit.tn'
            }
        }
        //build image on docker
        stage('Build Docker Image') {

			steps {
				bat 'docker build -t mahdi0606/timesheet:latest .'
			}
		}
		/// stage('Build Docker Mysql Images') {

			///steps {
				///bat 'docker compose up'
			///}
		///}
	}
}