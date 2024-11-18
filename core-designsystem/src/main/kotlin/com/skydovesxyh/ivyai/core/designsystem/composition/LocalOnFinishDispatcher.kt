
package com.skydovesxyh.ivyai.core.designsystem.composition

import androidx.compose.runtime.compositionLocalOf

val LocalOnFinishDispatcher = compositionLocalOf<(() -> Unit)?> { null }
