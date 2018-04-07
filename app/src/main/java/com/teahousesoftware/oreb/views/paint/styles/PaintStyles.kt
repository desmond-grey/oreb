package com.teahousesoftware.oreb.views.paint.styles

import android.graphics.Color
import android.graphics.Paint

fun rootNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 214, 84, 59)
    rootNoteFillColor.style = Paint.Style.FILL

    return rootNoteFillColor
}

fun thirdNoteFillColor(): Paint {
    val thirdNoteFillColor = Paint()
    thirdNoteFillColor.isAntiAlias = true
    thirdNoteFillColor.setARGB(255, 234, 211, 103)
    thirdNoteFillColor.style = Paint.Style.FILL

    return thirdNoteFillColor
}

fun fifthNoteFillColor(): Paint {
    val fifthNoteFillColor = Paint()
    fifthNoteFillColor.isAntiAlias = true
    fifthNoteFillColor.setARGB(255, 128, 166, 33)
    fifthNoteFillColor.style = Paint.Style.FILL

    return fifthNoteFillColor
}

fun seventhNoteFillColor(): Paint {
    val seventhNoteFillColor = Paint()
    seventhNoteFillColor.isAntiAlias = true
    seventhNoteFillColor.setARGB(255, 56, 107, 149)
    seventhNoteFillColor.style = Paint.Style.FILL

    return seventhNoteFillColor
}

fun ebonyFill(): Paint {
    val ebonyFill = Paint()
    ebonyFill.isAntiAlias = true
    ebonyFill.setARGB(128, 85, 93, 80)
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

fun blackFill(): Paint {
    val blackFill = Paint()
    blackFill.isAntiAlias = true
    blackFill.color = Color.BLACK
    blackFill.style = Paint.Style.FILL

    return blackFill
}

fun yellowFill(): Paint {
    val yellowFill = Paint()
    yellowFill.isAntiAlias = true
    yellowFill.color = Color.YELLOW
    yellowFill.style = Paint.Style.FILL

    return yellowFill
}

fun blackStrokeOnePixel(): Paint {
    val blackStrokeOnePixel = Paint()
    blackStrokeOnePixel.isAntiAlias = true
    blackStrokeOnePixel.strokeWidth = 1f
    blackStrokeOnePixel.color = Color.BLACK
    blackStrokeOnePixel.style = Paint.Style.STROKE

    return blackStrokeOnePixel
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