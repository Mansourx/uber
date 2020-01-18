package com.ahmadmansour.model

/**
 * Created by ahmad Mansour on 11,January,2020
 */
class Tip {

    var tipId: Int? = null
    var tipName: String? = null
    var tipDescription: String? = null
    var objectId: String? = null
    var timeStamp: Long = 0

    constructor(id: Int, n: String, d: String) {
        this.tipId = id
        this.tipName = n
        this.tipDescription = d
    }

    constructor(id: Int, n: String, d: String, objectId: String) {
        this.tipId = id
        this.tipName = n
        this.tipDescription = d
        this.objectId = objectId
    }
}