package com.ahmadmansour.model

/**
 * Created by ahmad Mansour on 11,January,2020
 */
class Place {

    var placeID: Int? = null
    var placeName: String? = null
    var placeDescription: String? =null
    var placeImg: Int? = null

    constructor(id: Int, pn: String, pd: String, pi: Int) {
        this.placeID = id
        this.placeName = pn
        this.placeDescription = pd
        this.placeImg = pi
    }
}