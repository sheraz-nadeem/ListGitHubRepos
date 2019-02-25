package com.sheraz.listrepos.widgets

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TextViewArcaHeavy : AppCompatTextView {

    private var fontHeavy: Typeface? = null

    constructor(context: Context) : super(context) {
        fontHeavy = Typeface.createFromAsset(context.assets, "fonts/Arca-Heavy.ttf")
        setCustomFontHeavy()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        fontHeavy = Typeface.createFromAsset(context.assets, "fonts/Arca-Heavy.ttf")
        setCustomFontHeavy()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        fontHeavy = Typeface.createFromAsset(context.assets, "fonts/Arca-Heavy.ttf")
        setCustomFontHeavy()
    }

    private fun setCustomFontHeavy() {
        this.typeface = fontHeavy
    }
}
