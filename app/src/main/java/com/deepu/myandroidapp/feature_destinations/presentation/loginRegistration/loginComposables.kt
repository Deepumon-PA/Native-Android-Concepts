package com.deepu.myandroidapp.feature_destinations.presentation.loginRegistration

import androidx.compose.animation.*
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.deepu.myandroidapp.R
import com.deepu.myandroidapp.ui.theme.CaribbeanGreen
import com.deepu.myandroidapp.ui.theme.DarkByzantineBlue


@ExperimentalAnimationApi
@Composable
fun WelcomeScreen(navController: NavController) {

    val scrollState = rememberScrollState()

    // Smooth scroll to specified pixels on first composition
//    LaunchedEffect(Unit) { scrollState.animateScrollTo(10000) }

    Box() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(DarkByzantineBlue),
//            .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            val logoState = remember {
                MutableTransitionState(false).apply {
                    targetState = true
                }
            }

            val customEasing = Easing { fraction -> fraction * fraction }

            AnimatedVisibility(
                visibleState = logoState,
               /* enter = slideIn(
                    { fullSize ->
                        IntOffset(fullSize.width / 4, 100)
                    },
                    tween(500, easing = customEasing)
                )*/
//        exit = slideOut()
            ) {
                Image(
                    painterResource(id = R.drawable.trvellilng_man),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
//                        .drawBehind {
//
//                                    drawCircle(
//                                        center = Offset(0f,0f),
////                                        brush = Brush(),
//                                        radius = (size.width+20),
//                                        brush: Brush,
//                                        radius: Float = size.minDimension / 2.0f,
//                            center: Offset = this.center,
//                            /*@FloatRange(from = 0.0, to = 1.0)*/
//                            alpha: Float = 1.0f,
//                            style: DrawStyle = Fill,
//                            colorFilter: ColorFilter? = null,
//                            blendMode: BlendMode = DefaultBlendMode
                                    )

//                            val wavePoint1 = Offset(size.width * 0f, size.height * 0.5f)
//                            val wavePoint2 = Offset(size.width * .1f, size.height * 0.55f)
//                            val wavePoint3 = Offset(size.width * .4f, size.height * 0.75f)
//                            val wavePoint4 = Offset(size.width * 0.75f, size.height * 0.8f)
//                            val wavePoint5 = Offset(size.width * 1.4f, -size.height.toFloat())
//
//                            val wavePath = Path().apply {
//                                moveTo(
//                                    wavePoint1.x,
//                                    wavePoint1.y
//                                ) // path point is set to the start of wave
//                                standardQuadFromTo(
//                                    wavePoint1,
//                                    wavePoint2
//                                )// drawing a bezier curve between points
//                                standardQuadFromTo(wavePoint2, wavePoint3)
//                                standardQuadFromTo(wavePoint3, wavePoint4)
//                                standardQuadFromTo(wavePoint4, wavePoint5)
//                                lineTo(size.width * 100f, size.height * 100f)
//                                lineTo(size.width * -100f, size.height * 100f)
//                                close() // end will be connected to starting point on closing
//                            }
//
//                            drawPath(
//                                path = wavePath,
//                                color = CaribbeanGreen
//                            )
//                        },
//                )
            }


            var visible by remember {
                mutableStateOf(true)
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(color = Color.White, fontSize = 50.sp)
                    ) {
                        append("G")
                    }
                    append("oThere")
                },
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(bottom = 10.dp)
                /* TextStyle(
                     color = Color.White,
                     fontWeight = FontWeight.ExtraBold,
                     fontSize = 30.sp,
                     fontFamily =
                 ), textAlign = TextAlign.Center*/
            )

            Text(
                text = "Explore more, Keep connected, the world is wide and you are welcome",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                textAlign = TextAlign.Center
            )


        }

        Button(

            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(backgroundColor = CaribbeanGreen),
            enabled = true,
//            border = BorderStroke(1.dp, brush = SolidColor(Color.Red)),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Take Me", style = MaterialTheme.typography.h1)
        }

    }

}

@ExperimentalAnimationApi
//@Preview
@Composable
fun WelcomeText(/*@PreviewParameter(FakePreviewProvider::class) navController: NavController*/
    visible: Boolean
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(color = Color(0xff2B2844))
//            .background(color = Color(0xff1EC28B))
            .clip(RoundedCornerShape(20.dp))
            .padding(bottom = 50.dp),
        contentAlignment = Alignment.Center
    ) {

        val density = LocalDensity.current
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                // Slide in from 40 dp from the top.
                initialOffsetY = { with(density) { -40.dp.roundToPx() } }
            ) + expandVertically(
                // Expand from the top.
                expandFrom = Alignment.Top
            ) + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {


        }
    }

}


//another way of using canvas graphics
@Composable
fun LoginAesthetics() {

    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(20.dp)
    ) {

    }

}

data class FakePreviewProvider(override val values: Sequence<NavController> = sequenceOf()) :
    PreviewParameterProvider<NavController>


//useful code - canvas custom path


//            val wavePoint1 = Offset(size.width * 0f, size.height * 0.5f)
//            val wavePoint2 = Offset(size.width * .1f, size.height * 0.55f)
//            val wavePoint3 = Offset(size.width * .4f, size.height * 0.05f)
//            val wavePoint4 = Offset(size.width * 0.75f, size.height * 0.7f)
//            val wavePoint5 = Offset(size.width * 1.4f, -size.height.toFloat())

//            val wavePoint1 = Offset(size.width * 0f, size.height * 0.5f)
//            val wavePoint2 = Offset(size.width * .1f, size.height * 0.55f)
//            val wavePoint3 = Offset(size.width * .4f, size.height * 0.75f)
//            val wavePoint4 = Offset(size.width * 0.75f, size.height * 0.8f)
//            val wavePoint5 = Offset(size.width * 1.4f, -size.height.toFloat())
//
//            val wavePath = Path().apply {
//                moveTo(wavePoint1.x, wavePoint1.y) // path point is set to the start of wave
//                standardQuadFromTo(
//                    wavePoint1,
//                    wavePoint2
//                )// drawing a bezier curve between points
//                standardQuadFromTo(wavePoint2, wavePoint3)
//                standardQuadFromTo(wavePoint3, wavePoint4)
//                standardQuadFromTo(wavePoint4, wavePoint5)
//                lineTo(size.width * 100f, size.height * 100f)
//                lineTo(size.width * -100f, size.height * 100f)
//                close() // end will be connected to starting point on closing
//            }
//
//            drawPath(
//                path = wavePath,
//                color = Color.Blue
//            )



