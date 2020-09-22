package ash.sasamkar.plobaltask.model

import com.google.gson.annotations.SerializedName

class Store {
    @SerializedName("name")
    var name : String ?=null

    @SerializedName("currency")
    var currency : String ?=null

    @SerializedName("money_format")
    var money_format : String?=null

    @SerializedName("data")
    var data : Data? = null

    class Data {
        @SerializedName("downloads")
        var downloads : StoreDetails ?= null

        @SerializedName("sessions")
        var sessions : StoreDetails ?=null

        @SerializedName("total_sale")
        var totalSale :StoreDetails ?=null

        @SerializedName("add_to_cart")
        var addToCart :StoreDetails ?=null


        class StoreDetails{
            @SerializedName("total")
            var total : Long ?=null

            @SerializedName("month_wise")
            var monthWise :MonthWise?=null

        }
        class  MonthWise{
            @SerializedName("jan")
            var jan : Integer ?=null
            @SerializedName("feb")
            var feb : Integer ?=null
            @SerializedName("mar")
            var mar : Integer ?=null
            @SerializedName("apr")
            var apr : Integer ?=null
            @SerializedName("may")
            var may : Integer ?=null
            @SerializedName("jun")
            var jun : Integer ?=null
        }

    }

}

