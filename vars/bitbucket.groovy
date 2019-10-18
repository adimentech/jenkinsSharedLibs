@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )

def prComment(String comment) {
    def http = new groovyx.net.http.HTTPBuilder('https://api.bitbucket.org/2.0/repositories/ClarizenInt')
    http.auth.basic(env.bb_user, env.bb_pass)
    http.request(POST) {
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
