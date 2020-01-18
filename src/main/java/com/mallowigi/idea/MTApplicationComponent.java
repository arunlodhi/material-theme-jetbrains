/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.mallowigi.idea;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.mallowigi.idea.wizard.MTWizardDialog;
import com.mallowigi.idea.wizard.MTWizardStepsProvider;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Component for Material Theme plugin initializations
 */
public final class MTApplicationComponent implements BaseComponent {

  /**
   * Initializes the MTAnalytics
   */
  private static void initAnalytics() {
    ApplicationManager.getApplication().runWriteAction(() -> MTAnalytics.getInstance().initAnalytics());
  }

  /**
   * Display wizard for new users
   */
  @SuppressWarnings("FeatureEnvy")
  private static void initWizard() {
    final boolean hasWizardBeenShown = !MTConfig.getInstance().isWizardShown();
    if (hasWizardBeenShown) {
      new MTWizardDialog(new MTWizardStepsProvider()).show();
      MTConfig.getInstance().setIsWizardShown(true);
    }
  }

  /**
   * Returns this component
   *
   * @return the MTApplicationComponent
   */
  public static MTApplicationComponent getInstance() {
    return ApplicationManager.getApplication().getComponent(MTApplicationComponent.class);
  }

  @Override
  public void initComponent() {
    // Show the wizard
    initWizard();

    // Init analytics
    initAnalytics();
  }

  /**
   * Component dispose method.
   */
  @Override
  public void disposeComponent() {
  }

  /**
   * Returns component's name.
   *
   * @return component's name
   */
  @NonNls
  @NotNull
  @Override
  public String getComponentName() {
    return "MTApplicationComponent";
  }

}
