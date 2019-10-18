def prComment(String message) {
    withCredentials([usernamePassword(credentialsId: 'bitbucket_http_access', passwordVariable: 'bb_pass', usernameVariable: 'bb_user')]) {
        try {
            def baseUrl = new URL("https://api.bitbucket.org/2.0/repositories/ClarizenInt/tf/pullrequests/12/comments").openConnection()
            def comment = "{'content': {'raw': ${message}}"
            baseUrl.setRequestMethod("POST")
            baseUrl.setDoOutput(true)
            baseUrl.setRequestProperty("Content-Type", "application/json")
            baseUrl.setRequestProperty("Authorization", "Basic ${bb_user}:${bb_pass}")
            baseUrl.getOutputStream().write(comment.getBytes("UTF-8"));
            def postRC = baseUrl.getResponseCode();
        } catch (ex) {
            println(ex)
            println(ex.message)
        }
    }
}
