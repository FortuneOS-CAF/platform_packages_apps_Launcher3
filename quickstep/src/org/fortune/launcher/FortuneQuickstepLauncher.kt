/*
 * Copyright (C) 2020-2024 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.fortune.launcher

import com.android.launcher3.uioverrides.QuickstepLauncher
import com.android.systemui.plugins.shared.LauncherOverlayManager

class FortuneQuickstepLauncher : QuickstepLauncher() {

    companion object {
        private const val TAG = "FortuneQuickstepLauncher"
    }

    override fun getDefaultOverlay(): LauncherOverlayManager {
        return OverlayCallbackImpl(this)
    }

}
