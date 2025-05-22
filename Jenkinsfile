pipeline {
    agent {
        kubernetes {
            yamlFile 'src/test/resources/k8s/jenkins-agent-pod.yaml'
            defaultContainer 'maven'
        }
    }

    parameters {
        choice(name: 'SIMULATION_TYPE', choices: ['SingleUser', 'Baseline', 'Load', 'Spike', 'Stress'], description: 'Tipo di test da eseguire')
        choice(name: 'SERVICE_TYPE', choices: ['User'], description: 'Tipo di microservizio da testare')
    }

    environment {
        MAVEN_OPTS = "-Dmaven.compiler.source=11 -Dmaven.compiler.target=11"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Gatling Test') {
            steps {
                sh """
                    mvn gatling:test -P${params.SERVICE_TYPE} -P${params.SIMULATION_TYPE}
                """
            }
        }

        stage('Publish Results') {
            steps {
                script {
                    def reportDir = sh(script: "ls -td target/gatling/${params.SIMULATION_TYPE.toLowerCase()}* | head -n 1", returnStdout: true).trim()
                    publishHTML(target: [
                        reportDir: reportDir,
                        reportFiles: 'index.html',
                        reportName: 'Gatling Report'
                    ])
                }
            }
        }
    }
}