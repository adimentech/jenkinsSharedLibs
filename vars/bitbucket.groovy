def prComment(String message) {
    def data = "{\"content\": {\"raw\": \"${message}\"}}"
    withCredentials([usernameColonPassword(credentialsId: 'bitbucket_http_access', variable: 'bb_credentials')]) {
        String prURL = "https://api.bitbucket.org/2.0/repositories/ClarizenInt/tf/pullrequests/12/comments"
        sh "curl -X POST -u \${bb_credentials} -H 'Content-Type: application/json' -d $data $prURL"
    }
}
