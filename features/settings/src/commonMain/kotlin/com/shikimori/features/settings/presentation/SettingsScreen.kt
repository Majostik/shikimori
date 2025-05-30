package com.shikimori.features.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

enum class ThemeMode {
    LIGHT, DARK, SYSTEM
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    currentTheme: ThemeMode = ThemeMode.SYSTEM,
    onThemeChanged: (ThemeMode) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SettingsTopBar()
        
        SettingsContent(
            currentTheme = currentTheme,
            onThemeChanged = onThemeChanged
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsTopBar() {
    TopAppBar(
        title = { Text("Settings") },
        navigationIcon = {
            Icon(
                Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Composable
private fun SettingsContent(
    currentTheme: ThemeMode,
    onThemeChanged: (ThemeMode) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AppearanceSection(
            currentTheme = currentTheme,
            onThemeChanged = onThemeChanged
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        AboutSection()
    }
}

@Composable
private fun AppearanceSection(
    currentTheme: ThemeMode,
    onThemeChanged: (ThemeMode) -> Unit
) {
    SectionHeader(text = "Appearance")
    
    SettingsCard {
        ThemeSelector(
            currentTheme = currentTheme,
            onThemeChanged = onThemeChanged
        )
    }
}

@Composable
private fun AboutSection() {
    SettingsCard {
        AboutInfo()
    }
}

@Composable
private fun SectionHeader(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
private fun SettingsCard(
    content: @Composable () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun ThemeSelector(
    currentTheme: ThemeMode,
    onThemeChanged: (ThemeMode) -> Unit
) {
    Column(
        modifier = Modifier.selectableGroup()
    ) {
        Text(
            text = "Theme",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        ThemeOption(
            text = "Light",
            icon = Icons.Default.Star,
            selected = currentTheme == ThemeMode.LIGHT,
            onClick = { onThemeChanged(ThemeMode.LIGHT) }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        ThemeOption(
            text = "Dark",
            icon = Icons.Default.Add,
            selected = currentTheme == ThemeMode.DARK,
            onClick = { onThemeChanged(ThemeMode.DARK) }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        ThemeOption(
            text = "System default",
            icon = Icons.Default.Settings,
            selected = currentTheme == ThemeMode.SYSTEM,
            onClick = { onThemeChanged(ThemeMode.SYSTEM) }
        )
    }
}

@Composable
private fun AboutInfo() {
    Text(
        text = "About",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    
    Text(
        text = "Shikimori App",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    
    Text(
        text = "Version 1.0.0",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun ThemeOption(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
} 