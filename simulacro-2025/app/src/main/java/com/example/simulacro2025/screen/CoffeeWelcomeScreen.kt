import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.simulacro2025.R
import com.example.simulacro2025.ui.theme.Black
import com.example.simulacro2025.ui.theme.Lighter
import com.example.simulacro2025.ui.theme.Marron
import com.example.simulacro2025.ui.theme.MaskColor
import com.example.simulacro2025.ui.theme.Simulacro2025Theme
import com.example.simulacro2025.ui.theme.White

@Composable
fun CoffeeWelcomeScreen(
    modifier: Modifier,
    onGetStartedClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize().background(color = Black)
    ) {

        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "Coffee Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().offset(0.dp, -100.dp).background(color = Black),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaskColor)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.splash_title),
                color = White,
                fontSize = 32.sp,
                lineHeight = 1.5.em,
                letterSpacing = TextUnit(0.5F, TextUnitType.Sp),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.splash_subtitle),
                color = Lighter,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onGetStartedClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Marron
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(text = "Get Started", color = White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Simulacro2025Theme {
        CoffeeWelcomeScreen(
            Modifier,
            {}
        )
    }
}