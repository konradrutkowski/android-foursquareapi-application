package konradrutkowski.com.tapapp.places

import io.realm.RealmObject

class Place : RealmObject() {
    var url: String? = null
    var id: String? = null
    var name: String? = null
    var city: String? = null
    var category: String? = null
    var checkins: String? = null
    var distance: String? = null
    var address: String? = null
    var dbID: Long = 0
}


