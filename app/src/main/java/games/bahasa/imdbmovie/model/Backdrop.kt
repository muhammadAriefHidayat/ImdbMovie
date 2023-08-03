package games.bahasa.imdbmovie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class ImagesBackdrop(
    @SerializedName("backdrops")
    val backdrops : List<DataBackdrop>
)

@Parcelize
data class DataBackdrop(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @SerializedName("file_path")
    val filePath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("width")
    val width: Int?
) : Parcelable