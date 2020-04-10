package com.curiousapps.mvi_architecture.ui.main.state

import com.curiousapps.mvi_architecture.model.BlogPost
import com.curiousapps.mvi_architecture.model.User

data class MainViewState(
    var blogPosts: List<BlogPost>? = null,
    var user: User? = null
) {
}