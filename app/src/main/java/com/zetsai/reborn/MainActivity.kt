package com.zetsai.reborn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Bg = Color(0xFF020712)
private val Panel = Color(0xFF07182B)
private val Cyan = Color(0xFF00D9FF)
private val Blue = Color(0xFF1678FF)
private val WhiteBlue = Color(0xFFD8F8FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.rgb(2, 7, 18)
        window.navigationBarColor = android.graphics.Color.rgb(2, 7, 18)

        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Cyan,
                    background = Bg,
                    surface = Panel
                )
            ) {
                ZetApp()
            }
        }
    }
}

@Composable
fun ZetApp() {
    var page by remember { mutableStateOf("Beranda") }

    Column(
        Modifier
            .fillMaxSize()
            .background(Bg)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(42.dp)
                    .border(2.dp, Cyan, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("Z", color = Cyan, fontSize = 28.sp,
                    fontWeight = FontWeight.Black)
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text("Zet's AI", color = WhiteBlue,
                    fontSize = 23.sp, fontWeight = FontWeight.Bold)
                Text("ONLINE • SIAP MEMBANTU",
                    color = Cyan, fontSize = 10.sp)
            }
            IconButton(onClick = { page = "Pengaturan" }) {
                Icon(Icons.Default.Settings, "Pengaturan", tint = Cyan)
            }
        }

        HorizontalDivider(color = Blue.copy(alpha = 0.6f))

        when (page) {
            "Chat" -> ChatPage()
            "Voice" -> VoicePage()
            "Tools" -> ToolsPage()
            "Pengaturan" -> SettingsPage()
            else -> HomePage(
                onOpen = { page = it }
            )
        }

        Spacer(Modifier.weight(1f, fill = false))

        Row(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFF06101F))
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NavItem("Beranda", Icons.Default.Home, page) { page = it }
            NavItem("Chat", Icons.Default.Chat, page) { page = it }
            NavItem("Voice", Icons.Default.Mic, page) { page = it }
            NavItem("Tools", Icons.Default.GridView, page) { page = it }
            NavItem("Pengaturan", Icons.Default.Settings, page) { page = it }
        }
    }
}

@Composable
fun NavItem(
    name: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: String,
    onSelect: (String) -> Unit
) {
    Column(
        Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onSelect(name) }
            .padding(horizontal = 8.dp, vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, name, tint = if (selected == name) Cyan
            else Color.Gray, modifier = Modifier.size(22.dp))
        Text(name, color = if (selected == name) Cyan
            else Color.LightGray, fontSize = 9.sp)
    }
}

@Composable
fun NeonPanel(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF0B2844), Color(0xFF040D1B))
                )
            )
            .border(1.dp, Blue.copy(alpha = 0.8f),
                RoundedCornerShape(18.dp))
            .padding(16.dp),
        content = content
    )
}

@Composable
fun HomePage(onOpen: (String) -> Unit) {
    LazyColumn(
        Modifier.fillMaxWidth().weight(1f),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            NeonPanel {
                Text("ASISTEN PRIBADI", color = Cyan,
                    fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("Halo, saya Zet's AI.",
                    color = WhiteBlue, fontSize = 25.sp,
                    fontWeight = FontWeight.Bold)
                Text("Ada yang bisa saya bantu hari ini?",
                    color = Color.LightGray, fontSize = 14.sp)
                Spacer(Modifier.height(18.dp))
                Box(
                    Modifier.fillMaxWidth().height(170.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(Modifier.fillMaxSize()) {
                        val center = Offset(size.width / 2, size.height / 2)
                        for (i in 1..5) {
                            drawCircle(
                                color = Cyan.copy(alpha = 0.8f / i),
                                radius = 32.dp.toPx() * i,
                                center = center,
                                style = androidx.compose.ui.graphics.drawscope.Stroke(
                                    width = if (i == 1) 3.dp.toPx() else 1.dp.toPx()
                                )
                            )
                        }
                    }
                    Text("Z", color = Cyan, fontSize = 72.sp,
                        fontWeight = FontWeight.Black)
                }
                Text("SISTEM SIAP DIGUNAKAN",
                    color = Cyan, fontSize = 10.sp)
            }
        }

        item {
            Text("Fitur Utama", color = WhiteBlue,
                fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        items(
            listOf(
                Triple("Chat AI", "Mulai percakapan", Icons.Default.Chat),
                Triple("Voice", "Mode perintah suara", Icons.Default.Mic),
                Triple("Tools", "Akses menu fitur", Icons.Default.GridView),
                Triple("Pengaturan", "Atur tampilan aplikasi", Icons.Default.Settings)
            )
        ) { item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Panel)
                    .border(1.dp, Blue.copy(alpha = 0.6f),
                        RoundedCornerShape(14.dp))
                    .clickable { onOpen(item.first) }
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(item.third, item.first, tint = Cyan,
                    modifier = Modifier.size(30.dp))
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(item.first, color = WhiteBlue,
                        fontWeight = FontWeight.Bold)
                    Text(item.second, color = Color.LightGray,
                        fontSize = 12.sp)
                }
                Icon(Icons.Default.ChevronRight, null, tint = Cyan)
            }
        }
    }
}

