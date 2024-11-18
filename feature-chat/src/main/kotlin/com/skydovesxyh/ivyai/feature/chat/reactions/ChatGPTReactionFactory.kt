
package com.skydovesxyh.ivyai.feature.chat.reactions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.skydovesxyh.ivyai.feature.chat.R
import io.getstream.chat.android.compose.ui.util.ReactionDrawable
import io.getstream.chat.android.compose.ui.util.ReactionIcon
import io.getstream.chat.android.compose.ui.util.ReactionIconFactory

class ChatGPTReactionFactory(
  private val supportedReactions: Map<String, ReactionDrawable> = mapOf(
    "good" to ReactionDrawable(
      iconResId = R.drawable.thumbsup,
      selectedIconResId = R.drawable.thumbsup
    ),
    "love" to ReactionDrawable(
      iconResId = R.drawable.love,
      selectedIconResId = R.drawable.love
    ),
    "smile" to ReactionDrawable(
      iconResId = R.drawable.smile,
      selectedIconResId = R.drawable.smile
    ),
    "joy" to ReactionDrawable(
      iconResId = R.drawable.joy,
      selectedIconResId = R.drawable.joy
    ),
    "wink" to ReactionDrawable(
      iconResId = R.drawable.wink,
      selectedIconResId = R.drawable.wink
    )
  )
) : ReactionIconFactory {

  @Composable
  override fun createReactionIcon(type: String): ReactionIcon {
    val reactionDrawable = requireNotNull(supportedReactions[type])
    return ReactionIcon(
      painter = painterResource(reactionDrawable.iconResId),
      selectedPainter = painterResource(reactionDrawable.selectedIconResId)
    )
  }

  @Composable
  override fun createReactionIcons(): Map<String, ReactionIcon> {
    return supportedReactions.mapValues {
      createReactionIcon(it.key)
    }
  }

  override fun isReactionSupported(type: String): Boolean {
    return supportedReactions.containsKey(type)
  }
}
