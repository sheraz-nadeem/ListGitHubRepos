package com.sheraz.listrepos.widgets

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TextViewNormal : AppCompatTextView {

    private var fontNormal: Typeface? = null

    constructor(context: Context) : super(context) {
        fontNormal = Typeface.createFromAsset(context.assets, "fonts/Geogtq-Lg.otf")
        setCustomFont()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        fontNormal = Typeface.createFromAsset(context.assets, "fonts/Geogtq-Lg.otf")
        setCustomFont()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        fontNormal = Typeface.createFromAsset(context.assets, "fonts/Geogtq-Lg.otf")
        setCustomFont()
    }

    private fun setCustomFont() {

        val typeface = typeface
        setTypeface(fontNormal)

        if (typeface.isItalic)
            setTypeface(getTypeface(), Typeface.ITALIC)
    }


}
