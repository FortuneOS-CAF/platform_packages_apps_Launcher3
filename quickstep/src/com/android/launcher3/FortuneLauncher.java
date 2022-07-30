/*
 *  Copyright (C) 2024 FortuneOS
 *  SPDX-License-Identifier: Apache-2.0
 */

package com.android.launcher3;

import android.app.smartspace.SmartspaceTarget;
import android.os.Bundle;

import com.android.launcher3.FortuneLauncherModelDelegate.SmartspaceItem;

import com.android.launcher3.model.BgDataModel;
import com.android.launcher3.qsb.LauncherUnlockAnimationController;
import com.android.launcher3.uioverrides.QuickstepLauncher;
import com.android.quickstep.SystemUiProxy;
import com.android.systemui.plugins.shared.LauncherOverlayManager;

import com.google.android.systemui.smartspace.BcSmartspaceDataProvider;

import java.util.List;
import java.util.stream.Collectors;

public class FortuneLauncher extends QuickstepLauncher {

    private BcSmartspaceDataProvider mSmartspacePlugin = new BcSmartspaceDataProvider();
    private LauncherUnlockAnimationController mUnlockAnimationController =
            new LauncherUnlockAnimationController(this);

    @Override
    protected LauncherOverlayManager getDefaultOverlay() {
        return new OverlayCallbackImpl(this);
    }

    public BcSmartspaceDataProvider getSmartspacePlugin() {
        return mSmartspacePlugin;
    }

    public LauncherUnlockAnimationController getLauncherUnlockAnimationController() {
        return mUnlockAnimationController;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SystemUiProxy.INSTANCE.get(this).setLauncherUnlockAnimationController(mUnlockAnimationController);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SystemUiProxy.INSTANCE.get(this).setLauncherUnlockAnimationController(null);
    }

    @Override
    public void onOverlayVisibilityChanged(boolean visible) {
        super.onOverlayVisibilityChanged(visible);
        mUnlockAnimationController.updateSmartspaceState();
    }

    @Override
    public void onPageEndTransition() {
        super.onPageEndTransition();
        mUnlockAnimationController.updateSmartspaceState();
    }

    @Override
    public void bindExtraContainerItems(BgDataModel.FixedContainerItems container) {
        if (container.containerId == -110) {
            List<SmartspaceTarget> targets = container.items.stream()
                                                            .map(item -> ((SmartspaceItem) item).getSmartspaceTarget())
                                                            .collect(Collectors.toList());
            mSmartspacePlugin.onTargetsAvailable(targets);
        }
        super.bindExtraContainerItems(container);
    }

}
