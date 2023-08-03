package games.bahasa.imdbmovie.data.api



import androidx.viewbinding.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {

        private val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }


        private fun headerInterceptor(): Interceptor {
            return Interceptor { cain ->
                val request = cain.request()
                val headerInterceptorRequest = request.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0M2MxODk1M2IxZDRhYWM0ZGU3MGQ3NDc2MWQ5MWY5NSIsInN1YiI6IjYyOGE2MjNiMWEzMjQ4NzdkZWNmOGVjOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.nZJqxofsC8tfvbU8zH7ZPMDBCUNlJIL2G7HJHAZ4S4c")
                    .build()

                cain.proceed(headerInterceptorRequest)
            }
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun getApiService(): ApiServices = retrofit.create(ApiServices::class.java)
    }
}