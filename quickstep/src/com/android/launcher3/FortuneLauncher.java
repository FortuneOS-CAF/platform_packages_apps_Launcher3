/*
 *  Copyright (C) 2024 FortuneOS
 *  SPDX-License-Identifier: Apache-2.0
 */

package com.android.launcher3;

import com.android.launcher3.uioverrides.QuickstepLauncher;
import com.android.systemui.plugins.shared.LauncherOverlayManager;

public class FortuneLauncher extends QuickstepLauncher {

    @Override
    protected LauncherOverlayManager getDefaultOverlay() {
        return new OverlayCallbackImpl(this);
    }

}
