@Grab("groovyx.net.http")
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType

def prComment(String comment) {
    def http = new HTTPBuilder('https://api.bitbucket.org/2.0/repositories/ClarizenInt')
    http.auth.basic(env.bb_user, env.bb_pass)
    http.request(POST) {
        uri.path = '/tf/pullrequests/7/comments'
        body = ['content': ['raw': comment]]
        requestContentType = ContentType.JSON

        response.success = { resp ->
            println "Success! ${resp.status}"
        }

        response.failure = { resp ->
            println "Request failed with status ${resp.status}"
        }
    }
}
