package com.example.clickbehaviorunit2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.clickbehaviorunit2.ui.theme.ClickBehaviorUnit2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClickBehaviorUnit2Theme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when(currentStep) {
            1-> StepOne {
                currentStep = 2
            }
            2 -> StepTwo {
                currentStep = 3
            }
            3 -> StepThree {
                currentStep = 4
            }
            4 -> StepFour {
                currentStep = 1
            }

        }
    }
}

@Composable
fun StepContent(
    imageRes : Int,
    textRes : Int,
    onClick : () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = stringResource(textRes),
            modifier = Modifier.wrapContentSize().clickable (
                onClick = onClick
            ).clip(
                RoundedCornerShape(16.dp))
                .background(color = Color(0xFFC0FFFF))
                .padding(8.dp)

        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = stringResource(R.string.lemon_restart_content_description),fontSize = 18.sp)

    }
}

@Composable
fun StepOne(onClick: () -> Unit) {
    StepContent(
        imageRes = R.drawable.lemon_tree,
        textRes = R.string.lemon_tree_content_description,
        onClick = onClick
    )
}

@Composable
fun StepTwo(onClick: () -> Unit) {
    val squeezeLemon = remember {(2..4).random()}
    var squeezeCount by remember { mutableIntStateOf(0) }
    StepContent(
        imageRes = R.drawable.lemon_squeeze,
        textRes = R.string.lemon_squeeze_content_description,
        onClick = {
            squeezeCount++
            if (squeezeCount > squeezeLemon)
                onClick()
        }
    )
}

@Composable
fun StepThree(onClick: () -> Unit) {
    StepContent(
        imageRes = R.drawable.lemon_drink,
        textRes = R.string.lemon_drink_content_description,
        onClick = onClick
    )
}

@Composable
fun StepFour(onClick: () -> Unit) {
    StepContent(
        imageRes = R.drawable.lemon_restart,
        textRes = R.string.lemon_restart_content_description,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClickBehaviorUnit2Theme {
        LemonApp()
    }
}



