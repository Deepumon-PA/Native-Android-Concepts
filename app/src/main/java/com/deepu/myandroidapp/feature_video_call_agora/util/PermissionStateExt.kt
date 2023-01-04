package com.deepu.myandroidapp.feature_video_call_agora.util

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@ExperimentalPermissionsApi
fun PermissionState.isPermissionDenied(): Boolean {
    return !shouldShowRationale && !hasPermission
    // return true if user has denied the permission multiple times else false so that we can request permission again again or show rationale
}