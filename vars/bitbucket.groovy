@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )

def oldprComment(String comment) {
    def http = new groovyx.net.http.HTTPBuilder('https://api.bitbucket.org/2.0/repositories/ClarizenInt')
    http.auth.basic(env.bb_user, env.bb_pass)
    http.request(groovyx.net.http.Method.POST) {
        uri.path = '/tf/pullrequests/7/comments'
        body = ['content': ['raw': comment]]
        requestContentType = groovyx.net.http.ContentType.JSON

        response.success = { resp ->
            println "Success! ${resp.status}"
        }

        response.failure = { resp ->
            println "Request failed with status ${resp.status}"
        }
    }
}

def prComment(String message) {
    withCredentials([usernamePassword(credentialsId: 'bitbucket_http_access', passwordVariable: 'bb_pass', usernameVariable: 'bb_user')]) {
        def baseUrl = new URL("https://api.bitbucket.org/2.0/repositories/ClarizenInt/tf/pullrequests/7/comments").openConnection()
        def comment = "{'content': {'raw': ${message}}"
        baseUrl.setRequestMethod("POST")
        baseUrl.setDoOutput(true)
        baseUrl.setRequestProperty("Content-Type", "application/json")
        baseUrl.setRequestProperty("Authorization", "Basic ${bb_user}:${bb_pass}")
        baseUrl.getOutputStream().write(comment.getBytes("UTF-8"));
        try {
            def postRC = baseUrl.getResponseCode();
        } catch(ex) {
            println(ex)
        }
    }
}
