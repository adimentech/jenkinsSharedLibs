def call(body) {
    pipeline {
        agent any
        options {
            skipDefaultCheckout(true)
        }
        stages {
            stage( 'Checkout' ) {
                checkout scm
            }
            stage( 'Analyze' ) {
                sonar.analyze()
            }
            stage( 'Deploy' ) {
                maven.deploy()
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
}
