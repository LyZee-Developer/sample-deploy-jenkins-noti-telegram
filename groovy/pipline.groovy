@Library("sample-deploy-jenkins-noti-telegram-lib@main") _
pipeline {
    agent any

    stages {
        stage("Processing our pipline "){
            steps{
                sh """
                echo 'Pipline is Processing 🙏'
                """
            }
        }
        stage('Clone code from git reposity') {
            steps {
                script{
                    CloneCodeFromGit()
                }
                sh """
                    ls -lrt
                """
            }
        }
        stage('Login user to docker') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DOCKER_USER_NAME_PW', passwordVariable: 'PW', usernameVariable: 'UserDockerCode')]) {
                    script{
                        LoginDocker("${UserDockerCode}","${PW}")
                    }
                }
                
            }
        }
        stage('Build push deploy project by docker hub') {
            steps {
                script{
                    BuildPushDeploy()
                }
            }
        }
    }
}
