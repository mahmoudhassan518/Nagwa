package com.mahmoud.nagwa.main


import android.view.View
import com.kaspersky.kaspresso.screens.KScreen
import com.mahmoud.nagwa.R
import com.mahmoud.nagwa.domain.models.Movie
import com.mahmoud.nagwa.presentation.ui.main.MainActivity
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.progress.KProgressBar

import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

class MainActivityScreen : KScreen<MainActivityScreen>() {
    override val layoutId: Int = R.layout.activity_main
    override val viewClass: Class<*> = MainActivity::class.java

    var recyclerView = KRecyclerView(
        builder = { withId(R.id.recyclerView_RecyclerView) },
        itemTypeBuilder = {
            itemType(MainActivityScreen::Item)
        }
    )

    class Item(parent: Matcher<View>) : KRecyclerItem<Movie>(parent) {
        val titleTextView = KTextView(parent) { withId(R.id.title_TextView) }
        val urlTextView = KTextView(parent) { withId(R.id.url_TextView) }
        val downloadButton = KView(parent) { withId(R.id.downloadFile_Button) }
    }

    val messageTextView = KTextView { withId(R.id.message_TextView) }
    val downloadProgressProgressBar = KProgressBar { withId(R.id.downloadProgress_ProgressBar) }

}