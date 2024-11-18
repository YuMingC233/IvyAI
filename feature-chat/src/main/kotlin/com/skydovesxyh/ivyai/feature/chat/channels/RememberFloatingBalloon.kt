
package com.skydovesxyh.ivyai.feature.chat.channels

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.rememberBalloonBuilder
import com.skydoves.balloon.compose.setBackgroundColor
import com.skydoves.balloon.compose.setTextColor
import com.skydovesxyh.ivyai.core.designsystem.theme.STREAM_PRIMARY

@Composable
internal fun rememberFloatingBalloon() = rememberBalloonBuilder {
  setWidthRatio(1.0f)
  setHeight(BalloonSizeSpec.WRAP)
  setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
  setArrowOrientation(ArrowOrientation.BOTTOM)
  setArrowPosition(0.5f)
  setArrowSize(10)
  setPadding(12)
  setMarginHorizontal(12)
  setTextSize(15f)
  setCornerRadius(8f)
  setTextColor(Color.White)
  setBackgroundColor(STREAM_PRIMARY)
  setBalloonAnimation(BalloonAnimation.ELASTIC)
}
