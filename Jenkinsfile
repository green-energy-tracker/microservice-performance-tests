pipeline {
    agent any

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
                publishHTML(target: [
                    reportDir: 'target/gatling',
                    reportFiles: "${params.SIMULATION_TYPE.toLowerCase()}-index.html",
                    reportName: 'Gatling Report'
                ])
            }
        }
    }
}