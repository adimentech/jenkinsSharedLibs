pipeline {
    agent any
    options {
        skipDefaultCheckout(true)
    }
    stages {
        stage( 'Checkout' ) {
            steps {
                checkout scm
            }
        }
        stage( 'Compile' ) {
            steps {
                script {
                    maven.compile()
                }
            }
        }
        stage( 'Analyze' ) {
            steps {
                script {
                    sonar.analyze()
                }
            }
        }
        stage( 'Deploy' ) {
            steps {
                script {
                    maven.deploy()
                }
            }
        }
    }
    post {
        always {
            echo "Always actions"
        }
        success {
            echo "Success actions"
        }
        failure {
            echo "Failure actions"
        }
    }
}