@Composable
fun ChatPage() {
    var input by remember { mutableStateOf("") }
    var messages by remember {
        mutableStateOf(listOf(
            "Zet: Halo! Saya Zet's AI. Ada yang ingin kamu tanyakan?"
        ))
    }

    Column(Modifier.fillMaxWidth().weight(1f).padding(12.dp)) {
        Text("MODE PERCAKAPAN", color = Cyan,
            fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))

        LazyColumn(
            Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(messages) { message ->
                NeonPanel {
                    Text(message, color = WhiteBlue, fontSize = 14.sp)
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Ketik pesan...") },
                singleLine = true
            )
            IconButton(onClick = {
                if (input.isNotBlank()) {
                    val question = input.trim()
                    messages = messages + "Kamu: $question" +
                        listOf("Zet: Ini versi awal Zet's AI. Koneksi AI online akan ditambahkan nanti.")
                    input = ""
                }
            }) {
                Icon(Icons.Default.Send, "Kirim", tint = Cyan)
            }
        }
    }
}

@Composable
fun VoicePage() {
    Column(
        Modifier.fillMaxWidth().weight(1f).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("VOICE COMMAND", color = Cyan, fontSize = 13.sp)
        Spacer(Modifier.height(30.dp))
        Box(
            Modifier.size(190.dp).border(3.dp, Cyan, CircleShape)
                .background(Panel, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Mic, "Mikrofon",
                tint = Cyan, modifier = Modifier.size(90.dp))
        }
        Spacer(Modifier.height(24.dp))
        Text("Mode suara siap", color = WhiteBlue, fontSize = 22.sp)
        Text("Fitur pengenalan suara akan diaktifkan pada tahap berikutnya.",
            color = Color.LightGray, fontSize = 13.sp)
    }
}

@Composable
fun ToolsPage() {
    Column(
        Modifier.fillMaxWidth().weight(1f).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Menu Fitur", color = Cyan, fontSize = 22.sp,
            fontWeight = FontWeight.Bold)
        listOf("Kontrol Aplikasi", "Informasi Cuaca",
            "Pencarian Web", "Pengingat", "Mode Kreatif").forEach {
            NeonPanel {
                Text(it, color = WhiteBlue, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun SettingsPage() {
    var darkMode by remember { mutableStateOf(true) }
    Column(
        Modifier.fillMaxWidth().weight(1f).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Pengaturan", color = Cyan, fontSize = 24.sp,
            fontWeight = FontWeight.Bold)
        NeonPanel {
            Text("Bahasa", color = Cyan)
            Text("Indonesia", color = WhiteBlue)
        }
        NeonPanel {
            Text("Tampilan", color = Cyan)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Mode gelap", color = WhiteBlue,
                    modifier = Modifier.weight(1f))
                Switch(checked = darkMode,
                    onCheckedChange = { darkMode = it })
            }
        }
        NeonPanel {
            Text("Tentang Zet's AI", color = Cyan)
            Text("Versi 1.0.0 • Reborn", color = WhiteBlue)
        }
    }
}
