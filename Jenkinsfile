pipeline {
    agent any
 environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerHub')
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
        stage('Build') {

			steps {
				bat 'docker build -t mahdi0606/timesheet:latest .'
			}
		}

		stage('Login') {

			steps {
				bat 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}

		stage('Push') {

			steps {
				bat 'docker push mahdi0606/timesheet:latest'
			}
		}
	}

	post {
		always {
			bat 'docker logout'
		}
    }
}