package com.teahousesoftware.oreb.shared

import android.graphics.Color
import android.graphics.Paint

fun firstNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 128, 192, 255)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun secondNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 128, 192, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun thirdNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 192, 224, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun fourthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 255, 255, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun fifthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 255, 223, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun sixthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 255, 192, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun seventhNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 255, 160, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun eighthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 255, 128, 128)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun ninthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 223, 128, 160)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun tenthNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 192, 128, 192)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun eleventhNoteFillColor(): Paint {
    val rootNoteFillColor = Paint()
    rootNoteFillColor.isAntiAlias = true
    rootNoteFillColor.setARGB(255, 160, 128, 223)
    rootNoteFillColor.style = Paint.Style.FILL
    return rootNoteFillColor

}

fun twelfthNoteFillColor(): Paint {
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