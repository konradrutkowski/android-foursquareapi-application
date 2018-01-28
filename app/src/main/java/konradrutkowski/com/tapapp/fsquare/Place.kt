package konradrutkowski.com.tapapp.fsquare

import android.os.Parcel
import android.os.Parcelable

class Place() : Parcelable {
    var url: String? = null
    var id: String? = null
    var name: String? = null
    var city: String? = null
    var category: String? = null
    var checkins: String? = null
    var distance: String? = null
    var address: String? = null
    var dbID: Long = 0

    constructor(parcel: Parcel) : this() {
        url = parcel.readString()
        id = parcel.readString()
        name = parcel.readString()
        city = parcel.readString()
        category = parcel.readString()
        checkins = parcel.readString()
        distance = parcel.readString()
        address = parcel.readString()
        dbID = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(city)
        parcel.writeString(category)
        parcel.writeString(checkins)
        parcel.writeString(distance)
        parcel.writeString(address)
        parcel.writeLong(dbID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Place> {
        override fun createFromParcel(parcel: Parcel): Place {
            return Place(parcel)
        }

        override fun newArray(size: Int): Array<Place?> {
            return arrayOfNulls(size)
        }
    }
}


