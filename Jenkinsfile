pipeline {
    agent any

    triggers {
        pollSCM('*/3 * * * *')
    }

    environment {
        AWS_ACCESS_KEY_ID = credentials('awsAccessKeyId')
        AWS_SECRET_ACCESS_KEY = credentials('awsSecretAccessKey')
        AWS_DEFAULT_REGION = 'ap-northeast-2'
        HOME = '.' // Avoid npm root owned
    }

    stages {
        // 레포지토리를 다운로드 받음
        stage('Prepare') {
            agent any

            steps {
                echo "Lets start Long Journey! ENV: ${ENV}"
                echo 'Clonning Repository'

                git url: 'https://github.com/jiyoung10/boardProject.git'
                    branch: 'master'
                    credentialsId: 'tokenForJenkins'
            }

            post {

                success {
                    echo 'prepare success'
                }

                always {
                    echo 'done prepare'
                }

                cleanup {
                    echo 'after all other post conditions'
                }
            }
        }

        stage('Build Gradle') {
            steps {
                sh 'chmod *+ gradlew'
                sh './gradlew clean build'

                sh 'ls -al ./build'
            }
        }

        stage('Docker build image') {
            steps {

            sh 'docker build . -t boardProject/docker-jenkins-pipeline-test'
            }
        }

        stage('Docker push image') {
            steps {
                withCredentials({})
            }
        }

        // aws s3에 파일 업로드
        stage('Deploy Frontend') {
            steps {
                echo 'Deploying Frontend'
                // 프론트엔드 디렉토리의 정적 파일들을 S3에 올림, 이 전에 반드시 EC2 instance profile 을 등록해야 함
                dir ('./website') {
                    sh '''
                    aws s3 sync ./ s3://jenkinstestserverbucket
                    '''
                }
            }
        } // stage Deploy Frontend

        post {
            success {
                echo 'Successfully Cloned Repository'

                mail to: 'zhsclqzlffj2@gmail.com',
                     subject: "Deploy Frontend Success",
                     body: "Successfully deployed frontend!"
            }
            failure {
                echo 'I failed :('

                mail to: 'zhsclqzlffj2@gmail.com',
                     subject: "Filed Pipline",
                     body: "Something is wrong with deploy frontend!"

            }
        }
    }
}