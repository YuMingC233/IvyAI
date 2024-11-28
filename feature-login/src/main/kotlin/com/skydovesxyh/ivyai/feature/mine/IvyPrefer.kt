package com.skydovesxyh.ivyai.feature.mine

import android.Manifest
import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.skydovesxyh.ivyai.core.navigation.AppComposeNavigator


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun IvyPrefer(
  composeNavigator: AppComposeNavigator,
) {
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

  val snackbarHostState = remember { SnackbarHostState() }
  val saveState = remember { mutableStateOf(false) }

  val tagList = listOf(
    "理性", "聪明", "果断", "严苛认真", "冷漠",
    "占有欲强", "浪漫", "文艺", "霸道","专业",
    "善良", "体贴", "可爱"
  )

  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
      .background(color = Color.White),
    topBar = {
      CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer,
          titleContentColor = Color.White
        ),
        title = {
          Text(
            "偏好设置",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        },
        navigationIcon = {
          IconButton(onClick = {
            composeNavigator.navigateUp()
          }) {
            androidx.compose.material.Icon(
              imageVector = Icons.Default.ArrowBackIosNew,
              contentDescription = null,
              tint = Color.White
            )
          }
        },
        scrollBehavior = scrollBehavior,
      )
    },
  ) { innerPadding ->
    // Content
    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
      Column (modifier = Modifier.padding(10.dp).fillMaxSize()) {
        Text(
          text = "选择您的偏好：",
          modifier = Modifier.padding(bottom = 20.dp),
          fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
          fontSize = 18.sp,
          color = Color.Black
        )
        // 标签列表，使用 FlowRow 自然排布
        FlowRow {
          // 根据 tagList 动态生成
          val selectedStates = remember { mutableStateOf(tagList.map { false }.toMutableList()) }

          for ((index, tag) in tagList.withIndex()) {
            FilterChip(
              modifier = Modifier.padding(end = 6.dp),
              onClick = {
                // 创建新的列表，确保 Jetpack Compose 观察到状态的变化
                selectedStates.value = selectedStates.value.mapIndexed { i, isSelected ->
                  if (i == index) !isSelected else isSelected
                }.toMutableList()
              },
              label = {
                Text(tag)
              },
              selected = selectedStates.value[index],
              leadingIcon = if (selectedStates.value[index]) {
                {
                  Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                  )
                }
              } else null,
            )
          }
        }
        Row (
          modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
          horizontalArrangement = Arrangement.Center
        ) {
          Button(
            onClick = {
              saveState.value = !saveState.value
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF005dfc),
              contentColor = Color.White
            ),
          ) {
            Text("保存数据")
          }
        }
      }
    }
  }

  if (saveState.value) {
    LaunchedEffect(Unit) {
      snackbarHostState.showSnackbar("保存成功！")
    }
  }

  SnackbarHost(hostState = snackbarHostState)
}

