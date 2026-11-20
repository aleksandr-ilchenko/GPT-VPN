package com.example.gptvpn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF0B1220)
private val Panel = Color(0xFF172337)
private val Accent = Color(0xFF42A5F5)
private val Muted = Color(0xFF9FB0C5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { GptVpnScreen() }
    }
}

@Composable
private fun GptVpnScreen() {
    var selectedServer by remember { mutableStateOf("Автоматический") }
    var showInfo by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Background,
            surface = Panel,
            primary = Accent,
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = Background) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Security, contentDescription = null, tint = Accent)
                    Spacer(Modifier.width(10.dp))
                    Text("GPT VPN", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.weight(1f))
                    Text("BETA", color = Accent, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.height(64.dp))
                Box(
                    modifier = Modifier.size(132.dp)
                        .background(Color(0xFF142B43), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.PowerSettingsNew,
                        contentDescription = "VPN пока не подключён",
                        tint = Accent,
                        modifier = Modifier.size(64.dp)
                    )
                }
                Spacer(Modifier.height(22.dp))
                Text("Не подключено", fontSize = 25.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(8.dp))
                Text("VPN-сервер ещё не настроен", color = Muted, fontSize = 14.sp)

                Spacer(Modifier.height(40.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = Panel),
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        InfoRow("Сервер", selectedServer)
                        HorizontalDivider(color = Color(0xFF2A3B52))
                        InfoRow("Протокол", "WireGuard (планируется)")
                        HorizontalDivider(color = Color(0xFF2A3B52))
                        InfoRow("Режим", "Только ChatGPT (планируется)")
                    }
                }

                Spacer(Modifier.height(24.dp))
                OutlinedButton(
                    onClick = { showInfo = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Accent)
                ) {
                    Text("Настройка подключения")
                }

                Spacer(Modifier.weight(1f))
                Text(
                    "Это стартовая версия интерфейса. Интернет-трафик пока не туннелируется.",
                    color = Muted,
                    fontSize = 11.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }

    if (showInfo) {
        AlertDialog(
            onDismissRequest = { showInfo = false },
            title = { Text("Следующий этап") },
            text = {
                Text(
                    "Для настоящего VPN нужны адрес сервера и конфигурация WireGuard. " +
                    "Мы добавим импорт конфигурации и VPN-подключение после выбора сервера."
                )
            },
            confirmButton = {
                TextButton(onClick = { showInfo = false }) { Text("Понятно") }
            }
        )
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = Muted, fontSize = 14.sp)
        Text(value, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}
