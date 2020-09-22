package ash.sasamkar.plobaltask.model

import com.google.gson.annotations.SerializedName

class ApiResponse {

    @SerializedName("apps")
    var result : List<Store> ?=null

    fun getResults(): List<Store>? {
        return result
    }

    fun setResults(results: List<Store>) {
        result = results
    }

}