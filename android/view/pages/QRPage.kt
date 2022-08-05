package com.tobiasheymann.ep.view.pages

import android.annotation.SuppressLint
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import com.tobiasheymann.ep.R
import com.tobiasheymann.ep.controller.view.navigate
import com.tobiasheymann.ep.model.Plant
import com.tobiasheymann.ep.model.services.DatabaseService
import com.tobiasheymann.ep.view.NavDestination
import com.tobiasheymann.ep.view.components.SearchResultComponent
import java.util.*

@ExperimentalMaterialApi
@Composable
fun QRPage(navController: NavHostController) {
    val plantCache = remember { mutableStateListOf<Plant>() }
    val extractedIds = remember { mutableStateListOf<String>() }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleCameraPreview(Modifier.align(Alignment.Center)) { temp ->
            if (temp.isNotEmpty()) {
                extractedIds.forEach {
                    if (!temp.contains(it)) {
                        extractedIds.remove(it)
                    }
                }

                temp.forEach {
                    if (!extractedIds.contains(it)) {
                        extractedIds.add(it)
                    }
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.background_curve),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .rotate(180f)
                .offset(y = 8.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.background)
        )

        Image(
            painter = painterResource(id = R.drawable.background_curve),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .offset(y = 5.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.background)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(128.dp)
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_qr),
                contentDescription = null,
            )
            Text(text = "Scanne einen QRCode!", color = Color.White)
        }

        LazyColumn(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(extractedIds) {
                PlantPreview(navController = navController, plantCache = plantCache, uuid = it)
            }
        }

        Box(modifier = Modifier.padding(start = 24.dp, top = 36.dp)) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White)
                    .size(32.dp, 32.dp),
                content = {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp, 20.dp),
                        tint = Color.Black
                    )
                }
            )
        }
    }
}

@Composable
fun SimpleCameraPreview(
    modifier: Modifier,
    onDataChanged: (List<String>) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val executor = ContextCompat.getMainExecutor(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder()
                    .build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()

                val imageAnalysis = ImageAnalysis.Builder()
                    .setTargetResolution(Size(2160, 3840))
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .apply {
                        setAnalyzer(executor, BarcodeImageAnalyzer(onDataChanged))
                    }

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    imageAnalysis,
                    preview
                )
            }, executor)
            previewView
        },
        modifier = modifier.fillMaxSize(),
    )
}

class BarcodeImageAnalyzer(
    private val onDataChanged: (List<String>) -> Unit
) : ImageAnalysis.Analyzer {

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {

        val mediaImage = imageProxy.image

        if (mediaImage != null) {

            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            val options = BarcodeScannerOptions.Builder()
                .setBarcodeFormats(
                    Barcode.FORMAT_QR_CODE,
                    Barcode.FORMAT_AZTEC
                )
                .build()

            val scanner = BarcodeScanning.getClient(options)
            scanner.process(image)
                .addOnSuccessListener { barcodes ->

                    val regex = Regex("\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b")
                    val temp = mutableListOf<String>()
                    for (barcode in barcodes) {

                        when (barcode.valueType) {
                            Barcode.TYPE_TEXT -> {
                                barcode?.rawValue?.let {
                                    if (regex.matches(it)) {
                                        temp.add(it)
                                    }
                                }
                            }
                        }
                    }

                    temp.sortWith(Comparator.naturalOrder())
                    onDataChanged(temp)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun PlantPreview(navController: NavHostController, plantCache: MutableList<Plant>, uuid: String) {

    val plant = remember { mutableStateOf<Plant?>(null) }
    val filteredPlantCache = plantCache.filter { it.metadata.uuid == uuid }

    if (filteredPlantCache.isNotEmpty()) {
        plant.value = filteredPlantCache[0]
    } else {
        downloadPlant(uuid = uuid) {
            plantCache.add(it)
            plant.value = it
        }
    }


    plant.value?.also { previewPlant ->
        SearchResultComponent(navController = navController, plant = previewPlant)

    } ?: run {
        Card(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder_square_large),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(116.dp)
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
        }
    }
}

fun downloadPlant(uuid: String, callback: (Plant) -> Unit) {
    DatabaseService.searchPlantById(uuid = uuid, callback = callback)
}