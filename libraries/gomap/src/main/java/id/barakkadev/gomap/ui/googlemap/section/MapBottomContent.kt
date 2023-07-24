package id.barakkadev.gomap.ui.googlemap.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import id.barakkadev.coremap.R
import id.barakkadev.coremap.data.UiState
import id.barakkadev.coremap.data.model.offline.map.GoMapPickParam
import id.barakkadev.coremap.domain.model.googlemap.MapGeocode
import id.barakkadev.coremap.ui.theme.Gray200
import id.barakkadev.coremap.util.Extensions.myToast
import id.barakkadev.gomap.ui.googlemap.GoogleMapViewModel

/** Created by github.com/im-o on 7/19/2023. */

@Composable
fun MapBottomContent(
    goMapPickParam: GoMapPickParam? = null,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onReverseGeocodeLoaded: (mapGeocode: MapGeocode?) -> Unit = {},
    onBookmarkClicked: (goMapPickParam: GoMapPickParam?) -> Unit = {},
) {
    val viewModel: GoogleMapViewModel = hiltViewModel()
    var isLoadGeocode by remember { mutableStateOf(false) }
    var mapGeocode by remember { mutableStateOf<MapGeocode?>(null) }
    val latLng = "${cameraPositionState.position.target.latitude},${cameraPositionState.position.target.longitude}"

    if (!cameraPositionState.isMoving) {
        viewModel.uiStateReverseGeocode.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    isLoadGeocode = true
                    viewModel.reverseGeocodeApiCall(latLng)
                }

                is UiState.Success -> {
                    if (uiState.data != mapGeocode) {
                        isLoadGeocode = false
                        mapGeocode = uiState.data
                    }
                }

                is UiState.Error -> {
                    LocalContext.current.myToast(uiState.errorMessage)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        val isOrLoading = cameraPositionState.isMoving || isLoadGeocode
        val isNotLoading = !cameraPositionState.isMoving && !isLoadGeocode
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(color = Gray200)
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Image(
                painter = painterResource(goMapPickParam?.idResAddressIcon ?: R.drawable.red_marker),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .weight(1f)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(
                        top = 12.dp,
                        bottom = 12.dp,
                        start = 0.dp,
                        end = if (goMapPickParam?.isShowBookmark == true) 0.dp else 16.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(4f)
            ) {
                Spacer(modifier = Modifier.size(if (isOrLoading) 16.dp else 0.dp))
                Text(
                    text = if (isOrLoading) stringResource(id = R.string.loading)
                    else mapGeocode?.shortName ?: stringResource(id = R.string.loading),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.size(if (isOrLoading) 16.dp else 6.dp))
                if (isNotLoading) Text(
                    text = mapGeocode?.formattedAddress ?: stringResource(id = R.string.loading),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (goMapPickParam?.isShowBookmark == true) Image(
                painter = painterResource(goMapPickParam.idResBookmarkIcon ?: R.drawable.bookmark),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
                    .weight(1f)
                    .clickable {
                        onBookmarkClicked(goMapPickParam)
                    }
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(48.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                onReverseGeocodeLoaded(mapGeocode)
            },
            enabled = isNotLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = goMapPickParam?.buttonColor ?: MaterialTheme.colorScheme.primary,
                contentColor = goMapPickParam?.buttonTextColor ?: Color.White,
            )
        ) {
            if (isOrLoading) CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 2.dp,
                modifier = Modifier.size(22.dp)
            )
            else Text(
                text = goMapPickParam?.buttonText ?: stringResource(id = R.string.pick_location),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.ExtraBold),
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}