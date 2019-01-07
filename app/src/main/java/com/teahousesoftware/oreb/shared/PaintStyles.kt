package com.teahousesoftware.oreb.shared

import android.graphics.Color
import android.graphics.Paint

fun chromaticFirstNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 128, 192, 255)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun chromaticSecondNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 128, 192, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun chromaticThirdNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 192, 224, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun chromaticFourthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 255, 255, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun chromaticFifthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 255, 223, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun chromaticSixthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 255, 192, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun chromaticSeventhNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 255, 160, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun chromaticEighthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 255, 128, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun chromaticNinthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 223, 128, 160)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun chromaticTenthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 192, 128, 192)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun chromaticEleventhNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 160, 128, 223)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun chromaticTwelfthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 128, 128, 255)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun transparentEbonyFill(): Paint {
    val ebonyFill = Paint()
    ebonyFill.isAntiAlias = true
    ebonyFill.setARGB(32, 85, 93, 80)
    ebonyFill.style = Paint.Style.FILL

    return ebonyFill
}

fun ebonyFill(): Paint {
    val ebonyFill = Paint()
    ebonyFill.isAntiAlias = true
    ebonyFill.setARGB( 128, 85, 93, 80)
    ebonyFill.style = Paint.Style.FILL

    return ebonyFill
}

fun whiteFill(): Paint {
    val whiteFill = Paint()
    whiteFill.isAntiAlias = true
    whiteFill.color = Color.WHITE
    whiteFill.style = Paint.Style.FILL

    return whiteFill
}

fun greyFill(): Paint {
    val greyFill = Paint()
    greyFill.isAntiAlias = true
    greyFill.color = Color.GRAY
    greyFill.style = Paint.Style.FILL

    return greyFill
}

fun ltGreyFill(): Paint {
    val ltGreyFill = Paint()
    ltGreyFill.isAntiAlias = true
    ltGreyFill.color = Color.LTGRAY
    ltGreyFill.style = Paint.Style.FILL

    return ltGreyFill
}

fun blackFill(): Paint {
    val blackFill = Paint()
    blackFill.isAntiAlias = true
    blackFill.color = Color.BLACK
    blackFill.style = Paint.Style.FILL

    return blackFill
}

fun blackStrokeOnePixel(): Paint {
    val blackStrokeOnePixel = Paint()
    blackStrokeOnePixel.isAntiAlias = true
    blackStrokeOnePixel.strokeWidth = 1f
    blackStrokeOnePixel.color = Color.BLACK
    blackStrokeOnePixel.style = Paint.Style.STROKE

    return blackStrokeOnePixel
}

fun greyStrokeOnePixel(): Paint {
    val greyStrokeOnePixel = Paint()
    greyStrokeOnePixel.isAntiAlias = true
    greyStrokeOnePixel.strokeWidth = 1f
    greyStrokeOnePixel.color = Color.GRAY
    greyStrokeOnePixel.style = Paint.Style.STROKE

    return greyStrokeOnePixel
}

fun ltGreyStrokeOnePixel(): Paint {
    val ltGreyStrokeOnePixel = Paint()
    ltGreyStrokeOnePixel.isAntiAlias = true
    ltGreyStrokeOnePixel.strokeWidth = 1f
    ltGreyStrokeOnePixel.color = Color.LTGRAY
    ltGreyStrokeOnePixel.style = Paint.Style.STROKE

    return ltGreyStrokeOnePixel
}

fun blackStrokeTwoPixels(): Paint {
    val blackStrokeTwoPixels = Paint()
    blackStrokeTwoPixels.isAntiAlias = true
    blackStrokeTwoPixels.strokeWidth = 2f
    blackStrokeTwoPixels.color = Color.BLACK
    blackStrokeTwoPixels.style = Paint.Style.STROKE

    return blackStrokeTwoPixels
}

fun blackStrokeThreePixels(): Paint {
    val blackStrokeThreePixels = Paint()
    blackStrokeThreePixels.isAntiAlias = true
    blackStrokeThreePixels.strokeWidth = 3f
    blackStrokeThreePixels.color = Color.BLACK
    blackStrokeThreePixels.style = Paint.Style.STROKE

    return blackStrokeThreePixels
}

fun blackStrokeFourPixels(): Paint {
    val blackStrokeFourPixels = Paint()
    blackStrokeFourPixels.isAntiAlias = true
    blackStrokeFourPixels.strokeWidth = 4f
    blackStrokeFourPixels.color = Color.BLACK
    blackStrokeFourPixels.style = Paint.Style.STROKE

    return blackStrokeFourPixels
}

fun fillColorForChromaticScaleNoteNumber(noteNumber: Int): Paint {
    var fillColor = whiteFill()
    when (noteNumber) {
        1 -> fillColor = chromaticFirstNoteFillColor()
        2 -> fillColor = chromaticSecondNoteFillColor()
        3 -> fillColor = chromaticThirdNoteFillColor()
        4 -> fillColor = chromaticFourthNoteFillColor()
        5 -> fillColor = chromaticFifthNoteFillColor()
        6 -> fillColor = chromaticSixthNoteFillColor()
        7 -> fillColor = chromaticSeventhNoteFillColor()
        8 -> fillColor = chromaticEighthNoteFillColor()
        9 -> fillColor = chromaticNinthNoteFillColor()
        10 -> fillColor = chromaticTenthNoteFillColor()
        11 -> fillColor = chromaticEleventhNoteFillColor()
        12 -> fillColor = chromaticTwelfthNoteFillColor()
        else -> {
            whiteFill()
        }
    }
    return fillColor
}