pipeline {
    agent any
    tools {
        maven 'Maven 3.6'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage ('Artifacts') {
            when {
              branch "master"
            }

            steps {
                sh 'cp ./target/WASMetadataExtractor-*-SNAPSHOT-jar-with-dependencies.jar /ci/artifacts/'
                sh 'chmod a+r /ci/artifacts/WASMetadataExtractor-*-SNAPSHOT-jar-with-dependencies.jar'
            }
        }
    }
}
