package bitflyday.com.mobile.application.rcode.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.RuntimeException
import bitflyday.com.mobile.application.rcode.result.Result

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): Result<R>{
        return try {
            withContext(coroutineDispatcher){}
            execute(parameters).let{
                Result.Success(it)
            }
        }catch (e: Exception){
            Result.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}