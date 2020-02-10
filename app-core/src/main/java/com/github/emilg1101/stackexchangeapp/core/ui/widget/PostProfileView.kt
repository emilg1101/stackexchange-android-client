package com.github.emilg1101.stackexchangeapp.core.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.github.emilg1101.stackexchangeapp.core.R
import com.github.emilg1101.stackexchangeapp.core.binding.loadProfileImage
import com.github.emilg1101.stackexchangeapp.core.extensions.format
import kotlinx.android.synthetic.main.view_post_profile.view.date
import kotlinx.android.synthetic.main.view_post_profile.view.profile_image
import kotlinx.android.synthetic.main.view_post_profile.view.profile_name
import java.util.Calendar

class PostProfileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var profileName: String? = null
        set(value) {
            field = value
            profile_name.text = value
        }

    var profileImageUrl: String? = null
        set(value) {
            field = value
            loadProfileImage(profile_image, value)
        }

    var dateFormat: String = "E d MMM yyyy"

    var postDate: Calendar? = null
        set(value) {
            field = value
            date.text = value?.format(dateFormat)
            date.visibility = if (value != null) View.VISIBLE else View.GONE
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_post_profile, this, true)

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.PostProfileView, 0, 0)

        try {
            profileName = a.getString(R.styleable.PostProfileView_profileName)
            profileImageUrl = a.getString(R.styleable.PostProfileView_profileImageUrl)
            dateFormat = a.getString(R.styleable.PostProfileView_dateFormat) ?: dateFormat
        } finally {
            a.recycle()
        }
    }
}
