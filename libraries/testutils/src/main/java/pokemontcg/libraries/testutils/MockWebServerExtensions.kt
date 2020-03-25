package pokemontcg.libraries.testutils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.enqueueResponse(responseCode: Int, body: String) {
    val response = MockResponse()
        .setResponseCode(responseCode)
        .setBody(body)
    this.enqueue(response)
}
