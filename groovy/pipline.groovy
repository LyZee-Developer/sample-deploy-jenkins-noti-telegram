@Library("sample-deploy-jenkins-noti-telegram-lib@main") _
pipeline {
    agent any
    environment{
        IMAGE_NAME="sample-deploy-jenkin-noti-telegram"
        scannerHome= tool 'sonarqube-scanner' 
    }
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
        stage("Check all of code where is fail or useless"){
            steps{
                withSonarQubeEnv(credentialsId: 'Token_Sample', installationName: 'sonarqube-scanner') {
                    script{
                
                        def projectKey = 'vue-sample-deploy-jenkis-noti-telegram' 
                        def projectName = 'SampleDeployJenkisNotificationTelegram'
                        def projectVersion = '1.0.0' 
                        sh """
                        ${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectKey=${projectKey} \
                        -Dsonar.projectName="${projectName}" \
                        -Dsonar.projectVersion=${projectVersion} \
                     """   
                        
                    }
                }
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
                withCredentials([usernamePassword(credentialsId: 'DOCKER_USER_NAME_PW', passwordVariable: 'PW', usernameVariable: 'UserDockerCode')]) {
                    script{
                        BuildPushDeploy("${UserDockerCode}","${IMAGE_NAME}","${currentBuild.number}")
                    }
                }
            }
        }
    }
}
