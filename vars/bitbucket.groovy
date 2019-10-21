def prComment(String message, String credentials) {
    def data = "{\"content\": {\"raw\": \"${message}\"}}"
    //TODO: no error handling in case of unset variables
    withCredentials([usernameColonPassword(credentialsId: "${credentials}", variable: 'bb_credentials')]) {
        String prURL = "${getPrApiUrl()}/comments"
        sh "curl -s -X POST -u \${bb_credentials} -H 'Content-Type: application/json' -d '$data' $prURL"
    }
}

def getPrApiUrl() {
    def changePath = new URL(env.CHANGE_URL).getPath()
    def (bbID, bbRepo, rest) = changePath.tokenize("/")
    return "https://api.bitbucket.org/2.0/repositories/${bbID}/${bbRepo}/pullrequests/${env.CHANGE_ID}"
}