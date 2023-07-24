package id.barakkadev.coremap.data.model.offline.map

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

/** Created by github.com/im-o on 7/19/2023. */

data class GoMapPickParam(
    val buttonColor: Color? = null,
    val buttonTextColor: Color? = null,
    val buttonText: String? = null,
    val isShowBookmark: Boolean? = null,
    @DrawableRes val idResPickIcon: Int? = null,
    @DrawableRes val idResAddressIcon: Int? = null,
    @DrawableRes val idResBookmarkIcon: Int? = null,
)
