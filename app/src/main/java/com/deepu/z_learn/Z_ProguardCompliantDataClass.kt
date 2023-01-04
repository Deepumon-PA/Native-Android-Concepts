package com.deepu.z_learn

import androidx.annotation.Keep

@Keep data class z_ProguardCompliantDataClass( //keep annotation will prevent this class from getting obfuscated
    var title: String
)