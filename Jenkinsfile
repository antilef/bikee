pipeline {
    agent any

    tools {
        gradle "gradle default"
    }



    stages {
        stage('Build') {
            steps {
                sh 'gradle clean build --no-daemon'
            }
        }

        stage('test') {
            steps {
                sh 'gradle test --no-daemon'
            }
        }
    }
}

