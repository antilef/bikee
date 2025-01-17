pipeline {
    agent any

    tools {
        gradle "gradle default"
    }



    stages {
        stage('Build') {
            steps {
                sh 'gradle build'
            }
        }
    }
}

