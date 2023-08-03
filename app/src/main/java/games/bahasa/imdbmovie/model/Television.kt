package games.bahasa.imdbmovie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Tv(
    val page: Int,
    val results: List<DataTvShow>
)

@Parcelize
data class DataTvShow(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_language")
    val original_language: String?,
    @SerializedName("original_name")
    val original_name: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("media_type")
    val media_type: String?,
    @SerializedName("genre_ids")
    val genre_ids: List<Int>?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("first_air_date")
    val first_air_date: String?,
    @SerializedName("vote_average")
    val vote_average: Double?,
    @SerializedName("vote_count")
    val vote_count: Int?,
    @SerializedName("origin_country")
    val origin_country: List<String>?
):Parcelable