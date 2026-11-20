package ru.omgtu.babikova.spacelaunches.data

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal actual suspend fun processInWorker(operation: String, body: String): List<String>? =
    suspendCancellableCoroutine { continuation ->
        val close = startWorker(operation, body,
            { count, item ->
                continuation.resume(object : AbstractList<String>() {
                    override val size = count
                    override fun get(index: Int): String = item(index)
                })
            },
            { continuation.resumeWithException(IllegalStateException("Data processing failed")) },
        )
        continuation.invokeOnCancellation { close() }
    }

@OptIn(kotlin.js.ExperimentalWasmJsInterop::class)
@JsFun("""(operation, body, success, failure) => {
    const worker = new Worker(new URL("launch-worker/main.mjs", document.baseURI), { type: "module" });
    worker.onmessage = ({ data }) => {
        worker.terminate();
        if (data === null) failure();
        else success(data.length, index => data[index]);
    };
    worker.onerror = () => {
        worker.terminate();
        failure();
    };
    worker.postMessage({ operation, body });
    return () => worker.terminate();
}""")
private external fun startWorker(
    operation: String,
    body: String,
    success: (Int, (Int) -> String) -> Unit,
    failure: () -> Unit,
): () -> Unit
